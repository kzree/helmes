import { CommonModule } from '@angular/common';
import { Component, DestroyRef, inject, OnInit, signal } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { TranslocoModule } from '@jsverse/transloco';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ComponentsModule } from '../components/components-module';
import { InputService } from './services/input-service';
import { Input, SectorWithLevel } from '../../types/api';
import { forkJoin } from 'rxjs';

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
  private destroyRef = inject(DestroyRef);

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
    const sectors$ = this.inputService.getSectorOptions();
    const inputs$ = this.inputService.getInputs();

    forkJoin({ sectors: sectors$, inputs: inputs$ })
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: ({ sectors, inputs }) => {
          this.sectorOptions.set(sectors);
          if (inputs.length > 0) {
            this.patchFormWithData(inputs[0]);
          }
          this.loading.set(false);
        },
        error: (error) => this.handleError(error),
      });
  }

  private handleError(error: unknown) {
    console.log('Error fetching sectors or inputs:', error);
    this.loading.set(false);
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

    this.loading.set(true);

    const formValue = this.inputForm.value;
    const operation$ = formValue.id
      ? this.inputService.updateInput(formValue)
      : this.inputService.saveNewInput(formValue);

    operation$.pipe(takeUntilDestroyed(this.destroyRef)).subscribe({
      next: (res) => {
        this.loading.set(false);
        this.patchFormWithData(res);
      },
      error: (error) => this.handleError(error),
    });
  }
}
