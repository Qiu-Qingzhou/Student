import com.shiwu.server.Bookserver;
import com.shiwu.server.Muserver;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ShuwuTest {
    private ApplicationContext a = new ClassPathXmlApplicationContext("application.xml");
    private JdbcTemplate jdbcTemplate = a.getBean(JdbcTemplate.class);
    //支持具名参数的JdbcTemplate的对象
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate = a.getBean(NamedParameterJdbcTemplate.class);

    @Test
    public void chuanbo(){
        //要用容器中的对象才可以，自己在方法中新创建的对象不行
        //Muserver muserver = new Muserver();
        Muserver muserver = a.getBean(Muserver.class);
        //muserver.dashiwu();
        System.out.println(muserver.getClass());//返回的是代理对象
    }
    @Test
    public void check()  {
        Bookserver bookserver = a.getBean(Bookserver.class);
        bookserver.checkout("bob",1);//用户名为bob的人，买了id为1的书
    }
    @Test
    public void inserts(){
        String sql1="insert into user (name,money) values (?,?)";
        List<Object[]> list1 = new ArrayList<>();
        list1.add(new Object[]{"bob", 10000});
        list1.add(new Object[]{"li", 10000});
        jdbcTemplate.batchUpdate(sql1, list1);

    }

    @Test
    public void insert2(){
        String sql1="insert into book (name,id,price) values (?,?,?)";
        List<Object[]> list1 = new ArrayList<>();
        list1.add(new Object[]{"book1", 1,100});
        list1.add(new Object[]{"book2", 2,100});
        list1.add(new Object[]{"book3", 3,100});
        list1.add(new Object[]{"book4", 4,100});
        jdbcTemplate.batchUpdate(sql1, list1);

    }

    @Test
    public void insert3(){
        String sql1="insert into book_stock (id,stock) values (?,?)";
        List<Object[]> list1 = new ArrayList<>();
        list1.add(new Object[]{1, 1000});
        list1.add(new Object[]{2, 1000});
        list1.add(new Object[]{3, 1000});
        list1.add(new Object[]{4, 1000});
        jdbcTemplate.batchUpdate(sql1, list1);

    }
}
