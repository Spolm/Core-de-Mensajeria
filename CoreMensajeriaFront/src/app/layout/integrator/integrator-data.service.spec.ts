import { TestBed, inject } from '@angular/core/testing';

import { IntegratorDataService } from './integrator-data.service';

describe('IntegratorDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [IntegratorDataService]
    });
  });

  it('should be created', inject([IntegratorDataService], (service: IntegratorDataService) => {
    expect(service).toBeTruthy();
  }));
});
