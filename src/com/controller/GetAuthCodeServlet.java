package com.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GetAuthCodeServlet")
public class GetAuthCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetAuthCodeServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String authCode = AuthCode.getAuthCode();
		request.getSession().setAttribute("authCode", authCode);

		try {
			ImageIO.write(AuthCode.getAuthImg(authCode), "JPEG", response.getOutputStream());	//发送图片
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
