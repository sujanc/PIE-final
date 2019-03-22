import { TokenStorageService } from './../auth/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { BuyPolicyComponent } from '../buy-policy/buy-policy.component';

@Component({
  selector: 'app-terms-and-conditions',
  templateUrl: './terms-and-conditions.component.html',
  styleUrls: ['./terms-and-conditions.component.css']
})
export class TermsAndConditionsComponent implements OnInit {
  username;
  policyname;
  insurername;
  marked=false;
  emailId:any;
  info: any;
  theCheckbox:any;
  constructor(private dialog: MatDialog, private token: TokenStorageService) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };

  }
  toggleVisibility(e){
    this.marked= e.target.checked;
  }
  openDialog(policyname,insurername): void {
    console.log("dfgh");
    // let as = window.localStorage.setItem("searchInsurer",insurername);
    //     let bs = window.localStorage.setItem("searchPolicy",policyname);
    const dialogRef = this.dialog.open(BuyPolicyComponent, {
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

}
