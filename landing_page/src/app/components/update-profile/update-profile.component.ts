import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { UserDashboardService } from './../../service/user-dashboard.service';
import { TokenStorageService } from './../companyauth/token-storage.service';


@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {
  firstForm: FormGroup;
  secondForm: FormGroup;
  thirdForm: FormGroup;
  username;
  info:any;
  constructor(private _route: ActivatedRoute, private userService: UserDashboardService, private router: Router,private formBuilder: FormBuilder,private token: TokenStorageService) { }
 
 
  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    console.log("abcd");
    this.username = this._route.snapshot.paramMap.get('username');
    this.firstForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
 
    });
    this.secondForm = this.formBuilder.group({
      gender: ['', Validators.required],
      age: ['', Validators.required],
      existingDisease: ['',Validators.required]
      });
      
   }
 
   submitForm() {
    console.log("hi");
 
     const signUpForm = Object.assign(this.firstForm.value, this.secondForm.value);
     console.log("his");

     console.log('registerForm.value : ', signUpForm);
 
    this.userService.updateProfile(this.username, signUpForm).subscribe(
      data => {
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }
}
