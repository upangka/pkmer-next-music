package io.gitee.pkmer.music.application.singer.query;

import io.gitee.pkmer.convention.page.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/10
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Value(staticConstructor = "commandOf")
public class SingerQuery extends PageQuery {
     String name;
     String sex;
}
