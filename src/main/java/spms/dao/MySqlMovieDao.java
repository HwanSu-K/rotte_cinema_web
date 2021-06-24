package spms.dao;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spms.vo.Movie;

//@Repository 애노테이션으로 변경
@Repository
public class MySqlMovieDao implements MovieDao {
	SqlSessionFactory sqlSessionFactory;

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public List<Movie> selectList(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.MovieDao.selectList", paramMap);
		} finally {
			sqlSession.close();
		}
	}
	
	public int insert(Movie movie) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.MovieDao.insert", movie);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Movie selectOne(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.MovieDao.selectOne", no);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int update(Movie movie) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Movie original = sqlSession.selectOne("spms.dao.MovieDao.selectOne", movie.getIndex());

			Hashtable<String, Object> paramMap = new Hashtable<String, Object>();
			if (!movie.getTitle().equals(original.getTitle())) {
				paramMap.put("title", movie.getTitle());
			}
			
			if (!movie.getDirector().equals(original.getDirector())) {
				paramMap.put("director", movie.getTitle());
			}
			
			if (!movie.getActor().equals(original.getActor())) {
				paramMap.put("actor", movie.getActor());
			}
			
			if (!movie.getGenre().equals(original.getGenre())) {
				paramMap.put("genre", movie.getGenre());
			}
			
			if (movie.getLimitAge() != original.getLimitAge()) {
				paramMap.put("limitAge", movie.getLimitAge());
			}
			
			if (!movie.getRunningTime().equals(original.getRunningTime())) {
				paramMap.put("runningTime", movie.getRunningTime());
			}
			
			if (!movie.getOpenDate().equals(original.getOpenDate())) {
				paramMap.put("openDate", movie.getOpenDate());
			}
			
			if (!movie.getEndDate().equals(original.getEndDate())) {
				paramMap.put("endDate", movie.getEndDate());
			}
			
			if (movie.getPoster() != null) {
				paramMap.put("poster", movie.getPoster());
			}
			
			if (!movie.getInfo().equals(original.getInfo())) {
				paramMap.put("info", movie.getInfo());
			}
			
			if (!movie.getTags().equals(original.getTags())) {
				paramMap.put("tags", movie.getTags());
			}
			
			if (movie.getState() != original.getState()) {
				paramMap.put("state", movie.getState());
			}
	
			if (paramMap.size() > 0) {
				paramMap.put("index", movie.getIndex());
				int count = sqlSession.update("spms.dao.MovieDao.update", paramMap);
				sqlSession.commit();
				return count;
			} else {				
				return 0;
			}
		}
		finally {
			sqlSession.close();
		}
	}
}
