package com.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload. SmartUploadException;

import java.io.IOException;

@WebServlet("/DownloadContact")
public class DownloadContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SmartUpload su = new SmartUpload();
		try {
			su.initialize(this.getServletConfig(), request, response);
			String path = "C:\\Users\\陈伟林\\IdeaProjects\\webDemo\\emailproxyweb\\upload\\";
			String filename = "test.xls";
			su.setContentDisposition(null);
			try {
				su.downloadFile(path + filename);
			} catch(SmartUploadException e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
