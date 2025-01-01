package io.gitee.pkmer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * At 2025/1/1
 */
@Data
@TableName("`mybatis_plus_test_user`")
public class MybatisPlusTestUser {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
