package spms.dao;

import java.util.List;

import spms.vo.Pay;

public interface PayDao {
	List<Pay> selectList(int no) throws Exception;
	
	Pay selectOne(int no) throws Exception ;
	
	int insert(Pay pay) throws Exception ;

}
