package kea.shipsandsails.services;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

//Indfør Thread til at vente på svar fra modstander

public class ClientConnection {

        //Declaration of the Inetaddress = IP address
        private InetAddress hostIP;
        private int port;

        private DataInputStream dis;
        private DataOutputStream dos;


    public ClientConnection(int port, String hostIP){ //Vi modtager IP som String -> Skal konverteres senere i programmet

        this.port = port;

        try {
           //test - Bruger local IP
            this.hostIP = InetAddress.getByName(hostIP);


       }

       catch (IOException ioEx){
            System.out.println("Failure");
        }

       try {
            //deklarere og initialisere en socket med argumenterne hostip og port
           Socket socket = new Socket(hostIP, port);
           dos = new DataOutputStream(socket.getOutputStream());
           dis = new DataInputStream(socket.getInputStream());


           //Testing the connection
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

           String inputText = "";
           String response = "";

           inputText = br.readLine();

           dos.writeUTF(inputText);
           dos.flush();

           response = dis.readUTF();
           System.out.println("Serveren siger: " + response);

           dos.close();





       }

        catch (IOException e) {
           e.printStackTrace();
       }

    }
}
