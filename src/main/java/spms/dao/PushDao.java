package spms.dao;

import java.util.List;

import spms.vo.Push;

public interface PushDao {
	List<Push> selectList() throws Exception;
	
	int update(int no) throws Exception;
}
