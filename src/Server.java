import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
   private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void startServer(){
        System.out.println("Server started");
        try{
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                System.out.println("A new client is connected");
                Thread thread = new Thread(clientHandler);
                thread.start();
                String check = "yes";
                System.out.println("do u want a stop server");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if(check.equals(input)){
                    System.out.println("Server is closed!!!");
                    closeServerSocket();
                }

            }
        }catch (IOException e){

        }
    }
    public void closeServerSocket(){
        try {
            if(serverSocket != null){
                serverSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();
    }

}
