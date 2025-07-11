import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { InputForm } from './input-form/input-form';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, InputForm],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('frontend');
}
