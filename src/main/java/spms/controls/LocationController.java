package spms.controls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.CinemaDao;
import spms.dao.MovieDao;

@Controller
public class LocationController {

	CinemaDao cinemaDao;

	@Autowired
	public LocationController setLocalDao(CinemaDao cinemaDao) {
		this.cinemaDao = cinemaDao;
		return this;
	}

	@RequestMapping("/location.do")
	public String execute(Map<String, Object> model) throws Exception {

		model.put("locals", cinemaDao.selectListLocal());
		model.put("cinemas", cinemaDao.selectListDefault());
		
		return "/cinema/page/LocationForm.jsp";
	}
}
