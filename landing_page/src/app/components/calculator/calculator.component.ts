import { InsurerPolicyService } from './../../service/insurer-policy.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { analyzeAndValidateNgModules } from '@angular/compiler';


@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css']
})
export class CalculatorComponent implements OnInit {
  premium : any;
flag: boolean = false;
  firstForm: FormGroup;
  private formSubmitAttempt: boolean;
  constructor(private router: Router,private formBuilder: FormBuilder,private insurerPolicyService: InsurerPolicyService) { }

  ngOnInit() {

    this.firstForm = this.formBuilder.group({
      userName: ['', Validators.required],
      city: ['', Validators.required],
      policyName: ['', Validators.required],
      sumInsured: ['', Validators.required],
      noOfAdults: ['', Validators.required],
      ageOfEldest: ['', Validators.required],
      noOfChildren: ['', Validators.required],
      noOfYears: ['', Validators.required]
      
    });
  }
  isFieldInvalid(field: string) {
    return (
      (!this.firstForm.get(field).valid && this.firstForm.get(field).touched) ||
      (this.firstForm.get(field).untouched && this.formSubmitAttempt) 
    );
  }

  submitForm() {
    console.log("hi");

   
        this.flag = true;
    // console.log('firstForm.value : ',this.firstForm.value);

      const signUpForm = Object.assign(this.firstForm.value);
      console.log('registerForm.value : ', signUpForm);
  
      this.insurerPolicyService.getPremium(signUpForm).subscribe(

      data => {
        
        this.premium = data;
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );


  }





}
