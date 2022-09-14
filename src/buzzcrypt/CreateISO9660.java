/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzzcrypt;

import com.github.stephenc.javaisotools.eltorito.impl.ElToritoConfig;
import com.github.stephenc.javaisotools.iso9660.ISO9660Directory;
import com.github.stephenc.javaisotools.iso9660.ISO9660File;
import com.github.stephenc.javaisotools.iso9660.ISO9660RootDirectory;
import com.github.stephenc.javaisotools.iso9660.impl.CreateISO;
import com.github.stephenc.javaisotools.iso9660.impl.ISO9660Config;
import com.github.stephenc.javaisotools.iso9660.impl.ISOImageFileHandler;
import com.github.stephenc.javaisotools.joliet.impl.JolietConfig;
import com.github.stephenc.javaisotools.rockridge.impl.RockRidgeConfig;
import com.github.stephenc.javaisotools.sabre.StreamHandler;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.Vector;
import javax.swing.JFileChooser;

/**
 *
 * @author Beerbuzz02
 */
public class CreateISO9660 extends javax.swing.JInternalFrame {

    /**
     * Creates new form CreateISO9660
     */
    public CreateISO9660() {
        initComponents();
    }
     private static boolean enableJoliet = true;
     private static boolean enableRockRidge = true;
     private static boolean enableElTorito = true;
     
     private static void handleOption(String option){
         if(option.equals("disable-joliet")){
             enableJoliet = false;
             
         }else if (option.equals("disable-rockridge")){
             enableRockRidge = false;
         }else if (option.equals("disable-eltorito")){
             enableElTorito = false;
         }
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Name_ISO = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Path_ISO = new javax.swing.JTextField();
        Select = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Status_ISO_Create = new javax.swing.JTextArea();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Create ISO 9660");
        setAutoscrolls(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_buzzcrypt/bee_build.png_24x24.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Name of ISO");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Path");

        Select.setBackground(new java.awt.Color(204, 204, 204));
        Select.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Select.setText("Select");
        Select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Choose File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Status_ISO_Create.setColumns(20);
        Status_ISO_Create.setRows(5);
        jScrollPane1.setViewportView(Status_ISO_Create);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel2)
                                .addGap(36, 36, 36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Path_ISO)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Select))
                            .addComponent(Name_ISO, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(258, 258, 258))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Name_ISO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Path_ISO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Select)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectActionPerformed
        // TODO add your handling code here:
        try {
             // TODO add your handling code here:
        JFileChooser pathdir = new JFileChooser();
        pathdir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        pathdir.showOpenDialog(null);
    
        
        File Import = pathdir.getSelectedFile();
        String path = Import.getAbsolutePath();
        Path_ISO.setText(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_SelectActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       
           try {
            
            
            
            
        String args[];
        Status_ISO_Create.setText("Start");
        
        String pathdis  = Path_ISO.getText();
        String namefile = Name_ISO.getText();
        
        
        
        File outfile = new File(pathdis+"/"+namefile+".iso");
        //File outfile = new File(args.length >0 ? args[0] : "Test.iso");
        
        
        
        
        ISO9660RootDirectory.MOVED_DIRECTORIES_STORE_NAME = "rr_removed";
        ISO9660RootDirectory root = new ISO9660RootDirectory();
        
       
        JFileChooser newfile = new JFileChooser();
 
        newfile.setMultiSelectionEnabled(true);
        newfile.showOpenDialog(null);
        
        File test [] = newfile.getSelectedFiles();
       
      
        
        Vector addData = new Vector();
        for (int i=0; i < test.length; i++){
            
            String name = test[i].getName();
            String path = test[i].getAbsolutePath();
                 
       
           
        ISO9660File file1 = new ISO9660File(path);
        root.addFile(file1);
        
        
        Status_ISO_Create.append("+++++++++++++++++++++++ ISO9660 Support +++++++++++++++++++++++ \n ");
       
        
        ISO9660Config isoconfig9660 = new ISO9660Config();
        isoconfig9660.allowASCII(false);
        isoconfig9660.setInterchangeLevel(1);
        isoconfig9660.restrictDirDepthTo8(true);
        isoconfig9660.setPublisher("Name Nickname");
        isoconfig9660.setVolumeID("Test JICC Volumn");
        isoconfig9660.setDataPreparer("Name Nickname");
        
        
        
        isoconfig9660.forceDotDelimiter(true);
        
        RockRidgeConfig  rrConfig = null;
        if (enableRockRidge) {
			// Rock Ridge support
			rrConfig = new RockRidgeConfig();
			rrConfig.setMkisofsCompatibility(false);
			rrConfig.hideMovedDirectoriesStore(true);
			rrConfig.forcePortableFilenameCharacterSet(true);
		}
        ElToritoConfig   elToritoConfig = null;
        if (enableElTorito) {
			// El Torito support
			elToritoConfig = new ElToritoConfig(
				new File("tomsrtbt-2.0.103.ElTorito.288.img"),
				ElToritoConfig.BOOT_MEDIA_TYPE_2_88MEG_DISKETTE,
				ElToritoConfig.PLATFORM_ID_X86, "isoTest", 4,
				ElToritoConfig.LOAD_SEGMENT_7C0);
		}
        JolietConfig  jolietConfig = null;
        if (enableJoliet) {
			// Joliet support
			jolietConfig = new JolietConfig();
			jolietConfig.setPublisher("Jens Hatlak");
			jolietConfig.setVolumeID("Joliet Test");
			jolietConfig.setDataPreparer("Jens Hatlak");
			jolietConfig.setCopyrightFile(new File("Copyright.txt"));
			jolietConfig.forceDotDelimiter(true);
		}

        
        Status_ISO_Create.append("+++++++++++++++++++++++ Joliet support +++++++++++++++++++++++\n");
        jolietConfig = new JolietConfig();
        jolietConfig.setPublisher("Test 2");
        jolietConfig.setVolumeID("Joliet Test");
        jolietConfig.setPublisher("Jens Hatlek");
        jolietConfig.forceDotDelimiter(true);
        
        
        
       
        Status_ISO_Create.append("+++++++++++++++++++++++ Create ISO +++++++++++++++++++++++ \n");
        StreamHandler streamHandler = new ISOImageFileHandler(outfile);
        Status_ISO_Create.append("+++++++++++++++++++++++ streamHandler +++++++++++++++++++++++ \n");
        CreateISO iso = new CreateISO(streamHandler, root);
        Status_ISO_Create.setText("+++++++++++++++++++++++ iso +++++++++++++++++++++++  \n");
        iso.process(isoconfig9660, rrConfig, jolietConfig,  elToritoConfig);
        Status_ISO_Create.append("+++++++++++++++++++++++ process +++++++++++++++++++++++  \n");
        Status_ISO_Create.append("+++++++++++++++++++++++ Done. File is: +++++++++++++++++++++++ \n");
        Status_ISO_Create.append("+++++++++++++++++++++++ "+outfile+" +++++++++++++++++++++++");
        
        
        }
        
   
        
        } catch (Exception e) {
            e.printStackTrace();
        }
      
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Name_ISO;
    private javax.swing.JTextField Path_ISO;
    private javax.swing.JButton Select;
    private javax.swing.JTextArea Status_ISO_Create;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
