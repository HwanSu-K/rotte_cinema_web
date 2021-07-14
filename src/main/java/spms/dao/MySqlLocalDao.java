package spms.dao;

import java.util.Hashtable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spms.vo.Local;

//@Repository 애노테이션으로 변경
@Repository
public class MySqlLocalDao implements LocalDao {
	SqlSessionFactory sqlSessionFactory;

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public List<Local> selectList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.LocalDao.selectList");
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int insert(Local local) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.LocalDao.insert", local);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Local selectOne(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.LocalDao.selectOne", no);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int update(Local local) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Local original = sqlSession.selectOne("spms.dao.LocalDao.selectOne", local.getIndex());

			Hashtable<String, Object> paramMap = new Hashtable<String, Object>();
			
			if (!local.getName().equals(original.getName())) {
				paramMap.put("name", local.getName());
			}

			if (!local.getClassName().equals(original.getClassName())) {
				paramMap.put("className", local.getClassName());
			}

			if (local.getSort() != original.getSort()) {
				paramMap.put("sort", local.getSort());
			}

			if (paramMap.size() > 0) {
				paramMap.put("index", local.getIndex());
				int count = sqlSession.update("spms.dao.LocalDao.update", paramMap);
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
