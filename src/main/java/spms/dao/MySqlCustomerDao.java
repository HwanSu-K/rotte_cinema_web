package spms.dao;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spms.vo.Customer;
import spms.vo.Token;

//@Repository 애노테이션으로 변경
@Repository
public class MySqlCustomerDao implements CustomerDao {
	SqlSessionFactory sqlSessionFactory;

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public List<Customer> selectList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.CustomerDao.selectList");
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public List<Token> selectList(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.TokenDao.selectList",no);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public int insert(Customer customer) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.CustomerDao.insert", customer);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	

	@Override
	public int insertToken(Customer customer) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.CustomerDao.insertToken", customer);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Customer selectOne(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.CustomerDao.selectOne", no);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public Customer selectOneKey(Customer customer) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.CustomerDao.selectOneKey", customer);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public int update(Customer customer) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Customer original = sqlSession.selectOne("spms.dao.CustomerDao.selectOne", customer.getIndex());

			Hashtable<String, Object> paramMap = new Hashtable<String, Object>();
			if (customer.getEmail() != original.getEmail()) {
				paramMap.put("email", customer.getEmail());
			}
			
			if (customer.getPassword() != original.getPassword()) {
				paramMap.put("password", customer.getPassword());
			}
			
			if (customer.getName() != original.getName()) {
				paramMap.put("name", customer.getName());
			}
			
			if (customer.getBirth() != original.getBirth()) {
				paramMap.put("birth", customer.getBirth());
			}
			
			if (customer.getPhonenum() != original.getPhonenum()) {
				paramMap.put("phonenum", customer.getPhonenum());
			}
	
			if (paramMap.size() > 0) {
				paramMap.put("index", customer.getIndex());
				int count = sqlSession.update("spms.dao.CustomerDao.update", paramMap);
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
	
	@Override
	public int updateToken(Customer customer) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.CustomerDao.updateToken", customer);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public int updateState(Customer customer) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.CustomerDao.updateState", customer);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public int updateKey(Customer customer) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.CustomerDao.updateKey", customer);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public int deleteToken(Customer customer) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("spms.dao.CustomerDao.deleteToken", customer);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Customer exist(String email) throws Exception {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("email", email);

		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.CustomerDao.exist", paramMap);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public Customer existToken(String token) throws Exception {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("token", token);
	

		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.CustomerDao.existToken", paramMap);
		} finally {
			sqlSession.close();
		}
	}
	
	@Override
	public Customer finder(Customer customer) throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.CustomerDao.finder", customer);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Customer email(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("spms.dao.CustomerDao.email", paramMap);
		} finally {
			sqlSession.close();
		}
	}
}
