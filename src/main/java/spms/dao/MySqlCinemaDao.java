package spms.dao;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spms.vo.Cinema;
import spms.vo.Movie;

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
	public List<Cinema> selectListDefault(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.CinemaDao.selectDefault",paramMap);
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

	@Override
	public int insert(Cinema cinema) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.CinemaDao.insert", cinema);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int update(Cinema cinema) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Cinema original = sqlSession.selectOne("spms.dao.CinemaDao.selectOneDefault", cinema.getIndex());

			Hashtable<String, Object> paramMap = new Hashtable<String, Object>();
			if (!cinema.getLocalIndex().equals(original.getLocalIndex())) {
				paramMap.put("localIndex", cinema.getLocalIndex());
			}
			
			if (!cinema.getTitle().equals(original.getTitle())) {
				paramMap.put("title", cinema.getTitle());
			}
			
			if (!cinema.getAddr().equals(original.getAddr())) {
				paramMap.put("addr", cinema.getAddr());
			}
			
			if (!cinema.getInfo().equals(original.getInfo())) {
				paramMap.put("info", cinema.getInfo());
			}
			
			if (!cinema.getLat().equals(original.getLat())) {
				paramMap.put("lat", cinema.getLat());
			}
			
			if (!cinema.getLng().equals(original.getLng())) {
				paramMap.put("lng", cinema.getLng());
			}
			
			if (cinema.getState() != original.getState()) {
				paramMap.put("state", cinema.getState());
			}
	
			if (paramMap.size() > 0) {
				paramMap.put("index", cinema.getIndex());
				int count = sqlSession.update("spms.dao.CinemaDao.update", paramMap);
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
