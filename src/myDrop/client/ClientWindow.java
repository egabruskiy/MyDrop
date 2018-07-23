package myDrop.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ClientWindow extends JFrame {

    private JTextArea jTextArea;
    private JTextField jTextField;
    private JPanel jPanelBottom;
    private JButton addFileButton;
    private FileClient client;

    public ClientWindow()  {

        client = new FileClient();
        client.init();


        setTitle("Client");
        setBounds(500, 150, 306, 329);
        setResizable(false);
        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jTextArea = new JTextArea();
        jTextArea.setEditable(false);
//        jTextArea.append(client.showFilelist());
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jTextField = new JTextField();
        addFileButton = new JButton("add");
        jTextField.setPreferredSize(new Dimension(200, 20));
        jPanelBottom = new JPanel();
        jPanelBottom.add(jTextField, BorderLayout.CENTER);
        jPanelBottom.add(addFileButton, BorderLayout.EAST);
        add(jPanelBottom, BorderLayout.SOUTH);
        add(jScrollPane, BorderLayout.CENTER);



        addFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    sendFileToServer();
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    public void sendFileToServer() throws IOException, ClassNotFoundException {

//        Path path = Paths.get("clientData/"+ jTextField.getText());
        Path path = Paths.get(jTextField.getText());
        String pathString = path.getFileName().toString();

        path.toString().length();
        client.sendFile(pathString);
        jTextArea.setText(client.showFilelist());


    }
}
