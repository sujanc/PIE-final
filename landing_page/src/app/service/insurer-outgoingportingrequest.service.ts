import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class InsurerOutgoingportingrequestService {
    trackName: string;
    visible: boolean;
    private messageSource = new BehaviorSubject('');
  currentMessage = this.messageSource.asObservable();
    constructor(private http: HttpClient) {
        this.visible = false;
    }

    // baseUrl = 'http://localhost:8092/porting/api/v1/';
    baseUrl = 'http://13.126.73.190:8092/porting/api/v1/';
    changeMessage(message: string) {
        this.trackName = message;
        console.log('in service');
        this.messageSource.next(message);
      }
    public getSearch(insurerName) {
        return this.http.get( this.baseUrl + 'outgoingportingrequest/' + insurerName );
    }
    show() {
        this.visible = true;
    }
    hide() {
        this.visible =false;
    }
}
