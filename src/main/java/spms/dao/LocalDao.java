package spms.dao;

import java.util.List;

import spms.vo.Local;

public interface LocalDao {
	List<Local> selectList() throws Exception;
	
	Local selectOne(int no) throws Exception;
	
	int insert(Local local) throws Exception;
	
	int update(Local local) throws Exception;
}
