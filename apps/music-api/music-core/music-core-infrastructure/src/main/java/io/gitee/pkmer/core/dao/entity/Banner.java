package io.gitee.pkmer.core.dao.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author pkmer
 * @since 2025-01-02
 */
@Data
@TableName("banner")
public class Banner {

    @TableId(value="id",type = IdType.ASSIGN_ID)
    private Long id;

    private String pic;
}
