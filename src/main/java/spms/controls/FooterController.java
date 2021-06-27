package spms.controls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.MovieDao;

@Controller
public class FooterController {
	MovieDao movieDao;

	@Autowired
	public FooterController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}

	@RequestMapping("/include/footer.do")
	public String execute(Map<String, Object> model) throws Exception {

		return "/cinema/include/IncludeFooter.jsp";
	}
}
