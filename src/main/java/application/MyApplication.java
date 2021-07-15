package application;

import controller.HomeController;
import controller.IController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class MyApplication {

    public TemplateEngine templateEngine;
    private Map<String, IController> controllersByURL;

    public MyApplication(final ServletContext servletContext) {
        super();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));
        templateResolver.setCacheable(true);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
        this.controllersByURL = new HashMap<>();

        this.controllersByURL.put("/", new HomeController());
    }

    public IController resolveControllerForRequest(final HttpServletRequest request) {
        final String path = getRequestPath(request);
        return this.controllersByURL.get(path);
    }

    private static String getRequestPath(final HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        final String contextPath = request.getContextPath();
        final int fragmentIndex = requestURI.indexOf(';');
        if (fragmentIndex != -1)
            requestURI = requestURI.substring(0, fragmentIndex);

        if (requestURI.startsWith(contextPath)) {
            return requestURI.substring(contextPath.length());
        }
        return requestURI;
    }
}