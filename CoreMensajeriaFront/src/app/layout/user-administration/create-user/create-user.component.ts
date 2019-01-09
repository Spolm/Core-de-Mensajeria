import { Component, OnInit } from '@angular/core';
import { UserAdministrationService } from '../user-administration.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent {

  stateJson: any = []
  townJson: any = []

  constructor(userAdministrationService: UserAdministrationService) {
    userAdministrationService.getStates().subscribe(data => {
      this.stateJson = data;
    });
    console.log(this.stateJson);
  }

}
