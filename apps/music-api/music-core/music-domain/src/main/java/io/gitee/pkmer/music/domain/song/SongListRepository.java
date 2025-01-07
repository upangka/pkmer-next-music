package io.gitee.pkmer.music.domain.song;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.ddd.shared.Repository;
import io.gitee.pkmer.music.domain.song.page.SongListPage;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
public interface SongListRepository extends Repository<SongListAggregate,SongListId> {

    PageResponse<SongListAggregate> pageLoad(SongListPage songListPage);
}
