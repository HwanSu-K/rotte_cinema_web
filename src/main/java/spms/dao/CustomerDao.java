package spms.dao;

import java.util.HashMap;
import java.util.List;

import spms.vo.Customer;
import spms.vo.Token;

public interface CustomerDao {
	List<Customer> selectList() throws Exception;
	
	List<Token> selectList(int no) throws Exception;
	
	int insert(Customer customer) throws Exception ;
	
	int insertToken(Customer customer) throws Exception;
	
	Customer selectOne(int no) throws Exception ;
	
	int update(Customer customer) throws Exception;
	
	int updateToken(Customer customer) throws Exception;
	
	int updateKey(Customer customer) throws Exception;
	
	int deleteToken(Customer customer) throws Exception;
	
	Customer exist(String email) throws Exception;
	
	Customer existToken(String token) throws Exception;
	
	Customer finder(Customer customer) throws Exception;
	
	Customer email(HashMap<String, Object> paramMap) throws Exception ;
}
