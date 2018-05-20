package cn.edu.jxnu.seckill.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import cn.edu.jxnu.seckill.util.ValidatorUtilJava;

/**
 * Java实现验证手机号
 * 
 * 待修改
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

	private boolean required = false;

	public void initialize(IsMobile constraintAnnotation) {
		required = constraintAnnotation.required();
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (required) {
			return ValidatorUtilJava.isMobile(value);
		} else {
			if (StringUtils.isEmpty(value)) {
				return true;
			} else {
				return ValidatorUtilJava.isMobile(value);
			}
		}
	}

}
