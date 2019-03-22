import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { RequestService } from './../../service/request.service';

@Component({
  selector: 'app-update-request',
  templateUrl: './update-request.component.html',
  styleUrls: ['./update-request.component.css']
})
export class UpdateRequestComponent implements OnInit {
  portingForm: any;
  res : Object;
  requestId;
  constructor(private _route: ActivatedRoute, private formBuilder: FormBuilder, private requestService: RequestService) { }
 
  ngOnInit() {
    this.requestId = this._route.snapshot.paramMap.get('requestId');
    this.portingForm = this.formBuilder.group({
      insuredName:['',Validators.required],
      dateOfBirth:['',Validators.required],
      insuredAddress:['',Validators.required],
      insurerProduct:['',Validators.required],
      sumInsured:['',Validators.required],
      cumulativeBonus:['',Validators.required],
      addOns:['',Validators.required],
      policyNumber:['',Validators.required],
      newInsurerProduct:['',Validators.required],
      newSumInsured:['',Validators.required],
      newCumulativeBonus:['',Validators.required],
      reasonForPortability:['',Validators.required],
      familyMembers:[''],
      exclusionPeriod:['',Validators.required]});
  }
  submitForm() {
    console.log("hi");
    console.log(this.portingForm);
    this.res = Object.assign(this.portingForm.value);
    console.log("hiii" + this.res);
    this.requestService.updateRequest(this.requestId, this.res).subscribe(
      data => {
        console.log('hel');
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }
}
