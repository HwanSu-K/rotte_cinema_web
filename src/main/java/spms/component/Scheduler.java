package spms.component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import spms.dao.CustomerDao;
import spms.dao.PushDao;
import spms.fcm.FcmService;
import spms.vo.Push;
import spms.vo.Token;

@Component
public class Scheduler {

	PushDao pushDao;
	CustomerDao customerDao;
	
	@Autowired
	public Scheduler setPushDao(PushDao pushDao) {
		this.pushDao = pushDao;
		return this;
	}
	
	@Autowired
	public Scheduler setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
		return this;
	}
	
	@Autowired
	FcmService fcmService;
	
    @Scheduled(cron="5 * * * * *")
    public void HofScheduler() {
    	
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//    	
//    	try {
//			List<Push> pushs = pushDao.selectList();
//			
//			for (Push push : pushs) {
//				Calendar movieDateTime = Calendar.getInstance();
//				movieDateTime.setTime(sdf.parse(push.getDate()));
//
//				Calendar now = Calendar.getInstance();
//				movieDateTime.setTime(new Date());
//				
//				movieDateTime.add(Calendar.MINUTE, +30);
//				
//				if(movieDateTime.compareTo(now) > 0) {
//					
//					List<Token> tokens = customerDao.selectList(push.getIndexCustomer());
//					
//					for (Token token : tokens) {
//						fcmService.sendTargetMessage(token.getValue(), "잠시후 영화가 시작됩니다", push.getTitle() + "/" + push.getDate(), "");
//						System.out.println(token.getValue());
//						System.out.println(push.getTitle());
//						System.out.println(push.getDate());
//					}
//					
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
}
