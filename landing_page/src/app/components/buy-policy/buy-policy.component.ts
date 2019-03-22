import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { PolicyService } from './../../service/policy.service';
import { Email } from './../../service/email';
import { EmailService } from './../../service/email.service';

@Component({
  selector: 'app-buy-policy',
  templateUrl: './buy-policy.component.html',
  styleUrls: ['./buy-policy.component.css']
})
export class BuyPolicyComponent implements OnInit {

  insurerName: any;
  policyName: any;
  insuredName: any;
  requestId: any;
  emailId: any;
  id: any;
  public object = {
    insurerName: this.insurerName,
    policyName: this.policyName,
    insuredName: this.insuredName,
    emailId: this.emailId
  }
  constructor(public route: ActivatedRoute, private router: Router, private policyService: PolicyService, private emailService: EmailService) { }
  sampleEmail: Email;
  ngOnInit() {
      // this.object.insurerName= this.route.snapshot.paramMap.get('insurername');
      // this.object.policyName= this.route.snapshot.paramMap.get('policyname');
      // this.object.insuredName= this.route.snapshot.paramMap.get('insuredname');
      // this.object.emailId = this.route.snapshot.paramMap.get('emailId');
     
      this.object.insuredName=window.localStorage.getItem('insuredname');
      this.object.emailId=window.localStorage.getItem('emailId');
      this.object.policyName=window.localStorage.getItem('recopolicyname');
      this.object.insurerName=window.localStorage.getItem('recoinsurername');
      // console.log(this.insurerName +" "+this.policyName+" "+this.insuredName +" "+this.emailId);
      this.requestId = this.policyService.buyPolicy(this.object).subscribe(
        data => {
          this.requestId = data;
          console.log(this.requestId);
          this.id = this.requestId.id;
          this.sampleEmail = {"to": this.object.emailId, "subject": "New Policy Bought",
          "body": "Congratulations on your new  Health Insurance -"
          + this.object.policyName+ " from " + this.object.insurerName+
          ".Your RequestId is " + this.requestId.id +
          ".Representative from" + this.object.insurerName + "will contact you within 7 days."
        };
          alert("Email sent successfully");
          this.emailService.sendEmail(this.sampleEmail).subscribe();
          
        },
        error => {
          console.log("error occured");
          console.log(error);
        }
      );
  }
 
 }