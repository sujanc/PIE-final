import { CalculatorComponent } from './../calculator/calculator.component';
import { InsurerAcceptincomingportingrequestService } from './../../service/insurer-acceptincomingportingrequest.service';
import { InsurerIncomingportingrequestService } from './../../service/InsurerIncomingportingrequestService';
import { InsurerOutgoingportingrequestService } from './../../service/insurer-outgoingportingrequest.service';
import { ActivatedRoute } from '@angular/router';
import { TokenStorageService } from './../companyauth/token-storage.service';

import { MatDialog, MatTableDataSource } from '@angular/material';
import { ReviewComponent } from '../review/review.component';

import { Component, OnInit } from '@angular/core';
import { InsurerAcceptoutgoingportingrequestService } from './../../service/insurer-acceptoutgoingportingrequest.service';
import { InsurerRejectincomingportingrequestService } from './../../service/InsurerRejectincomingportingrequestService';

import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-incoming-porting-requests',
  templateUrl: './incoming-porting-requests.component.html',
  styleUrls: ['./incoming-porting-requests.component.css']
})
export class IncomingPortingRequestsComponent implements OnInit {
  insurerLicense: any;
  info: any;

  requests: any;
  displayedColumns: string[] = ['requestID', 'userName', 'oldInsurerName','modifyStatusButton'];
  dataSource = new MatTableDataSource<Request[]>();
  // request: any;
  // public reviewComponent: ReviewComponent;
  public insuredName: any;
  message1: any;
action: any;
  constructor(private route:ActivatedRoute, private incoming: InsurerIncomingportingrequestService,
    private portrequest: InsurerAcceptincomingportingrequestService, 
    private portrequest1: InsurerRejectincomingportingrequestService, 
    public dialog: MatDialog,private token: TokenStorageService,private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    console.log("inside Incoming");
    this.insurerLicense = this.route.snapshot.paramMap.get('insurerLicense');
    console.log(this.insurerLicense);
    this.requests = this.incoming.getSearch(this.insurerLicense).subscribe(data =>
      {
        this.requests = data;
        console.log(data);
        console.log("after service method");
    });
    console.log(this.requests);
  }

  
  port(request) {
    this.message1 = "Request is accepted sucessfully ";
    this.action = "";
    this.portrequest.getSearch(request).subscribe(data=>{

      this.requests = this.incoming.getSearch(this.insurerLicense).subscribe(data =>
        {
          this.requests = data;
          console.log(data);
          console.log("after service2 method");
      });
      // alert("Request is accepted sucessfully");
      this.snackBar.open(this.message1,this.action, {
        duration: 4000,
        
        verticalPosition: 'top'
        
      });

      this.reloadData();

    });
    
  }

  port1(request) {
    this.portrequest1.getSearch(request).subscribe(data=>console.log(data));
    this.reloadData();
  }
  reloadData(){
    window.location.reload();
  }
  openDialog(insuredname): void {
    let as = window.localStorage.setItem("insuredname",insuredname);
    const dialogRef = this.dialog.open(ReviewComponent, {
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
