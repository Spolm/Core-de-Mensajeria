import { TestBed, inject } from '@angular/core/testing';

import { UserAdministrationService } from './user-administration.service';

describe('TemplateService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserAdministrationService]
    });
  });

  it('should be created', inject([UserAdministrationService], (service: UserAdministrationService) => {
    expect(service).toBeTruthy();
  }));
});