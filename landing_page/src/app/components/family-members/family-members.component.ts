import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { UserDashboardService } from './../../service/user-dashboard.service';

@Component({
  selector: 'app-family-members',
  templateUrl: './family-members.component.html',
  styleUrls: ['./family-members.component.css']
})
export class FamilyMembersComponent implements OnInit {
  firstForm: FormGroup;
  username:any;
  constructor(private route: ActivatedRoute, private userService: UserDashboardService, private router: Router,private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.username = this.route.snapshot.paramMap.get('username');
    this.firstForm = this.formBuilder.group({
      memberName: [''],
      memberAge: [''],
      memberGender: [''],
      relation: ['']
    })
  }

  submitForm() {
    console.log("hi");
 
     const signUpForm = Object.assign(this.firstForm.value);
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
