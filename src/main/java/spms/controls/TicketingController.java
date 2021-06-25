package spms.controls;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spms.dao.CinemaDao;
import spms.dao.MovieDao;
import spms.vo.Movie;
import spms.vo.Reserv;

@Controller
public class TicketingController {
	MovieDao movieDao;
	CinemaDao localDao;

	@Autowired
	public TicketingController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}
	
	@Autowired
	public TicketingController setLocalDao(CinemaDao localDao) {
		this.localDao = localDao;
		return this;
	}

	@RequestMapping(value = "/ticketing.do", method = RequestMethod.GET)
	public String movieLoad(String tab, String no, Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();

		if (no == null) {

			if (tab == null) {

			} else if (tab.equals("1")) {
				paramMap.put("view", "soon");
				paramMap.put("order", "open");
			} else if (tab.equals("2")) {
				paramMap.put("order", "rating");
			}
			
			model.put("movies", movieDao.selectList(paramMap));
		}
		else
		{
			
			model.put("cinemas", localDao.selectList());
			model.put("locals", localDao.selectListGroup());
			Movie detailInfo = movieDao.selectOne(Integer.parseInt(no));
			model.put("movie", detailInfo);
		}
		
		return "/cinema/page/TicketingForm.jsp";
	}

	@RequestMapping(value = "/ticketing.do", method = RequestMethod.POST)
	public String theaterLoad(Reserv reserv, Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		System.out.println("선택영화" + reserv);
		// model.put("movies", movieDao.selectList(paramMap));
		return "/cinema/page/TicketingForm.jsp";
	}
}
