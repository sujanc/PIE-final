import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DisplayAllPortingRequestsComponent } from './display-all-porting-requests/display-all-porting-requests.component';

const routes: Routes = [{path: 'display-all-porting-requests', component: DisplayAllPortingRequestsComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
