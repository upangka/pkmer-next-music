package io.gitee.pkmer.music.application.song.get;

import io.gitee.pkmer.ddd.shared.command.Command;
import lombok.Value;

@Value(staticConstructor = "commandOf")
public class GetSongCmd implements Command {
     Long id;
}
