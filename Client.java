package MyChat;

import java.io.*;
import java.net.Socket;

public class Client {

    static String name = "Настя";

    public static void main(String[] args) {
        Window w = new Window();
        w.start();

//        try (Socket socket = new Socket("192.168.2.103", 5555);) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
//            System.out.println("Клиент запущен, потоки ввода вывода созданы");
//            Thread t = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try{
//                        String message = reader.readLine();
//                        while (!"exit".equals(message)) {
//                            System.out.println("Сервер написал: " + message);
//                            message = reader.readLine();
//                        }
//                    } catch (IOException e) {
//
//                    }
//                }
//            });
//            t.start();
//            String myMessage = consoleReader.readLine();
//            while (!"exit".equals(myMessage)) {
//                writer.write(name + ": " + myMessage);
//                writer.newLine();
//                writer.flush();
//                System.out.println("Введите сообщение: ");
//                myMessage = consoleReader.readLine();
//            }
//            reader.close();
//            writer.close();
//        }catch (IOException e) {
//            System.out.println("Не удается подключиться к серверу :(");
//        }
    }
}
