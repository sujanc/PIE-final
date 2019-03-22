import { ActivatedRoute } from '@angular/router';
import { InsurerAcceptincomingportingrequestService } from './../../service/insurer-acceptincomingportingrequest.service';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './../companyauth/token-storage.service';

@Component({
  selector: 'app-porting-history',
  templateUrl: './porting-history.component.html',
  styleUrls: ['./porting-history.component.css']
})
export class PortingHistoryComponent implements OnInit {

  constructor(private incomingService: InsurerAcceptincomingportingrequestService, private route: ActivatedRoute,private token: TokenStorageService) { }
  insurerLicense: any;
  info:any;
  requests: any;
  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    console.log("inside Incoming");
    this.insurerLicense = this.route.snapshot.paramMap.get('insurerName');
    console.log(this.insurerLicense);
    this.requests = this.incomingService.portingHistory(this.insurerLicense).subscribe(data =>
      {
        this.requests = data;
        console.log(data);
        console.log("after service method");
    });
    console.log(this.requests);
  }

}
