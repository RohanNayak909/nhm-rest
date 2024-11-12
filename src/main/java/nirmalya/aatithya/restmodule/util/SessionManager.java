package nirmalya.aatithya.restmodule.util;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    // Explicitly specify the type arguments for ConcurrentHashMap
    private static Map<String, HttpSession> sessions = new ConcurrentHashMap<String, HttpSession>();

    public static HttpSession getSessionByUsername(String username) {
    	System.out.println("getSessionByUsername===== "+username);
        return sessions.get(username);
    }

    public static void registerSession(String username, HttpSession session) {
        sessions.put(username, session);
    }

    public static void removeSession(String username) {
        sessions.remove(username);
    }
}

