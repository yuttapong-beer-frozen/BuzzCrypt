/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzzcrypt;

import static buzzcrypt.FrameMainProgram.jTable1;
import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Beerbuzz02
 */
public class DecryptionISO9660 extends javax.swing.JInternalFrame {

    /**
     * Creates new form DecryptionISO9660
     */
    public DecryptionISO9660() {
        initComponents();
    }
      public boolean checks;
      private static final int IV_LENGTH = 16;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @param zipFile
     * @param outputFolder
     */
    @SuppressWarnings("unchecked")
    public void outzip(String zipFile,String outputFolder){
    try {
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
        ZipEntry ze = zis.getNextEntry();
        if(ze == null ){
            checks = false;
            
        }else{
            checks = true;
        } while(ze != null){
            String fileName = ze.getName();
            File newFile = new File(outputFolder + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(newFile);
            
            int len;
            while((len = zis.read(buffer)) > 0){
                fos.write(buffer, 0 , len);
            }
            fos.close();
            ze = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        System.out.println("Done");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
}
public static void intozip(String filepath,String fname)
{
    try {
            FileInputStream fis = new FileInputStream(filepath);
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(filepath+".zip"));
            zos.putNextEntry(new ZipEntry(fname));
            byte [] Buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = fis.read(Buffer)) > 0 ){
                zos.write(Buffer,0,bytesRead);
            }
            zos.closeEntry();
            zos.close();
            fis.close();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex);
    }

}
public static String md5(String input)
{
    
        String md5 = null;
        if(null == input ) return null;
        try {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(input.getBytes(),0,input.length());
        md5 = new BigInteger(1,digest.digest()).toString(8);
    } catch (NoSuchAlgorithmException ex) {
        ex.printStackTrace();
    }
    return md5;
}

public static void encrypt(InputStream in,OutputStream out,String password){
    try {
        byte[] iv = new byte[IV_LENGTH];
        byte[] temp = new byte[16];
        
        temp = md5(password).getBytes();
        byte[] keys = new byte[32];
           
                System.out.println(md5(password).getBytes().length);
                for (int x = 0; x <= 15; x++){
                    iv [x] = temp[x];
                    
                }
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                SecretKeySpec keySpec = new SecretKeySpec(keys,"AES");
                IvParameterSpec IvSpec = new IvParameterSpec(iv);
                cipher.init(Cipher.ENCRYPT_MODE,keySpec,IvSpec);
                
                out = new CipherOutputStream(out,cipher);
                int numRead = 0;
                byte[] buf = new byte[1024];
                while((numRead = in.read(buf)) >=0) {
                  out.write(buf , 0 , numRead);
                }
                out.close();
                        
                
            
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",0);
    }
}
public static int decrypt(InputStream in,OutputStream out,String password)
{
    try {
        byte[] keys = new byte[32];
        byte[] iv = new byte[IV_LENGTH];
        byte[] temp = new byte[16];
        temp = md5(password).getBytes();
        for(int x = 0; x <= 15; x++){
            iv[x] = temp[x];
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(keys,"AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);
        
        
        in = new CipherInputStream(in, cipher);
        byte[] buf = new byte[1024];
        int numRead = 0 ;
        while ((numRead = in.read(buf)) >=0){
            out.write(buf, 0 , numRead);
            
        }
        out.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
       
    return -1;
}
public static void copy(int mode,String inputFile,String outputFile,String password) throws Exception{
    int x = 0;
    BufferedInputStream is = new BufferedInputStream(new FileInputStream(inputFile));
    BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(outputFile));
    
    if(mode == Cipher.ENCRYPT_MODE){
    encrypt(is,os,password);
    x = 2;
    }
    else if (mode == Cipher.DECRYPT_MODE){
        x = 0;
        x = decrypt(is,os,password);
        
    }
    else throw new Exception("unknow mode");
    if(x == -1  || x == 0){
        //JOptionPane.showMessageDialog(null,"Decryption Failed","Failed",0);
    }else{
        if(x != 2){
           JOptionPane.showMessageDialog(null,"Decryption Done","Done",0);
        }
        is.close();
        os.close();
    }
}
 
    

 public void output(){
    
    int inputfile_1;
    
    JFileChooser inputfile = new JFileChooser();
    FileNameExtensionFilter filefilters = new FileNameExtensionFilter("Buzz encrypted files (*.enc)","enc");
   
    inputfile.setFileFilter(filefilters);
    inputfile_1 = inputfile.showOpenDialog(null);

  
    
         DefaultTableModel fx = (DefaultTableModel)jTable1.getModel();
     
        File filepath_1  = inputfile.getSelectedFile();
        String filefullpath  = filepath_1.getAbsolutePath();
        
       Vector addData = new Vector();
     
           String name = filepath_1.getName();
           long bytes = filepath_1.length();
           //long kilobytes = bytes/1024;
           
            addData.add(name);
            addData.add(bytes);
            addData.add("AES 128 bit");
            addData.add("Decryption");
            fx.addRow(addData);
          
       
       String password_1 = new String(passpharse_1.getPassword());
       String password_2 = new String(passpharse_2.getPassword());
       
       
       
        if(new String (passpharse_1.getPassword()).isEmpty()  == false){
            
            try {
                copy(Cipher.DECRYPT_MODE,filefullpath,filefullpath.replace(".enc",".zip"),password_1);
                String folder =  filepath_1.getParent();
                outzip(filefullpath.replace(".enc",".zip"),folder);
                File deletefile = new File(filefullpath.replace(".enc",".zip"));
                deletefile.delete();
               
                if(checks){
                    JOptionPane.showMessageDialog(null,"Decrypted Done","Successfu",1);
                }else{
                    JOptionPane.showMessageDialog(null,"Decrypted Fail","Fail",0);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e,"cancel",1);
            }
        }
 }
       
 
 

    
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        passpharse_1 = new javax.swing.JPasswordField();
        passpharse_2 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jCheckBox2 = new javax.swing.JCheckBox();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Decryption ISO 9660");
        setAutoscrolls(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_buzzcrypt/bee_decrypt.png_24x24.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Passpharse");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Re - Passpharse");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Select");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Use filekey");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Select File To Decryption");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Select");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(373, 373, 373)
                                .addComponent(jCheckBox2))
                            .addComponent(passpharse_2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(85, 85, 85)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passpharse_1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addGap(43, 43, 43)
                                .addComponent(jButton1)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passpharse_1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passpharse_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel4))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox1.isSelected()){
            jButton1.setEnabled(true);
            passpharse_1.setEnabled(false);
            passpharse_2.setEnabled(false);
          
        }else{
            jButton1.setEnabled(false);
            passpharse_1.setEnabled(true);
            passpharse_2.setEnabled(true);
            passpharse_1.setText("");
            passpharse_2.setText("");
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Properties prop = new Properties();
	InputStream input = null;

	try {
               JFileChooser inFile = new JFileChooser();
               inFile.showOpenDialog(null);
		input = new FileInputStream(inFile.getSelectedFile());

		// load a properties file
		prop.load(input);

		// get the property value and print it out
		
		passpharse_1.setText(prop.getProperty("dbpassword"));
                passpharse_2.setText(prop.getProperty("dbrepassword"));

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
                }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        jButton1.setEnabled(false);
    }//GEN-LAST:event_formInternalFrameOpened

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(!passpharse_1.getText().equals(passpharse_2.getText())){
            JOptionPane.showMessageDialog(null,"Plase input passpharse correct !!!","Warning",3);
        }else if(passpharse_1.getText().equals("") || passpharse_2.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Plase input yout passphrase");
        }else{
            output();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
        if (jCheckBox2.isSelected()) {
            passpharse_1.setEchoChar((char)0);
            passpharse_2.setEchoChar((char)0);
        }else{
            passpharse_1.setEchoChar('*');
            passpharse_2.setEchoChar('*');
            
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField passpharse_1;
    private javax.swing.JPasswordField passpharse_2;
    // End of variables declaration//GEN-END:variables
}
