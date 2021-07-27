package org.example;

import cn.seeu.mapper.WordMapper;
import cn.seeu.entry.Word;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MybatisTest {

    @Test
    public void test1() throws IOException {
        // 1. 配置文件创建一个SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SQLSession实例，能直接执行一杯映射的sql语句
        SqlSession session = sqlSessionFactory.openSession();
        try {
            Word word = session.selectOne("cn.seeu.WordMapper.selectWord", 1);
            System.out.println(word);
        } finally {
            session.close();
        }
    }

    /*
    总结：
    1.接口式编程
      原生：      Dao     ===>        DaoImpl
      mybatis：  Mapper   ===>       xxMapper.xml
    2. sqlSession岱庙和数据库一次绘画，用完必须关闭
    3. sqlSession和connection一样都是非线程安全的，
        每次使用都要获取新的对象
    4. mapper接口没有实现类，
        但是mybatis会为这个接口生成一个代理对象（即，接口和xml进行绑定）
    5. 两种重要的配置文件：
        mybatis的全局配置文件
        ☆sql映射文件，保存了没一个sql语句的映射信息
     */
    @Test
    public void test2() throws IOException {
        // 1. 创建一个SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSessionFactory对象
        SqlSession session = sqlSessionFactory.openSession();

        try {
            // 3.获取接口的实现类对象
            WordMapper mapper = session.getMapper(WordMapper.class);

//            List<Word> wordList = mapper.getWordsByIsDelete(0);
//            Map<String, Object> wordsMap = mapper.getWordByIdReturnMap(1);
            Map<Integer, Word> words = mapper.getWordsByIsDeleteReturnMap(0);
            System.out.println(words);
        } finally {
            session.close();
        }
    }

    /*
    1. mybatis允许增删改直接定义类型返回值：Integer、Long、Boolean
    如：  public int addWord(Word word);
    2. sqlSessionFactory.openSession() ==> 手动提交
       sqlSessionFactory.openSession(true) ==> 自动提交
     */
    @Test
    public void test3() throws IOException {
        // 1. 创建一个SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSessionFactory对象
        SqlSession session = sqlSessionFactory.openSession();

        try {
            WordMapper mapper = session.getMapper(WordMapper.class);
            // 新增
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            Word word = new Word("ability", simpleDateFormat.format(now));
            mapper.addWord(word);
            System.out.println(word.getId());
            // 更新
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date now = new Date();
//            Word word = new Word(3, "abide", simpleDateFormat.format(now));
//            mapper.updateWord(word);
            // 删除
//            mapper.deleteWord(4);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Test
    public void test4() throws IOException {
        // 1. 创建一个SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSessionFactory对象
        SqlSession session = sqlSessionFactory.openSession();

        try {
            WordMapper mapper = session.getMapper(WordMapper.class);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            Word word = new Word(3, "abide", simpleDateFormat.format(now));
            mapper.updateWord(word);
            session.commit();
        } finally {
            session.close();
        }
    }

    @Test
    public void test5() throws IOException {
        // 1. 创建一个SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSessionFactory对象
        SqlSession session = sqlSessionFactory.openSession();

        try {
            WordMapper mapper = session.getMapper(WordMapper.class);
            mapper.deleteWord(4);
            session.commit();
        } finally {
            session.close();
        }
    }
}
