import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { variable } from '@angular/compiler/src/output/output_ast';

@Injectable({
    providedIn: 'root'
})
export class SearchService {

    trackName: string;
    visible: boolean;
    v: any;
    
    public baseUrl = 'http://13.126.73.190:8095/api/v1/';
    private messageSource = new BehaviorSubject('');
  currentMessage = this.messageSource.asObservable();
    constructor(private http: HttpClient) {
        console.log("http search service is called");
        this.visible = false;
    }

   
    changeMessage(message: string) {
        this.trackName = message;
        console.log('in service');
        this.messageSource.next(message);
      }
    public getSearch(searchValue):any{
        console.log("inside service");
        console.log("search url: "+ this.baseUrl + 'policies/' + searchValue);
        this.v = this.http.get(  this.baseUrl + 'policies/' + searchValue );
        console.log("search policy object "+this.v);

        return this.v;
    }
    show() {
        this.visible = true;
    }
    hide() {
        this.visible =false;
    }
}
