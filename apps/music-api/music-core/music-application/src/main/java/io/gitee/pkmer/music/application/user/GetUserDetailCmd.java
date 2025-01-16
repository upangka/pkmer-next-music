package io.gitee.pkmer.music.application.user;

import io.gitee.pkmer.ddd.shared.command.Command;
import lombok.Data;

@Data
public class GetUserDetailCmd implements Command {
    private Long userId;
}
