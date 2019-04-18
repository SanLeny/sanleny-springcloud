import cn.sanleny.user.sample.UserSampleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: sanleny
 * @Date: 2019-04-17
 * @Description: PACKAGE_NAME
 * @Version: 1.0
 */
@SpringBootTest(classes = {UserSampleApplication.class})
@RunWith(SpringRunner.class)
public class SpringRedisTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void testRedis() throws Exception {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("name", "enjoy");
        String value = ops.get("name");
        System.out.println(value);
    }
}
