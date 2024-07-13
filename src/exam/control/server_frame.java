package exam.control;

import java.io.*;
import java.net.*;
import java.util.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class server_frame extends javax.swing.JFrame 
{
   ArrayList clientOutputStreams;
   ArrayList<String> users;
   public static boolean isConnected;
   public static int counter;
   public String counter_display;
   
   public InetAddress inet;
    public String ipaddress;
    
     public  Socket sock;
    public  BufferedReader reader;
    public  PrintWriter writer;

   public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket sock;
       PrintWriter client;

       public ClientHandler(Socket clientSocket, PrintWriter user) 
       {
            client = user;
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
                ta_chat.append("Unexpected error... \n");
            }

       }

       @Override
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" ;
            String[] data;

            try 
            {
                while ((message = reader.readLine()) != null) 
               // while(isConnected)
                {
                    message = reader.readLine();
                   // ta_chat.append("Received: " + message + "\n");
                    //data = message.split(":");
                    
                   // for (String token:data) 
                  //  {
                  //      ta_chat.append(token + "\n");
                  //  }
                 //   if(message.equalsIgnoreCase("uc"))
                 //   {
                        
                         client_activity.append("Received : " + message + "\n");
                         showMessageDialog(null," An user is trying to CHEAT !!! \n " +message);
                         String temp = "abc::";
                         if(message.equals(": :Disconnect"))
                         {
                             int dis = Integer.parseInt(connected_systems.getText().toString());
                             dis = dis - 1;
                            counter_display = Integer.toString(dis);
                            connected_systems.setText(counter_display);
                            counter--;
                            showMessageDialog(null," A Client got Disconnected");
                         }
                 //   }

//                    if (data[2].equals(connect)) 
//                    {
//                        tellEveryone((data[0] + ":" + data[1] + ":" + chat));
//                        userAdd(data[0]);
//                    } 
//                    else if (data[2].equals(disconnect)) 
//                    {
//                        tellEveryone((data[0] + ":has disconnected." + ":" + chat));
//                        userRemove(data[0]);
//                    } 
//                    else if (data[2].equals(chat)) 
//                    {
//                        tellEveryone(message);
//                    } 
//                    else 
//                    {
//                        ta_chat.append("No Conditions were met. \n");
//                    }
                } 
             } 
             catch (Exception ex) 
             {
                ta_chat.append("Lost a connection. \n");
                
                int dis = Integer.parseInt(connected_systems.getText().toString());
                dis = dis - 1;
                counter_display = Integer.toString(dis);
                connected_systems.setText(counter_display);
                ex.printStackTrace();
                clientOutputStreams.remove(client);
             } 
	} 
    }

    public server_frame() 
    {
        initComponents();
       getDetailsfForServer();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        b_start = new javax.swing.JButton();
        b_end = new javax.swing.JButton();
        b_clear = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        server_address = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        client_activity = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        connected_systems = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        shutdownall = new javax.swing.JButton();
        restartall = new javax.swing.JButton();
        sleepall = new javax.swing.JButton();
        onmodulesb = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Server's frame");
        setName("server"); // NOI18N
        setResizable(false);

        ta_chat.setEditable(false);
        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);

        b_start.setText("START");
        b_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_startActionPerformed(evt);
            }
        });

        b_end.setText("STOP");
        b_end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_endActionPerformed(evt);
            }
        });

        b_clear.setText("CLEAR");
        b_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_clearActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SERVER");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Log :-");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Server Address :-");

        server_address.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        server_address.setEnabled(false);
        server_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                server_addressActionPerformed(evt);
            }
        });

        client_activity.setEditable(false);
        client_activity.setColumns(20);
        client_activity.setRows(5);
        jScrollPane2.setViewportView(client_activity);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Connected Systems :-");

        connected_systems.setEditable(false);
        connected_systems.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        connected_systems.setEnabled(false);
        connected_systems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connected_systemsActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Client Actvity :-");

        shutdownall.setText("Shutdown All");
        shutdownall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shutdownallActionPerformed(evt);
            }
        });

        restartall.setText("Restart All");
        restartall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartallActionPerformed(evt);
            }
        });

        sleepall.setText("Sleep All");
        sleepall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sleepallActionPerformed(evt);
            }
        });

        onmodulesb.setText(" ON Modules");
        onmodulesb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onmodulesbActionPerformed(evt);
            }
        });

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(server_address))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(connected_systems, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sleepall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(shutdownall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(restartall, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(onmodulesb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(b_start, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(b_end, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(b_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(server_address, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(shutdownall, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(restartall, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(connected_systems, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sleepall, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(onmodulesb, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b_clear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(b_start, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b_end, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_endActionPerformed
        try 
        {
            Thread.sleep(5000);                 //5000 milliseconds is five second.
        } 
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
        tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
        ta_chat.append("Server stopping... \n");
        
        ta_chat.setText("");
        
        
    }//GEN-LAST:event_b_endActionPerformed

    private void b_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_startActionPerformed
        Thread starter = new Thread(new ServerStart());
        starter.start();
        
        ta_chat.append("Server started...\n");
    }//GEN-LAST:event_b_startActionPerformed

    private void b_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_clearActionPerformed
        ta_chat.setText("");
        client_activity.setText("");
    }//GEN-LAST:event_b_clearActionPerformed

    private void server_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_server_addressActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_server_addressActionPerformed

    private void connected_systemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connected_systemsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_connected_systemsActionPerformed

    private void shutdownallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shutdownallActionPerformed
        // TODO add your handling code here:
        
        Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
                
                writer.println("TRYING TO SHUTDOWN PC...");
                writer.flush(); 
		writer.println(Constants.shutdownall);
		ta_chat.append("\nALL SYSTEMS HAVE BEEN SHUTDOWN...\n");
                writer.flush();
                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());

            } 
            catch (Exception ex) 
            {
		ta_chat.append("CANNOT SHUTDOWN SYSTEMS... \n");
            }
        } 
    }//GEN-LAST:event_shutdownallActionPerformed

    private void restartallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartallActionPerformed
        // TODO add your handling code here:
        Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
                
                writer.println("TRYING TO RESTART PC...");
                writer.flush(); 
		writer.println(Constants.restartall);
		ta_chat.append("\nALL SYSTEMS HAVE BEEN RESTARTED...\n");
                writer.flush();
                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());

            } 
            catch (Exception ex) 
            {
		ta_chat.append("CANNOT RESTART SYSTEMS... \n");
            }
        } 
    }//GEN-LAST:event_restartallActionPerformed

    private void sleepallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sleepallActionPerformed
        // TODO add your handling code here:
        
        Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
                
                writer.println("TRYING TO SLEEP PC...");
                writer.flush(); 
		writer.println(Constants.sleepall);
		ta_chat.append("\nALL SYSTEMS HAVE BEEN SLEEP...\n");
                writer.flush();
                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());

            } 
            catch (Exception ex) 
            {
		ta_chat.append("CANNOT SLEEP SYSTEMS... \n");
            }
        } 
    }//GEN-LAST:event_sleepallActionPerformed

    private void onmodulesbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onmodulesbActionPerformed
        // TODO add your handling code here:
        
        ModulesSelect mod = new ModulesSelect();
        mod.setVisible(true);
        
        Thread CheckAllModules = new Thread(new CheckAllModules());
         CheckAllModules.start();
    }//GEN-LAST:event_onmodulesbActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
                this.dispose();
        
        TypeSelect ts3 = new TypeSelect();
        ts3.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                new server_frame().setVisible(true);
            }
        });
    }
    
    public class ServerStart implements Runnable 
    {
        @Override
        public void run() 
        {
            clientOutputStreams = new ArrayList();
            users = new ArrayList();  

            try 
            {
                ServerSocket serverSock = new ServerSocket(2222);

                while (true) 
                {
				Socket clientSock = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
				clientOutputStreams.add(writer);

				Thread listener = new Thread(new ClientHandler(clientSock, writer));
				listener.start();
				ta_chat.append("Got a connection. \n");
                                if(connected_systems.getText().toString().equals("")){
                                    
                                }
                                counter++;
                                counter_display = Integer.toString(counter);
                                connected_systems.setText(counter_display);
                }
            }
            catch (Exception ex)
            {
                ta_chat.append("Error making a connection. \n");
            }
        }
    }
    
    public void userAdd (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        ta_chat.append("Before " + name + " added. \n");
        users.add(name);
        ta_chat.append("After " + name + " added. \n");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void userRemove (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void tellEveryone(String message) 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(message);
		ta_chat.append("Sending: " + message + "\n");
                writer.flush();
                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());

            } 
            catch (Exception ex) 
            {
		ta_chat.append("Error telling everyone. \n");
            }
        } 
    }
     public void getDetailsfForServer()
    {
        try
    {
    
        inet = InetAddress.getLocalHost();
        ipaddress = inet.getHostAddress();
        server_address.setText(ipaddress);
    }
        catch (UnknownHostException ex)
        {
            showMessageDialog(null,"Host name cannot be resolved !");
        }
    }
    public class CheckAllModules implements Runnable
{
   public void run()
    {
         //getDetails();
        //showMessageDialog(null,"Thread started on SERVER.......");
        while(Constants.flagsall == true)
        {
            if(ServerConstants.ucbrowser == true || ServerConstants.internetexp == true || ServerConstants.chrome == true || ServerConstants.mozilla == true)
            {
               
                startUcbrowser();
              
                
                ServerConstants.ucbrowser = false; 
                        ServerConstants.internetexp = false; 
                                ServerConstants.chrome = false; 
                        ServerConstants.mozilla = false;
               
            }

            if(ServerConstants.pendrivedetect == true)
            {
                
                            startPendriveDetect();
                            ServerConstants.pendrivedetect = false;
                            
                
            }
            if(ServerConstants.appdetect1 == true)
            {
                
                 appDetect1();
                 ServerConstants.appdetect1 = false;
                
                
            }
            if(ServerConstants.appdetect2 == true)
            {
                
                appDetect2();
                 ServerConstants.appdetect2 = false;
                
                
                
            }
            if(ServerConstants.appdetect3 == true)
            {
                
                appDetect3();
                 ServerConstants.appdetect3 = false;
                
                
                
            }
            if(ServerConstants.appdetect4 == true)
            {
                
                 appDetect4();
                 ServerConstants.appdetect4 = false;
               
                
                
            }
            if(ServerConstants.appdetect5 == true)
            {
                
                appDetect5();
                 ServerConstants.appdetect5 = false;
                
                
            }
            
            try
            {
            Thread.sleep(2000);
            }
            catch(Exception ex)
            {
                showMessageDialog(null,ex);
            }
        }
    }
    
}
    public void unknownflush()
     {
         writer.println(":has connected.:Connect");
                writer.flush(); 
         
     }
     public void startUcbrowser() 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                 writer = (PrintWriter) it.next();
		showMessageDialog(null,"All Browser Detection Started !!");
                 writer.println("TRYING TO START BROWSER DETECTION...");
                writer.flush();
                
                 writer.println(ServerConstants.startallbrowser);
                 writer.flush(); // flushes the buffer
               //  showMessageDialog(null,"wroteeeeeeeeeee !!");
                 
                 

            } 
            catch (Exception ex) 
            {
		ta_chat.append("Error telling everyone. \n");
            }
        } 
    }
    public void startPendriveDetect() 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                 writer = (PrintWriter) it.next();
		showMessageDialog(null,"Pendrive Detection Started !!");
                 writer.println("TRYING TO START PENDRIVE DETECTION...");
                writer.flush();
                
                 writer.println(ServerConstants.startpendrive);
                 writer.flush(); // flushes the buffer
             //    showMessageDialog(null,"wroteeeeeeeeeee !!");
                 
                 

            } 
            catch (Exception ex) 
            {
		ta_chat.append("Error telling everyone. \n");
            }
        } 
    }
    public void appDetect1() 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                 writer = (PrintWriter) it.next();
		showMessageDialog(null,"Application Detection Started 1 !!");
                 writer.println("TRYING TO START APPLICATION DETECTION...");
                writer.flush();
              //  showMessageDialog(null,"trying to write "+ServerConstants.startapp1);
                 writer.println(ServerConstants.startapp1);
                 writer.flush(); // flushes the buffer
                // showMessageDialog(null,"wroteeeeeeeeeee !!");
                 
                 

            } 
            catch (Exception ex) 
            {
		ta_chat.append("Error telling everyone. \n");
            }
        } 
    }
    public void appDetect2() 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                 writer = (PrintWriter) it.next();
		showMessageDialog(null,"Application Detection Started 2 !!");
                 writer.println("TRYING TO START APPLICATION DETECTION...");
                writer.flush();
           //     showMessageDialog(null,"trying to write "+ServerConstants.startapp2);
                 writer.println(ServerConstants.startapp2);
                 writer.flush(); // flushes the buffer
           //      showMessageDialog(null,"wroteeeeeeeeeee !!");
                 
                 

            } 
            catch (Exception ex) 
            {
		ta_chat.append("Error telling everyone. \n");
            }
        } 
    }
    public void appDetect3() 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                 writer = (PrintWriter) it.next();
		showMessageDialog(null,"Application Detection Started 3 !!");
                 writer.println("TRYING TO START APPLICATION DETECTION...");
                writer.flush();
           //     showMessageDialog(null,"trying to write "+ServerConstants.startapp3);
                 writer.println(ServerConstants.startapp3);
                 writer.flush(); // flushes the buffer
          //       showMessageDialog(null,"wroteeeeeeeeeee !!");
                 
                 

            } 
            catch (Exception ex) 
            {
		ta_chat.append("Error telling everyone. \n");
            }
        } 
    }
    public void appDetect4() 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                 writer = (PrintWriter) it.next();
		showMessageDialog(null,"Application Detection Started 4 !!");
                 writer.println("TRYING TO START APPLICATION DETECTION...");
                writer.flush();
          //      showMessageDialog(null,"trying to write "+ServerConstants.startapp4);
                 writer.println(ServerConstants.startapp4);
                 writer.flush(); // flushes the buffer
           //      showMessageDialog(null,"wroteeeeeeeeeee !!");
                 
                 

            } 
            catch (Exception ex) 
            {
		ta_chat.append("Error telling everyone. \n");
            }
        } 
    }
    public void appDetect5() 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                 writer = (PrintWriter) it.next();
		showMessageDialog(null,"Application Detection Started 5 !!");
                 writer.println("TRYING TO START APPLICATION DETECTION...");
                writer.flush();
              //  showMessageDialog(null,"trying to write "+ServerConstants.startapp5);
                 writer.println(ServerConstants.startapp5);
                 writer.flush(); // flushes the buffer
                // showMessageDialog(null,"wroteeeeeeeeeee !!");
                 
                 

            } 
            catch (Exception ex) 
            {
		ta_chat.append("Error telling everyone. \n");
            }
        } 
    }
     
     
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_clear;
    private javax.swing.JButton b_end;
    private javax.swing.JButton b_start;
    private javax.swing.JTextArea client_activity;
    private javax.swing.JTextField connected_systems;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton onmodulesb;
    private javax.swing.JButton restartall;
    private javax.swing.JTextField server_address;
    private javax.swing.JButton shutdownall;
    private javax.swing.JButton sleepall;
    private javax.swing.JTextArea ta_chat;
    // End of variables declaration//GEN-END:variables
}
