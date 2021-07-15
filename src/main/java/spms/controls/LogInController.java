package spms.controls;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import spms.dao.CustomerDao;
import spms.dao.LikeDao;
import spms.dao.MovieDao;
import spms.dao.PayDao;
import spms.dao.ReservDao;
import spms.dao.ReservationDao;
import spms.dao.ReviewDao;
import spms.vo.Customer;
import spms.vo.Like;
import spms.vo.Movie;
import spms.vo.Pay;
import spms.vo.Reserv;
import spms.vo.ReservItem;
import spms.vo.Reservation;

@Controller
@SessionAttributes("customer")
public class LogInController {

	CustomerDao customerDao;
	ReservDao reservDao;
	PayDao payDao;
	ReservationDao reservationDao;
	LikeDao likeDao;
	ReviewDao reviewDao;
	MovieDao movieDao;
	
	@Autowired
	public LogInController setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
		return this;
	}
	
	@Autowired
	public LogInController setReservDao(ReservDao reservDao) {
		this.reservDao = reservDao;
		return this;
	}

	@Autowired
	public LogInController setPayDao(PayDao payDao) {
		this.payDao = payDao;
		return this;
	}

	@Autowired
	public LogInController setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
		return this;
	}
	
	@Autowired
	public LogInController setLikeDao(LikeDao likeDao) {
		this.likeDao = likeDao;
		return this;
	}
	
	@Autowired
	public LogInController setReviewDao(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
		return this;
	}
	
	@Autowired
	public LogInController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginForm(@CookieValue(value = "email", required = false) String email, HttpSession session, Map<String, Object> model) throws Exception {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer == null) {
			if (email != null) {
				model.put("email", email);
				model.put("saveEmailState", "checked");
			}
			return "/cinema/page/LoginForm.jsp";
		} else {
			
			String[] week = { "일", "월", "화", "수", "목", "금", "토" };


			List<ReservItem> reservItems = new ArrayList<ReservItem>();
			List<ReservItem> onReservItems = new ArrayList<ReservItem>();
			List<Pay> pays = payDao.selectList(customer.getIndex());

			for (Pay pay : pays) {
				if (pay == null) {

					break;
				}

				List<Reserv> reservs = reservDao.selectListPay(pay.getIndex());
				Reservation rev = reservationDao.selectOne(reservs.get(0).getShowingIndex());
				DateFormat df = new SimpleDateFormat("yyyy-MM-ddHH:mm");
				DateFormat time = new SimpleDateFormat("HH:mm");
				Date date = df.parse(rev.getDate() + rev.getStartTime());
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);

				rev.setWeek(week[cal.get(Calendar.DAY_OF_WEEK) - 1]);
				cal.add(Calendar.MINUTE, rev.getMovieRunningTime());
				rev.setEndTime(time.format(cal.getTime()));

				String reservSeat = "";
				String reservPerson = "";
				int adultCount = 0;
				int teenagerCount = 0;

				for (Reserv reserv : reservs) {
					reservSeat += (char) (reserv.getSeatY() + 64) + "열" + reserv.getSeatX() + "번 ";
					if (reserv.getPayCategory() == 1) {
						adultCount++;
					} else {
						teenagerCount++;
					}
				}
				if (adultCount > 0) {
					reservPerson += "성인 " + adultCount + "명 ";
				}

				if (teenagerCount > 0) {
					reservPerson += "청소년 " + teenagerCount + "명";
				}


				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				ReservItem temp = new ReservItem()
								.setIndexMovie(rev.getIndexMovie())
								.setMovie(rev.getMovieTitle())
								.setDate(rev.getDate())
								.setTime(rev.getStartTime() + "-" + rev.getEndTime())
								.setWeek(rev.getWeek())
								.setCinema(rev.getCinemaTitle())
								.setTheater(rev.getTheaterTitle())
								.setCustomer(reservPerson)
								.setSeat(reservSeat)
								.setPoster(rev.getMoviePoster())
								.setAge(Integer.toString(rev.getMovieLimitAge()))
								.setDatePay(sdf.format(pay.getDate()))
								.setCancel(false);
				
				if(date.compareTo(new Date()) > 0) {
					onReservItems.add(temp);
					temp.setCancel(true);
				}
				
				reservItems.add(temp);
			}

			model.put("reservs", reservItems);
			model.put("onReservs", onReservItems);
			
	
			model.put("movies", movieDao.selectListLike(customer.getIndex()));
			model.put("likes", likeDao.selectList(customer.getIndex()));
			model.put("reviews", reviewDao.selectList(customer.getIndex()));
			return "/cinema/page/MyPageForm.jsp";
		}
	}
}
