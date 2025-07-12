import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorText } from './error-text/error-text';
import { Label } from './label/label';

@NgModule({
  declarations: [],
  imports: [CommonModule, ErrorText, Label],
  exports: [ErrorText, Label],
})
export class ComponentsModule { }
