package myDrop.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientHandler {
    private DataBase dataBase;
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream outputStream;
    private ArrayList dbList;

    public ClientHandler(Server server, Socket socket) throws SQLException {


        try {
            dataBase.connect();
            dataBase.createTableEx();
            this.server = server;
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            try {

                while (true) {
            String inName = in.readUTF();
                saveFile(inName);
            }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
        }).start();
    }


    private void saveFile(String inName) throws IOException, SQLException {

      String  FILE_TO_RECEIVED = "serverData/" + inName;


        dataBase.addFile(inName);
        outputStream.writeUTF( dataBase.showAllData().toString());

        //receiveFile
        FileOutputStream fos = new FileOutputStream(FILE_TO_RECEIVED);
        byte[] buffer = new byte[8192];
        int read ;
        int totalRead = 0;
        int remaining = in.available();
        while ((read = in.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
            totalRead += read;//8
            remaining -= read;//6
            System.out.println("read " + totalRead + " bytes.");
            System.out.println("remaining " + remaining + " bytes.");
            fos.write(buffer, 0, read);
        }


        System.out.println("File is ready!");

    }

    private boolean isAvailable (String inName) throws SQLException {

        for (Object s : dataBase.showAllData()) {
            if (s.equals(inName)){
                System.out.println( " There is file with such name already");
                return false;
            }
            }
        return true;
    }



}
