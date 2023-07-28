package com.rathod.services;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rathod.dbconnect.ConnectDB;


public class caseAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public caseAdd() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String p_name = request.getParameter("name");
		String p_age = request.getParameter("age");
		String p_gender = request.getParameter("gender");
		String p_address = request.getParameter("address");
		String p_mob = request.getParameter("phone");
		String p_sym1 = request.getParameter("symp1");
		String p_sym2 = request.getParameter("symp2");
		String p_sym3=request.getParameter("symp3");
		 
		int p_did=0 , p_aid=UserData.getAid();;
		String disease="",doctor_name="";
		
		
		
		try 
		{
			
			Connection con = ConnectDB.getConnect();
			PreparedStatement ps = con.prepareStatement("select * from disease_set where (sym1=? and sym2=? and sym3=?) or (sym1=? and sym2=?) or (sym2=? and sym3=?) or (sym1=? and sym3=?)");
			ps.setString(1, p_sym1);
			ps.setString(2, p_sym2);
			ps.setString(3, p_sym3);
			ps.setString(4, p_sym1);
			ps.setString(5, p_sym2);
			ps.setString(6, p_sym2);
			ps.setString(7, p_sym3);
			ps.setString(8, p_sym1);
			ps.setString(9, p_sym3);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String dis = rs.getString("disease");
				System.out.println(dis);
				disease = dis;
			}
			
			
			
			PreparedStatement ps1 = con.prepareStatement("select * from doctor where treatment=? order by rand() limit 1"); 
			ps1.setString(1, disease);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()){
				p_did = rs1.getInt("did");
				doctor_name = rs1.getString("dname");
				System.out.println("hogya");
			}
			System.out.println(p_aid);
			System.out.println(p_did);
			System.out.println(disease);
			System.out.println(doctor_name);
			PreparedStatement ps2 = con.prepareStatement("insert into case_tbl values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps2.setInt(1, 0);
			ps2.setString(2, p_name);
			ps2.setString(3, p_age);
			ps2.setString(4, p_gender);
			ps2.setString(5, p_address);
			ps2.setString(6, p_mob);
			ps2.setString(7, p_sym1);
			ps2.setString(8, p_sym2);
			ps2.setString(9, p_sym3);
			ps2.setString(10, disease);
			ps2.setInt(11, p_did);
			ps2.setString(12, doctor_name);
			ps2.setInt(13, p_aid);
			int i = ps2.executeUpdate();
			if(i>0){
				response.sendRedirect("addCase.html");
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
