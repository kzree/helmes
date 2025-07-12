import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { map } from 'rxjs';

export interface SectorWithLevel extends Sector {
  nestingLevel: number;
  padding: number;
}

export interface Entity {
  id: string;
  createdAt: string;
  updatedAt: string;
}

export interface Sector extends Entity {
  name: string;
  subSectors: Sector[];
}

export interface NewInput {
  name: string;
  termsAccepted: boolean;
  sectors: Sector[];
}

export interface Input extends Entity, NewInput { }

@Injectable({
  providedIn: 'root',
})
export class InputService {
  private readonly PADDING_PER_LEVEL_IN_PX = 16;

  private apiUrl = environment.apiUrl;
  private http = inject(HttpClient);

  private flattenSectors(sectors: Sector[], level = 0) {
    const flattened: SectorWithLevel[] = [];
    sectors.forEach((sector) => {
      flattened.push({
        ...sector,
        nestingLevel: level,
        padding: level * this.PADDING_PER_LEVEL_IN_PX,
      });
      if (sector.subSectors && sector.subSectors.length > 0) {
        flattened.push(...this.flattenSectors(sector.subSectors, level + 1));
      }
    });

    return flattened;
  }

  getSectorOptions() {
    return this.http
      .get<Sector[]>(`${this.apiUrl}/sectors`)
      .pipe(map((sectors) => this.flattenSectors(sectors)));
  }

  getInputs() {
    return this.http.get<Input[]>(`${this.apiUrl}/inputs`, {
      withCredentials: true,
    });
  }

  saveNewInput(input: NewInput) {
    return this.http.post<Input>(`${this.apiUrl}/inputs`, input, {
      withCredentials: true,
    });
  }

  updateInput(input: Input) {
    return this.http.put<Input>(`${this.apiUrl}/inputs/${input.id}`, input, {
      withCredentials: true,
    });
  }
}
