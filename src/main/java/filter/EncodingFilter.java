package filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 초기화 코드 (필요 시 작성)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 요청과 응답의 인코딩을 UTF-8로 설정합니다
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 다음 필터 또는 서블릿으로 요청을 전달합니다
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 필터 정리 코드 (필요 시 작성)
    }
}