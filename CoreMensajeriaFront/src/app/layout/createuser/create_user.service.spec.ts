import { TestBed, inject } from '@angular/core/testing';

import { CreateUser } from './create_user.service';

describe('CreateUser', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CreateUser]
    });
  });

  it('should be created', inject([CreateUser], (service: CreateUser) => {
    expect(service).toBeTruthy();
  }));
});
