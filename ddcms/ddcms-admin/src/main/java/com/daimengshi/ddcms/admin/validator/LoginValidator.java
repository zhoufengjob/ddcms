package com.daimengshi.ddcms.admin.validator;

import com.daimengshi.ddcms.web.base.JsonValidator;
import com.jfinal.core.Controller;

/**
 * 登录校验
 * @author Rlax
 *
 */
public class LoginValidator extends JsonValidator {

    @Override
    protected void validate(Controller c) {
        validateString("username", 5, 16, "用户名格式不正确");
        validateString("password", 5, 12, "密码格式不正确");
        validateString("capval", 4, 4, "验证码格式不正确");
        validateCaptcha("capval", "验证码不正确");
    }
}
