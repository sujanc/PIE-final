import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { RequestService } from './../../service/request.service';
import { TokenStorageService } from './../companyauth/token-storage.service';


@Component({
  selector: 'app-display-requests',
  templateUrl: './display-requests.component.html',
  styleUrls: ['./display-requests.component.css']
})
export class DisplayRequestsComponent implements OnInit {
  public requests;
  username;
  info: any;
 constructor(public _route: ActivatedRoute, private router: Router, public requestService: RequestService,private token: TokenStorageService
  ) { }
 ngOnInit() {
  this.info = {
    token: this.token.getToken(),
    username: this.token.getUsername(),
    authorities: this.token.getAuthorities()
  };
  this.username = this._route.snapshot.paramMap.get('username');
    console.log('In delete component : ' + this.username);
  this.requests = this.requestService.getRequests(this.username).subscribe(
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
}
