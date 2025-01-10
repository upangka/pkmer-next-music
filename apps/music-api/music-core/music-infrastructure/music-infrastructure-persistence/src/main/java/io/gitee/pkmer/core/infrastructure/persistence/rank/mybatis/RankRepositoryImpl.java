package io.gitee.pkmer.core.infrastructure.persistence.rank.mybatis;

import io.gitee.pkmer.core.infrastructure.persistence.rank.RankConverter;
import io.gitee.pkmer.music.domain.rank.RankAggregate;
import io.gitee.pkmer.music.domain.rank.RankId;
import io.gitee.pkmer.music.domain.rank.RankRepository;
import org.springframework.stereotype.Repository;

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
public class RankRepositoryImpl implements RankRepository {

    private final RankDynamicMapper rankDynamicMapper;

    private final RankConverter rankConverter;
    public RankRepositoryImpl(RankDynamicMapper rankDynamicMapper,
                              RankConverter rankConverter) {
        this.rankDynamicMapper = rankDynamicMapper;
        this.rankConverter = rankConverter;
    }


    @Override
    public RankAggregate load(RankId rankId) {
        RankList rankList = rankDynamicMapper.selectByPrimaryKey(rankId.value()).orElseThrow();
        return rankConverter.toDomainModel(rankList);
    }

    @Override
    public void save(RankAggregate aggregateRoot) {
        switch (aggregateRoot.getChangingStatus()){
            case NEW -> insertAggregate(aggregateRoot);
            case DELETED -> deleteAggregate(aggregateRoot);
            default -> throw new UnsupportedOperationException("不支持的操作");
        }
    }

    private void insertAggregate(RankAggregate aggregateRoot) {
        RankList dataModel = rankConverter.toDataModel(aggregateRoot);
        rankDynamicMapper.insert(dataModel);
    }


    private void deleteAggregate(RankAggregate aggregateRoot) {
        rankDynamicMapper.deleteByPrimaryKey(aggregateRoot.getId().value());
    }
}
