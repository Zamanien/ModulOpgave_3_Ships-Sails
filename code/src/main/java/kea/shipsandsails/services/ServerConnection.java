package kea.shipsandsails.services;

import org.apache.catalina.Server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerConnection {

    private ServerSocket ss;
    private Socket socket;
    private final int PORT = 6060;

    public static DataOutputStream dos;
    public static DataInputStream dis;

    public ServerConnection() {
        try {
            ss = new ServerSocket(PORT);
            socket = ss.accept();

            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());

            //testing
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String inputText = "";
            String response = "";

            inputText = br.readLine();

            dos.writeUTF(inputText);
            dos.flush();

            response = dis.readUTF();
            System.out.println("Klienten siger: " + response);

            dos.close();


        } catch (IOException ioE) {
            ioE.printStackTrace();

        }

     }

}
        


