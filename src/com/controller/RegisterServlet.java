package com.controller;

import com.dao.DBConnection;

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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String message = null;
		String userName = request.getParameter("newUserName");
		String passWord = request.getParameter("newPassWord");
		String confirmPassWord = request.getParameter("confirmPassWord");
		System.out.println(userName + "" + passWord + "" + confirmPassWord);
		if(userName==null || userName.trim().length()==0 || passWord==null ||
				passWord.trim().length()==0 || confirmPassWord==null ||
				confirmPassWord.trim().length()==0) {
			message = URLEncoder.encode("密码账号不能为空！", "UTF-8");
			out.print("<script>alert(decodeURIComponent('"+message+"'));window.location.href='register.jsp'</script>");

		} else if(!passWord.equals(confirmPassWord)) {
			message = URLEncoder.encode("两次密码不相同！", "UTF-8");
			out.print("<script>alert(decodeURIComponent('"+message+"'));window.location.href='register.jsp'</script>");
		} else {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rSet = null;
			try {
				con = DBConnection.getConnection();
				String sql = "select * from account where account_id=?";
				pStatement = con.prepareStatement(sql);
				pStatement.setString(1, userName);
				rSet = pStatement.executeQuery();
				if(rSet.next()) {
					message = URLEncoder.encode("已存在该用户！", "UTF-8");
					out.print("<script>alert(decodeURIComponent('"+message+"'));window.location.href='register.jsp'</script>");
				} else {
					sql = "insert into account(account_id, pwd) values(?, ?)";
					pStatement = con.prepareStatement(sql);
					pStatement.setString(1, userName);
					pStatement.setString(2, passWord);
					pStatement.executeUpdate();
					session.setAttribute("userName", userName);
					message = URLEncoder.encode("注册成功", "UTF-8");
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
