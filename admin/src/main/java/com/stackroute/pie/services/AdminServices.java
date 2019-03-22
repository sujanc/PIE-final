package com.stackroute.pie.services;

import com.stackroute.pie.domain.FormFormat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminServices {
    FormFormat addNewFormFormat(FormFormat formFormat);
    List<FormFormat> getAllFormFormats();
    void deleteFormFormat(int formId);
    FormFormat getFormFormat(int formId);
    FormFormat updateFormFormat(int formId, FormFormat updatedFormFormat);
    FormFormat getFormFormatByName(String formName);
    List<FormFormat> searchFormFormatByFormName(String formName);
}
