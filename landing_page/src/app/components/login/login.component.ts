import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import { AuthService } from '../auth/auth.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { AuthLoginInfo } from '../auth/login-info';
import { InsurerPolicyService } from './../../service/insurer-policy.service';

@Component({
 selector: 'app-login',
 templateUrl: './login.component.html',
 styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 form: any = {};
 isLoggedIn = false;
 isLoginFailed = false;
 errorMessage = '';
 loginDetails: any;
 roles: string[] ;
 
 private loginInfo: AuthLoginInfo;

 constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router, private service: InsurerPolicyService) {}

 ngOnInit() {
   if (this.tokenStorage.getToken()) {
     this.isLoggedIn = true;
     this.roles = this.tokenStorage.getAuthorities();
   }
 }
 onSubmit(username) {
  console.log(this.form);
  console.log('xzxm');
  console.log('User Name :' + username);

     this.authService.getCount(username).subscribe(
     data => {
       this.loginDetails = data;
       console.log("asdadakdkahdhkjadhkjad")
       console.log('data is ', data);
       if(this.loginDetails == 0)
       {
         console.log("enterrd if condition")
         this.authService.updateCount(username).subscribe(
           data1 => {
             console.log(data1);
           },
           error => {
             console.log(error);
           }
         );
         this.service.getExternalPolicy(username).subscribe(
           data => {
             console.log(data);
           },
           error => {
             console.log(error);
           }
         );
       }
     },
     error => {
       console.log('we are getting some errors');

     }
   );
  this.loginInfo = new AuthLoginInfo(
    this.form.username,
    this.form.password);

  this.authService.attemptAuth(this.loginInfo).subscribe(
    data => {
      this.tokenStorage.saveToken(data.accessToken);
      this.tokenStorage.saveUsername(data.username);
      this.tokenStorage.saveAuthorities(data.authorities);

      this.isLoginFailed = false;
      this.isLoggedIn = true;
      if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_USER') {
      this.router.navigate(['/dashboard', this.form.username]);
       return true;
    }
        else if (role === 'ROLE_INSURER') {
          this.router.navigate(['/insurerDashboard', this.form.username]);
          return true;
         }
         else if(role === 'ROLE_ADMIN'){
           this.router.navigate(['/admin',this.form.username])
         }
    });
    window.location.reload();
  }
    error => {
      console.log(error);
      this.errorMessage = error.error.message;
      this.isLoginFailed = true;
    }
  });
}
 reloadPage() {
   window.location.reload();
 }
}
