//package clientside;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientSide {
    
    private static int threadCount;
    private static ClientThread[] threads;
    private static String myHost;

    public static void main(String[] args) {
        UserInterface ui;
        String command;
        boolean running = true;
        
        if (args.length < 1) { //ends if no command line arg.
            System.out.println("Error: Invalid Hostnamne (try localhost)");
            return;
        }    
        else {
            myHost = args[0];
            if (args.length == 2) { //optional command ln arg for thread count
                threadCount = Integer.parseInt(args[1]);
            }
            else {
                System.out.println("Default thread count: 1");
                threadCount = 1;
            }
        }
        if (threadCount < 1) {
            threadCount = 1;
        }
        
        ui = new UserInterface(threadCount); //creates UI object from UserInterface class
        while (running) { //while program is running
            ui.displayMenu(); //displays input menu from UI class
            command = ui.getCommand(); //gets command from UI class

            if (command.equals("thread")) { //if command == thread
                threadCount = ui.changeThreadCount(); //call change thread from UI
            }
            else if (command.equals("exit")) { //If command is exit
                signalServerExit(); //call server exit
                System.out.printf("Ending program%n");
                running = false; //program no longer running, exits loop
            }
            else {
                System.out.printf("Command is: %s%n", command); //print out command
                System.out.println("From Server: ");
                generateThreads(threadCount, command, myHost); //generate threads
                runThreads(); //execute threads
                getResults(threadCount); //get results from threads
            }
        }
        
        return;
    }
    
// Makes the threads
    private static void generateThreads(int threadCount, String command, String myHost) {
        threads = new ClientThread[threadCount]; //creates thread array from CLIentThread class for amount of threadCount
        
        for (int i = 0; i < threadCount; i++) { //foreach thread
            threads[i] = new ClientThread(command, myHost); //create thread object for each position of array
        }
    }

    private static void getResults(int threadCount) {
        double sum = 0; //initialize sum. needed for average
        
        System.out.printf("Server response times (milliseconds): %n");
        for(ClientThread t : threads) { //for each thread
            System.out.printf("%.2f, ", t.getTotalTime()); //print total time for that thread
	    sum += t.getTotalTime(); //add that time to the sum
        }
        System.out.printf("%nLatency (mean server response time): %.2f ms", (sum/((double)threadCount))); //calculate mean response
        System.out.printf("%n"); //new line
    }

    private static void runThreads() { //concurrent process, creates new thread until i reaches input thread length
        int i;
        boolean running = true; //program is running
        for (i = 0; i < threads.length; i++) {//start each thread
            threads[i].start();
        }
        while (running) {
            running = false;
            for (i = 0; i < threads.length; i++) { //check if thread is still alive
                if (threads[i].isAlive()) { //if thread alive, program is still running
                    running = true;
                }
            }
        }
    }
    
    private static void signalServerExit() {
        
        try {
            // What happens when the server ends
            Socket socket = new Socket(myHost, ClientThread.portNumber); //creates socket for server
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true); //receives output from server
            output.printf("exit%n");
            socket.close(); //closes socket
        }
        catch (IOException ex) {}
    }
}
