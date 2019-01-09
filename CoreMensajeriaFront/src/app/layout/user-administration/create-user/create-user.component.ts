import { Component, OnInit } from '@angular/core';
import { UserAdministrationService } from '../user-administration.service';
import { User } from '../models/user';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent {

  stateJson: any = []
  townJson: any = []
  rolesJson: any = []
  name: string;
  lastname: string;
  username: string;
  identification: string;
  birth: string;
  phone: string;
  email: string;
  town: string;
  role: string;
  user: User;

  constructor(private userAdministrationService: UserAdministrationService) {
    this.getStates();
    this.getRoles();
  }

  getStates() {
    this.userAdministrationService.getStates().subscribe(data => {
      this.stateJson = data;
    });
  }

  changeState(state: string) {
    console.log(state);
    this.getTown(state);
  }

  getRoles() {
    this.userAdministrationService.getRoles().subscribe(data => {
      this.rolesJson = data;
      console.log(this.rolesJson)
    });
  }

  changeRole(role: string) {
    console.log(role);
    this.role = role;
  }

  getTown(state: string) {
    this.userAdministrationService.getTown(state).subscribe(data => {
      this.townJson = data;
    });
  }

  changeTown(town: string) {
    this.town = town;
  }

  postUser() {
    this.user = new User;    
    this.user._nameUser = this.name;
    this.user._lastnameUser = this.lastname;
    this.user._usernameUser = this.username;
    this.user._identificationNumberUser = Number(this.identification);
    this.user._dateOfBirthUser = this.birth;
    this.user._phoneUser = this.phone;
    this.user._emailUser = this.email;
    this.user._roleUser = Number(this.role);
    this.user._rgUser = Number(this.town);
    this.userAdministrationService.postUser(this.user).subscribe((result) => {
      if(result){console.log("peroleo")}
      else{console.log("no peroleo")}
    })
  }
}
