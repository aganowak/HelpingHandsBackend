package com.codecool.helpinghands.validator.registrationValidators;

import com.codecool.helpinghands.dto.RegistrationDTO;
import com.codecool.helpinghands.validator.AbstractValidatorFacade;
import com.codecool.helpinghands.validator.ValidatorChain;
import com.codecool.helpinghands.validator.WrongInputException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegistrationValidatorFacade extends AbstractValidatorFacade <RegistrationDTO, WrongInputException> {

    public RegistrationValidatorFacade(List<ValidatorChain<RegistrationDTO, WrongInputException>> validators) {
        super(validators);
    }
}
