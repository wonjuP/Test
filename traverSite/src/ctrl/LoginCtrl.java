package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/login")
public class LoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginCtrl() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid").trim().toLowerCase();
		String pwd = request.getParameter("pwd").trim();
		String url = request.getParameter("url").replace('$', '&');
		
		LoginSvc loginSvc = new LoginSvc(); 
		MemberInfo loginInfo = loginSvc.getLoginMember(uid, pwd);

		if (loginInfo != null) { 	// 로그인 성공시
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", loginInfo);
			session.setMaxInactiveInterval(1800);
			response.sendRedirect(url);
			
		} else {	// 로그인 실패시
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script> alert('아이디는 또는 비밀번호를 잘못 입력하셨습니다. \\n 입력하신 내용을 다시 확인해 주세요.'); history.back(); </script>");
		}
	}
}
