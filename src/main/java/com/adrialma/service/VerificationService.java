package com.adrialma.service;

import java.io.IOException;
import java.net.Socket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.adrialma.dao.DaoBd;
import com.adrialma.model.User;

public class VerificationService {
	
	private String redirect ="";
	
	
	
	public VerificationService() {
		super();
	}

	public boolean checkAll(User user) {
		boolean allOk = true;
		if (!isConectionBdOk()) {
			redirect ="/OutOfService";
			allOk = false;
		}
		if (!isUserConected(user)) {
			redirect = "/Login";
			allOk = false;
		}
			
		return allOk;
		
		
	}
	
	public boolean isConectionBdOk() {
		// Verifier que le serveur mysql est started
		 boolean isUp = false;
		    try {
		    
		        Socket socket = new Socket("localhost", 3306);
		        //server OK
		        socket.close();  
		        
		        // Verifier conexion
		        DaoBd.conecter();
		        if (DaoBd.getCn()!=null) 
		        	isUp = true;
		        
		        
		    }
		    catch (IOException e)
		    {
		    	
		        System.out.println("Server is down.... Envoyer vers la page d'erreur");
		        DaoBd.closeConnection(); // appelle a la fonction close conection au cas ou il y aura une conexion active et le server down
		    }
		    
			
		return isUp; 
		
	}
	
	
	public boolean isUserConected(User user) {
		return (user == null) ? false : true;
	
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
	
	

	@Override
	public String toString() {
		return "VerificationService [redirect=" + redirect + "]";
	}
	
	
	

	
}
	
	


