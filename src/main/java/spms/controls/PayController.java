package spms.controls;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.PayDao;
import spms.dao.ReservDao;
import spms.dao.ReservationDao;
import spms.vo.Customer;
import spms.vo.Pay;
import spms.vo.Reserv;
import spms.vo.Reservation;

@Controller
public class PayController {
	String[] week = { "일", "월", "화", "수", "목", "금", "토" };
	
	PayDao payDao;
	ReservDao reservDao;
	ReservationDao reservationDao;

	@Autowired
	public PayController setPayDao(PayDao payDao) {
		this.payDao = payDao;
		return this;
	}
	
	@Autowired
	public PayController setReservDao(ReservDao reservDao) {
		this.reservDao = reservDao;
		return this;
	}
	
	@Autowired
	public PayController setRevDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
		return this;
	}

	@RequestMapping("/pay.do")
	public String execute(int index, Map<String, Object> model, HttpSession session) throws Exception {
		Customer customer = (Customer) session.getAttribute("customer");
		Pay pay = payDao.selectOne(index);
		
		if (customer == null || pay == null || customer.getIndex() != pay.getCustomerIndex()) {
			return "redirect:/error.do";
		}

		List<Reserv> reservs = reservDao.selectListPay(pay.getIndex());
		
		Reservation rev = reservationDao.selectOne(reservs.get(0).getShowingIndex());
		DateFormat df = new SimpleDateFormat("yyyy-MM-ddHH:mm");
		DateFormat time = new SimpleDateFormat("HH:mm");
		Date date = df.parse(rev.getDate() + rev.getStartTime());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		rev.setWeek(week[cal.get(Calendar.DAY_OF_WEEK) -1]);
		cal.add(Calendar.MINUTE, rev.getMovieRunningTime());
		rev.setEndTime(time.format(cal.getTime()));
		rev.setDate(rev.getDate().replaceAll("-", "."));

		String reservSeat = "";
		String reservPerson = "";
		int adultCount = 0;
		int teenagerCount = 0;
		
		for (Reserv reserv : reservs) {
			reservSeat += (char)(reserv.getSeatY() + 64) + "열" + reserv.getSeatX() + "번 ";
			if(reserv.getPayCategory() == 1) {
				adultCount++;
			} else {
				teenagerCount++;
			}
		}
		if(adultCount > 0) {
			reservPerson += "성인 " + adultCount +"명 ";
		}
		
		if(teenagerCount > 0) {
			reservPerson += "청소년 " + teenagerCount +"명";
		}
		
		model.put("customer", customer);
		model.put("pay", pay);
		model.put("reservSeat", reservSeat);
		model.put("reservPerson", reservPerson);
		model.put("reservation", rev);	
		
		
		
		return "/cinema/page/PayForm.jsp";
	}
}
