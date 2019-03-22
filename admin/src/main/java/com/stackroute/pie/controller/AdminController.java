package com.stackroute.pie.controller;

import com.stackroute.pie.domain.FormFormat;
import com.stackroute.pie.services.AdminServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@Controller
@RequestMapping("/api/v1/")
@CrossOrigin("*")
public class AdminController {

    private AdminServices adminServices;
    @Autowired
    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }
    @PostMapping("formformat")
    public ResponseEntity<FormFormat> addNewFormFormat(@RequestBody FormFormat formFormat) {
        FormFormat savedFormFormat = adminServices.addNewFormFormat(formFormat);
        return new ResponseEntity<>(savedFormFormat, HttpStatus.CREATED);
    }
    @GetMapping("formformats")
    public ResponseEntity<List<FormFormat>> getAllFormFormats() {
        List<FormFormat> listOfAllFormFormats = adminServices.getAllFormFormats();
        return new ResponseEntity<>(listOfAllFormFormats, HttpStatus.OK);
    }
    @GetMapping("formformat/{formId}")
    public ResponseEntity<FormFormat> getFormFormat(@PathVariable(value = "formId", required = true) int formId) {
        FormFormat retreivedFormFormat = adminServices.getFormFormat(formId);
        if(retreivedFormFormat == null)
            return new ResponseEntity<>(retreivedFormFormat, HttpStatus.OK);
        return new ResponseEntity<>(retreivedFormFormat, HttpStatus.OK);
    }
    @DeleteMapping("formformat/{formId}")
    public ResponseEntity<FormFormat> deleteFormFormat(@PathVariable(value = "formId", required = true) int formId) {
        FormFormat formFormat = adminServices.getFormFormat(formId);
        adminServices.deleteFormFormat(formId);
        return new ResponseEntity<>(formFormat, HttpStatus.OK);
    }
    @PutMapping("formformat/{formId}")
    public ResponseEntity<FormFormat> updateFormFormat(@PathVariable(value = "formId", required = true) int formId, @RequestBody FormFormat updatedFormFormat) {
        FormFormat newFormFormat = adminServices.updateFormFormat(formId, updatedFormFormat);
        return new ResponseEntity<>(newFormFormat, HttpStatus.OK);
    }
    @GetMapping("formformat/name={formName}")
    public ResponseEntity<FormFormat> getFormByName(@PathVariable(value = "formName", required = true) String formName) {
        FormFormat formFormat = adminServices.getFormFormatByName(formName);
        return new ResponseEntity<>(formFormat, HttpStatus.OK);
    }
    @GetMapping("formformat/search={formName}")
    public ResponseEntity<List<FormFormat>> searchFormByName(@PathVariable(value = "formName", required = true) String formName) {
        List<FormFormat> formFormat = adminServices.searchFormFormatByFormName(formName);
        return new ResponseEntity<>(formFormat, HttpStatus.OK);
    }
}
