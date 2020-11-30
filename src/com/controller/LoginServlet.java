package com.controller;

import com.dao.DBConnection;
import com.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String inputCode = request.getParameter("inputCode");
		String message = null;
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute("authCode");
		PrintWriter out = response.getWriter();
		if(userName==null || userName.trim().length()==0 || passWord==null ||
				passWord.trim().length()==0 || inputCode == null || inputCode.trim().length()==0 ||
				code==null || code.trim().length()==0) {
			message = URLEncoder.encode("密码账号不能为空！", "UTF-8");
			out.print("<script>alert(decodeURIComponent('"+message+"'));window.location.href='adminlogin.jsp'</script>");
		} else if(!code.equals(inputCode)) {
			message = URLEncoder.encode("验证码不对", "UTF-8");
			out.print("<script>alert(decodeURIComponent('"+message+"'));window.location.href='adminlogin.jsp'</script>");
		} else {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rSet = null;
			try {
				con = DBConnection.getConnection();
				String sql = "select * from account where account_id=? and pwd=?";
				pStatement = con.prepareStatement(sql);
				pStatement.setString(1, userName);
				pStatement.setString(2, passWord);
				rSet = pStatement.executeQuery();
				if(rSet.next()) {
					session.setAttribute("userName", userName);
					Constant.userName = userName;
					response.sendRedirect("index.jsp");
				} else {
					//System.out.println("不存在该用户");
					//response.sendRedirect("adminlogin.jsp");
					message = URLEncoder.encode("不存在该用户", "UTF-8");
					out.print("<script>alert(decodeURIComponent('"+message+"'));window.location.href='adminlogin.jsp'</script>");
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.free(con, null, null);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
