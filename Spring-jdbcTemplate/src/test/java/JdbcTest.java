import com.jdbc.entity.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import sun.awt.AppContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JdbcTest {
    private ApplicationContext a = new ClassPathXmlApplicationContext("application.xml");
    private JdbcTemplate jdbcTemplate = a.getBean(JdbcTemplate.class);
    //支持具名参数的JdbcTemplate的对象
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate = a.getBean(NamedParameterJdbcTemplate.class);

    @Test
    public void test() throws SQLException {
        DataSource bean = a.getBean(DataSource.class);
        Connection connection = bean.getConnection();
        System.out.println(connection);
        connection.close();
        System.out.println(jdbcTemplate);
    }
    //利用jdbcTemplate将数据库student中的person表id为1的用户名字改为小李

    @Test
    //更新操作
    public void update(){
        String sql = "update person set name= ? where id=?";
        jdbcTemplate.update(sql,"小李",1);
        System.out.println("更新成功");
    }

    //批量插入
    @Test
    public void batchinsert(){
        String sql = "insert into person(name,age) values (?,?)";
        List<Object[]> objects =new ArrayList<Object[]>();
        objects.add(new Object[]{"小熊",12});
        objects.add(new Object[]{"小猫",12});
        objects.add(new Object[]{"小狗",12});
        objects.add(new Object[]{"小羊",12});
        jdbcTemplate.batchUpdate(sql,objects);
    }

    //查询一个用户，用对象封装
    @Test
    public void qurye(){
        String sql = "select * from person where id = ?";
        RowMapper<Person> tRowMapper = new BeanPropertyRowMapper<>(Person.class);//不要忘记制定映射类
        Person person = jdbcTemplate.queryForObject(sql, tRowMapper, 1);
        System.out.println(person);
    }

    //查询多个用户，返回对象列表
    @Test
    public void query(){
        String sql = "select * from person where age > ?";
        List<Person> people = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Person.class), 12);
        for (int i = 0; i < people.size(); i++) {
            Person person =  people.get(i);
            System.out.println(person);

        }
    }

    //查询最大年龄
    @Test
    public void maxage(){
        String sql = "select max(age) from person";
        Double aDouble = jdbcTemplate.queryForObject(sql, Double.class);//返回java内置类型
        System.out.println(aDouble);
    }

    //使用具名参数利用map插入数据库记录
    @Test
    public void cha(){
        String sql = "insert into person (name,age) values (:nname,:age)";
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("nname","李明");
        stringObjectHashMap.put("age",20);
        namedParameterJdbcTemplate.update(sql,stringObjectHashMap);

    }

    //使用具名参数，插入对象
    @Test
    public void charu(){
        String sql = "insert into person (name,age) values (:name,:age)";//这里的值要严格对应数据库字段
        Person person = new Person();
        person.setAge(15);
        person.setName("花花");
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(person);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);

    }
}
