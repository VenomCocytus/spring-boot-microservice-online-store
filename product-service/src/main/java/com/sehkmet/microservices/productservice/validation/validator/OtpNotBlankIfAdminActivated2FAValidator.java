//package com.sehkmet.microservices.productservice.validation.validator;
//
//import com.sehkmet.microservices.productservice.repository.ProductRepository;
//import com.sehkmet.microservices.productservice.validation.annotation.OtpNotBlankIfAdminActivated2FA;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.lang.reflect.Field;
//
//import static com.sehkmet.utils.utils.Utils.translate;
//
//@RequiredArgsConstructor
//public class OtpNotBlankIfAdminActivated2FAValidator implements ConstraintValidator<OtpNotBlankIfAdminActivated2FA, Object> {
//
//    private final ProductRepository userRepository;
//
//    @Value("${max.otp.length}")
//    private int maxOtpLength;
//    @Override
//    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
//        if(object != null){
//            String username = null;
//            String otp = null;
//
//            // Retrieve required fields
//            Field[] fields = object.getClass().getDeclaredFields();
//            for (Field field : fields) {
//                field.setAccessible(true);
//                try {
//                    if (field.getName().equals("otp")) {
//                        otp = (String) field.get(object);
//                    }
//                    if (field.getName().equals("username")) {
//                        username = (String) field.get(object);
//                    }
//                } catch (IllegalAccessException e) {
//                    constraintValidatorContext.disableDefaultConstraintViolation();
//                    constraintValidatorContext.buildConstraintViolationWithTemplate(translate("constraint.violation.template.illegal-access-annotation-alert"))
//                            .addConstraintViolation();
//                    return false;
//                }
//            }
//
//            // Getting some user information ot throw exception
////            User userToValidate = this.userRepository.findByUsername(username);
////            if(userToValidate == null) {
////                constraintValidatorContext.disableDefaultConstraintViolation();
////                constraintValidatorContext.buildConstraintViolationWithTemplate(translate("constraint.violation.template.user-not-exists-with-username"))
////                        .addConstraintViolation();
////                return false;
////            }
////
////            boolean isOtpActivatedByUser = userToValidate.isTotp();
////            boolean isUserFirstConnection = userToValidate.isFirstConnexion();
////            boolean canUserAccessAdminPortal = userToValidate.isAdmin();
////
////            // Checking if the user can access the admin portal
////            if(!canUserAccessAdminPortal) {
////                constraintValidatorContext.disableDefaultConstraintViolation();
////                constraintValidatorContext.buildConstraintViolationWithTemplate(translate("constraint.violation.template.user-not-admin-alert"))
////                        .addConstraintViolation();
////                return false;
////            }
////
////            // Checking if the otp has been activated by the user
////            if(isOtpActivatedByUser || isUserFirstConnection) {
////
////                // Checking if the otp is present
////                if (otp == null || otp.trim().isEmpty()) {
////                    constraintValidatorContext.disableDefaultConstraintViolation();
////                    constraintValidatorContext.buildConstraintViolationWithTemplate(translate("constraint.violation.template.otp-required-annotation-alert"))
////                            .addConstraintViolation();
////                    return false;
////                }
////                if(otp.length() != maxOtpLength) {
////                    constraintValidatorContext.disableDefaultConstraintViolation();
////                    constraintValidatorContext.buildConstraintViolationWithTemplate(translate("default.otp-length-validation-alert"))
////                            .addConstraintViolation();
////                    return false;
////                }
////            }
////
////            // Checking if the otp must be present for the user
////            if(!isOtpActivatedByUser && otp != null) {
////                constraintValidatorContext.disableDefaultConstraintViolation();
////                constraintValidatorContext.buildConstraintViolationWithTemplate(translate("constraint.violation.template.otp-not-required-annotation-alert"))
////                        .addConstraintViolation();
////                return false;
////            }
//        }
//
//        return true;
//    }
//}
