
import java.io.*;
import static java.lang.Thread.sleep;
import java.net.*;
import java.util.*;

class Client {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 8081)) {

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));

            Scanner sc = new Scanner(System.in);
            String line = null;

            while (!"exit".equalsIgnoreCase(line)) {

                line = sc.nextLine();

                WaitingMessage Wait = new WaitingMessage();
                
                out.println(line);
                Thread thread = new Thread(Wait);
                thread.start();
                
                if (line.equalsIgnoreCase("exit")) {
                    System.out.println(in.readLine());
                   
                } 
                else 
                {
                    System.out.println(in.readLine() + " Waited = " + Wait.getCounter() + " Seconds!");
                     System.out.println("send request to server");
                    
                }
                Wait.resetCounter();
              
                //out.flush():

             
                
            }

            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
