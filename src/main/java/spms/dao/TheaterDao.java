package spms.dao;

import java.util.HashMap;
import java.util.List;

import spms.vo.Theater;

public interface TheaterDao {
	List<Theater> selectList(HashMap<String, Object> paramMap) throws Exception;
	
	List<Theater> selectListAround(HashMap<String, Object> paramMap) throws Exception;

}
