package spms.controls;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.LikeDao;
import spms.dao.MovieDao;
import spms.vo.Customer;
import spms.vo.Like;
import spms.vo.Movie;

@Controller
public class MovieDetailController {
	MovieDao movieDao;
	LikeDao likeDao;

	@Autowired
	public MovieDetailController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}
	
	@Autowired
	public MovieDetailController setLikeDao(LikeDao likeDao) {
		this.likeDao = likeDao;
		return this;
	}

	@RequestMapping("/moviedetail.do")
	public String execute(HttpSession session, int index, Map<String, Object> model) throws Exception {

		Movie detailInfo = movieDao.selectOne(index);
		model.put("movie", detailInfo);
		List<Movie> movies = movieDao.selectListRank(index);
		
		for (int i = 0; i < movies.size(); i++) {
			if(movies.get(i).getIndex() == index) {
				model.put("rank", i + 1);
				break;
			}
		}

		Customer customer = (Customer)session.getAttribute("customer");
		if(customer != null) {
			model.put("like", likeDao.selectOne(new Like().setIndexCustomer(customer.getIndex()).setIndexMovie(index)));
		}
		return "/cinema/page/MovieDetailForm.jsp";
	}
}
