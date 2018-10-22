import { RouterModule } from '@angular/router'; // we also need angular router for Nebular to function properly
import { NbSidebarModule, NbLayoutModule, NbSidebarService } from '@nebular/theme'
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NbThemeModule } from '@nebular/theme';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    NbThemeModule.forRoot({ name: 'corporate' }), // this will enable the default theme, you can change this to `cosmic` to enable the dark theme
    RouterModule.forRoot(
      [
        { path: "", component: AppComponent}
      ]
    ),
    NbLayoutModule,
    NbSidebarModule,
    BrowserModule
  ],
  providers: [NbSidebarService],
  bootstrap: [AppComponent]
})
export class AppModule { 

}
