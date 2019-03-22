import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { InsurerOutgoingportingrequestService } from './../../service/insurer-outgoingportingrequest.service';
import { InsurerAcceptoutgoingportingrequestService } from './../../service/insurer-acceptoutgoingportingrequest.service';
import {MatDialog, MatDialogConfig, MatTableDataSource} from '@angular/material';
import { AllPortingRequestsComponent } from '../all-porting-requests/all-porting-requests.component';
import { ReviewComponent } from '../review/review.component';
import { DisplayAllPortingRequestsComponent } from '../display-all-porting-requests/display-all-porting-requests.component';
import { TokenStorageService } from './../companyauth/token-storage.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-outgoing-porting-requests',
  templateUrl: './outgoing-porting-requests.component.html',
  styleUrls: ['./outgoing-porting-requests.component.css']
})
export class OutgoingPortingRequestsComponent implements OnInit {
  showAcceptRequestButton: boolean;
  clearedRequests: number[] = [];
  dialogReference: any;
  insurerLicense: any;
  requests: any;
  info: any;
  message1: any;
  action: any;
  displayedColumns: string[] = ['requestID', 'userName', 'oldInsurerName','modifyStatusButton'];
  dataSource = new MatTableDataSource<Request[]>();
  currentCompanyName: string;
  raiseGrievanceButtonIsClicked: boolean;
  idForGrievances: number;
  grievancesComponent = AllPortingRequestsComponent;
  constructor(private route: ActivatedRoute, private incoming: InsurerOutgoingportingrequestService, private portrequest: InsurerAcceptoutgoingportingrequestService, private dialog: MatDialog,private token: TokenStorageService,private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    this.showAcceptRequestButton = false;
    this.raiseGrievanceButtonIsClicked = false;
    console.log("inside outgoing");
    this.insurerLicense = this.route.snapshot.paramMap.get('insurerLicense');
    console.log(this.insurerLicense);
    this.requests = this.incoming.getSearch(this.insurerLicense).subscribe(data => {
      this.requests = data;
      console.log(data);
      console.log("after service method");
    });

  }
  port(request) {
    this.message1 = "Request is accepted sucessfully ";
    this.action = "";
    console.log("xyz");
    this.portrequest.getSearch(request).subscribe(data => {console.log(data), this.reloadData()});
    console.log("xynz");

    // alert("successfull");
    this.snackBar.open(this.message1,this.action, {
      duration: 6000,
      
      verticalPosition: 'top'
      
    });
    // this.reloadData();
   
  }
  reloadData() {
    window.location.reload();
  }
  openDialog(insuredname): void {
    let as = window.localStorage.setItem("insuredname", insuredname);
    const dialogRef = this.dialog.open(ReviewComponent, {
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
  raiseGrievanceButtonClicked(portrequestId: number, insuredName: string): boolean {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    dialogConfig.data = {
      portingRequestId: portrequestId,
      userName: insuredName
    };
    this.dialogReference = this.dialog.open(DisplayAllPortingRequestsComponent, dialogConfig);
    this.dialogReference.componentInstance.showAcceptRequestButton.subscribe((data) => {
      this.clearedRequests.push(data);
      this.showAcceptRequestButton = true;
    });
    this.raiseGrievanceButtonIsClicked = false;
    this.idForGrievances = portrequestId;
    return true;
  }
  isClearedRequest(portingRequestId: number): boolean {
    if(this.clearedRequests.includes(portingRequestId)) {
      return true;
    } else {
      return false;
    }
  }

}
