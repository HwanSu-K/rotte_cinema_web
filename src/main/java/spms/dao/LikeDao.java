package spms.dao;

import java.util.List;

import spms.vo.Like;

public interface LikeDao {
	List<Like> selectList(int no) throws Exception;
	
	int insert(Like like) throws Exception ;
	
	int delete(Like like) throws Exception ;
	
	Like selectOne(Like like) throws Exception ;
	
	Like selectOneCount(Like like) throws Exception ;
}
