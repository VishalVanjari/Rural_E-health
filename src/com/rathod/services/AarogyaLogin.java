package com.rathod.services;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rathod.dbconnect.ConnectDB;


public class AarogyaLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AarogyaLogin() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		String em = request.getParameter("email");
		String pass = request.getParameter("password");
		try {
			Connection con = ConnectDB.getConnect();
			PreparedStatement ps1 = con.prepareStatement("select * from aarogya where aemail=? and apass=? ");
			ps1.setString(1, em);
			ps1.setString(2, pass);
			ResultSet rs1 = ps1.executeQuery();
			
			if(rs1.next())
			{
				UserData.setAid(rs1.getInt(1));
				response.sendRedirect("aarogyadashboard.html");
			}
			else
			{
				response.sendRedirect("aarogyalogin.html");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
