import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-insurer-dashboard',
  templateUrl: './insurer-dashboard.component.html',
  styleUrls: ['./insurer-dashboard.component.css']
})
export class InsurerDashboardComponent implements OnInit {
  insurerLicense: any;
  insurerName:any;
  
  constructor(private route: ActivatedRoute,private router: Router) { }

  ngOnInit() {
    this.insurerLicense = this.route.snapshot.paramMap.get('insurerLicense');
    let company = window.localStorage.setItem("insurername",this.insurerLicense);
  
    this.router.navigate(['myCompanyPolicy',this.insurerLicense]);
  }

}
