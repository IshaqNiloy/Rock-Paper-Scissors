
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;
public class Server {
	private static ServerSocket server = null;
	private static Socket socket = null;
	private static final int port = 8999;
	
	public static void main(String[] args) {
		BufferedReader in = null;
		PrintWriter out = null;
		Scanner consoleInput = new Scanner(System.in);
		Random rand = new Random();
		String playerName;
		int playerInput;
		String playerInputString;
		int rock = 1;
		int paper = 2;
		int scissors = 3;
		try {
			System.out.println("Server is starting...");
			server = new ServerSocket(port);
			System.out.println("Server has started.");
		}catch(IOException e) {
			System.out.println("Cannot listen to port" + port);
			System.exit(-1);
		}
		while(true) {
			try {
				socket = server.accept();
				System.out.println("Server has accepted a client");
				
			}catch(IOException e) {
				System.out.println("Communication error with client");
				System.exit(-1);
			}
			
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//To receive something from the client
				out = new PrintWriter(socket.getOutputStream(),true);//To send something to the client
				out.println("Rock Paper Scissors Server");
				playerName = in.readLine();
				System.out.println("Client Name: " + playerName);
				out.println("Server: Let's play a game of rock paper scissors, " + playerName + ". " 
				+ "Assume 1 is Rock, 2 is Paper and 3 is Scissors. It's your turn...");
				while(socket.isConnected()) {
					while(true){
						try{
						 	playerInputString = in.readLine();
						 	playerInput = Integer.parseInt(playerInputString);
							if(playerInput < 1 || playerInput > 3){
								throw new IOException();
							}else{
								break;
							}
						}catch(IOException e){
							out.println("Server: Invalid Input!");
						}
					}
					
					int serverGeneratedDigit = rand.nextInt(3)  +  1;
					if(playerInput == rock && serverGeneratedDigit == scissors){
						out.println("Server: I choose " + serverGeneratedDigit + ". You win!");
					}else if(playerInput == paper && serverGeneratedDigit == rock){
						out.println("Server: I choose " + serverGeneratedDigit + ". You win!");
					}else if(playerInput == scissors && serverGeneratedDigit == paper){
						out.println("Server: I choose " + serverGeneratedDigit + ". You win!");
					}else if(playerInput == rock && serverGeneratedDigit == rock 
							|| playerInput == paper && serverGeneratedDigit == paper
							|| playerInput == scissors && serverGeneratedDigit == scissors){
						out.println("Server: I choose " + serverGeneratedDigit + ". It's a tie! Let's play again.");
					}
					else{
						out.println("Server: I choose " + serverGeneratedDigit + ". I win!");
					}
				}
			}catch(IOException e) {
				System.out.print("client left");
				consoleInput.close();
			}
		}
	}
}