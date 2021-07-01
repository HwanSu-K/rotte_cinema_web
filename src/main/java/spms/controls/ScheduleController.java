package spms.controls;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import spms.dao.CinemaDao;
import spms.dao.MovieDao;
import spms.vo.Movie;

@Controller
public class ScheduleController {
	MovieDao movieDao;
	CinemaDao cinemaDao;
	
	@Autowired
	public ScheduleController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}
	
	@Autowired
	public ScheduleController setLocalDao(CinemaDao cinemaDao) {
		this.cinemaDao = cinemaDao;
		return this;
	}

	@RequestMapping("/schedule.do")
	public String execute(Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("view","reserv");
		
		
		model.put("movies", movieDao.selectList(paramMap));
		model.put("locals", cinemaDao.selectListLocal());
		model.put("cinemas", cinemaDao.selectListDefault());
		return "/cinema/page/ScheduleForm.jsp";
	}
}
