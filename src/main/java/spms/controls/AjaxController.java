package spms.controls;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.response.Payment;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import spms.dao.CinemaDao;
import spms.dao.CustomerDao;
import spms.dao.LikeDao;
import spms.dao.MovieDao;
import spms.dao.PayDao;
import spms.dao.PayTypeDao;
import spms.dao.ReservDao;
import spms.dao.ReviewDao;
import spms.dao.TheaterDao;
import spms.etc.Encrypt;
import spms.etc.IamPay;
import spms.etc.MailSend;
import spms.etc.Rand;
import spms.fcm.FcmService;
import spms.vo.Cinema;
import spms.vo.Customer;
import spms.vo.Like;
import spms.vo.Movie;
import spms.vo.Pay;
import spms.vo.PayType;
import spms.vo.Reserv;
import spms.vo.Review;
import spms.vo.Token;

@Controller
public class AjaxController {

	MovieDao movieDao;
	ReviewDao reviewDao;
	TheaterDao theaterDao;
	LikeDao likeDao;
	CustomerDao customerDao;
	CinemaDao cinemaDao;
	PayTypeDao payTypeDao;
	ReservDao reservDao;
	PayDao payDao;

	@Autowired
	FcmService fcmService;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	public AjaxController setPayTypeDao(PayTypeDao payTypeDao) {
		this.payTypeDao = payTypeDao;
		return this;
	}

	@Autowired
	public AjaxController setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}

	@Autowired
	public AjaxController setReviewDao(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
		return this;
	}

	@Autowired
	public AjaxController setTheaterDao(TheaterDao theaterDao) {
		this.theaterDao = theaterDao;
		return this;
	}

	@Autowired
	public AjaxController setLikeDao(LikeDao likeDao) {
		this.likeDao = likeDao;
		return this;
	}

	@Autowired
	public AjaxController setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
		return this;
	}

	@Autowired
	public AjaxController setCinemaDao(CinemaDao cinemaDao) {
		this.cinemaDao = cinemaDao;
		return this;
	}

	@Autowired
	public AjaxController setReservDao(ReservDao reservDao) {
		this.reservDao = reservDao;
		return this;
	}

	@Autowired
	public AjaxController setPayDao(PayDao payDao) {
		this.payDao = payDao;
		return this;
	}

	@RequestMapping(value = "/movieobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getMovieObject(Movie movie) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Movie detailInfo = movieDao.selectOneDefault(movie.getIndex());
		map.put("movie", detailInfo);
		return map;
	}

	@RequestMapping(value = "/moviesobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getMoviesObject(String type) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", type);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("movies", movieDao.selectList(paramMap));
		return map;
	}

	@RequestMapping(value = "/moviestitle.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getMoviesTitle(String search) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("search", search);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("movies", movieDao.selectListTitle(paramMap));
		return map;
	}

	@RequestMapping(value = "/reviewsobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getReviewsObject(String index, String order) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("index", index);
		paramMap.put("order", order);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("reviews", reviewDao.selectList(paramMap));
		map.put("count", reviewDao.selectOneCount(paramMap));
		return map;
	}

	@RequestMapping(value = "/reviewobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object setReviewObject(HttpSession session, Review review) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) {
			review.setIndexCustomer(customer.getIndex());
			review.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

			reviewDao.insert(review);
			map.put("result", "success");
			return map;
		}
		map.put("result", "fail");
		return map;
	}

	@RequestMapping(value = "/theatersobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getTheaterObject(String cinema, String movie, String date) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();

		if (cinema != null && !cinema.equals("")) {
			paramMap.put("indexCinema", cinema);
		}

		if (movie != null && !movie.equals("")) {
			paramMap.put("indexMovie", movie);
		}

		if (date != null && !date.equals("")) {
			paramMap.put("date", date);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("theaters", theaterDao.selectList(paramMap));
		return map;
	}

	@RequestMapping(value = "/likeobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getLikeObject(HttpSession session, Like like) throws Exception {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) {
			like.setIndexCustomer(customer.getIndex());
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (likeDao.selectOne(like) != null) {
				likeDao.delete(like);
				map.put("return", 0);

			} else {
				likeDao.insert(like);
				map.put("return", 1);
			}
			map.put("count", likeDao.selectOneCount(like).getCount());

			return map;
		}
		return -1;
	}

	@RequestMapping(value = "/finderobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getfinderObject(Customer customer) throws Exception {		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("customer", customerDao.finder(customer));
		
		if(customer.getEmail() != null) {
			
			// 랜덤키 12자리 발생후 등록
			customer.setKey(Rand.code());
			// 입력받은 패스워드를 암호화해서 저장.
			customerDao.updateKey(customer);
			String title = "비밀번호 변경 메일입니다.";
			String content = "https://kumas.dev/rotte_cinema/password.do?key=" + customer.getKey();
			new MailSend().send(mailSender, customer.getEmail(), title, content);
		}
		return map;
	}

	@RequestMapping(value = "/emailobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getemailCheckObject(String email) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("email", email);
		Customer customer = customerDao.email(paramMap);

		if (customer == null) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/registrationobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getregistrationObject(Customer customer) throws Exception {

		if (customer != null) {

			// 랜덤키 12자리 발생후 등록
			customer.setKey(Rand.code());
			// 입력받은 패스워드를 암호화해서 저장.
			customer.setPassword(new Encrypt().encrypt(customer.getPassword()));
			String title = "가입 확인 메일입니다.";
			String content = "https://kumas.dev/rotte_cinema/registration.do?key=" + customer.getKey();
			new MailSend().send(mailSender, customer.getEmail(), title, content);
			return customerDao.insert(customer);
		}
		return -1;
	}

	@RequestMapping(value = "/loginobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getregistrationObject(String email, String password, String token, HttpSession session)
			throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Customer customer = customerDao.exist(email);
		if (customer != null && token != null) {
			if (new Encrypt().isMatch(password, customer.getPassword())) {
				customer.setPassword("");
				session.setAttribute("customer", customer);
				customer.setToken(token);
				Customer customerToken = customerDao.existToken(token);
				if (customerToken == null) {
					customerDao.insertToken(customer);
				} else {
					customerDao.updateToken(customer);
				}

				map.put("result", "connect");
				map.put("name", customer.getName());
				return map;
			}

		} else if (token != null) {
			session.invalidate();
			customerDao.deleteToken(new Customer().setToken(token));

			map.put("result", "disconnect");
			map.put("name", null);
			return map;
		}

		map.put("result", "fail");
		map.put("name", null);
		return map;
	}

	@RequestMapping(value = "/cinemaobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getCinemaObject(Cinema cinema) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Cinema detailInfo = cinemaDao.selectOneDefault(cinema.getIndex());
		map.put("cinema", detailInfo);
		return map;
	}

	@RequestMapping(value = "/tokentest.do")
	@ResponseBody
	public Object setPushObject(int no, String title, String body) throws Exception {

		List<Token> tokens = customerDao.selectList(no);
		for (Token token : tokens) {
			fcmService.sendTargetMessage(token.getValue(), title, body, "");
		}
		return "success";
	}

	@RequestMapping(value = "/customerobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getCustomerObject(HttpSession session, Like like) throws Exception {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) {
			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("customer", customer);

			return map;
		}
		return -1;
	}

	@RequestMapping(value = "/paytypeobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getPayTypeObject() throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("paytype", payTypeDao.selectList());

		return map;
	}
	
	@RequestMapping(value = "/paytestobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getPayTestObject() throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();

		
		
		//map.put("payobject", ((HashMap<String, Object>) IamPay.getPaymentObject("imp_9021439112036")).get("result"));
		//map.put("pay", payment.getAmount());
		

		return map;
	}
	
	@RequestMapping(value = "/reservobject.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getReservObject(int index) throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();

		
		map.put("reservs", reservDao.selectList(index));

		return map;
	}

	@RequestMapping(value = "/reservation.do", method = RequestMethod.POST)
	@ResponseBody
	public Object getReservationObject(@RequestParam String jsonData, String uid, HttpSession session)
			throws Exception {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) {
			HashMap<String, Object> map = new HashMap<String, Object>();

			// 직렬화 시켜 가져온 오브젝트 배열을 JSONArray 형식으로 바꿔준다.
			JSONArray array = JSONArray.fromObject(jsonData);

			List<Reserv> reservs = new ArrayList<Reserv>();
			List<PayType> payType = payTypeDao.selectList(); 
			
			// 실제로 결제한 티켓의 금액을 합산.
			int totalAmount = 0;
			for (int i = 0; i < array.size(); i++) {

				// JSONArray 형태의 값을 가져와 JSONObject 로 풀어준다.
				JSONObject obj = (JSONObject) array.get(i);

				Reserv reserv = new Reserv();

				reserv.setSeatX((int) obj.get("seatX"));
				reserv.setSeatY((int) obj.get("seatY"));
				reserv.setPayCategory((int) obj.get("payCategory"));
				reserv.setShowingIndex((int) obj.get("showingIndex"));
				totalAmount += payType.get(reserv.getPayCategory() - 1).getAmount();
				reservs.add(reserv);
			}

			// 예약 되어있는 좌석이 있는지 체크.
			for (Reserv reserv : reservs) {
				if (reservDao.selectOne(reserv) != null) {
					map.put("result", "fail");
					map.put("message", "선택된 좌석이 이미 예약 되어 있습니다.");
					return map;
				}
			}
			// 실제로 결제 되어있는지 체크.
			@SuppressWarnings("unchecked")
			Object iamPay = ((HashMap<String, Object>) IamPay.getPaymentObject(uid)).get("result");

			if (iamPay == null) {
				map.put("result", "fail");
				map.put("message", "결제내역이 없습니다.");
				return map;
			}
			
			Payment payment = (Payment)iamPay;

			// 결제되어야 할 금액이 맞는지 체크. 다르다면 결제를 취소하고 오류를 리턴.
			if(totalAmount != payment.getAmount().intValue())
			{
				IamPay.getCancelPaymentObject(uid);
				map.put("result", "fail");
				map.put("message", "결제금액이 불일치 합니다.");
				return map;
			}
			
			Pay pay = new Pay();
			pay.setAmount(payment.getAmount().intValue());
			pay.setCustomerIndex(customer.getIndex());
			pay.setUid(uid);
			pay.setCardName(payment.getCardName());
			pay.setCardNumber(payment.getCardNumber());

			payDao.insert(pay);

			// 예약 처리
			for (Reserv reserv : reservs) {
				reserv.setPayIndex(pay.getIndex());
				reservDao.insert(reserv);

			}

			map.put("result", "success");
			map.put("amount", pay.getAmount());
			map.put("reservs", reservs);
			
			IamPay.getCancelPaymentObject(uid);
			
			return map;
		}
		return -1;
	}
}
