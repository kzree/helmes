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

    console.log('Form submitted:', this.inputForm.value);
  }
}
