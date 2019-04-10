//package clientside;
import java.util.InputMismatchException;
import java.util.Scanner;

// User Interface
public class UserInterface {
    
    private int threadCount;
    private String[] serverCommands = {"thread" ,"date", "uptime", "free", "netstat", 
        "users", "ps aux", "exit"}; //server commands in an array
    
    public UserInterface() { //constructor
        this.threadCount = 0;
    }
    
    public UserInterface(int threadCount) { //constructor
        this.threadCount = threadCount;
    }
    
    public void displayMenu() { //displays menu
        System.out.printf("Select a Command to be run on the server%n");
        System.out.printf("Threads to be used %d%n", this.threadCount);
        System.out.printf("0. Change Thread Count%n");
        System.out.printf("1. Get Server Date and Time%n");
        System.out.printf("2. Get Server Uptime%n");
        System.out.printf("3. Get Server memory%n");
        System.out.printf("4. Get Server Netstat%n");
        System.out.printf("5. Get Server Current Users%n");
        System.out.printf("6. Get Server running processes%n");
        System.out.printf("7. Exit Program%n");
        System.out.printf("Enter Selection [Numbers only]: ");
        return;
    }
    
    public int getUserInput() {//gets user input after displayMenu() is displayed
        int number;
        Scanner input = new Scanner(System.in); //scanner
        
        try {
            number = input.nextInt();//input
            if (number < 0 || number > 7) { //checks if out of bounds
                System.out.printf("Invalid Command Given%n");
                number = getUserInput();
            }
        }
        catch (InputMismatchException ex) {//checks if input is int
            System.out.printf("Invalid Input: Input an Integer only%n");
            number = getUserInput();
        }
        
        return number; //returns userinput
    }
    
    public String getCommand() {
        int commandId = this.getUserInput(); //gets input and sets commandID
        
        return this.serverCommands[commandId]; //servercommand == commandID (int) from array of serverCommands
    }
    
    public int changeThreadCount() { //changes thread count
        int newCount = 1;
        
        System.out.printf("Enter new thread Count: "); //displays for new count
        Scanner input = new Scanner(System.in);//scanner
        try {
            newCount = input.nextInt();//input
        }
        catch (InputMismatchException ex) { //catch if not int
            System.out.printf("Invalid Input: Input an Integer only%n");
            newCount = this.changeThreadCount();
        }

        this.threadCount = newCount; //sets new thread count from input
        return newCount;
    }
}
