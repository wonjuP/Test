package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;
import java.net.*;
import svc.*;
import vo.*;

@WebServlet("/mschdList")
public class MschdListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MschdListCtrl() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ArrayList<ScheduleInfo> scheduleInfo = new ArrayList<ScheduleInfo>();
		
		// 연도별     		등록 최신순 order by si_date desc  | 등록 오래된 순 order by si_date
		// 연도별 / 
		String schtype = request.getParameter("schtype"); 
		String where = "";	
		
		MschdListSvc mschdListSvc = new MschdListSvc();
		scheduleInfo = mschdListSvc.getMschdList(where);
		
		request.setAttribute("scheduleInfo", scheduleInfo);
	}
}
