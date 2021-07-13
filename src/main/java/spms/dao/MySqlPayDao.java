package spms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spms.vo.Movie;
import spms.vo.Pay;
import spms.vo.PayType;

//@Repository 애노테이션으로 변경
@Repository
public class MySqlPayDao implements PayDao {
	SqlSessionFactory sqlSessionFactory;

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	@Override
	public List<Pay> selectList(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.PayDao.selectList", no);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public Pay selectOne(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.PayDao.selectOne", no);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public int insert(Pay pay) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.PayDao.insert", pay);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
}
