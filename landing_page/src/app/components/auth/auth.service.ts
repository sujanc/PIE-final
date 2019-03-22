import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { JwtResponse } from './jwt-response';
import { AuthLoginInfo } from './login-info';
import { SignUpInfo } from './signup-info';
import { Router } from '@angular/router';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private loginUrl = 'http://13.126.73.190:8092/commonauth/api/auth/signin';
  private countUrl = 'http://13.126.73.190:8092/commonauth/api/auth/count/';
  private signupUrl = 'http://13.126.73.190:8092/userreg/api/auth/signup';
  // private loginUrl = 'http://13.126.73.190:8096/api/auth/signin';
  // private signupUrl = 'http://13.126.73.190:8093/api/auth/signup';

  

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }


  constructor(private http: HttpClient) {
  }

  getCount(username:String):any {
    return this.http.get(this.countUrl+username);
  }
 
  updateCount(username:String):any{
    return this.http.post(this.countUrl+'update/'+username,0)
  }
  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    this.loggedIn.next(true);
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }
}
