import { FormFormat } from './../components/formformat';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class FetchFormsService {

  // private adminUrl = 'http://localhost:8092/admin/api/v1/formformats';
  private adminUrl = 'http://13.126.73.190:8092/admin/api/v1/';
  private getAllFormFormatsUrl = 'formformats';
  private addNewFormFormatUrl = 'formformat';
  private deleteFormFormatUrl = 'formformat/';
  constructor(private http: HttpClient) { }
  getAllFormFormats(): Observable<FormFormat[]> {
    return this.http.get<FormFormat[]>(this.adminUrl + this.getAllFormFormatsUrl);
  }
  addNewFormFormat(): Observable<FormFormat> {
    return this.http.get<FormFormat>(this.adminUrl + this.addNewFormFormatUrl);
  }
  deleteFormFormat(formId): void {
    console.log(formId);
    console.log(this.adminUrl + this.deleteFormFormatUrl + formId);
    this.http.delete<FormFormat>(this.adminUrl + this.deleteFormFormatUrl + formId);
  }
}
