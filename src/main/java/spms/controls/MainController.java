package spms.controls;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.BannerDao;
import spms.dao.MovieDao;

@Controller
public class MainController {
	MovieDao movieDao;
	BannerDao bannerDao;

	@Autowired
	public MainController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}
	
	@Autowired
	public MainController setBannerDao(BannerDao bannerDao) {
		this.bannerDao = bannerDao;
		return this;
	}

	@RequestMapping("/main.do")
	public String execute(Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", "qration");
		
		model.put("movies", movieDao.selectList());
		
		model.put("qrations", movieDao.selectList(paramMap));
		
		model.put("banners", bannerDao.selectList(null));
		return "/cinema/page/MainForm.jsp";
	}
}
