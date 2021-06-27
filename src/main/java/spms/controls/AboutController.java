package spms.controls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.MovieDao;

@Controller
public class AboutController {
	MovieDao movieDao;

	@Autowired
	public AboutController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}

	@RequestMapping("/about.do")
	public String execute(Map<String, Object> model) throws Exception {

		return "/cinema/page/AboutForm.jsp";
	}
}
