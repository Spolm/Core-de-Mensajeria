import { TestBed, inject } from '@angular/core/testing';

import { EditProfile } from './edit_profile.service';

describe('EditProfile', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EditProfile]
    });
  });

  it('should be created', inject([EditProfile], (service: EditProfile) => {
    expect(service).toBeTruthy();
  }));
});
