package spms.fcm;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.api.client.util.Value;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class FcmService {

	public void sendTargetMessage(String targetToken, String title, String body, String image)
			throws FirebaseMessagingException, IOException {
		try {
			GoogleCredentials googleCredentials = GoogleCredentials
					.fromStream(new ClassPathResource("lottecinema-firebase-adminsdk.json").getInputStream());
			FirebaseOptions options = FirebaseOptions.builder().setCredentials(googleCredentials).build();

			if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options);
			}
			Message msg = Message.builder()
					.setAndroidConfig(AndroidConfig.builder()
							.setTtl(3600 * 1000) //
							.setPriority(AndroidConfig.Priority.NORMAL)
							.setNotification(AndroidNotification.builder()
									.setTitle(title)
									.setBody(body)
									.build())
							.build())
					.setToken(targetToken)
					.build();
			String response = FirebaseMessaging.getInstance().send(msg);
			System.out.println(response);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
