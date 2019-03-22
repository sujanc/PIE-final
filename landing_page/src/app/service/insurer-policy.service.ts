import { PolicyPremium } from './../policy-premium';
import { PolicyFormInfo } from './../components/policyStore/policy-form-info';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpClientModule } from '@angular/common/http';
const httpOptions = {
headers: new HttpHeaders({
  'Access-Control-Allow-Origin':'*',

})
};



@Injectable({
providedIn: 'root'
})
export class InsurerPolicyService {

constructor(private http: HttpClient) { }

//localUrl = 'http://13.126.73.190:8092/insurerregservice/api/v1/';
policiesUrl = 'http://13.126.73.190:8092/policy/api/v1/';
externalPolicyUrl = 'http://13.126.73.190:8092/externalinsurerdbservice/api/v1/';
//  localUrl = 'http://localhost:8094/api/v1/';



addNewPolicy(policy : PolicyFormInfo):Observable<Object> {
  console.log('adadsasdas')
 return this.http.post(this.policiesUrl+ 'policy',policy);
}

getPolicies(insurerLicense : String):Observable<Object> {
  console.log('adadsasdas')
 return this.http.get(this.policiesUrl+'policy/'+ insurerLicense);
}

getPremium(premium : PolicyPremium):Observable<Object> {
 console.log('adadsasdas')
return this.http.post('http://13.126.73.190:8092/insurerregservice/api/v1'+ '/policy/premium/calculator',premium);
}

getExternalPolicy(insurerName:String):Observable<Object> {
 console.log('externalPolicy')
 return this.http.get(this.externalPolicyUrl+ 'policy/external/' + insurerName)
}

deletePolicy(insurerName:String,policyName:String):Observable<Object>{
  console.log(insurerName);
  console.log(policyName);
  return this.http.delete(this.policiesUrl+'policy/'+insurerName+'/'+policyName);

}
}