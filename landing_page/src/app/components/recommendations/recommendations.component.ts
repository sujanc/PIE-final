import { UserDashboardService } from './../../service/user-dashboard.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit, SimpleChanges } from '@angular/core';
import { TermsAndConditionsComponent } from '../terms-and-conditions/terms-and-conditions.component';
import { MatDialog } from '@angular/material';
import { TokenStorageService } from './../companyauth/token-storage.service';


@Component({
  selector: 'app-recommendations',
  templateUrl: './recommendations.component.html',
  styleUrls: ['./recommendations.component.css']
})
export class RecommendationsComponent implements OnInit {
  userName: any;
   info:any;
  public recommendations;
  constructor(public route: ActivatedRoute, private router: Router, public userService: UserDashboardService, private dialog: MatDialog,private token: TokenStorageService) { }
  ngOnInit() {

    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    this.userName = this.route.snapshot.paramMap.get('username');
    console.log('recommendations : ' + this.userName);
    
    this.recommendations = this.userService.getRecommendations(this.userName).subscribe(
      data => {
        this.recommendations = data;
      },
      error => {
        console.log('some error occured');
        console.log(error.errorMessage);
      }
   );
    }
    ngOnChanges(changes: SimpleChanges) {
      this.router.navigate(['/dashboard',this.userName]);
    }
    openDialog(insurername,policyname,username): void {
      let as = window.localStorage.setItem("recoinsurername",insurername);
      let data = window.localStorage.setItem("recopolicyname",policyname);
      let data1 = window.localStorage.setItem("recousername",username);
      const dialogRef = this.dialog.open(TermsAndConditionsComponent, {
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
      });
    }
   
}
