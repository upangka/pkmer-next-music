package io.gitee.pkmer.music.application.singer.query;

import io.gitee.pkmer.ddd.shared.command.Command;
import lombok.Value;

@Value(staticConstructor = "commandOf")
public class GetSingerDetailCmd implements Command {
    Long id;
}
