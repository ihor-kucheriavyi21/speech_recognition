package ihorko.work.db_learning.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class CookieUserFilter implements Filter {

    private static final String COOKIE_NAME = "dblearning_user_id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        // Перевіряємо чи є COOKIE
        Cookie[] cookies = httpReq.getCookies();
        boolean found = false;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (COOKIE_NAME.equals(c.getName())) {
                    found = true;
                    break;
                }
            }
        }

        // Якщо нема — створюємо
        if (!found) {
            Cookie newCookie = new Cookie(COOKIE_NAME, UUID.randomUUID().toString());
            newCookie.setMaxAge(60 * 60 * 24 * 30); // 30 днів
            newCookie.setPath("/");
            httpResp.addCookie(newCookie);
        }

        chain.doFilter(request, response);
    }
}
