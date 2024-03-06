package com.adrialma.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrialma.dao.InitProjectDAO;

/**
 * Servlet implementation class InitProject
 */
@WebServlet("/InitProject")
public class InitProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String redirect ="/";
		String bdName = "testing";
		InitProjectDAO initProj= new InitProjectDAO();
		
		if (initProj.checkDBExists(bdName)) {
			System.out.println("la BDD existe, pas de creation, redirection vers l'index");
			initProj.closeConnection();
		}
		else {
			System.out.println("la bd n'existe pas, creation de la bdd");
			initProj.createBDD(bdName);
			
			InitProjectDAO.connect(bdName); //connection a la bdd creé
			initProj.createTableUser();
			initProj.createTablePuzzle();
			initProj.createTableGame();
			initProj.createTableGamePuzzle();
			initProj.initTablePuzzle();
			initProj.closeConnection();
			
		}
		
		this.getServletContext().getRequestDispatcher(redirect).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
