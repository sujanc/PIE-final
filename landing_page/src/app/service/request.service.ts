import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RequestService {
  private requestUrl = 'http://13.126.73.190:8092/porting/api/v1/';
  constructor(private http: HttpClient) {
   console.log('http service got called');
  }
  public postRequest(portingForm): any {
   console.log('HERE : ' + portingForm);
   const postedRequest = this.http.post(this.requestUrl + 'request',portingForm );
   console.log('hiii' + postedRequest);
   return postedRequest;
  }
  public getRequests(username): any {
   console.log( username);
   const requests = this.http.get(this.requestUrl + 'request/' + username);
   console.log(requests);
   return requests;
  }
  public deleteRequest(requestId): any {
   console.log(requestId);
   const deletedRequest = this.http.delete(this.requestUrl + 'request/' + requestId);
   return deletedRequest;
  
  }
  public updateRequest(requestId,form): any {
   console.log(requestId);
   const updatedRequest = this.http.put(this.requestUrl + 'request/' + requestId, form);
   return updatedRequest;
  
  }
}
