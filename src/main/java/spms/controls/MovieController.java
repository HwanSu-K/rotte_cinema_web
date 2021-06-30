package spms.controls;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.LikeDao;
import spms.dao.MovieDao;
import spms.vo.Customer;

@Controller
public class MovieController {
	MovieDao movieDao;
	LikeDao likeDao;

	@Autowired
	public MovieController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}
	
	@Autowired
	public MovieController setLikeDao(LikeDao likeDao) {
		this.likeDao = likeDao;
		return this;
	}

	@RequestMapping("/movie.do")
	public String execute(HttpSession session, String type, String search, Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("type", type);
		paramMap.put("search", search);
		model.put("movies", movieDao.selectList(paramMap));
		Customer customer = (Customer)session.getAttribute("customer");
		if(customer != null) {
			model.put("likes", likeDao.selectList(customer.getIndex()));	
		}
		
		return "/cinema/page/MovieForm.jsp";
	}
}
