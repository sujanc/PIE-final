import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormFormat } from '../formformat';
import { FormServiceService } from '../../service/form-service.service';
import { DisplayAllFormsComponent } from '../display-all-forms/display-all-forms.component';

@Component({
  selector: 'app-add-new-form',
  templateUrl: './add-new-form.component.html',
  styleUrls: ['./add-new-form.component.css']
})
export class AddNewFormComponent implements OnInit {

  displayNewFormClicked = false;
  private newForm: FormFormat;
  newFormName: string;
  private newField: string;
  newFormId = 0;

  @Input() allFormFormats: FormFormat[];
  @Output() newFormCreated = new EventEmitter<FormFormat>();

  constructor(private formService: FormServiceService) { this.formService = formService;}

  ngOnInit() {
    this.displayNewFormClicked = false;
    this.newForm = {"formId": 1, "formName": "Temp", "fields": []};
    this.newForm.formName = "temp";
    this.newForm.formId = -1;
  }

  createNewForm(): void {
    this.displayNewFormClicked = true;
    this.newForm = {"formId": 1, "formName": "Temp", "fields": []};
  }
  addNewField(): void {
    this.newForm.fields.push(this.newField);
    this.newField = null;
  }
  saveForm(): void {

    for(let formFormat of this.allFormFormats) {
      console.log(formFormat.formId);
      if(formFormat.formId > this.newFormId)
        this.newFormId = formFormat.formId;
    }
    this.newForm.formName = this.newFormName;
    this.newForm.formId = this.newFormId + 1;
    this.formService.saveForm(this.newForm).subscribe();
    this.newFormCreated.emit(null);
    this.displayNewFormClicked = false;
    this.newForm.formName = null;
    this.newForm.formId = null;
    this.newFormName = null;
    this.newFormId = null;
    this.newField = null;
  }
}
