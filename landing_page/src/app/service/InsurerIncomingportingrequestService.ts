import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class InsurerIncomingportingrequestService {
        trackName: string;
        visible: boolean;
        private messageSource = new BehaviorSubject('');
      currentMessage = this.messageSource.asObservable();
        constructor(private http: HttpClient) {
            this.visible = false;
        }
    
        baseUrl = 'http://13.126.73.190:8092/porting/api/v1/';
        changeMessage(message: string) {
            this.trackName = message;
            console.log('in service');
            this.messageSource.next(message);
          }
        public getSearch(newInsurerName) {
            return this.http.get( this.baseUrl + 'incomingportingrequest/' + newInsurerName );
        }
        show() {
            this.visible = true;
        }
        hide() {
            this.visible =false;
        }
    }
