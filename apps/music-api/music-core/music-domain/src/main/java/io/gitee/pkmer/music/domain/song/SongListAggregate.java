package io.gitee.pkmer.music.domain.song;

import io.gitee.pkmer.ddd.common.AuditableEntity;
import io.gitee.pkmer.ddd.shared.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@Getter
@Builder
@AllArgsConstructor
public class SongListAggregate extends AuditableEntity implements AggregateRoot {
    private SongListId id;

    private String title;

    private String pic;

    @Builder.Default
    private List<Style> styles = Collections.emptyList();

    private String introduction;
}