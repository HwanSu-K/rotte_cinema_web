package spms.dao;

import java.util.HashMap;
import java.util.List;

import spms.vo.Movie;

public interface MovieDao {
	List<Movie> selectList(HashMap<String, Object> paramMap) throws Exception;
	
	List<Movie> selectListTitle(HashMap<String, Object> paramMap) throws Exception;
	
	int insert(Movie movie) throws Exception ;
	
	Movie selectOne(int no) throws Exception ;
	
	Movie selectOneDefault(int no) throws Exception ;
	
	int update(Movie movie) throws Exception;
}
