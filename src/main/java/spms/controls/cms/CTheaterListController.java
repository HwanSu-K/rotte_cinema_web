package spms.controls.cms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.TheaterDao;

//@RequestParam 적용
@Controller
public class CTheaterListController {
	TheaterDao theaterDao;

	@Autowired
	public CTheaterListController setMemberDao(TheaterDao theaterDao) {
		this.theaterDao = theaterDao;
		return this;
	}

	@RequestMapping("/cms/theater/list.do")
	public String execute(Map<String, Object> model) throws Exception {
		model.put("theaters", theaterDao.selectListDefault());
		return "/cms/TheaterListForm.jsp";
	}
}
