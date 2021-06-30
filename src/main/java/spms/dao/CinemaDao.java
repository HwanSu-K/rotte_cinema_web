package spms.dao;

import java.util.List;

import spms.vo.Cinema;
import spms.vo.Customer;

public interface CinemaDao {
	List<Cinema> selectListDefault() throws Exception;
	
	List<Cinema> selectList(int no) throws Exception;
	
	List<Cinema> selectListLocal() throws Exception;
	
	Cinema selectOneDefault(int no) throws Exception ;
}
