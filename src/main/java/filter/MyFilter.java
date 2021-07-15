package filter;

import application.MyApplication;
import controller.IController;
import org.thymeleaf.ITemplateEngine;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {
    private ServletContext servletContext;
    private MyApplication application;

    public void init(final FilterConfig filterConfig) {
        this.servletContext = filterConfig.getServletContext();
        this.application = new MyApplication(this.servletContext);
    }

    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) {
        try {
            if (!process((HttpServletRequest) request, (HttpServletResponse) response)) {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getRequestURI().startsWith("/css") || request.getRequestURI().startsWith("/images") || request.getRequestURI().startsWith("/favicon"))
            return false;
        IController controller = this.application.resolveControllerForRequest(request);
        ITemplateEngine templateEngine = this.application.templateEngine;
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        controller.process(request, response, this.servletContext, templateEngine);
        return true;
    }
}