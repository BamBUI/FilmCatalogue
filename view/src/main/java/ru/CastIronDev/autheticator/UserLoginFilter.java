package ru.CastIronDev.autheticator;


import ru.CastIronDev.authenticator.domain.ShopUser;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {UserLoginFilter.USER_FILTER_URI + "*", UserLoginFilter.ADMIN_FILTER_URI + "*" })
public class UserLoginFilter implements Filter {

    public static final String USER_FILTER_URI = "/user/";
    public static final String ADMIN_FILTER_URI = "/admin/";
    @Inject
    private AuthenticatorBean authenticationBean;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        ShopUser.Role role = authenticationBean.getRole();
        if(role != null){
            String uri = request.getRequestURI();
            String beginOfAdminUri = request.getContextPath() + ADMIN_FILTER_URI;
            if(uri.startsWith (beginOfAdminUri) && role != ShopUser.Role.ADMIN){
                response.sendRedirect(request.getContextPath() + "/errors.xhtml");
                return;
            }

            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        authenticationBean.setRequestedPage(request.getRequestURI());
        response.sendRedirect(request.getContextPath() + "/login.xhtml");
    }

    @Override
    public void destroy() {
        //
    }
}
