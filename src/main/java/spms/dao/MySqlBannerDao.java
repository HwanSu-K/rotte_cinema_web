package spms.dao;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spms.vo.Banner;
import spms.vo.Movie;

//@Repository 애노테이션으로 변경
@Repository
public class MySqlBannerDao implements BannerDao {
	SqlSessionFactory sqlSessionFactory;

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public List<Banner> selectList(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.BannerDao.selectList", paramMap);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public int insert(Banner banner) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.BannerDao.insert", banner);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Banner selectOne(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.BannerDao.selectOne", no);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int update(Banner banner) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Banner original = sqlSession.selectOne("spms.dao.BannerDao.selectOne", banner.getIndex());

			Hashtable<String, Object> paramMap = new Hashtable<String, Object>();
			if (banner.getImagePath() != null) {
				paramMap.put("imagePath", banner.getImagePath());
			}
			
			if (banner.getVideoPath() != null) {
				paramMap.put("videoPath", banner.getVideoPath());
			}
			
			if (banner.getState() != original.getState()) {
				paramMap.put("state", banner.getState());
			}
	
			if (paramMap.size() > 0) {
				paramMap.put("index", banner.getIndex());
				int count = sqlSession.update("spms.dao.BannerDao.update", paramMap);
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
