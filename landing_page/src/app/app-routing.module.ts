import { PortingHistoryComponent } from './components/porting-history/porting-history.component';
import { PolicybuymailComponent } from './components/policybuymail/policybuymail.component';
import { FamilyMembersComponent } from './components/family-members/family-members.component';
import { BuyPolicyComponent } from './components/buy-policy/buy-policy.component';
import { MyPoliciesComponent } from './components/my-policies/my-policies.component';
import { ContactComponent } from './components/contact/contact.component';
import { AboutComponent } from './components/about/about.component';
import { InsurerDashboardComponent } from './components/insurer-dashboard/insurer-dashboard.component';
import { DisplayRequestsComponent } from './components/display-requests/display-requests.component';
import { CompanyPolicyDeleteComponent } from './components/company-policy-delete/company-policy-delete.component';
import { CompanyregisterComponent } from './components/companyregister/companyregister.component';
import { GetCompanyPolicyComponent } from './components/get-company-policy/get-company-policy.component';
import { UpdateProfileComponent } from './components/update-profile/update-profile.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { UpdateRequestComponent } from './components/update-request/update-request.component';
import { DeleteRequestComponent } from './components/delete-request/delete-request.component';
import { PortingRequestComponent } from './components/porting-request/porting-request.component';
// import { FormDashboardComponent } from './components/form-dashboard/form-dashboard.component';
import { OutgoingPortingRequestsComponent } from './components/outgoing-porting-requests/outgoing-porting-requests.component';
import { IncomingPortingRequestsComponent } from './components/incoming-porting-requests/incoming-porting-requests.component';
import { AddNewPolicyComponent } from './components/add-new-policy/add-new-policy.component';
// import { LoginInsurerComponent } from './components/login-insurer/login-insurer.component';
import { RecommendationsComponent } from './components/recommendations/recommendations.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SearchComponent } from './components/search/search.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormsDashboardComponent } from './components/forms-dashboard/forms-dashboard.component';
import {DisplayAllPortingRequestsComponent} from './components/display-all-porting-requests/display-all-porting-requests.component';
import {AllPortingRequestsComponent} from './components/all-porting-requests/all-porting-requests.component';
import { ShowChatResultsComponent } from './components/show-chat-results/show-chat-results.component';
import { CompanyPolicyComponent } from './components/company-policy/company-policy.component';
import { PolicyDetailsComponent } from './components/policy-details/policy-details.component';
import { GuestUserComponent } from './components/guest-user/guest-user.component';

const routes: Routes = [
  {path: '',
    redirectTo: 'home',
    pathMatch: 'full'},
  {path: 'search/:value', component: SearchComponent},
  {path: 'login', component: LoginComponent },
  {path: '', redirectTo: 'home',  pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'guestUser', component: GuestUserComponent},
  // {path: 'guestUser/:insurername/:policyname', component: GuestUserComponent},
  {path:'register', component:RegisterComponent},
  { path: 'dashboard/:username', component: DashboardComponent},
  {path: 'policyBuyMail', component: PolicybuymailComponent},
  {path: 'recommendations/:username', component: RecommendationsComponent},
  {path: 'mypolicies/:username', component: MyPoliciesComponent},
  // {path:'companylogin', component: LoginInsurerComponent},
  {path: 'newPolicyForm', component: AddNewPolicyComponent},
  {path: 'incomingPortingRequest/:insurerLicense', component: IncomingPortingRequestsComponent},
 {path:'outgoingPortingRequest/:insurerLicense', component: OutgoingPortingRequestsComponent},
  {path: 'admin', component:FormsDashboardComponent},
    {path: 'porting/:username', component:PortingRequestComponent},
   {path: 'deleteRequest/:requestId', component:DeleteRequestComponent},
   {path: 'updateRequest/:requestId', component:UpdateRequestComponent},
   {path: 'myprofile/:username', component:UserProfileComponent},
   {path: 'updateProfile/:username', component:UpdateProfileComponent},
   {path:'newPolicyForm', component:AddNewPolicyComponent},
   {path:'myCompanyPolicy/:insurerLicense',component:GetCompanyPolicyComponent},
   {path:'deletePolicy/:insurerName/:policyName',component:CompanyPolicyDeleteComponent},
   {path: 'companyregister', component: CompanyregisterComponent},
   {path: 'myrequests/:username',component: DisplayRequestsComponent},
   {path: 'insurerDashboard/:insurerLicense',component:InsurerDashboardComponent},
   {path: 'about',component:AboutComponent},
   {path: 'contact',component:ContactComponent},
   {path: 'companyPolicy/:insurerLicense', component:CompanyPolicyComponent},
   {path: 'all-porting-requests', component:AllPortingRequestsComponent},
   {path: 'display-all-porting-requests', component:DisplayAllPortingRequestsComponent},
   {path: 'buyPolicy',component: BuyPolicyComponent},
   {path: 'familyMembers/:username',component: FamilyMembersComponent},
   {path: 'showChatResults',component:ShowChatResultsComponent},
   {path: 'policyDetails/:insurerName/:policyName',component: PolicyDetailsComponent},
   {path: 'requests/:insurerName', component: PortingHistoryComponent},
   {path: '**', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
