import com.aop.Math;
import com.aop.Mathimp;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MathTest {
    private ApplicationContext a = new ClassPathXmlApplicationContext("AOP.xml");
    @Test
    public void test(){

        Mathimp math = a.getBean(Mathimp.class);
        math.add(1,2);
        math.chu(1,1);//正常调用
        //math.chu(1,0);异常调用

    }
}
