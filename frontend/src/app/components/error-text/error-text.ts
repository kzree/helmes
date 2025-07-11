import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-error-text',
  imports: [CommonModule],
  templateUrl: './error-text.html',
  styleUrl: './error-text.css',
})
export class ErrorText {
  @Input() control: AbstractControl | null = null;
  @Input() fieldName = '';

  get isErrorShown(): boolean {
    return !!(this.control && this.control.invalid && this.control.touched);
  }

  get errorMessage(): string | null {
    if (!this.control || !this.control.errors) return null;

    if (this.control.errors['required']) {
      return `Field is required`;
    }

    return null;
  }
}
