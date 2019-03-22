import { Router } from '@angular/router';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { SignUpInfo } from '../auth/signup-info';
@Component({
 selector: 'app-register',
 templateUrl: './register.component.html',
 styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
 // title = 'Please tell us about yourself.';
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

constructor(private authService: AuthService, private router: Router,private formBuilder: FormBuilder) { }
ngOnInit() {
  console.log("abcd");
  this.firstForm = this.formBuilder.group({
    username: ['', Validators.required],
    email: ['', Validators.required],
    password: ['', [Validators.required, Validators.minLength(6)]],
  });
  this.secondForm = this.formBuilder.group({
    gender: ['', Validators.required],
    fullName: ['', Validators.required],
    age: ['',Validators.required],
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
      this.errorMessage = error.error.message;
      this.isSignUpFailed = true;
    }
  );
  this.router.navigate(['/login']);
}
}
