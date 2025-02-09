package io.gitee.pkmer.music.application.singer.query;

import io.gitee.common.view.TotalView;
import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis.Singer;
import io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis.SingerDynamicMapper;
import io.gitee.pkmer.minio.props.PkmerMinioProps;
import io.gitee.pkmer.music.domain.singer.Sex;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis.SingerDynamicSqlSupport.name;
import static io.gitee.pkmer.core.infrastructure.persistence.singer.mybatis.SingerDynamicSqlSupport.sex;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

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
@Service
public class SingerService {

    private final SingerDynamicMapper singerDynamicMapper;
    private final String minioServer;
    public SingerService(SingerDynamicMapper singerDynamicMapper, PkmerMinioProps props){
        this.singerDynamicMapper = singerDynamicMapper;
        this.minioServer = props.getUrl();
    }


    public PageResponse<SingerView> query(SingerQuery query){
        WhereApplier whereApplier = buildWhereApplier(query);

        List<Singer> singers = singerDynamicMapper.select(c ->
                c.applyWhere(whereApplier)
                        .configureStatement(s -> s.setNonRenderingWhereClauseAllowed(true))
                        .limit(query.getPageSize())
                        .offset(query.offset()));


        long total = singerDynamicMapper.count(c -> c
                .applyWhere(whereApplier)
                .configureStatement(s -> s.setNonRenderingWhereClauseAllowed(true))
        );

        List<SingerView> list = singers.stream().map(this::toView).toList();
        return handleResults((int) total, list, query);
    }

    public TotalView getPageTotal(SingerQuery query){
        WhereApplier whereApplier = buildWhereApplier(query);
        int total = (int)singerDynamicMapper.count(c -> c
                .applyWhere(whereApplier)
                .configureStatement(s -> s.setNonRenderingWhereClauseAllowed(true))
        );

        return TotalView.builder()
                .total(total)
                .totalPages(total / query.getPageSize() + 1)
                .build();
    }

    private WhereApplier buildWhereApplier(SingerQuery query){
        Byte sexValue = handleSexValue(query.getSex());

       return where(name, isLikeWhenPresent(query.getName()).map(s -> "%" + s + "%"))
                .and(sex, isEqualToWhenPresent(sexValue)).toWhereApplier();
    }

    private PageResponse<SingerView> handleResults(int total,
                                                   List<SingerView> list,
                                                   SingerQuery query){
        return PageResponse.<SingerView>builder()
                .total(total)
                .totalPages((total / query.getPageSize() + 1))
                .currentPageNo(query.getPageNo())
                .list(list)
                .build();
    }

    private Byte handleSexValue(String sexStr){
        Byte sexValue = null;
        if(sexStr != null && !sexStr.trim().isEmpty()){
            sexValue = Sex.valueOf(sexStr).getValue();
        }
        return sexValue;
    }

    private SingerView toView(Singer singer){
        SingerView view = new SingerView();
        view.setId(singer.getId().toString());
        view.setName(singer.getName());
        view.setSex(Sex.valueOf(singer.getSex()).name());
        view.setPic(addMinioServer(singer.getPic()));
        view.setBirth(singer.getBirth());
        view.setLocation(singer.getLocation());
        view.setIntroduction(singer.getIntroduction());
        return view;
   }

   private String addMinioServer(String url){
        return minioServer + url;
   }
}
