package com.stackroute.pie.services;

import com.stackroute.pie.domain.FormFormat;
import com.stackroute.pie.repository.AdminRepository;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServicesImpl implements AdminServices{
    private AdminRepository adminRepository;
    @Autowired
    public AdminServicesImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    public FormFormat addNewFormFormat(FormFormat formFormat) {
        return adminRepository.save(formFormat);

    }
    @Override
    public List<FormFormat> getAllFormFormats() {
        return adminRepository.findAll();
    }

    @Override
    public void deleteFormFormat(int formId) {
        adminRepository.deleteById(formId);
    }

    @Override
    public FormFormat getFormFormat(int formId) {
        if(!adminRepository.existsById(formId))
            return null;
        return adminRepository.findById(formId);
    }

    @Override
    public FormFormat updateFormFormat(int formId, FormFormat updatedFormFormat) {
        adminRepository.deleteById(formId);
        updatedFormFormat.setFormId(formId);
        return adminRepository.save(updatedFormFormat);
    }

    @Override
    public FormFormat getFormFormatByName(String formName) {
        return adminRepository.findByFormName(formName);
    }

    @Override
    public List<FormFormat> searchFormFormatByFormName(String formName) {
        List<FormFormat> fetchedFormFormats = adminRepository.findAll();
        List<FormFormat> filteredFormFormats = new ArrayList<>();
        List<FormFormat> exactSearchFormFormat = new ArrayList<>();
        for(FormFormat formFormat : fetchedFormFormats) {
            if(FuzzySearch.ratio(formName.toLowerCase(), formFormat.getFormName().toLowerCase()) == 100 ) {
                exactSearchFormFormat.add(formFormat);
                return exactSearchFormFormat;
            }
            if(FuzzySearch.ratio(formName.toLowerCase(), formFormat.getFormName().toLowerCase()) > 50) {
                filteredFormFormats.add(formFormat);
            }
        }
        return filteredFormFormats;
    }

}
