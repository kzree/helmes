import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

export interface Sector {
  id: string;
  name: string;
  createdAt: string;
  updatedAt: string;
}

@Injectable({
  providedIn: 'root',
})
export class InputService {
  private apiUrl = 'http://localhost:4000/api'; // TODO: get this value from env variables
  private http = inject(HttpClient);

  getSectors() {
    return this.http.get<Sector[]>(`${this.apiUrl}/sectors`);
  }
}
