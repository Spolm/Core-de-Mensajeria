import { TestBed, inject } from '@angular/core/testing';

import { StatisticsServiceService } from './statistics-service.service';

describe('StatisticsServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [StatisticsServiceService]
    });
  });

  it('should be created', inject([StatisticsServiceService], (service: StatisticsServiceService) => {
    expect(service).toBeTruthy();
  }));
});
