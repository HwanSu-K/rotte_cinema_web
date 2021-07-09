package spms.dao;

import java.util.List;

import spms.vo.Reserv;

public interface ReservDao {
	List<Reserv> selectList(int no) throws Exception;
	
	Reserv selectOne(Reserv reserv) throws Exception;
	
	int insert(Reserv reserv) throws Exception ;
}
