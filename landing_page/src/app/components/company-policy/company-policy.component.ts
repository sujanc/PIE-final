import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { InsurerPolicyService } from './../../service/insurer-policy.service'
import { RequestService } from './../../service/request.service';

@Component({
  selector: 'app-company-policy',
  templateUrl: './company-policy.component.html',
  styleUrls: ['./company-policy.component.css']
})
export class CompanyPolicyComponent implements OnInit {
  public requests;
  public newInsurer;
  public newPolicyName;
  public exclusionperiod;
  public insuredname;
  public oldinsurername;
  public oldpolicyname;
 insurerLicense;
 public portingRequest = {
    newInsurerName: this.newInsurer,
    newInsurerProduct: this.newPolicyName,
    exclusionPeriod: this.exclusionperiod,
    insuredName: this.insuredname,
    insurerName: this.oldinsurername,
    insurerProduct: this.oldpolicyname
 }
 
 constructor(public _route: ActivatedRoute, private router: Router, public insurerPolicyService: InsurerPolicyService, private requestService: RequestService) { }
 ngOnInit() {
 this.insurerLicense = this._route.snapshot.paramMap.get('insurerLicense');
   console.log('In delete component : ' + this.insurerLicense);
 this.requests = this.insurerPolicyService.getPolicies(this.insurerLicense).subscribe(
   data => {
     this.requests = data;
 },
   error => {
     console.log('some error occured');
     console.log(error.errorMessage);
   // tslint:disable-next-line:semicolon
   }
 );
 }
 port(insurername,policyname,exclusionperiod){
   this.portingRequest.insurerName = window.localStorage.getItem("oldInsurer");
   this.portingRequest.insurerProduct = window.localStorage.getItem("oldPolicy");
   this.portingRequest.insuredName = window.localStorage.getItem("insuredname");
   
   this.portingRequest.newInsurerName = insurername;
   this.portingRequest.newInsurerProduct = policyname;
   this.portingRequest.exclusionPeriod = exclusionperiod;
  
   console.log("In company policy"+this.portingRequest);
   this.requestService.postRequest(this.portingRequest).subscribe(
     data => {
       console.log('hel');
       console.log(data);
     },
     error => {
       console.log(error);
     }
   );
   console.log(this.insuredname);
   this.router.navigate(['/dashboard',window.localStorage.getItem('insuredname')]);
 }
}
