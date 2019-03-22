import { CalculatorComponent } from './../calculator/calculator.component';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {
  noPause: any;
  constructor(public dialog: MatDialog) { }

  ngOnInit() {
  }
  // openDialog(): void {
  //   const dialogRef = this.dialog.open(CalculatorComponent, {
  //   });

  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log('The dialog was closed');
  //   });
  // }

}
