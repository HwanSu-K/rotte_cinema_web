package spms.dao;

import java.util.List;

import spms.vo.Cinema;

public interface CinemaDao {
	List<Cinema> selectList(int no) throws Exception;
	List<Cinema> selectListLocal() throws Exception;
}
