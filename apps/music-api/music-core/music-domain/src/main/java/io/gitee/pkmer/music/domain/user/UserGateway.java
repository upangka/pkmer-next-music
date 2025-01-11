package io.gitee.pkmer.music.domain.user;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/11
 * </p>
 */
public interface UserGateway {

    /**
     * 加密密码
     */
    String encodePassword(String password);
}
