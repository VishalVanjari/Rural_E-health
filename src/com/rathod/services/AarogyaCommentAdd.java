package com.rathod.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rathod.dbconnect.ConnectDB;

/**
 * Servlet implementation class AarogyaCommentAdd
 */
public class AarogyaCommentAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AarogyaCommentAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String cmt = request.getParameter("comment");
		int aid=UserData.getAid();
		int did=UserData.getDid();
		int cid=UserData.getCid();
		try {
			Connection con = ConnectDB.getConnect();
			PreparedStatement ps = con.prepareStatement("insert into comment_tbl values(?,?,?,?,?) ");
			ps.setInt(1, 0);
			ps.setString(2, cmt);
			ps.setInt(3, cid);
			ps.setInt(4, aid);
			ps.setInt(5, 0);
			int i = ps.executeUpdate();
			if(i>0){
				response.sendRedirect("AarogyaComment.jsp");
			}else{
				response.sendRedirect("failed.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
