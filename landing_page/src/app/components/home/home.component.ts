import { Component, OnInit } from '@angular/core';

import { TokenStorageService } from '../auth/token-storage.service';
import { DialogbotComponent } from '../dialogbot/dialogbot.component';
import { MatDialog } from '@angular/material';
import { CalculatorComponent } from '../calculator/calculator.component';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  info: any;

  constructor(private token: TokenStorageService,public dialog: MatDialog) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
  }
  openDialog1(): void {
    const dialogRef = this.dialog.open(DialogbotComponent, {
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  
  // logout() {
  //   this.token.signOut();
  //   window.location.reload();
  // }
}
