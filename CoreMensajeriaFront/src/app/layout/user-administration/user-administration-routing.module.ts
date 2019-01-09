import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAdministrationComponent } from './user-administration.component';
import { CreateUserComponent } from './create-user/create-user.component';

const routes: Routes = [
    { path: '', component: UserAdministrationComponent },
    { path: 'create', component: CreateUserComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class UserAdministrationRoutingModule {}