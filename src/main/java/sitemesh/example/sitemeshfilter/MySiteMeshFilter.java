package sitemesh.example.sitemeshfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(urlPatterns = { "/*" })
public class MySiteMeshFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("filter#start");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        ResponseWrapper rw = new ResponseWrapper(res);
        chain.doFilter(request, rw);
        
        String content = rw.getContent();
        
        if(req.getRequestURI().endsWith("index")) {
            content = "<main class=\"col-md-9 ms-sm-auto col-lg-10 px-md-4\">" + content + "</main>";
        }
        request.setAttribute("content", content);
//        StringBuilder sb = new StringBuilder();
//        String line = "";
//        PrintWriter pw = response.getWriter();
        // 讀取 layout.jsp 裡的內容
//        BufferedReader in = 
//                new BufferedReader(new FileReader("/Users/huangweixin/eclipse-workspace/sitemeshtest/src/main/webapp/WEB-INF/jsp/layout.jsp"));
//        // 由於內容都變成字串所以會讀取到 <%@ %> 裡的內容 所以先把第一行讀取掉
//        in.readLine();
//        // 接著把內容用迴圈取出
//        while ((line = in.readLine()) != null) {
//            sb.append(line);
//        }
//        in.close();
//        System.out.println("內容=> " + sb.toString());
//
//        if (req.getRequestURI().endsWith("order")) {
//            System.out.println("是order");
//            pw.write(sb.toString().replace("<mysitemesh:body/>", content));
//        } else {
//            System.out.println("不是order");
//            pw.write(sb.toString().replace("<mysitemesh:body/>",
//                    "<main class=\"col-md-9 ms-sm-auto col-lg-10 px-md-4\">" + content + "</main>"));
//        }
//        //強制將內容寫出去
//        pw.flush();
//        pw.close();
        request.getRequestDispatcher("/WEB-INF/jsp/layout.jsp").forward(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}