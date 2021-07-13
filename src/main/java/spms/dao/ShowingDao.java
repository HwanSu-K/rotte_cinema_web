package spms.dao;

import java.util.HashMap;
import java.util.List;

import spms.vo.Showing;

public interface ShowingDao {
	List<Showing> selectList(int no) throws Exception;

	Showing selectOne(HashMap<String, Object> paramMap) throws Exception ;
	
	Showing selectOneShowing(HashMap<String, Object> paramMap) throws Exception ;
	
	int insert(Showing showing) throws Exception;
}
