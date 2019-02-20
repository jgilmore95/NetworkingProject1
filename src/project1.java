import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class project1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int userInput = 0;
		
		System.out.printf("%s", "Please select an option:\n"
				+ "1. Host current Date and Time\n"
				+ "2. Host uptime\n"
				+ "3. Host memory use\n"
				+ "4. Host Netstat\n"
				+ "5. Host current users\n"
				+ "6. Host running processes\n"
				+ "7. Quit\n");
		
		userInput = input.nextInt();
		
		switch(userInput) {
		
		case 1:
			break;
			
		case 2:
			break;
			
		case 3:
			break;
			
		case 4:
			break;
			
		case 5:
			break;
			
		case 6:
			break;
			
		case 7:
			System.exit(0);
			break;
			
		default:
			System.out.println("Please select a number between 1-7");
		}
				

	}
	
	//---------------------------------------------------------------------
	/*
	 * ****BELOW IS A WORKING WAY TO SEND A LINUX COMMAND ON THE CURRENT PC*****
	 * 
	 * ProcessBuilder processBuilder = new ProcessBuilder();

    // Run this on Windows, cmd, /c = terminate after this run
    processBuilder.command("bash", "-c", "top");  //<-- terminal command goes here

    try {

        Process process = processBuilder.start();

		// blocked :(
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("\nExited with error code : " + exitCode);

    } catch (IOException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
	 * 
	 */
	 
	
	

}
