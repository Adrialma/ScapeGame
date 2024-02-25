package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrialma.model.RegisterForm;

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
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/Register.jsp").
		forward(request, response);
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
		
		
		
		RegisterForm registerForm = new RegisterForm(request);
		request.setAttribute("user", registerForm.getUser());
		
		if (registerForm.getErrorList().size()<1) {
			// TODO enregistrer le user dans la bd et redirectioner sur le site de success enregistrement
			// (il y pas eu d'erreur)
		}else {
			//erreurs dans les donnes, afficher la page d'erreur
			request.setAttribute("errorList", registerForm.getErrorList());
			doGet(request, response);
			
		}
		
		
		
	}

}
