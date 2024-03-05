package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrialma.form.RegisterForm;
import com.adrialma.service.VerificationService;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		VerificationService verifService = new VerificationService();
		if (verifService.isConectionBdOk()) {	//Tester la conection a la BD
			this.getServletContext().getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);
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

		System.out.println(request.getParameter("firstName")); 
		System.out.println(request.getParameter("lastName"));
		System.out.println(request.getParameter("userName"));
		System.out.println(request.getParameter("password"));
		
		VerificationService verifService = new VerificationService();
		if (verifService.isConectionBdOk()) {	//Tester la conection a la BD
			String redirect = traiterRequest(request);
			this.getServletContext().getRequestDispatcher(redirect).forward(request, response);	
			
		}else { // erreur dans la conexion a la bd
			System.out.println("Pas de conexion a la BD, service non disponible");
			this.getServletContext().getRequestDispatcher("/OutOfService").forward(request, response);	
		}
	}
	
	public String traiterRequest(HttpServletRequest request) {
		
		RegisterForm registerForm = new RegisterForm(request);
		request.setAttribute("user", registerForm.getUser());
		
		if (registerForm.getErrorList().size()<1) {
			return "/WEB-INF/RegisterOK.jsp";
		}else {
			//erreurs dans les donnes, afficher la page d'erreur
			request.setAttribute("errorList", registerForm.getErrorList());
			return "/WEB-INF/Register.jsp";
		}
	}
}
