import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Channel } from './channel';
import { ApiService } from './api.service';


@Injectable({
  providedIn: 'root'
})
export class ChannelDataService {

  channels: Channel[] = [];

  constructor(
    private api: ApiService
  ) { }

  public getAllChannels(): Observable<Channel[]>{
    return this.api.getAllChannels();
  }
}
