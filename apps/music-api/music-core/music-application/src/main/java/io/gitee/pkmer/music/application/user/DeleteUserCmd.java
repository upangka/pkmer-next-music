package io.gitee.pkmer.music.application.user;

import io.gitee.pkmer.ddd.shared.command.Command;
import lombok.Getter;
import lombok.Value;
@Getter
@Value(staticConstructor = "commandOf")
public class DeleteUserCmd implements Command {
     Long id;
}
