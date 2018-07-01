package cn.edu.jxnu.seckill.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import cn.edu.jxnu.seckill.validator.IsMobile;
import lombok.Data;

@Data
public class LoginVO {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;

    @Override
    public String toString() {
        return "LoginVO{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
