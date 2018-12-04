import { NgModule } from '@angular/core';
	import { Routes, RouterModule } from '@angular/router';
    import { CreateuserComponent } from './createuser.component';
    
	const routes: Routes = [
	    {
	        path: '',
	        component: CreateuserComponent
	    }
	];

	@NgModule({
	    imports: [RouterModule.forChild(routes)],
	    exports: [RouterModule]
	})
	export class CreateuserRoutingModule {}