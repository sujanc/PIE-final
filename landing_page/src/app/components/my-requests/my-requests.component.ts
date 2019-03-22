import { UserDashboardService } from './../../service/user-dashboard.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-my-requests',
  templateUrl: './my-requests.component.html',
  styleUrls: ['./my-requests.component.css']
})
export class MyRequestsComponent implements OnInit {
  insuredId;
  requestObj;
  constructor(private route: ActivatedRoute, private router: Router, public dashboardService: UserDashboardService) { }

  ngOnInit() {
    console.log('xzxm');
    this.insuredId = this.route.snapshot.paramMap.get('id');
    console.log('In requests component : ' + this.insuredId);
    this.requestObj = this.dashboardService.getRequests(this.insuredId).subscribe(
      data => {
        this.requestObj = data;
        console.log('request' + this.requestObj.insuredId);
        // console.log(this.requestObj.requests.get(0));
  },
      error => {
        console.log('some error occured');
        console.log(error.errorMessage);
      // tslint:disable-next-line:semicolon
      }
    );
  }

}
