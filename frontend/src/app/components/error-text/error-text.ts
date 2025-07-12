import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { AbstractControl } from '@angular/forms';
import { TranslocoModule } from '@jsverse/transloco';

@Component({
  selector: 'app-error-text',
  imports: [CommonModule, TranslocoModule],
  template: `
    <ng-container *transloco="let t">
      <span class="text-sm text-red-400" *ngIf="isErrorShown">{{
        t(errorMessage)
      }}</span>
    </ng-container>
  `,
  styles: ``,
})
export class ErrorText {
  @Input() control: AbstractControl | null = null;
  @Input() fieldName = '';

  get isErrorShown(): boolean {
    return !!(this.control && this.control.invalid && this.control.touched);
  }

  get errorMessage(): string {
    if (!this.control || !this.control.errors) return '';

    const errorKey = Object.keys(this.control.errors)[0];
    console.log(`form.error.${errorKey}`);
    return `form.error.${errorKey}`;
  }
}
