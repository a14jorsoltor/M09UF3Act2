import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class KKServer {
    public static void main(String[] args) throws IOException {
        int portNumber = 40004;
        int clientConnectat = 0;
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer " + portNumber);

// TOT EL TRY EN UN FILL, ON LI PASEM EL SERVERSOCKET I UN NOM PE.

            try (

                    ServerSocket serverSocket = new ServerSocket(portNumber);
            ) {
                while (true) {

                    KKThread filServer = new KKThread(serverSocket.accept());
                    filServer.start();
                    System.out.println("Client conectat " + ++clientConnectat);
                }
            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port "
                        + portNumber + " or listening for a connection");
                System.out.println(e.getMessage());
            }
        }
    }

}





