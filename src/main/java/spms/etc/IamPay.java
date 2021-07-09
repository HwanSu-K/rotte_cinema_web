package spms.etc;

import java.io.IOException;
import java.util.HashMap;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

public class IamPay {
	final static String API_KEY = "2051141026560563";
	final static String API_SECRET = "0ce02aad021de4ecdad5443b34c6aa30147b7ac431754e1bd1080f43495cd4b4479cd41afff6b7b2";
	
	public static Object getPaymentObject(String uid) throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();

		IamportClient client = new IamportClient(API_KEY, API_SECRET);

		String imp_uid = uid;
		try {
			IamportResponse<Payment> payment_response = client.paymentByImpUid(imp_uid);

			map.put("result", payment_response.getResponse());

		} catch (IamportResponseException e) {
			System.out.println(e.getMessage());

			switch (e.getHttpStatusCode()) {
			case 401:
				break;
			case 500:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	public static Object getCancelPaymentObject(String uid) throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();

		IamportClient client = new IamportClient(API_KEY, API_SECRET);

		String imp_uid = uid;

		CancelData cancel_data = new CancelData(imp_uid, true); // imp_uid를 통한 전액취소

		try {
			IamportResponse<Payment> payment_response = client.cancelPaymentByImpUid(cancel_data);

			map.put("result", payment_response.getResponse());

		} catch (IamportResponseException e) {
			System.out.println(e.getMessage());

			switch (e.getHttpStatusCode()) {
			case 401:
				break;
			case 500:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}
}
