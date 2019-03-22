import { UserService } from './../../service/user.service';
import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from "@angular/material";
import { UserDashboardService } from './../../service/user-dashboard.service';
@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  public profile;
  public username;
  constructor(private userService: UserDashboardService,
    private mdDialogRef: MatDialogRef<ReviewComponent>
  ) { }

  ngOnInit() {
    this.username = window.localStorage.getItem("insuredname");
    console.log("oijh"+this.username);
    this.profile = this.userService.getProfile(this.username).subscribe(
      data => {
        this.profile = data;
        console.log(data);
        console.log(this.profile);
    },
      error => {
        console.log('some error occured');
        console.log(error.errorMessage);
    
      }
    );
  }

}
