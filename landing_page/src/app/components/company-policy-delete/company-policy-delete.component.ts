import { InsurerPolicyService } from './../../service/insurer-policy.service';
import { Component, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
 selector: 'app-company-policy-delete',
 templateUrl: './company-policy-delete.component.html',
 styleUrls: ['./company-policy-delete.component.css']
})
export class CompanyPolicyDeleteComponent implements OnInit {
policyName;
insurerName;
insurerLicense;
 constructor(private route: ActivatedRoute, private router: Router, public insurerPolicyService: InsurerPolicyService) { }


 ngOnInit() {
  this.insurerName = this.route.snapshot.paramMap.get('insurerName');
   this.policyName = this.route.snapshot.paramMap.get('policyName');
   this.insurerLicense = window.localStorage.getItem("insurername");
   console.log(this.policyName)
   console.log(this.insurerName)
   this.insurerPolicyService.deletePolicy(this.insurerName,this.policyName).subscribe(policy => console.log(policy)); 
   this.router.navigate(["/myCompanyPolicy", this.insurerLicense]);
 } 
}