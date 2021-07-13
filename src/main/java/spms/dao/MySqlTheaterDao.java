package spms.dao;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spms.vo.Cinema;
import spms.vo.Theater;

//@Repository 애노테이션으로 변경
@Repository
public class MySqlTheaterDao implements TheaterDao {
	SqlSessionFactory sqlSessionFactory;

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public List<Theater> selectList(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.TheaterDao.selectList", paramMap);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public List<Theater> selectListAround(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.TheaterDao.selectListAround", paramMap);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public List<Theater> selectListDefault() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.TheaterDao.selectListDefault");
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public List<Theater> selectListCinema() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.TheaterDao.selectListCinema");
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public Theater selectOneDefault(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.TheaterDao.selectOneDefault", no);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public int insert(Theater theater) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.TheaterDao.insert", theater);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int update(Theater theater) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Theater original = sqlSession.selectOne("spms.dao.TheaterDao.selectOneDefault", theater.getIndex());

			Hashtable<String, Object> paramMap = new Hashtable<String, Object>();
			if (!theater.getName().equals(original.getName())) {
				paramMap.put("name", theater.getName());
			}
			
			if (theater.getSeatX() != original.getSeatX()) {
				paramMap.put("seatX", theater.getSeatX());
			}
			
			if (theater.getSeatY() != original.getSeatY()) {
				paramMap.put("seatY", theater.getSeatY());
			}
			
			if (theater.getIndexCinema() != original.getIndexCinema()) {
				paramMap.put("indexCinema", theater.getIndexCinema());
			}
			
			if (paramMap.size() > 0) {
				paramMap.put("index", theater.getIndex());
				int count = sqlSession.update("spms.dao.TheaterDao.update", paramMap);
				sqlSession.commit();
				return count;
			} else {				
				return 0;
			}
		} finally {
			sqlSession.close();
		}
	}
}
