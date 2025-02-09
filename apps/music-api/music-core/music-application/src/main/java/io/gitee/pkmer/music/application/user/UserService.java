package io.gitee.pkmer.music.application.user;

import io.gitee.common.view.TotalView;
import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.core.infrastructure.persistence.consumer.mybatis.Consumer;
import io.gitee.pkmer.core.infrastructure.persistence.consumer.mybatis.ConsumerDynamicMapper;
import io.gitee.pkmer.minio.props.PkmerMinioProps;
import io.gitee.pkmer.music.domain.singer.Sex;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static io.gitee.pkmer.core.infrastructure.persistence.consumer.mybatis.ConsumerDynamicSqlSupport.username;
import static org.mybatis.dynamic.sql.SqlBuilder.isLikeWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.where;

@Service
public class UserService {

    private final String minioServer;
    private final ConsumerDynamicMapper consumerDynamicMapper;
    public UserService(PkmerMinioProps props,
                       ConsumerDynamicMapper consumerDynamicMapper){

        this.minioServer = props.getUrl();
        this.consumerDynamicMapper = consumerDynamicMapper;
    }

    /**
     * 分页查询用户信息
     * @param query
     * @return
     */
    public PageResponse<UserDetailView> pageQueryUser(UserQuery query){
        WhereApplier whereApplier = buildWhereApplier(query);

        List<Consumer> users = consumerDynamicMapper.select(c ->
                c.applyWhere(whereApplier)
                        .configureStatement(s -> s.setNonRenderingWhereClauseAllowed(true))
                        .limit(query.getPageSize())
                        .offset(query.offset()));



        int total = (int)consumerDynamicMapper.count(c -> c
                .applyWhere(whereApplier)
                .configureStatement(s -> s.setNonRenderingWhereClauseAllowed(true))
        );

        PageResponse<UserDetailView> pageResponse = new PageResponse<>();
        List<UserDetailView> list = toView(users);

        pageResponse
                .setList(list)
                .setTotal(total)
                .setTotalPages(total / query.getPageSize() + 1)
                .setCurrentPageNo(query.getPageNo());
        return pageResponse;
    }


    /**
     * 获取总数，用于服务端渲染分页组件
     * @param query
     * @return
     */
    public TotalView getTotal(UserQuery query){
        WhereApplier whereApplier = buildWhereApplier(query);
        int total = (int)consumerDynamicMapper.count(c -> c
                .applyWhere(whereApplier)
                .configureStatement(s -> s.setNonRenderingWhereClauseAllowed(true))
        );

        return TotalView.builder()
                .total(total)
                .totalPages(total / query.getPageSize() + 1)
                .build();
    }

    private WhereApplier buildWhereApplier(UserQuery query){
        return  where(username, isLikeWhenPresent(query.getUsername()).map(s -> "%" + s + "%")).toWhereApplier();
    }



    private String addMinioServer(String path){
        return minioServer + path;
    }

    public UserDetailView toView(Consumer source) {
        if ( source == null ) {
            return null;
        }

        UserDetailView.UserDetailViewBuilder userDetailView = UserDetailView.builder();
        Sex sex = Sex.valueOf(source.getSex());
        userDetailView.id( source.getId().toString());
        userDetailView.username( source.getUsername() );
        userDetailView.sex( sex.name() );
        userDetailView.phoneNum( source.getPhoneNum() );
        userDetailView.email( source.getEmail() );
        userDetailView.birth( source.getBirth().toLocalDate() );
        userDetailView.introduction( source.getIntroduction() );
        userDetailView.location( source.getLocation() );
        userDetailView.avator( addMinioServer(source.getAvator()));
        userDetailView.createTime( source.getCreateTime() );
        userDetailView.updateTime( source.getUpdateTime() );

        return userDetailView.build();
    }

    private List<UserDetailView> toView(List<Consumer> source) {
        if ( source == null ) {
            return null;
        }

        List<UserDetailView> list = new ArrayList<UserDetailView>( source.size() );
        for ( Consumer consumer : source ) {
            list.add( toView( consumer ) );
        }

        return list;
    }
}
