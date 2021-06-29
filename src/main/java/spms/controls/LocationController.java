package spms.controls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.MovieDao;

@Controller
public class LocationController {
	MovieDao movieDao;

	@Autowired
	public LocationController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}

	@RequestMapping("/location.do")
	public String execute(Map<String, Object> model) throws Exception {

		return "/cinema/page/LocationForm.jsp";
	}
}
