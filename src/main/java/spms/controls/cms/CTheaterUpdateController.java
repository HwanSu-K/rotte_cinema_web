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
public class CTheaterUpdateController {
	TheaterDao theaterDao;

	@Autowired
	public CTheaterUpdateController setMemberDao(TheaterDao theaterDao) {
		this.theaterDao = theaterDao;
		return this;
	}

	@RequestMapping(value = "/cms/theater/update.do", method = RequestMethod.GET)
	public String updateForm(int no, Map<String, Object> model) throws Exception {
		Theater detailInfo = theaterDao.selectOneDefault(no);
		model.put("theater", detailInfo);
		model.put("cinemas", theaterDao.selectListCinema());
		return "/cms/TheaterUpdateForm.jsp";
	}

	@RequestMapping(value = "/cms/theater/update.do", method = RequestMethod.POST)
	public String update(Theater theater) throws Exception {

		
		theaterDao.update(theater);

		return "redirect:list.do";
	}
}
