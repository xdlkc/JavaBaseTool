package per.lkc.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2020/2/19.
 *
 * @author zhangjunbo.
 */
@Slf4j
public class AFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("go into afilter...");
        log.debug("sadasdasdad");
    }

    @Override
    public void destroy() {

    }
}
