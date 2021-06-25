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
	public String execute(String tab, String search, Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if(tab == null) {

		} else if(tab.equals("1")) {
			paramMap.put("view", "soon");
			paramMap.put("order", "open");	
		} else if(tab.equals("2")) {
			paramMap.put("view", "qration");
		}
		
		paramMap.put("search", search);
		model.put("movies", movieDao.selectList(paramMap));
		return "/cinema/page/MovieForm.jsp";
	}
}
