import { CompanyAuthService } from './../companyauth/companyauth.service';
import { Component, OnInit } from '@angular/core';
import { SignUpInfo } from '../auth/signup-info';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InsurerPolicyService } from './../../service/insurer-policy.service';

@Component({
 selector: 'app-companyregister',
 templateUrl: './companyregister.component.html',
 styleUrls: ['./companyregister.component.css']
})
export class CompanyregisterComponent implements OnInit { // title = 'Please tell us about yourself.';

form: any = {};
signupInfo: SignUpInfo;
isSignedUp = false;
isSignUpFailed = false;
errorMessage = '';
selectedValue: string;
selectedCar: string;
firstForm: FormGroup;
secondForm: FormGroup;
private formSubmitAttempt: boolean;




constructor(private authService: CompanyAuthService, private router: Router,private formBuilder: FormBuilder,private insurerPolicyService: InsurerPolicyService) { }


ngOnInit() {
  console.log("abcd");
  this.firstForm = this.formBuilder.group({
    insurerName: ['', Validators.required],
    insurerLicense: ['', Validators.required],
    insurerEmail: ['', Validators.required],
    password: ['', [Validators.required, Validators.minLength(6)]]
  });
  
  this.secondForm = this.formBuilder.group({
    insurerAddress: ['', Validators.required],
    securityQuestion: ['', Validators.required],
    securityAnswer: ['', Validators.required]});

 }

 isFieldInvalid(field: string) {
  return (
    (!this.firstForm.get(field).valid && this.firstForm.get(field).touched) ||
    (this.firstForm.get(field).untouched && this.formSubmitAttempt) 
  );
}


 submitForm() {
  console.log("hi");

  const signUpForm = Object.assign(this.firstForm.value, this.secondForm.value);
    console.log('registerForm.value : ', signUpForm);

  this.authService.signUp(signUpForm).subscribe(
    data => {
      console.log(data);
    },
    error => {
      console.log(error);
    }
  );
  this.router.navigate(['/login']);
}
}