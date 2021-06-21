package spms.controls;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.MovieDao;

//@RequestParam 적용
@Controller
public class MovieListController {
	MovieDao movieDao;

	@Autowired
	public MovieListController setMemberDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}

	@RequestMapping("/cms/movie/list.do")
	public String execute(String orderCond, Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderCond", orderCond);

		model.put("movies", movieDao.selectList(paramMap));
		return "/cms/MovieListForm.jsp";
	}
}
