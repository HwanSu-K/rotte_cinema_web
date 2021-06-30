package spms.dao;

import java.util.HashMap;
import java.util.List;

import spms.vo.Banner;

public interface BannerDao {
	List<Banner> selectList() throws Exception;
	
	int insert(Banner banner) throws Exception ;
	
	Banner selectOne(int no) throws Exception ;
	
	int update(Banner banner) throws Exception;
}
