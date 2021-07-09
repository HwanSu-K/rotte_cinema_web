package spms.etc;

import java.util.Random;

public class Rand {
	public static String code() {
		Random rnd = new Random();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 50; i++) {
			if (rnd.nextBoolean()) {
				buf.append((char) ((int) (rnd.nextInt(26)) + 65));
			} else {
				buf.append((char) ((int) (rnd.nextInt(26)) + 97));
			}
		}
		
		return buf.toString();
	}
}
