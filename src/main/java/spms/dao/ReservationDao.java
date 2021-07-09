package spms.dao;

import spms.vo.Reservation;

public interface ReservationDao {
	Reservation selectOne(int no) throws Exception;

}
