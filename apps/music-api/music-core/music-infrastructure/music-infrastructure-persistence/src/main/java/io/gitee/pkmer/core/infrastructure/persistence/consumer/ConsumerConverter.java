package io.gitee.pkmer.core.infrastructure.persistence.consumer;

import io.gitee.pkmer.core.infrastructure.persistence.consumer.mybatis.Consumer;
import io.gitee.pkmer.music.domain.singer.Sex;
import io.gitee.pkmer.music.domain.user.UserAggregate;
import io.gitee.pkmer.music.domain.user.UserId;
import org.springframework.stereotype.Component;

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
@Component
public class ConsumerConverter {
    /**
     * 数据模型转换成领域对象
     */
    public UserAggregate toDomainModel(Consumer consumer){
        UserAggregate userAggregate = UserAggregate.builder()
                .id(new UserId(consumer.getId()))
                .username(consumer.getUsername())
                .password(consumer.getPassword())
                .sex(Sex.valueOf(consumer.getSex()))
                .phoneNum(consumer.getPhoneNum())
                .email(consumer.getEmail())
                .birth(consumer.getBirth())
                .introduction(consumer.getIntroduction())
                .location(consumer.getLocation())
                .avator(consumer.getAvator())
                .build();
        userAggregate.setCreateTime(consumer.getCreateTime());
        return userAggregate;

    }

    /**
     * 领域对象转换成数据模型
     */
    public Consumer toDataModel(UserAggregate userAggregate){
       // setter方法生成数据模型
        Consumer consumer = new Consumer();
        consumer.setId(userAggregate.getId().value());
        consumer.setUsername(userAggregate.getUsername());
        consumer.setPassword(userAggregate.getPassword());
        consumer.setSex(userAggregate.getSex().getValue());
        consumer.setPhoneNum(userAggregate.getPhoneNum());
        consumer.setEmail(userAggregate.getEmail());
        consumer.setBirth(userAggregate.getBirth());
        consumer.setIntroduction(userAggregate.getIntroduction());
        consumer.setLocation(userAggregate.getLocation());
        consumer.setAvator(userAggregate.getAvator());
        consumer.setCreateTime(userAggregate.getCreateTime());
        return consumer;
    }
}
