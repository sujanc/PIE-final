import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { InsurerPolicyService } from './../../service/insurer-policy.service';

@Component({
  selector: 'app-deletepopup',
  templateUrl: './deletepopup.component.html',
  styleUrls: ['./deletepopup.component.css']
})
export class DeletepopupComponent implements OnInit {
insurerName:any;
policyName:any;
insurerLicense;
  constructor(private router: Router, private route: ActivatedRoute, private insurerPolicyService: InsurerPolicyService) { }

  ngOnInit() {
    this.insurerName = window.localStorage.getItem("insurername");
    this.policyName = window.localStorage.getItem("policyname");
    this.insurerLicense = window.localStorage.getItem("insurername");
    console.log("in delete popup with insurername"+ this.insurerName);
  }
deletePop(insurerName, policyName){
  // this.insurerName = this.route.snapshot.paramMap.get('insurerName')
   this.insurerLicense = window.localStorage.getItem("insurername");
  //  console.log(this.policyName)
  //  console.log(this.insurerName)
   this.router.navigate(["/deletePolicy", insurerName, policyName]);
}
}