package kea.shipsandsails.services;

import org.apache.catalina.Server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.io.IOException;


public class ServerConnection {

    private static ServerSocket ss;
    private final int PORT = 6060;

    public ServerConnection() {
        try {
            ss = new ServerSocket(PORT);
        }
        catch (Exception Y){
            System.out.println("Failure");
        )


    }

        /*try {
            ServerSocket ss = new ServerSocket(6060);
            Socket socket = ss.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            //Close
            //socket.close();
            //ss.close();
            
        }

        catch (Exception x){
            System.out.println("Exception X");
        }

         */

        

    }
}
