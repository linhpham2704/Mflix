package controller;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import utils.MyCookie;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyController implements IController{
    WebContext ctx;
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, ITemplateEngine templateEngine) throws Exception {
        String lang = request.getParameter("lang");
        Locale locale = new Locale("en");

        if (lang != null) {
            locale = new Locale(lang);
            response.addCookie(new Cookie("lang", lang));
        } else {
            if (new MyCookie().getCookie(request, "lang") != null)
                locale = new Locale(new MyCookie().getCookie(request, "lang").getValue());
        }
        ctx = new WebContext(request, response, servletContext, locale);
    }

}
