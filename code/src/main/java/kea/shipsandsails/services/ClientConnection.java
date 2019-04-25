package kea.shipsandsails.services;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class ClientConnection {

        //Declaration of the Inetaddress = IP address
        public static InetAddress hostIP;
        public static final int PORT = 6060;


    public ClientConnection(){

       try {
            hostIP = InetAddress.getLocalHost();

       }

       catch (IOException ioEx){
            System.out.println("Failure");
        }


       /* try {
            Socket s = new Socket(6060);
            DataInputStream inp = new DataInputStream(s.getInputStream());
            DataOutputStream outp = new DataOutputStream(s.getOutputStream());

            String str = inp.readUTF();

        }

        catch (Exception Y){

            System.out.println("Exception Y");
        }
    }

        */

    }
}
