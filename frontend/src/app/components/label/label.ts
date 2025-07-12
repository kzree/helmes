import { NgClass } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-label',
  imports: [NgClass],
  template: `
    <label [ngClass]="{ 'font-semibold': true, required }" [for]="htmlFor">
      <ng-content></ng-content>
    </label>
  `,
  styles: ``,
})
export class Label {
  @Input() htmlFor = '';
  @Input() required = false;
}
