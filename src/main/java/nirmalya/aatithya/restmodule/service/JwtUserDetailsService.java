package nirmalya.aatithya.restmodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nirmalya.aatithya.restmodule.user.dao.UserLoginDao;
import nirmalya.aatithya.restmodule.user.model.User;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserLoginDao userLoginDao;

    // Map to track failed login attempts
    private ConcurrentHashMap<String, Integer> failedAttempts = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Long> lockTime = new ConcurrentHashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        System.out.println("username " + username);
        try {
            user = userLoginDao.loadUserByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("CustomUserDetailsService====" + user);

        if (user != null && (user.getUserMobile().equals(username) || user.getUser().equals(username) || user.getUserEmail().equals(username))) {
            return new CustomUserDetails(user);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public void validateLogin(String username, String password) throws BadCredentialsException {
        if (lockTime.containsKey(username)) {
            long lockDuration = System.currentTimeMillis() - lockTime.get(username);
            if (lockDuration < 30 * 60 * 1000) { // 30 minutes
                throw new BadCredentialsException("Account is locked. Try again later.");
            } else {
                lockTime.remove(username); // Clear lock if duration is over
                failedAttempts.remove(username); // Reset failed attempts
            }
        }

        // Add your password validation logic here
        // If the password is incorrect
        if (!isPasswordValid(username, password)) {
            failedAttempts.merge(username, 1, Integer::sum);
            if (failedAttempts.get(username) >= 3) {
                lockTime.put(username, System.currentTimeMillis()); // Lock the account
                throw new BadCredentialsException("Account is locked due to multiple failed attempts. Try again later.");
            }
            throw new BadCredentialsException("Invalid username or password.");
        }

        // If login is successful, reset failed attempts
        failedAttempts.remove(username);
    }

    private boolean isPasswordValid(String username, String password) {
        // Implement your password checking logic here (e.g., check against hashed passwords)
        return true; // This should return true if the password is valid, otherwise false.
    }
}
