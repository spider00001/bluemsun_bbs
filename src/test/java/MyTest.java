import com.bluemsun.entity.User;
import com.bluemsun.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserServiceImpl userServiceImpl = context.getBean("UserServiceImpl", UserServiceImpl.class);
        for (User user : userServiceImpl.queryAllUser()) {
            System.out.println(user);
        }

    }
}
