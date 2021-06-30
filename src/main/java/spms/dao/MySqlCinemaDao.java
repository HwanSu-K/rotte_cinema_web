package spms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spms.vo.Cinema;

//@Repository 애노테이션으로 변경
@Repository
public class MySqlCinemaDao implements CinemaDao {
	SqlSessionFactory sqlSessionFactory;

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public List<Cinema> selectListDefault() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.CinemaDao.selectDefault");
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public List<Cinema> selectList(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.CinemaDao.selectList", no);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public List<Cinema> selectListLocal() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.CinemaDao.selectListLocal");
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Cinema selectOneDefault(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.CinemaDao.selectOneDefault", no);
		} finally {
			sqlSession.close();
		}
	}
}
