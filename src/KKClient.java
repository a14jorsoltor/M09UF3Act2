import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class KKClient {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 40004;
        if (args.length != 2) {
            System.err.println("Usage: java EchoClient " + hostName + " " + portNumber);
        }
        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(
                        new InputStreamReader(System.in))
        ) {
            String userInput;

            System.out.println(in.readLine());



            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Pong dice: " + in.readLine());




            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);

        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);

        }
    }
}
