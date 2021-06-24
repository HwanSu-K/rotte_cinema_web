package spms.controls;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spms.dao.MovieDao;

@Controller
public class TicketingController {
	MovieDao movieDao;

	@Autowired
	public TicketingController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}

	@RequestMapping(value = "/ticketing.do", method = RequestMethod.GET)
	public String movieLoad(String tab, Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (tab == null) {
			
		} else if (tab.equals("1")) {
			paramMap.put("view","soon");
			paramMap.put("order","open");
		} else if (tab.equals("2")) {
			paramMap.put("order","rating");
		}
		
		model.put("movies", movieDao.selectList(paramMap));
		return "/cinema/page/TicketingForm.jsp";
	}

	@RequestMapping(value = "/ticketing.do", method = RequestMethod.POST)
	public String theaterLoad(String movie, Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		System.out.println("선택영화" + movie);
		// model.put("movies", movieDao.selectList(paramMap));
		return "/cinema/page/TicketingForm.jsp";
	}
}
