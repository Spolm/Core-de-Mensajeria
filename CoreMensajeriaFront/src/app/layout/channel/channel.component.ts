import { Component, OnInit } from '@angular/core';

import { ChannelDataService } from './channel-data.service'
import { Channel } from './channel'

@Component({
  selector: 'app-channel',
  templateUrl: './channel.component.html',
  styleUrls: ['./channel.component.scss'],
  providers: [ChannelDataService]
})
export class ChannelComponent implements OnInit {

  channels: Channel[] = [];

  constructor(
    private channelDataService: ChannelDataService,
  ) { }

  ngOnInit() {
    this.channelDataService
    .getAllChannels()
    .subscribe(
      (channels) => {
        this.channels = channels;
        console.log(channels);
      }
    )
  }

}
