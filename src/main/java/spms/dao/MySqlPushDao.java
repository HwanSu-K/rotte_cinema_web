package spms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spms.vo.Push;

//@Repository 애노테이션으로 변경
@Repository
public class MySqlPushDao implements PushDao {
	SqlSessionFactory sqlSessionFactory;

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public List<Push> selectList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.PushDao.selectList");
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int update(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.PushDao.update", no);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	
}
