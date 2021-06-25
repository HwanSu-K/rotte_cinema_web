package spms.dao;

import java.util.List;

import spms.vo.Cinema;

public interface CinemaDao {
	List<Cinema> selectList() throws Exception;
	List<Cinema> selectListGroup() throws Exception;
}
