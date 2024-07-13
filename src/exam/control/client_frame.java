package exam.control;

import java.net.*;
import java.io.*;
import java.util.*;
import static javax.swing.JOptionPane.showMessageDialog;

import exam.control.BrowserDetection;
import static java.lang.System.console;
import static javax.swing.JOptionPane.showConfirmDialog;
 import net.jimmc.jshortcut.JShellLink;

public class client_frame extends javax.swing.JFrame 
{
    String username, address;
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;
    
    public  Socket sock;
    public  BufferedReader reader;
    public  PrintWriter writer;
    
    public InetAddress inet;
    public String pcname;
    public String ipaddress;
    
   // public static String backgroundstart ;
    
    public static int counter = 0;
    
   // public String addressforserver;
    
    //--------------------------//
    
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
    
    //--------------------------//
    
    public void userAdd(String data) 
    {
         users.add(data);
    }
    
    //--------------------------//
    
    public void userRemove(String data) 
    {
         ta_chat.append(data + " is now offline.\n");
    }
    
    //--------------------------//
    
    public void writeUsers() 
    {
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
         for (String token:tempList) 
         {
             //users.append(token + "\n");
         }
    }
    
    //--------------------------//
    
    public void sendDisconnect() 
    {
        String bye = (username + ": :Disconnect");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
            ta_chat.append("Could not send Disconnect message.\n");
        }
    }

    //--------------------------//
    
    public void Disconnect() 
    {
        try 
        {
            ta_chat.append("Disconnected.\n");
            sock.close();
        } catch(Exception ex) {
            ta_chat.append("Failed to disconnect. \n");
        }
        isConnected = false;
        tf_username.setEditable(true);

    }
    
    public client_frame() 
    {
        initComponents();
        
    }
    
    //--------------------------//
    
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
           // String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     stream = reader.readLine();

                     if (stream.equals("SHUTDOWNALL")) 
                     {
                        ta_chat.append("\nSERVER IS TRYING TO SHUTDOWN PC...\n");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                         try
                        {
                            Process p = Runtime.getRuntime().exec("shutdown -s");
                        }
                        catch(Exception e)
                        {
                            showConfirmDialog(null,"Cannot perform Operation...please try again later...");
                        }
                     } 
                     else if (stream.equals("RESTARTALL"))
                     {
                        ta_chat.append("\nSERVER IS TRYING TO RESTART PC...\n");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                         try
                        {
                            Process p = Runtime.getRuntime().exec("shutdown -r");
                        }
                         catch(Exception e)
                        {
                            showConfirmDialog(null,"Cannot perform Operation...please try again later...");
                        }
                     } 
                     else if (stream.equals("SLEEPALL"))
                     {
                        ta_chat.append("\nSERVER IS TRYING TO SLEEP PC...\n");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                         try
                        {
                            Process p = Runtime.getRuntime().exec("shutdown -h");
                        }
                         catch(Exception e)
                        {
                            showConfirmDialog(null,"Cannot perform Operation...please try again later...");
                        }
                     }
                     else if (stream.equals("STARTALLBROWSER"))
                     {
                        ta_chat.append("\nBROWSER DETECTION STARTED...\n");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                         try
                        {
                                //backgroundstart = "browser";
                                    Thread BackgroundBrowser = new Thread(new BackgroundBrowser());
                                    BackgroundBrowser.start();
                        }
                         catch(Exception e)
                        {
                            showConfirmDialog(null,"Cannot perform Operation...please try again later...");
                        }
                     }
                     else if (stream.equals("STARTPENDRIVE"))
                     {
                        ta_chat.append("\nPENDRIVE DETECTION STARTED...\n");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                         try
                        {
                               // backgroundstart = "pendrive";
                                Thread BackgroundPendrive = new Thread(new BackgroundPendrive());
                                    BackgroundPendrive.start();
                            
                        }
                         catch(Exception e)
                        {
                            showConfirmDialog(null,"Cannot perform Operation...please try again later...");
                        }
                     }
                     
                         
                         else if (stream.substring(stream.length() - 4).equals(".exe"))
                     {
                        ta_chat.append("\nAPPLICATION DETECTION STARTED...\n");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                         
                         if(counter == 0)
                     {
                          counter = 1;
                           try
                          {
                                Constants.app1name = stream;
                                
                             //   backgroundstart = "app1";
                            // showConfirmDialog(null,"RECEVIED ON CLIENT  "+Constants.app1name);
                              
                                   Thread BackgroundApp1 = new Thread(new BackgroundApp1());
                                   BackgroundApp1.start();
                               
                            }
                           catch(Exception e)
                           {
                               showConfirmDialog(null,"Cannot perform Operation...please try again later...");
                           }
                      }
                         else if(counter == 1)
                     {
                          counter = 2;
                           try
                          {
                                Constants.app2name = stream;
                                
                            //    backgroundstart = "app2";
                            
                             
                                    Thread BackgroundApp2 = new Thread(new BackgroundApp2());
                                    BackgroundApp2.start();
                               
                            }
                           catch(Exception e)
                           {
                               showConfirmDialog(null,"Cannot perform Operation...please try again later...");
                           }
                      }
                         else if(counter == 2)
                     {
                          counter = 3;
                           try
                          {
                                Constants.app3name = stream;
                                
                              //  backgroundstart = "app3";
                                
                                 Thread BackgroundApp3 = new Thread(new BackgroundApp3());
                                 BackgroundApp3.start();
                            }
                           catch(Exception e)
                           {
                               showConfirmDialog(null,"Cannot perform Operation...please try again later...");
                           }
                      }
                     else if(counter == 3)
                     {
                          counter = 4;
                           try
                          {
                                Constants.app4name = stream;
                                
                                //backgroundstart = "app4";
                                Thread BackgroundApp4 = new Thread(new BackgroundApp4());
                                 BackgroundApp4.start();
                               
                            }
                           catch(Exception e)
                           {
                               showConfirmDialog(null,"Cannot perform Operation...please try again later...");
                           }
                      }
                     else if(counter == 4)
                     {
                          counter = 0;
                           try
                          {
                                Constants.app5name = stream;
                                
                               // backgroundstart = "app5";
                               Thread BackgroundApp5 = new Thread(new BackgroundApp5());
                                 BackgroundApp5.start();
                               
                            }
                           catch(Exception e)
                           {
                               showConfirmDialog(null,"Cannot perform Operation...please try again later...");
                           }
                      }
                         
                     
                     
                     
                     }
                     
                     
                     
                     
                }
           }catch(Exception ex) { }
        }
    }

    //--------------------------//
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_address = new javax.swing.JLabel();
        tf_address = new javax.swing.JTextField();
        lb_port = new javax.swing.JLabel();
        tf_port = new javax.swing.JTextField();
        b_connect = new javax.swing.JButton();
        b_disconnect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Client's frame");
        setName("client"); // NOI18N
        setResizable(false);

        lb_address.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_address.setText("Address : -");

        tf_address.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        tf_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_addressActionPerformed(evt);
            }
        });

        lb_port.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_port.setText("Port :-");

        tf_port.setEditable(false);
        tf_port.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        tf_port.setText("2222");
        tf_port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_portActionPerformed(evt);
            }
        });

        b_connect.setText("Connect");
        b_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_connectActionPerformed(evt);
            }
        });

        b_disconnect.setText("Disconnect");
        b_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_disconnectActionPerformed(evt);
            }
        });

        ta_chat.setEditable(false);
        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CLIENT");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Log :-");

        tf_username.setEnabled(false);
        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });

        jButton1.setText("Add to Desktop");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lb_address)
                                        .addGap(18, 18, 18)
                                        .addComponent(tf_address, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(33, 33, 33)
                                .addComponent(lb_port, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(b_connect, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(111, 111, 111)
                                .addComponent(b_disconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(230, 230, 230)
                                .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_address)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_port)
                        .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b_disconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_connect, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(23, 23, 23))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_addressActionPerformed
       
    }//GEN-LAST:event_tf_addressActionPerformed

    private void tf_portActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_portActionPerformed
   
    }//GEN-LAST:event_tf_portActionPerformed

    private void b_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_connectActionPerformed
       
        
        
        Thread CheckAll = new Thread(new CheckAll());
         CheckAll.start();
         
         // Thread BackgroundModuleStart = new Thread(new BackgroundModuleStart());
        // BackgroundModuleStart.start();
         
        
        if (isConnected == false) 
        {
            username = tf_username.getText();
            tf_username.setEditable(false);

            try 
            {
                address = tf_address.getText();
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush(); 
                ta_chat.append("You are Connected to Server.\n");
                showConfirmDialog(null,"You are Connected to Server. !");
                isConnected = true; 
            } 
            catch (Exception ex) 
            {
                ta_chat.append("Cannot Connect! Try Again. \n");
               // tf_username.setEditable(true);
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            ta_chat.append("You are already connected. \n");
        }
    }//GEN-LAST:event_b_connectActionPerformed

    private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_disconnectActionPerformed
        sendDisconnect();
        Disconnect();
    }//GEN-LAST:event_b_disconnectActionPerformed

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_usernameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

                try {
//                    String programPath1 = client_frame.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
//                    String programPath = URLDecoder.decode(programPath1, "UTF-8");
            showMessageDialog(null, "To Create Shortcut Run this File from : D:\\Exam_Control.jar");

            String programPath = "D:\\Exam_Control.jar";
            File shortcutFile = new File(System.getenv("APPDATA") + "\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\yourShortcut.lnk");
            JShellLink link = new JShellLink();
            link.setFolder(JShellLink.getDirectory("desktop"));
            link.setName("Smart Lab Monitoring");
            link.setPath(programPath);
            link.save();
            
            showMessageDialog(null, "To Run your App on Startup put the Desktop Shortcut to Windows Startup Folder."+programPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        
        mainframe mf1 = new mainframe();
        mf1.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new client_frame().setVisible(true);
            }
        });
    }
    public  void callanywhere()
    {
         try {
               for(int i=0;i<10;i++)
               {
                writer.println("uc");
                writer.flush(); // flushes the buffer
               }
            } catch (Exception ex) {
                showConfirmDialog(null,"Message was not Sent !");
            }
    }
    public void getDetails()
    {
        try
{
    
    inet = InetAddress.getLocalHost();
    pcname = inet.getHostName();
    ipaddress = inet.getHostAddress();
}
catch (UnknownHostException ex)
{
    showConfirmDialog(null,"Host name cannot be resolved !");
}
    }
    public void unknownflush()
     {
         writer.println(username + ":has connected.:Connect");
                writer.flush(); 
         
     }
public void forEveryone()
{
    getDetails();
                
                writer.println("USER :"+pcname +" is Attempting to CHEAT !!!__"+ipaddress);
                 //writer.println("USER hghfghfg cheat msggg:");
                 writer.flush(); // flushes the buffer
                 unknownflush();
                ta_chat.append("Do not try to CHEAT !!! PCNAME : "+pcname +" IP ADDRESS : "+ipaddress +"\n");
              //  writer.println("uc");
}
public class CheckAll implements Runnable
{
   public void run()
    {
         getDetails();
        //showConfirmDialog(null,"Thread started.......");
        while(Constants.flagsall == true)
        {
            if(Constants.ucbrowser == true)
            {
               
                //showConfirmDialog(null,"thread check all uc browser==true");
                writer.println("USER :"+pcname +" is Attempting to CHEAT !!!__"+ipaddress);
                 //writer.println("USER hghfghfg cheat msggg:");
                 writer.flush(); // flushes the buffer
                 unknownflush();
                ta_chat.append("Do not try to CHEAT !!! PCNAME : "+pcname +" IP ADDRESS : "+ipaddress +"\n");
                //  writer.println("uc");
                Constants.ucbrowser = false;
            }

            if(Constants.internetexp == true)
            {
                
                //showConfirmDialog(null,"thread check all uc browser==true");
                writer.println("USER :"+pcname +" is Attempting to CHEAT !!!__"+ipaddress);
                 //writer.println("USER hghfghfg cheat msggg:");
                 writer.flush(); // flushes the buffer
                 unknownflush();
                ta_chat.append("Do not try to CHEAT !!! PCNAME : "+pcname +" IP ADDRESS : "+ipaddress +"\n");
                //  writer.println("uc");
                Constants.internetexp = false;
                
            }
            if(Constants.chrome == true)
            {
                
                //showConfirmDialog(null,"thread check all uc browser==true");
                writer.println("USER :"+pcname +" is Attempting to CHEAT !!!__"+ipaddress);
                 //writer.println("USER hghfghfg cheat msggg:");
                 writer.flush(); // flushes the buffer
                 unknownflush();
                ta_chat.append("Do not try to CHEAT !!! PCNAME : "+pcname +" IP ADDRESS : "+ipaddress +"\n");
                //  writer.println("uc");
                Constants.chrome = false;
                
            }
            if(Constants.mozilla == true)
            {
                
                //showConfirmDialog(null,"thread check all uc browser==true");
                writer.println("USER :"+pcname +" is Attempting to CHEAT !!!__"+ipaddress);
                 //writer.println("USER hghfghfg cheat msggg:");
                 writer.flush(); // flushes the buffer
                 unknownflush();
                ta_chat.append("Do not try to CHEAT !!! PCNAME : "+pcname +" IP ADDRESS : "+ipaddress +"\n");
                //  writer.println("uc");
                Constants.mozilla = false;
                
            }
            if(Constants.pendrivedetect == true)
            {
                
                //showConfirmDialog(null,"thread check all uc browser==true");
                writer.println("USER :"+pcname +" is Attempting to CHEAT !!!__"+ipaddress);
                 //writer.println("USER hghfghfg cheat msggg:");
                 writer.flush(); // flushes the buffer
                 unknownflush();
                ta_chat.append("Do not try to CHEAT !!! PCNAME : "+pcname +" IP ADDRESS : "+ipaddress +"\n");
                //  writer.println("uc");
                Constants.pendrivedetect = false;
                
            }
            if(Constants.appdetect1 == true)
            {
                
                //showConfirmDialog(null,"thread check all uc browser==true");
                writer.println("USER :"+pcname +" is Attempting to CHEAT !!!__"+ipaddress);
                 //writer.println("USER hghfghfg cheat msggg:");
                 writer.flush(); // flushes the buffer
                 unknownflush();
                ta_chat.append("Do not try to CHEAT !!! PCNAME : "+pcname +" IP ADDRESS : "+ipaddress +"\n");
                //  writer.println("uc");
                Constants.appdetect1 = false;
                
            }
            if(Constants.appdetect2 == true)
            {
                
                //showConfirmDialog(null,"thread check all uc browser==true");
                writer.println("USER :"+pcname +" is Attempting to CHEAT !!!__"+ipaddress);
                 //writer.println("USER hghfghfg cheat msggg:");
                 writer.flush(); // flushes the buffer
                 unknownflush();
                ta_chat.append("Do not try to CHEAT !!! PCNAME : "+pcname +" IP ADDRESS : "+ipaddress +"\n");
                //  writer.println("uc");
                Constants.appdetect2 = false;
                
            }
            if(Constants.appdetect3 == true)
            {
                
                //showConfirmDialog(null,"thread check all uc browser==true");
                writer.println("USER :"+pcname +" is Attempting to CHEAT !!!__"+ipaddress);
                 //writer.println("USER hghfghfg cheat msggg:");
                 writer.flush(); // flushes the buffer
                 unknownflush();
                ta_chat.append("Do not try to CHEAT !!! PCNAME : "+pcname +" IP ADDRESS : "+ipaddress +"\n");
                //  writer.println("uc");
                Constants.appdetect3 = false;
                
            }
            if(Constants.appdetect4 == true)
            {
                
                //showConfirmDialog(null,"thread check all uc browser==true");
                writer.println("USER :"+pcname +" is Attempting to CHEAT !!!__"+ipaddress);
                 //writer.println("USER hghfghfg cheat msggg:");
                 writer.flush(); // flushes the buffer
                 unknownflush();
                ta_chat.append("Do not try to CHEAT !!! PCNAME : "+pcname +" IP ADDRESS : "+ipaddress +"\n");
                //  writer.println("uc");
                Constants.appdetect4 = false;
                
            }
            if(Constants.appdetect5 == true)
            {
                
                //showConfirmDialog(null,"thread check all uc browser==true");
                writer.println("USER :"+pcname +" is Attempting to CHEAT !!!__"+ipaddress);
                 //writer.println("USER hghfghfg cheat msggg:");
                 writer.flush(); // flushes the buffer
                 unknownflush();
                ta_chat.append("Do not try to CHEAT !!! PCNAME : "+pcname +" IP ADDRESS : "+ipaddress +"\n");
                //  writer.println("uc");
                Constants.appdetect5 = false;
                
            }
            
            try
            {
            Thread.sleep(2000);
            }
            catch(Exception ex)
            {
                showConfirmDialog(null,ex);
            }
        }
    }
    
}
public class BackgroundBrowser implements Runnable
{
   public void run()
    {
        while(true)
        {
        
             try
           {
               String line;
           
               String pidInfo =" ";
            
                while(pidInfo != "chrome.exe" || pidInfo != "UCBrowser.exe" || pidInfo != "firefox.exe" || pidInfo != "iexplore.exe")
                {
                  pidInfo =" ";
                 Process p =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");

                 BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));

                     while ((line = input.readLine()) != null) 
                     {
                              pidInfo+=line; 
                     }
                      input.close();
                      if(pidInfo.contains("chrome.exe"))
                      {
                                  Constants.chrome = true;
                                //  showConfirmDialog(null,"GOOGLE CHROME DETECTED !!");
                                 
                      }
                      if(pidInfo.contains("UCBrowser.exe"))
                      {
                                Constants.ucbrowser = true;
                                //  showConfirmDialog(null,"UC BROWSER DETECTED !!");
                                  
                                  //client_frame.callanywhere();
                      }
                      if(pidInfo.contains("firefox.exe"))
                      {
                                 Constants.mozilla = true;
                                //  showConfirmDialog(null,"MOZILLA FIREFOX DETECTED !!!!!!");
                                 
                      }
                      if(pidInfo.contains("iexplore.exe"))
                      {
                                  Constants.internetexp = true;
                                //  showConfirmDialog(null,"INTERNET EXPLORER DETECTED !!");
                                 
                      }
                      //backgroundstart = "";
               
                }
               
           }
           catch(Exception e)
           {
               
           }
         
        }
    }
}
public class BackgroundPendrive implements Runnable
{
   public void run()
    {
        while(true)
        {
        
        
        
             try
                    {
                        File[] oldListRoot = File.listRoots();
                        while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (File.listRoots().length > oldListRoot.length) {
						//System.out.println("new drive detected");
						oldListRoot = File.listRoots();
						System.out.println("drive" + oldListRoot[oldListRoot.length - 1] + " detected");
                                                Constants.pendrivedetect = true;
						 showConfirmDialog(null,"PENDRIVE DETECTED: Please Remove your PENDRIVE");
                                                 

					} else if (File.listRoots().length < oldListRoot.length) {
						System.out.println(oldListRoot[oldListRoot.length - 1] + " drive removed");
                                                showConfirmDialog(null,"PENDRIVE DETECTED and REMOVED...");
						 showConfirmDialog(null,"YOU MUST NOT DO IT AGAIN...");
						oldListRoot = File.listRoots();
                                                
                                                        
                                                        
					}
				}
                        }
                        catch(Exception e)
                        {

                        }
        }
    }
}
public class BackgroundApp1 implements Runnable
{
   public void run()
    {
        while(true)
        {
        try
                    {
                        String line;

                        String pidInfo =" ";
                        
                        while(pidInfo != Constants.app1name)
                      {

                        
                            pidInfo =" ";
                            Process p =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");

                                BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));

                                while ((line = input.readLine()) != null)
                                {
                                    line = line.toLowerCase();
                                    pidInfo = pidInfo.toLowerCase();               
                                    pidInfo+=line;
                                     
                                }
                               
                                input.close();
                                if(pidInfo.contains(Constants.app1name))
                                {
                                    
                                   // showConfirmDialog(null,"APPLICATION OPENED !!! "+Constants.app1name);
                                    Constants.appdetect1 = true;
                                }

                      }  

                        }
                        catch(Exception e)
                        {

                        }
        
        
    }
    }
}
public class BackgroundApp2 implements Runnable
{
   public void run()
    {
        
            while(true)
        {
                    try
                    {
                        String line;

                        String pidInfo =" ";
                        
                        while(pidInfo != Constants.app2name)
                      {

                        
                            pidInfo =" ";
                            Process p =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");

                                BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));

                                while ((line = input.readLine()) != null)
                                {
                                    line = line.toLowerCase();
                                    pidInfo = pidInfo.toLowerCase();             
                                    pidInfo+=line;
                                }
                                input.close();
                                if(pidInfo.contains(Constants.app2name))
                                {
                                    
                                   // showConfirmDialog(null,"APPLICATION OPENED !!! "+Constants.app2name);
                                    Constants.appdetect2 = true;
                                }

                      }  

                        }
                        catch(Exception e)
                        {

                        }
                    
        }
    }
}
public class BackgroundApp3 implements Runnable
{
   public void run()
    {
        while(true)
        {
        try
                    {
                        String line;

                        String pidInfo =" ";
                        
                        while(pidInfo != Constants.app3name)
                      {

                        
                            pidInfo =" ";
                            Process p =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");

                                BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));

                                while ((line = input.readLine()) != null)
                                {
                                   line = line.toLowerCase();
                                    pidInfo = pidInfo.toLowerCase();             
                                    pidInfo+=line;
                                }
                                input.close();
                                if(pidInfo.contains(Constants.app3name))
                                {
                                    
                                  //  showConfirmDialog(null,"APPLICATION OPENED !!! "+Constants.app3name);
                                    Constants.appdetect3 = true;
                                }

                      }  

                        }
                        catch(Exception e)
                        {

                        }
        
    }
    }
}
public class BackgroundApp4 implements Runnable
{
   public void run()
    {
        while(true)
        {
        try
                    {
                        String line;

                        String pidInfo =" ";
                        
                        while(pidInfo != Constants.app4name)
                      {

                        
                            pidInfo =" ";
                            Process p =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");

                                BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));

                                while ((line = input.readLine()) != null)
                                {
                                    line = line.toLowerCase();
                                    pidInfo = pidInfo.toLowerCase();             
                                    pidInfo+=line;
                                }
                                input.close();
                                if(pidInfo.contains(Constants.app4name))
                                {
                                    
                                  //  showConfirmDialog(null,"APPLICATION OPENED !!! "+Constants.app4name);
                                    Constants.appdetect4 = true;
                                }

                      }  

                        }
                        catch(Exception e)
                        {

                        }
        
    }
    }
}
public class BackgroundApp5 implements Runnable
{
   public void run()
    {
       while(true)
        {
        try
                    {
                        String line;

                        String pidInfo =" ";
                        
                        while(pidInfo != Constants.app5name)
                      {

                        
                            pidInfo =" ";
                            Process p =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");

                                BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));

                                while ((line = input.readLine()) != null)
                                {
                                    line = line.toLowerCase();
                                    pidInfo = pidInfo.toLowerCase();             
                                    pidInfo+=line;
                                }
                                input.close();
                                if(pidInfo.contains(Constants.app5name))
                                {
                                    
                                  //  showConfirmDialog(null,"APPLICATION OPENED !!! "+Constants.app5name);
                                    Constants.appdetect5 = true;
                                }

                      }  

                        }
                        catch(Exception e)
                        {

                        }
        
    }
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_address;
    private javax.swing.JLabel lb_port;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextField tf_address;
    private javax.swing.JTextField tf_port;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
