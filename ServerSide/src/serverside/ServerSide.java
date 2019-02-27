
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import java.lang.management.*;

public class ServerSide {
    
    public static void main(String[] args) {
     
        
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        String command;
        
    
        try {
           serverSocket = new ServerSocket(9001);
           System.out.println("The Socket has been created.");
        }
        catch (IOException e) {
           System.out.println(e);
        }
        
        try {
            boolean running = true;
            while (running) {
          
                clientSocket = serverSocket.accept();
                System.out.println("A Client has been Accepted");
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


                
                while(!clientSocket.isClosed() && ((command = in.readLine()) != null)) {
                    System.out.println(command);
                    
                    if (command.equals("exit")) {
                       System.out.println("Exiting.");
                       running = false;
                       break;
                    }
                    
                    else {
                       try {
                          Runtime rt = Runtime.getRuntime();
                          Process pr = rt.exec(command);
         
                          BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
         
                          String line = null;
                          while ((line = input.readLine()) != null) {
                             System.out.println(line);
                             out.println(line);
                          }
                          
                          out.close();
                          System.out.println("Ending Process");         
                          pr.waitFor();
                       } 
                    
                       catch (Exception e) {
                          System.out.println("Runtime Exception");
                          System.out.println(e.toString());
                       }                  
                    }
                }
            }
        }
        
        catch (IOException e) {
           System.out.println(e);
        }
    }
}

