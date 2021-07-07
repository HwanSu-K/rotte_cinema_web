package spms.dao;

import spms.vo.Reservation;

public interface ReservationDao {
	Reservation selectList(int no) throws Exception;

}
