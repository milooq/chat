package MyChat;

import java.io.*;
import java.net.Socket;

public class MyConnection implements Runnable {

//  Нужен метод у сервера,
//  который будет рассылать сообщения всем клиентам.
//  У сервера должен быть отдельный поток на каждого клиента.


    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Server server;

    public MyConnection(Socket socket, Server server){
        this.socket = socket;
        try{
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {

        }
        this.server = server;
    }

    public void sendMessage (String message) throws IOException {
        writer.write(message);
        writer.newLine();
        writer.flush();
    }

    @Override
    public void run() {
        try{
            String message = reader.readLine();
            while (!message.contains("!exit")) {
                server.messageReceived(message);
                message = reader.readLine();
            }

            server.connections.removeIf(user -> user.socket.getInetAddress() == this.socket.getInetAddress());

            System.out.println("Exited!");

        } catch (IOException e) {

        }
    }
}
