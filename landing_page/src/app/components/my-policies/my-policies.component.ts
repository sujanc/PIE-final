import { UserDashboardService } from './../../service/user-dashboard.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { TokenStorageService } from './../companyauth/token-storage.service';

@Component({
  selector: 'app-my-policies',
  templateUrl: './my-policies.component.html',
  styleUrls: ['./my-policies.component.css']
})
export class MyPoliciesComponent implements OnInit {

public policies;
username: any;
firstForm: FormGroup;
info:any;
constructor(public _route: ActivatedRoute, private router: Router, public userService: UserDashboardService, private formBuilder: FormBuilder,private token: TokenStorageService) { }
ngOnInit() {
  this.info = {
    token: this.token.getToken(),
    username: this.token.getUsername(),
    authorities: this.token.getAuthorities()
  };
  this.firstForm = this.formBuilder.group({
    username: ['', Validators.required],
  });
this.username = this._route.snapshot.paramMap.get('username');
console.log(this.username);
this.policies = this.userService.getPolicies(this.username).subscribe(
  data => {

    this.policies = data;
    console.log(this.policies);
},
  error => {
    console.log('some error occured');
    console.log(error.errorMessage);
  }
);
}
next(insurerName,policyName,newInsurer){
  console.log(newInsurer);
  
  let as = window.localStorage.setItem("oldInsurer",insurerName);
  let policyname = window.localStorage.setItem("oldPolicy",policyName);
  this.router.navigate(['/companyPolicy',newInsurer]);
}

}
