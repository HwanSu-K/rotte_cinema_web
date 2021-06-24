package spms.controls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.MovieDao;

@Controller
public class EventController {
	MovieDao movieDao;

	@Autowired
	public EventController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}

	@RequestMapping("/event.do")
	public String execute(Map<String, Object> model) throws Exception {
		//HashMap<String, Object> paramMap = new HashMap<String, Object>();
		//paramMap.put("view", view);
		//model.put("movies", movieDao.selectList(paramMap));
		return "/cinema/page/WaringForm.jsp";
	}
}
