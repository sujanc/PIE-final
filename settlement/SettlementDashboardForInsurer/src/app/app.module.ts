import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AllPortingRequestsComponent } from './all-porting-requests/all-porting-requests.component';
import {HttpClientModule} from '@angular/common/http';
import { DisplayAllPortingRequestsComponent } from './display-all-porting-requests/display-all-porting-requests.component';

@NgModule({
  declarations: [
    AppComponent,
    AllPortingRequestsComponent,
    DisplayAllPortingRequestsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
