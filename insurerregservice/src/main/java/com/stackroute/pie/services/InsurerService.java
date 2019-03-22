package com.stackroute.pie.services;

import com.stackroute.pie.domain.Insurer;
import com.stackroute.pie.domain.PremiumCalci;
import com.stackroute.pie.message.request.SignUpForm;

public interface InsurerService {
    //Method to add insurer to the Database
    Insurer addInsurer(SignUpForm signUpForm);
    //Method to calculate the premium based on the POJO
    long calculatePremium(PremiumCalci premiumCalci);
}
