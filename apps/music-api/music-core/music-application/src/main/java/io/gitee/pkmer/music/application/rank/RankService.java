package io.gitee.pkmer.music.application.rank;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.core.infrastructure.persistence.rank.mybatis.RankDynamicMapper;
import io.gitee.pkmer.core.infrastructure.persistence.rank.mybatis.RankList;
import io.gitee.pkmer.core.infrastructure.persistence.rank.mybatis.RankListDynamicSqlSupport;
import io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.SongList;
import io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.SongListDynamicMapper;
import io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis.SongListDynamicSqlSupport;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * DDD CQSR 分离查询
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
public class RankService {
    // 构造方法注入RankDynamicMapper
    private final RankDynamicMapper rankDynamicMapper;
    private final SongListDynamicMapper songListDynamicMapper;

    public RankService(RankDynamicMapper rankDynamicMapper,
                       SongListDynamicMapper songListDynamicMapper) {
        this.rankDynamicMapper = rankDynamicMapper;
        this.songListDynamicMapper = songListDynamicMapper;
    }

    public PageResponse<RankView> pageQuery(RankQuery query){
        // orderby score 降序

        List<RankList> ranks = rankDynamicMapper.select(c -> c
                .orderBy(RankListDynamicSqlSupport.score.descending())
                .limit(query.getPageSize())
                .offset(query.offset())
        );

        List<RankView> views = new ArrayList<>();
        if(!ranks.isEmpty()){
            Map<Long, SongList> songListMap = handleSongList(ranks);
            views.addAll(toViews(ranks, songListMap));
        }

        long total = rankDynamicMapper.count(c -> c);

        return PageResponse.<RankView>builder()
                .total((int)total)
                .totalPages((int)total / query.getPageSize() + 1)
                .currentPageNo(query.getPageNo())
                .list(views)
                .build();

    }


    /**
     * 查询用户歌单评分
     */
    public RankView getUserSongListRank(Long userId,Long songListId){

        RankList rankList = rankDynamicMapper.selectOne(c -> c
                .where(RankListDynamicSqlSupport.consumerId, isEqualTo(userId))
                .and(RankListDynamicSqlSupport.songListId, isEqualTo(songListId))
                .limit(1)
        ).orElseThrow();


        Map<Long, SongList> songListMap = handleSongList(List.of(rankList));

        return toView(rankList, songListMap);
    }

    /**
     * 处理查询歌单
     */
    private Map<Long, SongList> handleSongList(List<RankList> ranks) {
        List<Long> songListIds = ranks.stream().map(RankList::getSongListId).toList();

        SelectStatementProvider selectStatementProvider = select(SongListDynamicSqlSupport.id, SongListDynamicSqlSupport.title)
                .from(SongListDynamicSqlSupport.songList)
                .where(SongListDynamicSqlSupport.id, isIn(songListIds))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        return songListDynamicMapper.selectMany(selectStatementProvider)
                .stream()
                .collect(Collectors.toMap(SongList::getId, songList -> songList));
    }

    /**
     * 转化为视图层
     */
    private RankView toView(RankList rankList,Map<Long, SongList> songListMap){
        Long songListId = rankList.getSongListId();
        SongList songList = songListMap.get(songListId);
        // builder构建RankView
        return RankView.builder()
                .id(rankList.getId())
                .songListId(songListId)
                .songListName(songList.getTitle())
                .score(rankList.getScore())
                .build();
    }

    /**
     * 转化为视图层
     */
    private List<RankView> toViews(List<RankList> rankLists,Map<Long, SongList> songListMap){
        return rankLists.stream()
                .map(rankList -> toView(rankList, songListMap))
                .toList();
    }
}
