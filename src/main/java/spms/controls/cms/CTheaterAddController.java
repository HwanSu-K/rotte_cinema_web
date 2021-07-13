package spms.controls.cms;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spms.dao.TheaterDao;
import spms.vo.Theater;

//@RequestParam 적용
@Controller
public class CTheaterAddController {
	TheaterDao theaterDao;

	@Autowired
	public CTheaterAddController setMemberDao(TheaterDao theaterDao) {
		this.theaterDao = theaterDao;
		return this;
	}

	@RequestMapping(value = "/cms/theater/add.do", method = RequestMethod.GET)
	public String addForm(Map<String, Object> model) throws Exception {
		model.put("cinemas", theaterDao.selectListCinema());

		return "/cms/TheaterAddForm.jsp";
	}

	@RequestMapping(value = "/cms/theater/add.do", method = RequestMethod.POST)
	public String add(Theater theater) throws Exception {

		theaterDao.insert(theater);

		return "redirect:list.do";
	}
}
