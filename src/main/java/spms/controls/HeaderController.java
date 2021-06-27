package spms.controls;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.MovieDao;

@Controller
public class HeaderController {
	MovieDao movieDao;

	@Autowired
	public HeaderController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}

	@RequestMapping("/include/header.do")
	public String execute(Map<String, Object> model) throws Exception {

		return "/cinema/include/IncludeHeader.jsp";
	}
}
