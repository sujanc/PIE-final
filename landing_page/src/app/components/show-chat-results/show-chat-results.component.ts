import { Component, OnInit } from '@angular/core';
import { ChatService } from '../../service/chat.service';
import { MatDialog } from '@angular/material';
import { CalculatorComponent } from '../calculator/calculator.component';

@Component({
  selector: 'app-show-chat-results',
  templateUrl: './show-chat-results.component.html',
  styleUrls: ['./show-chat-results.component.css']
})
export class ShowChatResultsComponent implements OnInit {
  policies: any;
  constructor(private chat:ChatService,private dialog: MatDialog) { }

  ngOnInit() {
   this.chat.showChatResults().subscribe(
      data=>{
        console.log(data);
        this.policies=data;
      }
    );
  }

  openDialog3(): void {
    const dialogRef = this.dialog.open(CalculatorComponent, {
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
