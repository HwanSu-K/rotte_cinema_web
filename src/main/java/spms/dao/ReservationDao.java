package spms.dao;

import java.util.HashMap;
import java.util.List;

import spms.vo.Reservation;

public interface ReservationDao {
	List<Reservation> selectList(HashMap<String, Object> paramMap) throws Exception;
	
	Reservation selectOne(int no) throws Exception;

}
