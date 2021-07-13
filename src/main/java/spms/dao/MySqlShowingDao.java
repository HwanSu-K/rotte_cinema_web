package spms.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spms.vo.Showing;

//@Repository 애노테이션으로 변경
@Repository
public class MySqlShowingDao implements ShowingDao {
	SqlSessionFactory sqlSessionFactory;

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	
	@Override
	public List<Showing> selectList(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.ShowingDao.selectList", no);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Showing selectOne(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.ShowingDao.selectOne",paramMap);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public Showing selectOneShowing(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.ShowingDao.selectOneShowing",paramMap);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int insert(Showing showing) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.ShowingDao.insert", showing);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
}
