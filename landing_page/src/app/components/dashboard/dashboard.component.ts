import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { UserDashboardService } from './../../service/user-dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
 username: any;
 getUser: any;
 
  constructor(private route: ActivatedRoute, private userService: UserDashboardService, private router: Router) { }

  ngOnInit() {
    this.username = this.route.snapshot.paramMap.get('username');
    let as = window.localStorage.setItem("insuredname",this.username);
    this.getUser = this.userService.getProfile(this.username).subscribe(
      data=>{
        this.getUser = data;
        console.log(data);
        let bs = window.localStorage.setItem("emailId",data.email);
      }
    );
 this.router.navigate(['recommendations',this.username]);
  } 

}
