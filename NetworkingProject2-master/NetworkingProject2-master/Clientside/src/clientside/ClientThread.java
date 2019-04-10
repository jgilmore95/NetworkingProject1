//package clientside;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread extends Thread {
    private double elaspedTime;
    private double totalTime;
    private long startTime;
    private String serverCommand;
    private String myHost;
    

    public static final int portNumber = 9001; //server port number
    
    public ClientThread(String command, String myHost) { //constructor
        this.serverCommand = command;
        this.elaspedTime = 0;
        this.myHost = myHost;
    }
    //get methods
    public double getElaspedTime() {
        return this.elaspedTime;
    }
    
    public double getTotalTime() {
	return this.totalTime;
    }
	
    public String getServerCommand() {
        return this.serverCommand;
    }
    
    public void run() {
        try {
            String str;
	    String line = null;
   
            Socket socket = new Socket(this.myHost, this.portNumber); //creates socket
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true); //output for server
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); //input from server
	    
	    totalTime = 0; //initialize total time for the getmethod
	    startTimer(); //get current time in milliseconds and starts timer
            output.println(this.serverCommand); //prints command to be executed
	    
	    while((line = input.readLine()) != null) { //reads response from server
	
	
            	System.out.println(line); // prints response from server
		
	    }
	    endTimer(); //ends timer
	    this.totalTime += this.elaspedTime; //sets total time from elapsed time calucated in endTimer()
	    System.out.println("----------------------------------------------------------------------");
            socket.close();
        }
        catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            System.out.printf("Exception thrown%n");
        }
    }
    
    private void startTimer() {
        this.startTime = System.currentTimeMillis(); //gets current time in Milliseconds
    }
    
    private void endTimer() {
        long currentTime = System.currentTimeMillis(); //gets current time in milliseconds
        this.elaspedTime = currentTime - this.startTime; // current time - star time = elapsed time
    }
}
