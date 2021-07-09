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

@Controller
public class TicketingController {
	MovieDao movieDao;
	CinemaDao cinemaDao;

	@Autowired
	public TicketingController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}

	@Autowired
	public TicketingController setLocalDao(CinemaDao cinemaDao) {
		this.cinemaDao = cinemaDao;
		return this;
	}

	@RequestMapping(value = "/ticketing.do", method = RequestMethod.GET)
	public String movieLoad(String type, String index, Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if (index == null) {
			paramMap.put("type", type);
			model.put("movies", movieDao.selectList(paramMap));
		} else {
			Movie detailInfo = movieDao.selectOneDefault(Integer.parseInt(index));
			model.put("movie", detailInfo);
			model.put("locals", cinemaDao.selectListLocal());
			model.put("cinemas", cinemaDao.selectList(Integer.parseInt(index)));
		}

		return "/cinema/page/TicketingForm.jsp";
	}
}
