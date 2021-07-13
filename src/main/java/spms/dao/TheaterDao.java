package spms.dao;

import java.util.HashMap;
import java.util.List;

import spms.vo.Theater;

public interface TheaterDao {
	List<Theater> selectList(HashMap<String, Object> paramMap) throws Exception;
	
	List<Theater> selectListAround(HashMap<String, Object> paramMap) throws Exception;

	List<Theater> selectListDefault() throws Exception;
	
	List<Theater> selectListCinema() throws Exception;
	
	Theater selectOneDefault(int no) throws Exception;
	
	int insert(Theater theater) throws Exception;
	
	int update(Theater theater) throws Exception;
}
