import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { TranslocoModule } from '@jsverse/transloco';
import { ComponentsModule } from '../components/components-module';
import { Input, InputService, SectorWithLevel } from './input-service';

@Component({
  selector: 'app-input-form',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ComponentsModule,
    TranslocoModule,
  ],
  templateUrl: './input-form.html',
  styles: ``,
})
export class InputForm implements OnInit {
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

  ngOnInit() {
    this.inputService.getSectorOptions().subscribe({
      next: (data) => {
        this.sectorOptions.set(data);
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
