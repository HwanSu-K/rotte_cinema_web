package spms.etc;

import org.mindrot.jbcrypt.BCrypt;

public class Encrypt{

	public String encrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public boolean isMatch(String password, String hashed) {
		return BCrypt.checkpw(password, hashed);
	}

}
