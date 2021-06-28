package spms.controls;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import spms.dao.MovieDao;
import spms.dao.ReviewDao;
import spms.dao.TheaterDao;
import spms.vo.Customer;
import spms.vo.Movie;
import spms.vo.Review;

@Controller
public class AjaxController {
	MovieDao movieDao;
	ReviewDao reviewDao;
	TheaterDao theaterDao;

	@Autowired
	public AjaxController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}
	
	@Autowired
	public AjaxController setReviewDao(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
		return this;
	}
	
	@Autowired
	public AjaxController setTheaterDao(TheaterDao theaterDao) {
		this.theaterDao = theaterDao;
		return this;
	}

	@RequestMapping(value="/movieobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getMovieObject(Movie movie) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Movie detailInfo = movieDao.selectOneDefault(movie.getIndex());
		map.put("movie", detailInfo);
        return map;
    }
	
	@RequestMapping(value="/moviesobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getMoviesObject(String type) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type",type);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("movies", movieDao.selectList(paramMap));
        return map;
    }
	
	@RequestMapping(value="/moviestitle.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getMoviesTitle(String search) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("search",search);
	
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("movies", movieDao.selectListTitle(paramMap));
        return map;
    }
	
	@RequestMapping(value="/reviewsobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getReviewsObject(String index) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("index",index);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("reviews", reviewDao.selectList(paramMap));
        return map;
    }
	
	@RequestMapping(value="/reviewobject.do", method = RequestMethod.POST)
	@ResponseBody
	public int setReviewObject(HttpSession session, Review review) throws Exception {
		Customer customer = (Customer)session.getAttribute("customer");
		if(customer != null) {
			review.setIndexCustomer(customer.getIndex());
			review.setDate(new SimpleDateFormat ("yyyy-MM-dd").format(new Date()));
			
			return reviewDao.insert(review);
		}
		return -1;
    }
	
	@RequestMapping(value="/theatersobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getTheatersObject(String cinema, String movie) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("indexCinema",cinema);
		paramMap.put("indexMovie",movie);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("theaters", theaterDao.selectList(paramMap));
        return map;
    }
}
