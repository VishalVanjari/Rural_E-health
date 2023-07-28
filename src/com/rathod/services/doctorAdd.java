package com.rathod.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rathod.dbconnect.ConnectDB;


public class doctorAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public doctorAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		String a_name = request.getParameter("name");
		String a_mob = request.getParameter("phone");
		String a_city = request.getParameter("city");
		String a_spec = request.getParameter("specialization");
		String a_treat = request.getParameter("treatment");
		String a_email = request.getParameter("email");
		String a_pass = request.getParameter("password");
		
		try 
		{
			Connection con = ConnectDB.getConnect();
			PreparedStatement ps1 = con.prepareStatement("insert into doctor values(?,?,?,?,?,?,?,?)");
			ps1.setInt(1, 0);
			ps1.setString(2, a_name);
			ps1.setString(3, a_email);
			ps1.setString(4, a_mob);
			ps1.setString(5, a_pass);
			ps1.setString(6, a_city);
			ps1.setString(7, a_spec);
			ps1.setString(8, a_treat);
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
