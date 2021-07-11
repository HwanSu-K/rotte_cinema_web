package spms.dao;

import spms.vo.Pay;

public interface PayDao {
	
	Pay selectOne(int no) throws Exception ;
	
	int insert(Pay pay) throws Exception ;

}
