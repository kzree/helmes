import { Component, inject, signal, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { InputService, Sector } from './input-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-input-form',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './input-form.html',
  styleUrl: './input-form.css',
})
export class InputForm implements OnInit {
  private inputService = inject(InputService);
  private fb = inject(FormBuilder);

  sectorOptions = signal<Sector[]>([]);
  loading = signal(true);

  inputForm: FormGroup = this.fb.group({
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

  ngOnInit() {
    this.inputService.getSectors().subscribe({
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
        const { id, name, termsAccepted, sectors } = data[0];
        this.inputForm.patchValue({
          id,
          name,
          termsAccepted,
          sectors,
        });
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

  onSubmit() {
    this.markAllFieldsAsTouched();
    if (!this.inputForm.valid) {
      console.error('Form is invalid:', this.inputForm.errors);
      return;
    }

    const isExistingInput = !!this.inputForm.value.id;

    if (isExistingInput) {
      this.inputService.updateInput(this.inputForm.value).subscribe({
        next: (res) => {
          console.log('Input updated successfully:', res);
          const { id, name, termsAccepted, sectors } = res;
          this.inputForm.patchValue({
            id,
            name,
            termsAccepted,
            sectors,
          });
        },
        error: (err) => {
          console.error('Error updating input:', err);
        },
      });
      return;
    }
    this.inputService.saveNewInput(this.inputForm.value).subscribe({
      next: (res) => {
        console.log('Input saved successfully:', res);
        const { id, name, termsAccepted, sectors } = res;
        this.inputForm.patchValue({
          id,
          name,
          termsAccepted,
          sectors,
        });
      },
      error: (err) => {
        console.error('Error saving input:', err);
      },
    });
  }
}
