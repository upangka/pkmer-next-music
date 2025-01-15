package io.gitee.pkmer.music.domain.user;


import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * 用户输入验证器
 * <p>
 * 负责验证邮箱和密码是否符合格式要求
 * </p>
 */
@Component
public class UserValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    private static final String EMAIL_ERROR_MESSAGE = "Email format is invalid";
    private static final String PASSWORD_ERROR_MESSAGE = "Password must be at least 8 characters long and include at least one number, one uppercase letter, one lowercase letter, and one special character";

    /**
     * 验证用户凭证
     * 该方法用于验证用户输入的电子邮件和密码是否符合规范
     *
     * @param email 用户输入的电子邮件字符串
     * @param rawPassword 用户输入的原始密码字符串
     */
    public void validate(String email, String rawPassword) {
        validateInput(email, EMAIL_PATTERN, EMAIL_ERROR_MESSAGE, "Email");
        validateInput(rawPassword, PASSWORD_PATTERN, PASSWORD_ERROR_MESSAGE, "Password");
    }

    /**
     * 验证输入字符串是否符合给定的正则表达式模式
     *
     * @param input 要验证的输入字符串
     * @param pattern 正则表达式模式，用于验证输入字符串的格式
     * @param errorMessage 当输入字符串不符合正则表达式模式时抛出的异常信息
     * @param fieldName 字段名称，用于在输入为空时抛出的异常信息中指明字段
     * @throws IllegalArgumentException 如果输入为空或不符合正则表达式模式，则抛出此异常
     */
    private void validateInput(String input, Pattern pattern, String errorMessage, String fieldName) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        if (!pattern.matcher(input).matches()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
