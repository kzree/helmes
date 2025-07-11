import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

export interface Entity {
  id: string;
  createdAt: string;
  updatedAt: string;
}

export interface Sector extends Entity {
  name: string;
}

export interface NewInput {
  name: string;
  termsAccepted: boolean;
  sectors: Sector[];
}

export interface Input extends Entity, NewInput {}

@Injectable({
  providedIn: 'root',
})
export class InputService {
  private apiUrl = environment.apiUrl;
  private http = inject(HttpClient);

  getSectors() {
    return this.http.get<Sector[]>(`${this.apiUrl}/sectors`);
  }

  getInputs() {
    return this.http.get<Input[]>(`${this.apiUrl}/inputs`);
  }

  saveNewInput(input: NewInput) {
    return this.http.post<Input>(`${this.apiUrl}/inputs`, input);
  }

  updateInput(input: Input) {
    return this.http.put<Input>(`${this.apiUrl}/inputs/${input.id}`, input);
  }
}
