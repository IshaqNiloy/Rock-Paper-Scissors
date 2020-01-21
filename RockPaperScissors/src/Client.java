import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class Client {
	public static Socket socket = null;
	
	public static void main(String[] args) {
		try {
			socket = new Socket("localhost",8999);
			System.out.println("Connected to server\n" + "Socket: " + socket.getInetAddress() +  ". " + socket.getPort() + "\n");
		}catch(IOException e) {
			System.out.println("Cannot connect to server");
		}
		BufferedReader in = null;
		PrintWriter out = null;
		Scanner consoleInput = new Scanner(System.in);
		
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//To accept something from the server
			out = new PrintWriter(socket.getOutputStream(),true); //To send the digit to the server
			
			System.out.println("Server: "+ in.readLine());
			System.out.println("Client:your name:");
			out.println(consoleInput.nextLine());
			System.out.println(in.readLine());
			while(true) {
				System.out.println("Client:");
				out.println(consoleInput.nextLine());
				System.out.println(in.readLine());
			}
		}catch(IOException e) {
			e.printStackTrace();
			consoleInput.close();
		}
	}
}