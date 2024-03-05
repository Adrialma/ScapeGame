package com.adrialma.service;

import java.io.IOException;
import java.net.Socket;

import com.adrialma.dao.DaoBd;

public class VerificationService {
	
	
	
	
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
		    }
			
		return isUp; 
		
	}
	
	
	public void isUserConected() {
		
	}

	
	
	
	

}
