import java.io.*;
import static java.lang.Thread.sleep;
import java.net.*;
import java.util.Scanner;
  
// Server class
class Server {
    public static void main(String[] args)
    {
        ServerSocket server = null;
  
        try {
  
            
            server = new ServerSocket(8081);
            server.setReuseAddress(true);
  
            
            while (true) {
  
                
                Socket client = server.accept();
  
                
                System.out.println("IAM ALIVE"
                                   + client.getInetAddress()
                                         .getHostAddress());
  
                
                ClientHandler clientSock
                    = new ClientHandler(client);
  
                
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
  

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
  
        // Constructor
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }
       
        public void run()
        {
            PrintWriter out = null;
            BufferedReader in = null;
            boolean exitStatus = false;
            try {
                    
                 
                out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
  
                  
                in = new BufferedReader(
                    new InputStreamReader(
                        clientSocket.getInputStream()));
  
                String line;
                while ((line = in.readLine()) != null) {
                 
                    
                    System.out.printf(
                        " Sent from the client: %s\n",
                        line);
                    out.println(line);
                    Scanner myObj = new Scanner(System.in); 
                    System.out.println("response back");
                    String response = myObj.nextLine();
                   try{
                       sleep(5000);
                       if (in.readLine().equalsIgnoreCase("exit")) {
                        Thread.sleep(5000);
                        out.println("Goodbye!");
                        exitStatus = false;
                    } else {
                        Thread.sleep(5000);
                        out.println("Response Received!");
                        System.out.println("Respose is sent after 5 seconds!");
                    }
                   }
                   catch(InterruptedException ex){
                       
                   }         
                    System.out.println("Sent by server: " + response);
    
    
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
