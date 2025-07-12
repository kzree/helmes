import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErrorText } from './error-text/error-text';
import { Label } from './label/label';
import { Loader } from './loader/loader';

@NgModule({
  declarations: [],
  imports: [CommonModule, ErrorText, Label, Loader],
  exports: [ErrorText, Label, Loader],
})
export class ComponentsModule { }
