import { TermsAndConditionsComponent } from './../terms-and-conditions/terms-and-conditions.component';
import { SearchService } from './../../service/search.service';
import { Component, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TokenStorageService } from '../auth/token-storage.service';
import { MatDialog } from '@angular/material';
import { BuyPolicyComponent } from '../buy-policy/buy-policy.component';
import { CalculatorComponent } from '../calculator/calculator.component';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  message: any;
  policies: any;
  policyName: string;
  diseasesCovered: any;
  premium: any;
  userName: any;
  agegroup: any;
  flag = 0;
  result;
  info: any;
  emailId: any;

  // public policy = {
  //   policyName : this.policyName,
  //   diseasesCovered: this.diseasesCovered,
  //   premium: this.premium,
  //   agegroup: this.agegroup
  // };
  constructor(private route: ActivatedRoute, private searchService: SearchService, private token: TokenStorageService, private dialog: MatDialog) { }
  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    this.userName = window.localStorage.getItem("insuredname");
    this.emailId = window.localStorage.getItem("emailId");
    console.log("emailId"+this.emailId +" "+ this.userName);
    console.log("in search");
    this.message = this.route.snapshot.paramMap.get('value');
    console.log( "abd" + this.message);
    this.policies = this.searchService.getSearch(this.message).subscribe(data=>
      {
        this.policies = data;
        
      });
    console.log("fcgvhbj" + this.policies.policyId);
    
  }
  // openDialog(policyname,insurername): void {
  //   let as = window.localStorage.setItem("searchInsurer",insurername);
  //       let bs = window.localStorage.setItem("searchPolicy",policyname);
  //   const dialogRef = this.dialog.open(BuyPolicyComponent, {
  //   });

  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log('The dialog was closed');
  //   });
  // }
  // ngOnChanges(changes: SimpleChanges): void {
  //   //Called before any other lifecycle hook. Use it to inject dependencies, but avoid any serious work here.
  //   //Add '${implements OnChanges}' to the class.
  //   window.location.reload();
  // }
  openDialog(insurername,policyname): void {
    let as = window.localStorage.setItem("recoinsurername",insurername);
    let data = window.localStorage.setItem("recopolicyname",policyname);
    // let data1 = window.localStorage.setItem("username",username);
    const dialogRef = this.dialog.open(TermsAndConditionsComponent, {
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openDialog3(): void {
    const dialogRef = this.dialog.open(CalculatorComponent, {
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

}
