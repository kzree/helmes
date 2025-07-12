import { Component, inject, signal, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Input, InputService, Sector } from './input-service';
import { CommonModule } from '@angular/common';
import { ComponentsModule } from '../components/components-module';
import { TranslocoModule } from '@jsverse/transloco';

interface SectorWithLevel extends Sector {
  nestingLevel: number;
  padding: number;
}

@Component({
  selector: 'app-input-form',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ComponentsModule,
    TranslocoModule,
  ],
  templateUrl: './input-form.html',
  styleUrl: './input-form.css',
})
export class InputForm implements OnInit {
  private readonly PADDING_PER_LEVEL_IN_PX = 16;

  private inputService = inject(InputService);
  private formBuilder = inject(FormBuilder);

  sectorOptions = signal<SectorWithLevel[]>([]);
  loading = signal(true);

  inputForm: FormGroup = this.formBuilder.group({
    id: [null],
    name: ['', [Validators.required]],
    sectors: [[], [Validators.required]],
    termsAccepted: [false, [Validators.requiredTrue]],
  });

  get name() {
    return this.inputForm.get('name');
  }

  get sectors() {
    return this.inputForm.get('sectors');
  }

  get termsAccepted() {
    return this.inputForm.get('termsAccepted');
  }

  isSectorSelected(sector: SectorWithLevel): boolean {
    const selectedSectors: SectorWithLevel[] =
      this.inputForm.get('sectors')?.value || [];
    return selectedSectors.some((selected) => selected.id === sector.id);
  }

  private flattenSectors(sectors: Sector[], level = 0) {
    const flattened: SectorWithLevel[] = [];
    sectors.forEach((sector) => {
      flattened.push({
        ...sector,
        nestingLevel: level,
        padding: level * this.PADDING_PER_LEVEL_IN_PX,
      });
      if (sector.subSectors && sector.subSectors.length > 0) {
        flattened.push(...this.flattenSectors(sector.subSectors, level + 1));
      }
    });

    return flattened;
  }

  ngOnInit() {
    this.inputService.getSectors().subscribe({
      next: (data) => {
        this.sectorOptions.set(this.flattenSectors(data));
        this.loading.set(false);
      },
      error: (error) => {
        console.error('Error fetching sectors:', error);
        this.loading.set(false);
      },
    });

    this.inputService.getInputs().subscribe({
      next: (data) => {
        if (!data.length) {
          return;
        }
        this.patchFormWithData(data[0]);
      },
      error: (error) => {
        console.error('Error fetching inputs:', error);
      },
    });
  }

  private markAllFieldsAsTouched() {
    Object.keys(this.inputForm.controls).forEach((key) => {
      this.inputForm.get(key)?.markAsTouched();
    });
  }

  private patchFormWithData(data: Input) {
    const { id, name, termsAccepted, sectors } = data;
    this.inputForm.patchValue({
      id,
      name,
      termsAccepted,
      sectors,
    });
  }

  onSubmit() {
    if (!this.inputForm.valid) {
      this.markAllFieldsAsTouched();
      return;
    }

    const isExistingInput = !!this.inputForm.value.id;

    if (isExistingInput) {
      this.inputService.updateInput(this.inputForm.value).subscribe({
        next: (res) => this.patchFormWithData(res),
        error: (err) => {
          console.error('Error updating input:', err);
        },
      });
      return;
    }

    this.inputService.saveNewInput(this.inputForm.value).subscribe({
      next: (res) => this.patchFormWithData(res),
      error: (err) => {
        console.error('Error saving input:', err);
      },
    });
  }
}
