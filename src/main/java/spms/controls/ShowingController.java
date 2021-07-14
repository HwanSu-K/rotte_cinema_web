package spms.controls;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import spms.dao.ShowingDao;
import spms.vo.Showing;

@Controller
public class ShowingController {

	ShowingDao showingDao;
	
	@Autowired
	public ShowingController setPayTypeDao(ShowingDao showingDao) {
		this.showingDao = showingDao;
		return this;
	}
	
	@RequestMapping(value = "/showingobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object setShowingObject(String indexMovie, int indexTheater, String date) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("date", date);
		paramMap.put("indexTheater", indexTheater);
		if(showingDao.selectOneShowing(paramMap) != null)
		{
			map.put("result", "fail");
			map.put("message", "해당 일자에 이미 상영정보가 있습니다.");
			return map;
		}
		Showing showing;
		
		if(indexMovie == null) {
			showing = showingDao.selectOne(paramMap);	
		} else {
			showing = new Showing().setIndexMovie(Integer.parseInt(indexMovie));	
		}
		
		
		showing.setIndexTheater(indexTheater);
		showing.setDate(date);
		
		int hour = (int) (Math.random() * 2) + 8;
		for (int j = 0; j < (int)(Math.random() * 3) + 2; j++) {
			showing.setStartTime(String.format("%02d:%02d", hour += Rand(), Minute()));
			showingDao.insert(showing);
		}
		
		map.put("result", "succes");
		map.put("message", "성공적으로 추가 되었습니다.");
		
		return map;
	}
	
	int Minute() {
		return (int)(Math.random() * 5) * 10;
	}
	
	int Rand() {
		return (int)(Math.random() * 3) + 2;
	}
}
