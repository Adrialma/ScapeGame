package com.adrialma.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrialma.dao.DaoBd;
import com.adrialma.dao.UserDAO;
import com.adrialma.model.Factory;
import com.adrialma.model.User;
import com.adrialma.service.VerificationService;

/**
 * Servlet implementation class ShowGames
 */
@WebServlet("/ShowGames")
public class ShowGames extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowGames() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		VerificationService verifService = new VerificationService();
		if (verifService.isConectionBdOk()) {	//Tester la conection a la BD
			getAllInfo(request);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ShowGames.jsp").forward(request, response);
			
		}else {
			this.getServletContext().getRequestDispatcher("/OutOfService").forward(request, response);
			System.out.println("Pas de conexion a la BD, service non disponible");
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	
	public void getAllInfo(HttpServletRequest request){
		
		UserDAO userDAO = new UserDAO();
		Factory factory = new Factory(userDAO.get());
		factory.getGames();
		factory.getPuzzles();
		
		for (User user: factory.getUserList())
			System.out.println(user);
		
		request.setAttribute("userList",factory.getUserList());

		
		
		
	}
	
	
	

}
