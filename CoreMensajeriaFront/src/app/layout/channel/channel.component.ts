import { Component, OnInit } from '@angular/core';

import { ChannelDataService } from './channel-data.service';
import { ToastrService } from 'ngx-toastr';
import { Channel } from './channel';

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
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    
    this.channelDataService
    .getAllChannels()
    .subscribe(
      ( channels ) => {
        this.channels = channels;
      },( _ => {
        this.toastr.error( "Error en la Conexión" );
      })
    )
    

  }

}
