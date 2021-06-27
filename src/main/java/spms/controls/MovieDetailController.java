package spms.controls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.MovieDao;
import spms.vo.Movie;

@Controller
public class MovieDetailController {
	MovieDao movieDao;

	@Autowired
	public MovieDetailController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}

	@RequestMapping("/moviedetail.do")
	public String execute(int index, Map<String, Object> model) throws Exception {

		Movie detailInfo = movieDao.selectOne(index);
		model.put("movie", detailInfo);
		return "/cinema/page/MovieDetailForm.jsp";
	}
}
