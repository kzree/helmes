import { TestBed } from '@angular/core/testing';
import { InputService } from './input-service';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { Sector } from '../../../types/api';

describe('FooService', () => {
  let service: InputService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(),
        InputService,
      ],
    });
    service = TestBed.inject(InputService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should flatten sectors correctly', () => {
    const date = new Date().toISOString();
    const mockSectors: Sector[] = [
      {
        id: 'a',
        name: 'Technology',
        subSectors: [
          {
            id: 'b',
            name: 'Software',
            subSectors: [
              {
                id: 'c',
                name: 'Web Development',
                subSectors: [],
                createdAt: date,
                updatedAt: date,
              },
            ],
            createdAt: date,
            updatedAt: date,
          },
        ],
        createdAt: date,
        updatedAt: date,
      },
    ];

    const result = service['flattenSectors'](mockSectors);

    expect(result.length).toBe(3);
    expect(result[0]).toEqual({
      id: 'a',
      name: 'Technology',
      subSectors: jasmine.any(Array),
      nestingLevel: 0,
      padding: 0,
      createdAt: date,
      updatedAt: date,
    });
    expect(result[1]).toEqual({
      id: 'b',
      name: 'Software',
      subSectors: jasmine.any(Array),
      nestingLevel: 1,
      padding: 16,
      createdAt: date,
      updatedAt: date,
    });
    expect(result[2]).toEqual({
      id: 'c',
      name: 'Web Development',
      subSectors: [],
      nestingLevel: 2,
      padding: 32,
      createdAt: date,
      updatedAt: date,
    });
  });

  it('should handle empty sectors array', () => {
    const result = service['flattenSectors']([]);
    expect(result).toEqual([]);
  });

  it('should handle sectors without subSectors', () => {
    const date = new Date().toISOString();
    const mockSectors: Sector[] = [
      {
        id: 'a',
        name: 'Simple Sector',
        subSectors: [],
        createdAt: date,
        updatedAt: date,
      },
    ];

    const result = service['flattenSectors'](mockSectors);

    expect(result.length).toBe(1);
    expect(result[0]).toEqual({
      id: 'a',
      name: 'Simple Sector',
      subSectors: [],
      nestingLevel: 0,
      padding: 0,
      createdAt: date,
      updatedAt: date,
    });
  });
});
