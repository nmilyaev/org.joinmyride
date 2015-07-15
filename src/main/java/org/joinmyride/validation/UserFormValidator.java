package org.joinmyride.validation;

/**
 * Created by nmilyaev on 7/14/15.
 */

import org.joinmyride.model.User;
import org.joinmyride.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class UserFormValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "userForm.name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "userForm.password.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "userForm.confirmPassword.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "userForm.email.empty");

        if (!errors.hasFieldErrors("confirmPassword")) {
            if (!user.getPassword().equals(user.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "userForm.confirmPassword.diff");
            }
        }
    }

    /**
     * Use this method to additionally set errors depending on processing elsewhere
     * A good example - unique email violation occurring during the commit.
     * @param target - name of the error target
     * @param errors - errors of this Validator
     */
    public void setValidationError(String target, Errors errors){
        errors.rejectValue(target, "userForm.email.nonUnique");
    }
}