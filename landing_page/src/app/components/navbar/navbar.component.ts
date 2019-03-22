import { CalculatorComponent } from './../calculator/calculator.component';
import { MatDialog } from '@angular/material';
import { TokenStorageService } from './../companyauth/token-storage.service';
import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { InternalFormsSharedModule } from '@angular/forms/src/directives';
import { Subscription } from 'rxjs';
import { ObservableMedia, MediaChange } from '@angular/flex-layout';


@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.css"]
})
export class NavbarComponent implements OnInit {
  @Input() login: boolean;
  sign = "SignIn";
  username: any;
  message: string;
  name: any;
  isLogin: any;
  currentRoute: any;
  info: any;
  insuredName: any;
  insurerName: any;
  opened = true;
  logout1 = true;
  over = "side";
  expandHeight = "42px";
  collapseHeight = "42px";
  displayMode = "flat";
  // overlap = false;

  watcher: Subscription;
  constructor(
    private router: Router,
    private token: TokenStorageService,
    media: ObservableMedia,
    public dialog: MatDialog
    
  ) {
    this.watcher = media.subscribe((change: MediaChange) => {
      if (change.mqAlias === "sm" || change.mqAlias === "xs") {
        this.opened = false;
        this.over = "over";
      } else {
        this.opened = true;
        this.over = "side";
      }
    });
  }

  ngOnInit() {
    this.insuredName = window.localStorage.getItem("insuredname");
    this.insurerName = window.localStorage.getItem("insurername");
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    console.log(this.message);
    this.router.events.subscribe(event => {
      if (event.constructor.name === "NavigationEnd") {
        this.name = (<any>event).url.split("/").slice(-1)[0];
        this.isLogin = this.currentRoute === "insurerDashboard/:insurerLicense";
      }
    });
  }
  search() {
    console.log("search method");
    // this.router.navigateByUrl(`/search/${this.message}`, { skipLocationChange: true }).then(()=> {
    //   console.log("in search");
    //   this.router.navigate([`/search/${this.message}`]); 
    // })
    this.router.navigateByUrl('/login', { skipLocationChange: false }).then(() => {
      this.router.navigateByUrl(`/search/${this.message}`)
    })
  }
  logout() {
    this.token.signOut();
    window.location.reload();
    this.logout1 = true;
    this.router.navigate(["/home"]);
  }
  dashboard() {
    if (this.info.authorities == "ROLE_USER") {
      this.logout1 = false;
      return true;
    }
  }
  idashboard() {
    if (this.info.authorities == "ROLE_INSURER") {
      this.logout1 = false;
      return true;
    }
  }
  openDialog(): void {
    const dialogRef = this.dialog.open(CalculatorComponent, {
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
