package nirmalya.aatithya.restmodule.filter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/*")
public class TokenValidationFilter implements Filter {
	 @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {
	        HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpSession session = httpRequest.getSession(false);
	        if (session != null) {
	            String username = (String) session.getAttribute("username");
	            System.out.println("USER NAME======== "+username);
	            String sessionToken = (String) session.getAttribute("loginToken");
System.out.println("SESSION TOKEN------= "+sessionToken);
	            // Fetch the latest token from the database
	            String latestToken = fetchLatestTokenFromDatabase(username);
	            System.out.println("LATEST TOKEN"+latestToken);
	            if (latestToken != null && !latestToken.equals(sessionToken)) {
	                // Invalidate the session if the token doesn't match
	                session.invalidate();
	            }
	        }
	        chain.doFilter(request, response);
	    }

	    private String fetchLatestTokenFromDatabase(String username) {
	        // Implement this method to fetch the latest token from the database
	        // Example using DAO or service layer
	        return ""; // Replace with actual token fetching logic
	    }

	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	        // Initialization code, if needed
	    }

	    @Override
	    public void destroy() {
	        // Cleanup code, if needed
	    }
	}
