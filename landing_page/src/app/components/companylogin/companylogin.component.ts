// import { AuthService } from './../companyauth/companyauth.service';
// import { AuthLoginInfo } from './../companyauth/login-info';
// import { TokenStorageService } from './../companyauth/token-storage.service';
// import { Component, OnInit } from '@angular/core';

// @Component({
//   selector: 'app-login',
//   templateUrl: './companylogin.component.html',
//   styleUrls: ['./companylogin.component.css']
// })
// export class CompanyLoginComponent implements OnInit {
//   form: any = {};
//   isLoggedIn = false;
//   isLoginFailed = false;
//   errorMessage = '';
//   roles: string[] = [];
//   private loginInfo: AuthLoginInfo;

//   constructor(private authService: AuthService, private tokenStorage: TokenStorageService) { }

//   ngOnInit() {
//     if (this.tokenStorage.getToken()) {
//       this.isLoggedIn = true;
//       this.roles = this.tokenStorage.getAuthorities();
//     }
//   }
//   onSubmit() {
//     console.log(this.form);

//     this.loginInfo = new AuthLoginInfo(
//       this.form.insurerLicense,
//       this.form.password);

//     this.authService.attemptAuth(this.loginInfo).subscribe(
//       data => {
//         this.tokenStorage.saveToken(data.accessToken);
//         this.tokenStorage.saveInsurerLicense(data.insurerLicense);
//         this.tokenStorage.saveAuthorities(data.authorities);

//         this.isLoginFailed = false;
//         this.isLoggedIn = true;
//         this.roles = this.tokenStorage.getAuthorities();
//         this.reloadPage();
//       },
//       error => {
//         console.log(error);
//         this.errorMessage = error.error.message;
//         this.isLoginFailed = true;
//       }
//     );
//   }

//   reloadPage() {
//     window.location.reload();
//   }
// }
