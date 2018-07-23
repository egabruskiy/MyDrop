package myDrop.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;


import static myDrop.ServerConst.PORT;

public class Server  {

        private ClientHandler client;

    public Server() throws SQLException {
        ServerSocket ss = null;
        Socket socket = null;
            try {
                ss = new ServerSocket(PORT);

                while (true) {

                    socket = ss.accept();
                    client = new ClientHandler(this, socket);
                    System.out.println("Client has connected!");
                }
            }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
    }