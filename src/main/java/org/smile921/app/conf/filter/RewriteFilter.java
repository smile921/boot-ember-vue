package org.smile921.app.conf.filter;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class RewriteFilter implements Filter {

    public static final String REWRITE_TO = "rewriteURL";

    public static final String REWRITE_PATTERNS = "rewritePatterns";

    private Set<String> urlPatterns = null ;

    private String rewriteTo = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        rewriteTo = filterConfig.getInitParameter(REWRITE_TO);
        String exceptURL = filterConfig.getInitParameter(REWRITE_PATTERNS);
        if(!StringUtils.isEmpty(exceptURL)){
            urlPatterns = Collections.unmodifiableSet(
                    new HashSet<>(Arrays.asList(exceptURL.split(";",0))));

        }else {
            urlPatterns = Collections.emptySet();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getServletPath();
        String context =  req.getContextPath();
        if(isMatch(urlPatterns,servletPath)){
            // 匹配到的路径重写
            req.getRequestDispatcher(context+ rewriteTo).forward(req,response);
        }else {
            chain.doFilter(request,response);
        }
    }

    /**
     *
     * @param patterns
     * @param path
     * @return true  匹配，false 不匹配
     */
    private boolean isMatch(Set<String> patterns, String path) {
        if(null== patterns){
            return false;
        }
        for(String url : patterns){
            if(url.startsWith("/*")){
                String name = url.substring(0,url.length()-2);
                if(path.contains(name)){
                    return true;
                }
            }else{
                Pattern pattern = Pattern.compile(url);
                if(pattern.matcher(path).matches()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
