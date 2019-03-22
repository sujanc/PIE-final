import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PolicyService } from './../../service/policy.service';
import { EmailService } from './../../service/email.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-guest-user',
  templateUrl: './guest-user.component.html',
  styleUrls: ['./guest-user.component.css']
})

export class GuestUserComponent implements OnInit {
  guestUser: FormGroup;
  insurername: any;
  policyname: any;
  requestId: any;
  sampleEmail: any;
  email:any;
  message1: any;
  action: any;
  public object = {
    insurername: this.insurername,
    policyname: this.policyname
  }
  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute, private router: Router, private policyService: PolicyService, private emailService: EmailService, private snackBar: MatSnackBar) { }
 
  ngOnInit() {
    // this.object.insurername = this.route.snapshot.paramMap.get('insurername');
    // this.object.policyname = this.route.snapshot.paramMap.get('policyname');
    this.object.insurername = window.localStorage.getItem('recoinsurername');
    this.object.policyname = window.localStorage.getItem('recopolicyname');
    this.guestUser = this.formBuilder.group({
      email:['',Validators.required]
        });
    console.log("guest user "+ this.guestUser);
    console.log("insurername "+ this.object.insurername);
  }
  submitForm(email){
    // const signUpForm = Object.assign(this.guestUser.value, this.object); 
    const obj1 = Object.assign({},this.object);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
    console.log("sign "+ obj1.insurername);
    const obj2 = Object.assign(obj1,this.guestUser.value);
    console.log(obj2.insurername);
    this.message1 = "E-mail has been sent succesfully ";
    this.action = "";

    // this.router.navigate(["/buyPolicy", obj2]);
    this.requestId = this.policyService.buyPolicy(obj2).subscribe(
      data => {
        this.requestId = data;
        console.log("inside subscribe");
        console.log(this.requestId.id);

        this.sampleEmail = {"to": email, "subject": "New Policy Bought",
        "body": "Congratulations on your new  Health Insurance -"
        + this.object.policyname+ " from " + this.object.insurername+
        ".Your RequestId is " + this.requestId.id +
         ".Representative from " + this.object.insurername + "will contact you within 7 days."
      };
        this.emailService.sendEmail(this.sampleEmail).subscribe();
        // alert("E-mail has been sent succesfully.");
        console.log("sdfghj");
        this.snackBar.open(this.message1,this.action, {
          duration: 4000,
          

          
        });
      //   cc-snackBar{
      //     position: relative;
      //     top: -2px;;
      // }

        this.router.navigate(['/home']);
      },
      error => {
        console.log("error occured");
        console.log(error);
      }
      
    );
  }
}
