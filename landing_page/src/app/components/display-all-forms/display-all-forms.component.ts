import { Component, OnInit } from "@angular/core";
import { FormFormat } from "../formformat";
import { FormServiceService } from "../../service/form-service.service";
import { Template } from '@angular/compiler/src/render3/r3_ast';

@Component({
  selector: "app-display-all-forms",
  templateUrl: "./display-all-forms.component.html",
  styleUrls: ["./display-all-forms.component.css"]
})
export class DisplayAllFormsComponent implements OnInit {
  allFormFormats: FormFormat[];
  currentFormFormat: FormFormat;

  showFormDetailsComponent = false;

  searchTerm: string;
  constructor(private formService: FormServiceService) {}

  ngOnInit() {
    this.getAllFormFormats();
    this.searchTerm = "";
    this.currentFormFormat = {formId: 1, formName: "Template", fields:[]};
  }
  getAllFormFormats(): void {
    this.formService
      .getAllFormFormats()
      .subscribe(allFormFormats => (this.allFormFormats = allFormFormats));
  }
  getAllFormFormatsOnEventClick(dullValue: any) {
    this.formService.getAllFormFormats().subscribe(allFormFormats => (this.allFormFormats = allFormFormats));
  }
  showFormDetails(formId: number): void {
    this.showFormDetailsComponent = true;
    this.formService
      .getFormFormat(formId)
      .subscribe(
        currentFormFormat => (this.currentFormFormat = currentFormFormat)
      );
  }
  deleteForm(formId: number): void {
    console.log('Delete Pressed');
    this.formService.deleteForm(formId).subscribe((data) =>  this.formService
      .getAllFormFormats()
      .subscribe(allFormFormats => (this.allFormFormats = allFormFormats)));
  }
  makeShowFormDetailsComponentFalse(): void {
    this.showFormDetailsComponent = false;
  }
  searchForForms(searchTerm: string): void {
    this.formService.searchForForms(searchTerm).subscribe((data) => (this.allFormFormats = data));
  }
  clearSearch(): void {
    this.formService.getAllFormFormats().subscribe((data) => this.allFormFormats = data);
  }
}
