package spms.controls;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.MovieDao;

@Controller
public class MovieController {
	MovieDao movieDao;

	@Autowired
	public MovieController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}

	@RequestMapping("/movie.do")
	public String execute(String type, String search, Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("type", type);
		paramMap.put("search", search);
		model.put("movies", movieDao.selectList(paramMap));
		return "/cinema/page/MovieForm.jsp";
	}
}
