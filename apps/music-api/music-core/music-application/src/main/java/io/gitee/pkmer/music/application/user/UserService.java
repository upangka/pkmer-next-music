package io.gitee.pkmer.music.application.user;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.core.infrastructure.persistence.consumer.mybatis.Consumer;
import io.gitee.pkmer.core.infrastructure.persistence.consumer.mybatis.ConsumerDynamicMapper;
import io.gitee.pkmer.core.infrastructure.persistence.consumer.mybatis.ConsumerDynamicSqlSupport;
import io.gitee.pkmer.minio.props.PkmerMinioProps;
import io.gitee.pkmer.music.domain.user.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isLikeWhenPresent;
import static org.mybatis.dynamic.sql.SqlBuilder.where;
import static io.gitee.pkmer.core.infrastructure.persistence.consumer.mybatis.ConsumerDynamicSqlSupport.*;

@Service
public class UserService {

    private final String minioServer;
    private final ConsumerDynamicMapper consumerDynamicMapper;
    public UserService(PkmerMinioProps props,
                       ConsumerDynamicMapper consumerDynamicMapper){

        this.minioServer = props.getUrl();
        this.consumerDynamicMapper = consumerDynamicMapper;
    }

    public PageResponse<UserDetailView> pageQueryUser(UserQuery query){
        WhereApplier whereApplier = where(username, isLikeWhenPresent(query.getUsername()).map(s -> "%" + s + "%")).toWhereApplier();

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
        List<UserDetailView> list = Converter.INSTANCE.toView(users);

        list.forEach(this::handleAvatar);

        pageResponse
                .setList(list)
                .setTotal(total)
                .setTotalPages(total / query.getPageSize() + 1)
                .setCurrentPageNo(query.getPageNo());
        return pageResponse;
    }

    private void handleAvatar(UserDetailView user){
        user.setAvator(minioServer + user.getAvator());
    }

    @Mapper
    public interface Converter {

        Converter INSTANCE = Mappers.getMapper(Converter.class);

        @Mapping(source = "id", target = "id", qualifiedByName = "longToString")
        UserDetailView toView(Consumer source);
        List<UserDetailView> toView(List<Consumer> source);

        @Named("longToString")
        default String longToString(Long value) {
            return value != null ? value.toString() : null;
        }
    }
}
