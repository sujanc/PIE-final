import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';
// import 'rxjs/add/operator/do';
@Injectable({
  providedIn: 'root'
})
export class UserDashboardService {
public localMongoUrl = 'http://13.126.73.190:8092/porting/api/auth/requests/';
 public recommendationsUrl = 'http://13.126.73.190:8086/api/v1/policyByAgeGender/';
 public profileUrl = 'http://13.126.73.190:8092/userreg/api/auth/profile/';
 public policiesUrl = 'http://13.126.73.190:8092/policy/api/v1/policies/';
 constructor(private http: HttpClient) {
   console.log('http service got called');
 }
   public getRequests(userId): any {
     console.log( userId);
     const requests = this.http.get(this.localMongoUrl + userId);
     console.log(requests);
     return requests;
   }
   public getRecommendations(username): any {
     console.log(username);
     const recommendations = this.http.get(this.recommendationsUrl + username);
     return recommendations;
   }
   public getPolicies(username): any {
    console.log(username);
    let getp =  this.http.get(this.policiesUrl + "manasa");
    console.log("getp "+getp);
    return getp;
  }
   public getProfile(username): any {
     console.log('service' + username);
     const profile = this.http.get(this.profileUrl + username);
     console.log('after');
     console.log(profile);
     return profile;
   }
   public updateProfile(username, form): any {
     console.log('service' + username);
     const profile = this.http.put(this.profileUrl + username,form);
     console.log('after');
     return profile;
   }
   public familyMembers(username, form): any {
    console.log('service' + username);
    const profile = this.http.put(this.profileUrl + 'familymembers',form);
    console.log('after');
    return profile;
  }
}
