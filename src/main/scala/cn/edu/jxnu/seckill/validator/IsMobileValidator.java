package cn.edu.jxnu.seckill.validator;

import cn.edu.jxnu.seckill.util.ValidatorUtilScala;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Java实现验证手机号
 * 
 * 待修改
 * 
 * 有时存在一个编译问题，运行正常
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
			return new ValidatorUtilScala().isMobile(value);
		} else {
			if (StringUtils.isEmpty(value)) {
				return true;
			} else {
				return new ValidatorUtilScala().isMobile(value);
			}
		}
	}

}
