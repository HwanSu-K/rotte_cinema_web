package spms.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spms.vo.PayType;
import spms.vo.Reservation;

//@Repository 애노테이션으로 변경
@Repository
public class MySqlReservationDao implements ReservationDao {
	SqlSessionFactory sqlSessionFactory;

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public List<Reservation> selectList(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.ReservationDao.selectList", paramMap);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public Reservation selectOne(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.ReservationDao.selectOne", no);
		} finally {
			sqlSession.close();
		}
	}
}
