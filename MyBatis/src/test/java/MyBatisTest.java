import com.mybatis.dao.Userannotion;
import com.mybatis.dao.Userdao;
import com.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class MyBatisTest {
    //统一一个工厂,连接要每次获取
    private SqlSessionFactory sqlSessionFactory;

    @Before//执行每个测试前先运行它
    public void init () throws IOException {
        //获取SqlSessionFactory对象
        String resource = "config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    //使用注解类进行查询
    @Test
    public void aootion(){
        SqlSession session = sqlSessionFactory.openSession();
        Userannotion userdao = session.getMapper(Userannotion.class);//使用接口,不是实体类
        User getuserbyid = userdao.getuserbyid(1);
        System.out.println(getuserbyid);
        session.close();
    }

    //一定要记住要手动提交
    //查
    @Test
    public void cha()  {
        //获得数据库连接
        SqlSession session = sqlSessionFactory.openSession();
        Userdao userdao = session.getMapper(Userdao.class);//使用接口,不是实体类
        User getuserbyid = userdao.getuserbyid(1);
        System.out.println(getuserbyid);
        //使用两个参数进行查询,
        // public User getuserbyidandname(@Param("id") Integer id, @Param("name") String name);
        User user = userdao.getuserbyidandname(6, "小东");
        System.out.println(user);
        session.close();
    }

    //改
    @Test
    public void gai()  {
        //获得数据库连接
        SqlSession session = sqlSessionFactory.openSession();
        Userdao userdao = session.getMapper(Userdao.class);//使用接口,不是实体类
        User user = new User();
        user.setAge(12);
        user.setName("小下");
        user.setId(1);
        System.out.println(userdao.update(user));
        session.commit();
        session.close();
    }

    //删
    @Test
    public void shan()  {
        //获得数据库连接
        SqlSession session = sqlSessionFactory.openSession();
        Userdao userdao = session.getMapper(Userdao.class);//使用接口,不是实体类
        System.out.println(userdao.delete(2));
        session.commit();
        session.close();
    }

    //增
    @Test
    public void zeng()  {
        //获得数据库连接
        SqlSession session = sqlSessionFactory.openSession();
        Userdao userdao = session.getMapper(Userdao.class);//使用接口,不是实体类
        User user = new User();
        user.setName("小人");
        user.setAge(14);
        System.out.println(userdao.insert(user));

        //1.插入之后,获取对象在数据库中的自增主键id
        System.out.println(user.getId());
        session.commit();
        session.close();
    }

    @Test
    //2
    public void zeng2()  {
        //获得数据库连接
        SqlSession session = sqlSessionFactory.openSession();
        Userdao userdao = session.getMapper(Userdao.class);//使用接口,不是实体类
        User user = new User();
        user.setName("非自增主键");
        user.setAge(14);
        user.setId(null);
        System.out.println(userdao.insert2(user));
        //插入之后,获取对象在数据库中的非自增主键id
        System.out.println(user.getId());
        session.commit();
        session.close();
    }

    //查,返回一个map
    @Test
    public void chaMap()  {
        //获得数据库连接
        SqlSession session = sqlSessionFactory.openSession();
        Userdao userdao = session.getMapper(Userdao.class);//使用接口,不是实体类
        Map<Integer, User> integerUserMap = userdao.getuserMap();
        System.out.println(integerUserMap);
        System.out.println(integerUserMap.get(1).getName());
        session.close();
    }


}
