package com.rathod.services;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rathod.dbconnect.ConnectDB;


public class aarogyaAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public aarogyaAdd() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String a_name = request.getParameter("name");
		String a_mob = request.getParameter("phone");
		String a_village = request.getParameter("village");
		String a_district = request.getParameter("district");
		String a_email = request.getParameter("email");
		String a_pass = request.getParameter("password");
		
		try 
		{
			Connection con = ConnectDB.getConnect();
			PreparedStatement ps1 = con.prepareStatement("insert into aarogya values(?,?,?,?,?,?,?)");
			ps1.setInt(1, 0);
			ps1.setString(2, a_name);
			ps1.setString(3, a_email);
			ps1.setString(4, a_mob);
			ps1.setString(5, a_pass);
			ps1.setString(6, a_village);
			ps1.setString(7, a_district);
			int i = ps1.executeUpdate();
			if(i>0){
				response.sendRedirect("success.html");
			}else{
				response.sendRedirect("failed.html");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
