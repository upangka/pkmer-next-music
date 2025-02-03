package io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis;

import io.gitee.pkmer.core.infrastructure.persistence.collect.CollectConverter;
import io.gitee.pkmer.music.domain.collect.CollectAggregate;
import io.gitee.pkmer.music.domain.collect.CollectId;
import io.gitee.pkmer.music.domain.collect.CollectRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
@Repository
public class CollectRepositoryImpl implements CollectRepository {

    private final CollectDynamicMapper collectDynamicMapper;
    private final CollectConverter collectConverter;
    public CollectRepositoryImpl(CollectDynamicMapper collectDynamicMapper,
                                 CollectConverter collectConverter){
        this.collectDynamicMapper = collectDynamicMapper;
        this.collectConverter = collectConverter;
    }

    @Override
    public Optional<CollectAggregate> load(CollectId collectId) {
        Optional<Collect> collectOptional = collectDynamicMapper.selectByPrimaryKey(collectId.value());
        if(collectOptional.isPresent()){
            Collect collect = collectOptional.get();
            CollectAggregate domainModel = collectConverter.toDomainModel(collect);
            return Optional.of(domainModel);
        }else{
            return Optional.empty();
        }

    }

    @Override
    public void save(CollectAggregate aggregateRoot) {
        switch (aggregateRoot.getChangingStatus()){
            case NEW -> insertAggregate(aggregateRoot);
            case UPDATED -> updateAggregate(aggregateRoot);
            case DELETED -> deleteAggregate(aggregateRoot);
            default -> throw new UnsupportedOperationException("不支持的操作");
        }
    }

    private void insertAggregate(CollectAggregate aggregateRoot) {
        Collect dataModel = this.collectConverter.toDataModel(aggregateRoot);
        collectDynamicMapper.insert(dataModel);
    }
    private void updateAggregate(CollectAggregate aggregateRoot) {

    }
    private void deleteAggregate(CollectAggregate aggregateRoot) {
        CollectId id = aggregateRoot.getId();
        collectDynamicMapper.deleteByPrimaryKey(id.value());
    }
}
