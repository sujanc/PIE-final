import { DeletepopupComponent } from './../deletepopup/deletepopup.component';
import { InsurerPolicyService } from './../../service/insurer-policy.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit, SimpleChanges } from '@angular/core';
import { RequestService } from './../../service/request.service';
import { MatDialog } from '@angular/material';
import { TokenStorageService } from './../companyauth/token-storage.service';



@Component({
 selector: 'app-get-company-policy',
 templateUrl: './get-company-policy.component.html',
 styleUrls: ['./get-company-policy.component.css']
})
export class GetCompanyPolicyComponent implements OnInit {
info:any;
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

constructor(public _route: ActivatedRoute, private router: Router, public insurerPolicyService: InsurerPolicyService, private requestService: RequestService,public dialog: MatDialog,private token: TokenStorageService) { }
ngOnInit() {
  this.info = {
    token: this.token.getToken(),
    username: this.token.getUsername(),
    authorities: this.token.getAuthorities()
  };
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


Delete(insurerName,policyName): void {
  window.localStorage.setItem("insurername", insurerName);
  window.localStorage.setItem("policyname", policyName);
  console.log("insurername"+ insurerName);
  console.log("policyname"+ policyName);


  const dialogRef = this.dialog.open(DeletepopupComponent,{});
  dialogRef.componentInstance.insurerName =  insurerName;
  dialogRef.componentInstance.policyName =  policyName;

  dialogRef.afterClosed().subscribe(result =>{
    console.log('The dialog was closed');
  });
}
}