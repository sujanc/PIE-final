import { InsurerPolicyService } from './../../service/insurer-policy.service';
import { FormGroup, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute} from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { TokenStorageService } from './../companyauth/token-storage.service';
import { MatSnackBar } from '@angular/material';



@Component({
selector: 'app-add-new-policy',
templateUrl: './add-new-policy.component.html',
styleUrls: ['./add-new-policy.component.css']
})
export class AddNewPolicyComponent implements OnInit {
 formSubmitAttempt :any;
 info:any;
firstForm;
secondForm;
thirdForm;
message1: any;
action: any;

constructor(private formBuilder:FormBuilder,private router:Router,private service:InsurerPolicyService,private token: TokenStorageService,private snackBar: MatSnackBar) {

}
isFieldInvalid(field: string) {
 return (
   (!this.firstForm.get(field).valid && this.firstForm.get(field).touched) ||
   (this.firstForm.get(field).untouched && this.formSubmitAttempt)
 );
}

ngOnInit() {
  this.info = {
    token: this.token.getToken(),
    username: this.token.getUsername(),
    authorities: this.token.getAuthorities()
  };
this.firstForm = this.formBuilder.group({
insurerName: ["", Validators.required],
insurerLicense:["", Validators.required],
policyName: ["", Validators.required],
policyId: ["", Validators.required]

}
);
this.secondForm = this.formBuilder.group({
 policyType: ["", Validators.required],
 genderAvail: ["", Validators.required],
 minAge: ["", Validators.required],
 diseasesCovered: ["",Validators.required],
maxAge: ["", Validators.required],
//  hospitals: ["", Validators.required]
}
);
this.thirdForm = this.formBuilder.group({
policyTerm: ["", Validators.required],
minSumInsured: ["", Validators.required],
maxSumInsured: ["", Validators.required],
waitingPeriod: ["", Validators.required],
policyDescription: ["", Validators.required],
imageUrl : ["", Validators.required]

}
);
}

onSubmit() {
console.log("hi");
const result = Object.assign(this.firstForm.value,this.secondForm.value,this.thirdForm.value);
this.message1 = "Policy added succesfully ";
    this.action = "";
console.log('registerForm.value : ',result);
   this.service.addNewPolicy(result).subscribe(
     data => {
       console.log('data is ', data);
       console.log("sdfghj");

       // this.router.navigate(["/login"]);
       //alert("New policy added succesfully");
       this.snackBar.open(this.message1,this.action, {
        duration: 4000,
        
        verticalPosition: 'top'
        
      });
     },
     error => {
       console.log('we are getting some errors');

     }
   );
}
}