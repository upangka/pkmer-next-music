package io.gitee.pkmer.core.infrastructure.persistence.consumer.mybatis;

import io.gitee.pkmer.core.infrastructure.persistence.consumer.ConsumerConverter;
import io.gitee.pkmer.music.domain.user.UserAggregate;
import io.gitee.pkmer.music.domain.user.UserId;
import io.gitee.pkmer.music.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/11
 * </p>
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final ConsumerDynamicMapper consumerDynamicMapper;
    private final ConsumerConverter consumerConverter;
    public UserRepositoryImpl(ConsumerDynamicMapper consumerDynamicMapper,
                              ConsumerConverter consumerConverter){
        this.consumerDynamicMapper = consumerDynamicMapper;
        this.consumerConverter = consumerConverter;
    }

    @Override
    public UserAggregate load(UserId userId) {

        Consumer consumer = consumerDynamicMapper
                .selectByPrimaryKey(userId.value())
                .orElseThrow();
        return consumerConverter.toDomainModel(consumer);
    }

    @Override
    public void save(UserAggregate aggregateRoot) {
        switch (aggregateRoot.getChangingStatus()){
            case NEW -> insertAggregate(aggregateRoot);
            case UPDATED -> updateAggregate(aggregateRoot);
            case DELETED -> deleteAggregate(aggregateRoot);
            default -> throw new UnsupportedOperationException("不支持的操作");
        }
    }

    private void insertAggregate(UserAggregate aggregateRoot){
        Consumer dataModel = consumerConverter.toDataModel(aggregateRoot);
        consumerDynamicMapper.insert(dataModel);
    }
    private void updateAggregate(UserAggregate aggregateRoot){
        Consumer dataModel = consumerConverter.toDataModel(aggregateRoot);
        consumerDynamicMapper.updateByPrimaryKeySelective(dataModel);
    }
    private void deleteAggregate(UserAggregate aggregateRoot){
        consumerDynamicMapper.deleteByPrimaryKey(aggregateRoot.getId().value());
    }

}
