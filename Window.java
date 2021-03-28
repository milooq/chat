package MyChat;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class Window extends JFrame {

        public static int width = 800;
        public static int height = 600;

        public static final int cX = width/2;
        public static final int cY = height/2;

        JTextField inputField = new JTextField();
        int ifWidth = 700, ifHeight = 30;

        JTextArea chatField = new JTextArea("");
        int cfWidth = 700, cfHeight = 400;

        JButton sendButton;
        int ibWidth = 50, ibHeight = ifHeight;

        int bottomIntend = 100;
        int topIntend = 30;

        public static String name = "Миша - дурачок";
        BufferedWriter writer;
        BufferedReader reader;

        public Window() {

            this.setTitle("MyChat");
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setBounds(0, 0, width, height);
            this.setLocationRelativeTo(null);
            this.setLayout(null);

            inputField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        sendMessage();
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });


            sendButton = new JButton("");
            //sendButton.setIcon(new ImageIcon("pencil.png"));
            sendButton.setBounds(cX + ifWidth / 2 - ibWidth,
                    height - ifHeight - bottomIntend, ibWidth, ifHeight);

            inputField.setBounds(cX - ifWidth / 2,
                    height - ifHeight - bottomIntend, ifWidth - ibWidth - ibWidth / 2, ifHeight);

            chatField.setBounds(cX - cfWidth / 2, topIntend, cfWidth, cfHeight);
            chatField.setFocusable(false);

            sendButton.addActionListener(e -> sendMessage());

            this.add(sendButton);
            this.add(inputField);
            this.add(chatField);

            addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosing(WindowEvent e)
                {
                    try {
                        writer.write("!exit");
                        writer.newLine();
                        writer.flush();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    e.getWindow().dispose();
                }
            });


            this.setVisible(true);
        }

        private void sendMessage(){
            String myMessage = inputField.getText();
            try {
                writer.write(name + ": " + myMessage);
                writer.newLine();
                writer.flush();
            } catch (IOException ioException) {
                System.out.println("Error!");
                ioException.printStackTrace();
            }
            inputField.setText("");
        }

        public void start(){
            try (Socket socket = new Socket("192.168.2.104", 5555);) {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                System.out.println("Клиент запущен, потоки ввода вывода созданы");

                String message = reader.readLine();

                    while (!message.contains("!exit")) {
                        chatField.append("\n"+message);
                        message = reader.readLine();
                    }

                reader.close();
                writer.close();
            }catch (IOException e) {
                System.out.println("Не удается подключиться к серверу :(");
            }
        }

    @Override
    public void repaint() {
        super.repaint();
    }
}
