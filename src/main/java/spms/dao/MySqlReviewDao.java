package spms.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spms.vo.Review;

//@Repository 애노테이션으로 변경
@Repository
public class MySqlReviewDao implements ReviewDao {
	SqlSessionFactory sqlSessionFactory;

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public List<Review> selectList(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.ReviewDao.selectList", paramMap);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public int insert(Review review) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.ReviewDao.insert", review);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int selectOneCount(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.ReviewDao.selectOneCount", paramMap);
		} finally {
			sqlSession.close();
		}
	}
}
