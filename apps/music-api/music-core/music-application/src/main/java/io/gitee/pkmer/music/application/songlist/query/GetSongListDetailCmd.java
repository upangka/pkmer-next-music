package io.gitee.pkmer.music.application.songlist.query;

import io.gitee.pkmer.ddd.shared.command.Command;
import lombok.Data;
import lombok.Value;

@Data
@Value(staticConstructor = "commandOf")
public class GetSongListDetailCmd implements Command {
    Long songListId;
}
