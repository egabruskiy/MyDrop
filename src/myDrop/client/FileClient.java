package myDrop.client;
import java.io.*;
import java.net.Socket;
import static myDrop.ServerConst.SERVER_URL;


public class FileClient {

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private FileInputStream fis;


    public void init() {
        try {
            socket = new Socket(SERVER_URL, 8189);
            dos = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



        public void sendFile(String file) {

        try {

            fis = new FileInputStream(file);
            dos.writeUTF(file);
            byte[] buffer = new byte[4096];
            while (fis.read(buffer) > 0) {
                dos.write(buffer);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }


    }
    public String showFilelist()  {

        String inName = "";
        try {
            dis = new DataInputStream(socket.getInputStream());
            inName = dis.readUTF();
        } catch(IOException e){
            e.printStackTrace();
        }
        return inName.replace(",", "\n")
                .replace("[","")
                .replace("]","");
    }



}
