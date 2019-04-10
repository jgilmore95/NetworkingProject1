
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import java.lang.management.*;

public class ServerSide {
    
    public static void main(String[] args) {
     
        //initialize
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        String command;
        
    
        try {
           serverSocket = new ServerSocket(9001); //creates socket
           System.out.println("The Socket has been created.");
        }
        catch (IOException e) { //catch any errors
           System.out.println(e);
        }
        
        try {
            boolean running = true;
            while (running) { //while program is running
          
                clientSocket = serverSocket.accept(); //client socket = accepted socket
                System.out.println("A Client has been Accepted");
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); //output from command
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //input ClientThread (has command)


                
                while(!clientSocket.isClosed() && ((command = in.readLine()) != null)) { //if socket is not closed, command = in from bufferedReader
                    System.out.println(command); //prints command
                    
                    if (command.equals("exit")) { //checks if exit command
                       System.out.println("Exiting.");
                       running = false; // program is no longer running, exit loop
                       break;
                    }
                    
                    else {
                       try {
                          Runtime rt = Runtime.getRuntime(); //get runtime
                          Process pr = rt.exec(command); //execute command
         
                          BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream())); //data from command
         
                          String line = null;
                          while ((line = input.readLine()) != null) {//output results of command
                             System.out.println(line);
                             out.println(line);
                          }
                          
                          out.close(); //close printwriter
                          System.out.println("Ending Process");         
                          pr.waitFor(); //checks for more commands, false if no more commands
                       } 
                    
                       catch (Exception e) { //catch errors from runtime
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

