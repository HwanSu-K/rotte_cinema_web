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

import spms.dao.CinemaDao;
import spms.dao.CustomerDao;
import spms.dao.LikeDao;
import spms.dao.MovieDao;
import spms.dao.ReviewDao;
import spms.dao.TheaterDao;
import spms.vo.Cinema;
import spms.vo.Customer;
import spms.vo.Like;
import spms.vo.Movie;
import spms.vo.Review;

@Controller
public class AjaxController {
	MovieDao movieDao;
	ReviewDao reviewDao;
	TheaterDao theaterDao;
	LikeDao likeDao;
	CustomerDao customerDao;
	CinemaDao cinemaDao;

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

	@Autowired
	public AjaxController setLikeDao(LikeDao likeDao) {
		this.likeDao = likeDao;
		return this;
	}

	@Autowired
	public AjaxController setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
		return this;
	}

	@Autowired
	public AjaxController setCinemaDao(CinemaDao cinemaDao) {
		this.cinemaDao = cinemaDao;
		return this;
	}

	@RequestMapping(value = "/movieobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getMovieObject(Movie movie) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Movie detailInfo = movieDao.selectOneDefault(movie.getIndex());
		map.put("movie", detailInfo);
		return map;
	}

	@RequestMapping(value = "/moviesobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getMoviesObject(String type) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", type);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("movies", movieDao.selectList(paramMap));
		return map;
	}

	@RequestMapping(value = "/moviestitle.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getMoviesTitle(String search) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("search", search);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("movies", movieDao.selectListTitle(paramMap));
		return map;
	}

	@RequestMapping(value = "/reviewsobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getReviewsObject(String index) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("index", index);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("reviews", reviewDao.selectList(paramMap));
		return map;
	}

	@RequestMapping(value = "/reviewobject.do", method = RequestMethod.POST)
	@ResponseBody
	public int setReviewObject(HttpSession session, Review review) throws Exception {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) {
			review.setIndexCustomer(customer.getIndex());
			review.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

			return reviewDao.insert(review);
		}
		return -1;
	}

	@RequestMapping(value = "/theatersobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getTheaterObject(String cinema, String movie) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (cinema != null) {
			paramMap.put("indexCinema", cinema);
		}
		if (movie != null) {
			paramMap.put("indexMovie", movie);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("theaters", theaterDao.selectList(paramMap));
		return map;
	}

	@RequestMapping(value = "/likeobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getLikeObject(HttpSession session, Like like) throws Exception {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) {
			like.setIndexCustomer(customer.getIndex());
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (likeDao.selectOne(like) != null) {
				likeDao.delete(like);
				map.put("return", 0);

			} else {
				likeDao.insert(like);
				map.put("return", 1);
			}
			map.put("count", likeDao.selectOneCount(like).getCount());

			return map;
		}
		return -1;
	}

	@RequestMapping(value = "/finderobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getfinderObject(Customer customer) throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("customer", customerDao.finder(customer));
		return map;
	}

	@RequestMapping(value = "/emailobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getemailCheckObject(String email) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("email", email);
		Customer customer = customerDao.email(paramMap);

		if (customer == null) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/registrationobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getregistrationObject(Customer customer) throws Exception {

		if (customer != null) {

			return customerDao.insert(customer);
		}
		return -1;
	}

	@RequestMapping(value = "/loginobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getregistrationObject(String email, String password, HttpSession session) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Customer customer = customerDao.exist(email, password);
		if (customer != null) {
			session.setAttribute("customer", customer);
			map.put("result", "success");
			map.put("name", customer.getName());
			return map;

		} else {
			map.put("result", "fail");
			map.put("name", null);
			return map;
		}
	}

	@RequestMapping(value = "/cinemaobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getCinemaObject(Cinema cinema) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Cinema detailInfo = cinemaDao.selectOneDefault(cinema.getIndex());
		map.put("cinema", detailInfo);
		return map;
	}
}
