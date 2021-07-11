package spms.dao;

import java.util.HashMap;
import java.util.List;

import spms.vo.Cinema;

public interface CinemaDao {
	List<Cinema> selectListDefault() throws Exception;
	
	List<Cinema> selectListDefault(HashMap<String, Object> paramMap) throws Exception;
	
	List<Cinema> selectList(int no) throws Exception;
	
	List<Cinema> selectListLocal() throws Exception;
	
	Cinema selectOneDefault(int no) throws Exception ;
	
	int insert(Cinema cinema) throws Exception;
	
	int update(Cinema cinema) throws Exception;
}
