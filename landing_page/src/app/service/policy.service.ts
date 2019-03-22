
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
// import 'rxjs/Rx';
@Injectable({
  providedIn: 'root'
})
export class PolicyService {
  public httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      // 'Authorization': 'my-auth-token'
    })
  };
  public policyDetailsUrl = 'http://13.126.73.190:8092/policy/api/v1/policy/';
  public portingpolicy = 'http://13.126.73.190:8092/policy/api/v1/';
  public buypolicy = 'http://13.126.73.190:8092/externalinsurerdbservice/api/v1/external/buypolicy';
  policyDetails:any;
  constructor(private http: HttpClient) {
    console.log('http service got called');
  }
  public getPolicyDetails(insurerName,policyName): any {
    console.log("inside service");
   
    return this.http.get(this.policyDetailsUrl + insurerName +'/'+ policyName, this.httpOptions);
}

public buyPolicy(policy): any {
  console.log('inside buy policy');
  let as = this.http.post(this.buypolicy,policy);
  return as;
 }
  public getPolicyByPolicyName(policyName): any {
    console.log("inside service");
   
    return this.http.get(this.portingpolicy +'policyByName/' + policyName);
   }
  public getAllPolicies(): any{
    console.log("inside get all policies");
    return this.http.get(this.portingpolicy + 'policies');
  }
}
