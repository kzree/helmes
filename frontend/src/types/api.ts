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
