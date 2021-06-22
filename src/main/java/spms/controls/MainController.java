package spms.controls;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.MovieDao;

//@RequestParam 적용
@Controller
public class MainController {
	MovieDao movieDao;

	@Autowired
	public MainController setMemberDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}

	@RequestMapping("/main.do")
	public String execute(Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		model.put("movies", movieDao.selectList(paramMap));
		return "/cinema/page/MainForm.jsp";
	}
}
