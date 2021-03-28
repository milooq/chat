package MyChat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    ArrayList<MyConnection> connections = new ArrayList<>();

    public static void main(String[] args) {
        new Server().serverStart();
    }

    public void serverStart() {
        try (ServerSocket server = new ServerSocket(5555);) {
            System.out.println("Сервер ожидает подключения");
            while (true) {
                Socket socket = server.accept();
                System.out.println("К нам подключился: " + socket.getInetAddress());
                MyConnection connection = new MyConnection(socket, this);
                new Thread(connection).start();
                connections.add(connection);
            }
        } catch (IOException e) {
            System.out.println("Проблемы с сервером");
        }
    }

    public void messageReceived(String messageFromClient) {
        try {
            for (MyConnection connection : connections) {
                connection.sendMessage(messageFromClient);
            }
        } catch (IOException e) {

        }
    }
}
