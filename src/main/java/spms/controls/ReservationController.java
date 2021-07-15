package spms.controls;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spms.dao.PayTypeDao;
import spms.dao.ReservationDao;
import spms.vo.Reservation;

@Controller
public class ReservationController {
	String[] week = { "일", "월", "화", "수", "목", "금", "토" };
	
	ReservationDao reservationDao;
	
	@Autowired
	public ReservationController setRevDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
		return this;
	}

	@RequestMapping("/reservation.do")
	public String movieLoad(int index, Map<String, Object> model) throws Exception {
		Reservation rev = reservationDao.selectOne(index);
		DateFormat df = new SimpleDateFormat("yyyy-MM-ddHH:mm");
		DateFormat time = new SimpleDateFormat("HH:mm");
		Date date = df.parse(rev.getDate() + rev.getStartTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		rev.setWeek(week[cal.get(Calendar.DAY_OF_WEEK) -1]);
		cal.add(Calendar.MINUTE, rev.getMovieRunningTime());
		rev.setEndTime(time.format(cal.getTime()));
		rev.setDate(rev.getDate().replaceAll("-", "."));

		if(date.compareTo(new Date()) > 0) {
			model.put("reservation", rev);	
			return "/cinema/page/ReservationForm.jsp";
		} else {
			return "redirect:/main.do";
		}
	}
}
