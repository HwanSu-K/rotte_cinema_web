package spms.controls.cms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spms.dao.MovieDao;
import spms.dao.TheaterDao;
import spms.vo.Theater;

//@RequestParam 적용
@Controller
public class TheaterListController {
	TheaterDao theaterDao;
	MovieDao movieDao;

	@Autowired
	public TheaterListController setMemberDao(TheaterDao theaterDao) {
		this.theaterDao = theaterDao;
		return this;
	}
	
	@Autowired
	public TheaterListController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}


	@RequestMapping(value = "/cms/theater/add.do", method = RequestMethod.GET)
	public String addGet(Map<String, Object> model) throws Exception {
		model.put("cinemas", theaterDao.selectListCinema());

		return "/cms/TheaterAddForm.jsp";
	}

	@RequestMapping(value = "/cms/theater/add.do", method = RequestMethod.POST)
	public String addPost(Theater theater) throws Exception {

		theaterDao.insert(theater);

		return "redirect:list.do";
	}
	
	@RequestMapping("/cms/theater/list.do")
	public String list(Map<String, Object> model) throws Exception {
		model.put("theaters", theaterDao.selectListDefault());
		return "/cms/TheaterListForm.jsp";
	}
	
	@RequestMapping(value = "/cms/theater/update.do", method = RequestMethod.GET)
	public String updateGet(int no, Map<String, Object> model) throws Exception {
		Theater detailInfo = theaterDao.selectOneDefault(no);
		model.put("theater", detailInfo);
		model.put("cinemas", theaterDao.selectListCinema());
		model.put("movies", movieDao.selectListTitle(null));
		return "/cms/TheaterUpdateForm.jsp";
	}

	@RequestMapping(value = "/cms/theater/update.do", method = RequestMethod.POST)
	public String updatePost(Theater theater) throws Exception {

		theaterDao.update(theater);

		return "redirect:list.do";
	}
}
