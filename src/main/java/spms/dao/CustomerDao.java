package spms.dao;

import java.util.HashMap;
import java.util.List;

import spms.vo.Customer;

public interface CustomerDao {
	List<Customer> selectList(HashMap<String, Object> paramMap) throws Exception;
	
	int insert(Customer customer) throws Exception ;
	
	Customer selectOne(int no) throws Exception ;
	
	int update(Customer customer) throws Exception;
	
	Customer exist(String email, String password) throws Exception;
	
	Customer finder(Customer customer) throws Exception;
	
	Customer email(HashMap<String, Object> paramMap) throws Exception ;
}
