package com.sehkmet.microservices.productservice.validation.annotation;

import com.sehkmet.microservices.productservice.validation.validator.OtpNotBlankIfAdminActivated2FAValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {OtpNotBlankIfAdminActivated2FAValidator.class})
public @interface OtpNotBlankIfAdminActivated2FA {
    String message() default "{default.otp-required-for-user}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
