# SB Admin rewritten in Angular6 and Bootstrap 4

Simple Dashboard Admin App built using Angular 6 and Bootstrap 4

This project is a port of the famous Free Admin Bootstrap Theme [SB Admin v6.0](http://startbootstrap.com/template-overviews/sb-admin-2/) to Angular5 Theme.

Powered by [StartAngular](http://startangular.com/) & [StrapUI](http://strapui.com/)

## [Demo](http://rawgit.com/start-angular/SB-Admin-BS4-Angular-6/master/dist/)

## [SB Admin Material version](https://github.com/start-javascript/sb-admin-material)

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 6.0.0.

### Introduction

Provides fast, reliable and extensible starter for the development of Angular projects.

`sb-admin-bs4-angular5` provides the following features:

*   Developed using boostrap-v4.0.0
*   angular-v6.0.0
*   angular/cli-v6.0.0
*   [ng-bootstrap-v2.0.0](https://github.com/ng-bootstrap/)
*   [ngx-translate-v10.0.0](https://github.com/ngx-translate)
*   Following the best practices.
*   Ahead-of-Time compilation support.
*   Official Angular i18n support.
*   Production and development builds.
*   Tree-Shaking production builds.

### How to start

**Note** that this seed project requires **node >=v6.9.0 and npm >=3**.

In order to start the project use:

```bash
$ git clone https://github.com/start-angular/SB-Admin-BS4-Angular-6
$ cd SB-Admin-BS4-Angular-6
# install the project's dependencies
$ npm install
# watches your files and uses livereload by default run `npm start` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.
$ npm start
# prod build, will output the production application in `dist`
# the produced code can be deployed (rsynced) to a remote server
$ npm run build
```

### Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive/pipe/service/class/module`.

### Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

### Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).
Before running the tests make sure you are serving the app via `ng serve`.

### Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

### Instrucciones para crear una nuevo modulo en Angular

  Pasos para activar una nueva vista:

  1. ng g module layout/profile (crea un modulo).

  2. ng g component layout/profile -m=layout/profile/profile.module.ts (crea componente y agrega el enlace al módulo).

  3. Luego creas profile-routing.module.ts y copias de otro ya creado y modificas todo lo necesario.
	  	import { NgModule } from '@angular/core';
		import { Routes, RouterModule } from '@angular/router';
		import { ProfileComponent } from './profile.component';

		const routes: Routes = [
		    {
		        path: '',
		        component: ProfileComponent
		    }
		];

		@NgModule({
		    imports: [RouterModule.forChild(routes)],
		    exports: [RouterModule]
		})
		export class ProfileRoutingModule {}

  4. En su archivo layout-routing.module.ts agregue la carga secundaria
  	 { path: 'profile', loadChildren: './profile/profile.module#ProfileModule' },

  5. Recuerda haces la importación del routing.modulo ¡Muy importante!
  		import { NgModule } from '@angular/core';
		import { CommonModule } from '@angular/common';

		import { ProfileRoutingModule } from './profile-routing.module';
		import { ProfileComponent } from './profile.component';

		@NgModule({
		  imports: [CommonModule,ProfileRoutingModule],
		  declarations: [ProfileComponent]
		})
		export class ProfileModule { }
