package io.gitee.pkmer.music.application.songlist.get;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.song.SongListAggregate;
import io.gitee.pkmer.music.domain.song.SongListRepository;
import io.gitee.pkmer.music.domain.song.Style;
import io.gitee.pkmer.music.domain.song.page.SongListPage;
import io.gitee.pkmer.music.domain.song.page.SongListPageBuilderFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/7
 * </p>
 */
@Component
public class GetAllSongListHandler implements CommandHandler<SongListPageQueryCmd,
        PageResponse<SongListDto>> {

    private final SongListRepository repository;
    private final SongListPageBuilderFactory songListPageBuilderFactory;
    public GetAllSongListHandler(SongListRepository repository, SongListPageBuilderFactory songListPageBuilderFactory){
        this.repository = repository;
        this.songListPageBuilderFactory = songListPageBuilderFactory;
    }

    @Override
    public PageResponse<SongListDto> execute(SongListPageQueryCmd cmd) {
        SongListPage songListPage = buildSongListPage(cmd);
        PageResponse<SongListAggregate> page= repository.pageLoad(songListPage);
        List<SongListDto> list = page.getList().stream().map(this::buildSongListDto).toList();

        PageResponse<SongListDto> pageResults = new PageResponse<>();
        pageResults.setList(list)
                .setTotal(page.getTotal())
                .setTotalPages(page.getTotalPages())
                .setCurrentPageNo(page.getCurrentPageNo());
        return pageResults;
    }
    private SongListPage buildSongListPage(SongListPageQueryCmd cmd){
        return songListPageBuilderFactory.createBuilder()
                .setPageNo(cmd.getPageNo())
                .setPageSize(cmd.getPageSize())
                .setStyle(cmd.getStyle())
                .build();
    }


    private SongListDto buildSongListDto(SongListAggregate root){

        Long id = root.getId().value();
        List<String> styles = root.getStyles().stream().map(Style::getDesc).toList();

        return SongListDto.builder()
                .id(id.toString())
                .styles(styles)
                .title(root.getTitle())
                .pic(root.getPic())
                .introduction(root.getIntroduction())
                .build();
    }
}
