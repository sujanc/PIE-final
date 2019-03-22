import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class InsurerAcceptoutgoingportingrequestService {

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
      console.log('IN accpeting Outgoing PR service');
      this.messageSource.next(message);
    }
  public getSearch(request) {
      return this.http.put( this.baseUrl + 'acceptoutgoingportingrequest', request );
  }
  show() {
      this.visible = true;
  }
  hide() {
      this.visible =false;
  }
}
