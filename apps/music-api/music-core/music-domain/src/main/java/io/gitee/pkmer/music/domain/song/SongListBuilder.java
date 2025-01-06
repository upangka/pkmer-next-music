package io.gitee.pkmer.music.domain.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * SongList聚合构建工厂
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
public class SongListBuilder {

    private final StyleValidator styleValidator;

    private Long id;
    private String title;
    private String pic;
    private String styles;
    private String introduction;

     SongListBuilder(StyleValidator styleValidator){
        this.styleValidator = styleValidator;
    }


    public SongListBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public SongListBuilder title(String title) {
        this.title = title;
        return this;
    }

    public SongListBuilder pic(String pic) {
        this.pic = pic;
        return this;
    }

    public SongListBuilder styles(String styles) {
        this.styles = styles;
        return this;
    }

    public SongListBuilder introduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public SongListAggregate build() {
        List<Style> songListStyles = buildStyle();
        SongListId songListId = buildId();

        validate();

        return SongListAggregate.builder()
                .id(songListId)
                .pic(pic)
                .title(title)
                .styles(songListStyles)
                .introduction(introduction)
                .build();

    }

    private List<Style> buildStyle(){
        List<Style> container = new ArrayList<>();
        for(String style: styles.split("-")){
            container.add(Style.of(style));
        }
        return container;
    }

    private SongListId buildId(){
        return new SongListId(id);
    }

    private void validate(){
        styleValidator.validate(styles);
    }
}
