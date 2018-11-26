import { TestBed, inject } from '@angular/core/testing';

import { ChannelDataService } from './channel-data.service';

describe('ChannelDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ChannelDataService]
    });
  });

  it('should be created', inject([ChannelDataService], (service: ChannelDataService) => {
    expect(service).toBeTruthy();
  }));
});
