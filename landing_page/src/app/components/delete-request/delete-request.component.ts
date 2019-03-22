import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { RequestService } from './../../service/request.service';

@Component({
  selector: 'app-delete-request',
  templateUrl: './delete-request.component.html',
  styleUrls: ['./delete-request.component.css']
})
export class DeleteRequestComponent implements OnInit {
  requestId;
  constructor(private _route: ActivatedRoute, private router: Router, public requestService: RequestService) { }
 
  ngOnInit() {
    console.log('delete');
    this.requestId = this._route.snapshot.paramMap.get('requestId');
    console.log('In delete component : ' + this.requestId);
         this.requestService.deleteRequest(this.requestId).subscribe(
        data => {
          console.log(data);
        }
       );
 
       this.router.navigate(['/myrequests']);
 
      error => {
        console.log('some error occured in delete component');
        console.log(error.errorMessage);
      }
 
  }
}
