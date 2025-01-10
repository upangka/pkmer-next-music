package io.gitee.pkmer.music.application.collect;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis.Collect;
import io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis.CollectDynamicMapper;
import io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis.CollectDynamicSqlSupport;
import io.gitee.pkmer.music.domain.enums.SongAndListType;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.where;

/**
 * DDD CQSR 查询分离
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/10
 * </p>
 */
@Service
public class CollectService {

    private final CollectDynamicMapper collectDynamicMapper;
    public CollectService(CollectDynamicMapper collectDynamicMapper){
        this.collectDynamicMapper = collectDynamicMapper;
    }

    public PageResponse<CollectView> pageQuery(CollectQuery query){

        WhereApplier whereApplier = where(CollectDynamicSqlSupport.userId, isEqualTo(query.getUserId())).toWhereApplier();

        List<Collect> collects = collectDynamicMapper.select(c -> c.applyWhere(whereApplier)
                .orderBy(CollectDynamicSqlSupport.createTime));

        long total = collectDynamicMapper.count(c -> c.applyWhere(whereApplier));


        return PageResponse.<CollectView>builder()
                .total((int)total)
                .totalPages((int)total / query.getPageSize() + 1)
                .currentPageNo(query.getPageNo())
                .list(toView(collects))
                .build();

    }


    private CollectView toView(Collect collect){
        String type = SongAndListType.valueOf(collect.getType()).getDesc();
        return CollectView.builder()
                .songListId(collect.getSongListId())
                .songId(collect.getSongId())
                .type(type)
                .createTime(collect.getCreateTime())
                .build();
    }

    private List<CollectView> toView(List<Collect> collects){
        return collects.stream().map(this::toView).toList();
    }


}

