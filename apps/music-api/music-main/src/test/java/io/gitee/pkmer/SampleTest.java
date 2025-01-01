package io.gitee.pkmer;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import io.gitee.pkmer.entity.MybatisPlusTestUser;
import io.gitee.pkmer.mapper.MybatisPlusTestUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/1
 */
@SpringBootTest
public class SampleTest {
    @Autowired
    MybatisPlusTestUserMapper mapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<MybatisPlusTestUser> userList = mapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "");
        userList.forEach(System.out::println);
    }
}
