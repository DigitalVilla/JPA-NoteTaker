package my.utils;

//
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//
public class CookieHelper {

    /**
     * This method adds a cookie to the browser
     *
     * @param name name of the cookie
     * @param vlaue of the cookie
     * @param days how many days before expire
     * @param response
     */
    public void addCookie(String name, String vlaue, int days, HttpServletResponse response) {
        // create cookie
        Cookie cookie = new Cookie(name, vlaue);
        // set life span 100 days
        cookie.setMaxAge(60 * 60 * 24 * days);
        // send cookie to browser
        response.addCookie(cookie);
    }

    /**
     * This method deletes a cookie by name
     *
     * @param macth name of cookie to delete
     * @param request
     * @param response
     */
    public void deleteCookies(String macth, HttpServletRequest request, HttpServletResponse response) {
        for (Cookie c : request.getCookies()) {
            if (c.getName().equals(macth)) {
                c.setMaxAge(0);
                response.addCookie(c);
                return;
            }
        }
    }

    /**
     * This method finds a cookie and makes a new cookie with the same value
     *
     * @param macth cookie to find
     * @param newName new cookie
     * @param request
     * @param response
     * @return it returns true if succesfully created
     */
    public boolean makeCookieByValue(String macth, String newName,
            HttpServletRequest request, HttpServletResponse response) {
        for (Cookie c : request.getCookies()) {
            if (c.getName().equals(macth)) {
                addCookie(newName, c.getValue(), 1, response);
                return true;
            }
        }
        return false;
    }

}
