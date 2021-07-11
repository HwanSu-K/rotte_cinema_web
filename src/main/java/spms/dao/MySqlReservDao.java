package spms.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spms.vo.Reserv;
import spms.vo.Review;

//@Repository 애노테이션으로 변경
@Repository
public class MySqlReservDao implements ReservDao {
	SqlSessionFactory sqlSessionFactory;

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public List<Reserv> selectList(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.ReservDao.selectList", no);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public List<Reserv>  selectListPay(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.ReservDao.selectListPay", no);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public Reserv selectOne(Reserv reserv) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.ReservDao.selectOne", reserv);
		} finally {
			sqlSession.close();
		}
	}
	
	
	
	@Override
	public int insert(Reserv reserv) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.ReservDao.insert", reserv);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
}
