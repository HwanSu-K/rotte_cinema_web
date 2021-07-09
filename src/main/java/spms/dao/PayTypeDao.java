package spms.dao;

import java.util.List;

import spms.vo.PayType;

public interface PayTypeDao {
	List<PayType> selectList() throws Exception;
}
