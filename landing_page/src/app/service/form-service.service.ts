import { Injectable } from '@angular/core';
import { FormFormat } from '../components/formformat';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class FormServiceService {
  allFormFormats: Observable<FormFormat[]>;

  //TODO: Remember to configure the AWS URL before pushing to git
  adminUrl = 'http://13.126.73.190:8092/admin/api/v1/';
  // adminUrl = 'http://13.126.73.190:8092/admin/api/v1/';
  getAllFormsUrl = 'formformats/';
  getFormFormatUrl = 'formformat/';
  saveFormUrl = 'formformat/';
  updateFormUrl = 'formformat/';
  deleteFormUrl = 'formformat/';
  searchUrl = 'formformat/search=';

  tempFormFormat: Observable<FormFormat>;
  constructor(private http: HttpClient) {}
  getAllFormFormats(): Observable<FormFormat[]> {
    return this.http.get<FormFormat[]>(this.adminUrl + this.getAllFormsUrl);
  }
  getFormFormat(formId: number): Observable<FormFormat> {
    return this.http.get<FormFormat>(
      this.adminUrl + this.getFormFormatUrl + formId
    );
  }
  saveForm(newFormFormat: FormFormat): Observable<FormFormat> {
    this.tempFormFormat = this.http.post<FormFormat>(
      this.adminUrl + this.saveFormUrl,
      newFormFormat
    );
    return this.tempFormFormat;
  }
  deleteForm(formId: number): Observable<FormFormat> {
    return this.http.delete<FormFormat>(
      this.adminUrl + this.deleteFormUrl + formId
    );
  }
  updateForm(formFormat: FormFormat): Observable<FormFormat> {
   this.tempFormFormat = this.http.put<FormFormat>(this.adminUrl + this.updateFormUrl + formFormat.formId, formFormat);
   this.getAllFormFormats();
   return this.tempFormFormat;
  }
  searchForForms(searchTerm: string): Observable<FormFormat[]> {
    return this.http.get<FormFormat[]>(this.adminUrl + this.searchUrl + searchTerm);
  }
}
