/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import file.*;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author josue
 */
public class Tela extends javax.swing.JFrame {
    
    private boolean aply = false;

    /**
     * Creates new form Tela
     */
    public Tela() {
        
        initComponents();
        
    }
    
    public void Enter(boolean nao_e_teste, String title){
        
        setVisible(true);
        setLocation(300,150);
        setResizable(false);
        setTitle(title);
        this.aply = nao_e_teste;
        
    }
    
    private String Number(int num, int max){
        
        String txt = "";
        
        if(num < 10 && max >= 10){
            txt += "0";
        }
        
        if(num < 100 && max >= 100){
            txt += "0";
        }
        
        if(num < 1000 && max >= 1000){
            txt += "0";
        }
        
        txt += num;
        
        return txt;
        
    }
    
    private boolean isTempTrack(String txt, int min){
        
        if(min < 0){min = min - min * 2;}
        
        final String tema = "1234567890,:";
        
        int sel = 0;
        
        for(int n = 0; n < txt.length(); n++){
            
            for(int g = 0; g < tema.length(); g++){
                
                if(tema.charAt(g) == txt.charAt(n)){
                    
                    sel++;
                    break;
                    
                }//if(tema.charAt(g) == txt.charAt(n))
                
            }//for(int g = 0; g < tema.length(); g++)
            
        }//for(int n = 0; n < txt.length(); n++)
        
        return sel >= min;
        
    }//isTempTrack(String txt)
    
    private void Converter(String file){
        
        String ouput = file.substring(0, file.lastIndexOf("\\")+1);
        String files = file.substring(file.lastIndexOf("\\")+1);
        String name = files.substring(0, files.lastIndexOf("."));
        String ext = files.substring(files.lastIndexOf(".")+1);
        
        if(ext.equalsIgnoreCase("csv")){
            
            csv orm = new csv(file);
            
            int max_tot = 0;
            
            int max_track = 0;
            int max_folder = 0;
            int[] track_folder = new int[orm.Tot()];
            int[] track_one = new int[orm.Tot()];
            boolean[] track_val = new boolean[orm.Tot()];
            
            for(int ff = 0; ff < orm.Tot(); ff++){
                
                track_one[ff] = 0;
                track_folder[ff] = 1000;
                track_val[ff] = false;
                
            }//for(int ff = 0; ff < orm.Tot(); ff++)
            
            String all_folder = "";
            
            if(orm.Tot() >= 0){
                
                int track_folder_one = 1;
                
                for(int x = 0; x < orm.Tot(); x++){
                    
                    max_tot += new Numero(orm.Read(x, 6)).Num();
                    
                    int total = new Numero(orm.Read(x, 3)).Num();
                    
                    if(total > max_track){
                        max_track = total;
                    }
                    
                    if(orm.Read(x, 2).equalsIgnoreCase(all_folder)){
                        
                        track_folder_one++;
                        
                    } else {//if(!orm.Read(x, 2).equalsIgnoreCase(all_folder))
                        
                        track_folder_one = 1;
                        max_folder++;
                        
                        all_folder = orm.Read(x, 2);
                        
                        track_val[x] = true;
                        
                    }//if(!orm.Read(x, 2).equalsIgnoreCase(all_folder))
                    
                    track_one[x] = track_folder_one;
                    
                }//for(int x = 0; x < orm.Tot(); x++)
                
                int z = 0;
                boolean coding = true;
                
                for(int rew = orm.Tot()-1; rew >= 0; rew--){
                    
                    if(track_val[rew]){
                        coding = true;
                    }else if(coding){
                        z = track_one[rew];
                        coding = false;
                    }
                    
                    track_folder[rew] = z;
                    
                }
                
                z = 0;
                
            }//if(orm.Tot() >= 0)
            
            String htm = name;
            
            if(orm.Tot() >= 0){
                
                htm += ";Duração total: ";
                htm += new Hora(max_tot).Load();
                
                int folder = 1;
                String indo = "";
                
                for(int i = 0; i < orm.Tot(); i++){
                    
                    htm += "\n";
                    
                    /* orm.Read(i, 2) -- Álbum **/

                    if(!orm.Read(i, 2).isBlank() && !orm.Read(i, 2).equalsIgnoreCase(indo) && max_folder > 1){
                        
                        htm += "Pasta: ";
                        htm += Number(folder,max_folder);
                        htm += " de ";
                        htm += max_folder;
                        htm += ";";
                        htm += orm.Read(i, 2);
                        htm += "\n";
                        
                        indo = orm.Read(i, 2);
                        folder++;

                    }/*if(!orm.Read(i, 2).isBlank()) */
                    
                    // orm.Read(i, 3) -- Arquivo
                    
                    Numero track = new Numero(orm.Read(i, 3));
                    
                    if(track.Val() && track.Num() > 0 && track.Num() < 1000){
                        
                        htm += "Faixa ";
                        htm += Number(track.Num(),max_track);
                        htm += " de ";
                        htm += Number(max_track,0);
                        htm += ";";
                        
                    }//if(track.Val() && track.Num() > 0 && track.Num() < 1000)
                    
                    /* Faixa da pasta **/
                    
                    if(track_folder[i] > 1 && max_folder > 1){
                        
                        htm += "Arquivo: ";
                        htm += Number(track_one[i],track_folder[i]);
                        htm += " de ";
                        htm += Number(track_folder[i],0);
                        htm += ";";
                        
                    }//if(track_folder[i] > 0)
                    
                    /* Faixa da pasta */
                    
                    /* orm.Read(i, 6) -- Duração **/
                    
                    Numero number_track = new Numero(orm.Read(i, 6));
                    
                    Hora duraction_track = new Hora(number_track.Num());
                    
                    htm += "Duração: ";
                    htm += duraction_track.Load();
                    htm += ";";
                    
                    // orm.Read(i, 0) -- Título
                    
                    /* orm.Read(i, 10) -- Nome */
                    
                    if(orm.Read(i, 0).isBlank()){
                        
                        // Data e Hora do arquvi Original
                        
                        String origin[] = orm.Read(i,8).split(" ");
                        
                        Data orn =  new Data(origin[0]);
                        
                        Hora or;
                        
                        if(origin.length > 1){
                            or = new Hora(origin[1]);
                        } else {
                            or = new Hora(false);
                        }
                        
                        if(orn.Val() || or.Val()){
                            
                            if(orn.Val()){htm += orn.Load();}
                            
                            if(or.Val()){
                                
                                if(orn.Val()){
                                    htm += " | ";
                                }
                                
                                htm += or.Load();
                                
                            }//if(or.Val())
                            
                        } else {//if(orn.Val() || or.Val())
                            
                            htm += "Sem Título";
                            
                        }//if(orn.Val())
                        
                    } else {//if(orm.Read(i, 0).isBlank())
                        
                        if(isTempTrack(orm.Read(i, 0),10)){
                            htm += orm.Read(i, 0).trim().replace(" - ", ";");
                        } else {
                            htm += orm.Read(i, 0);
                        }
                        
                    }//if(orm.Read(i, 0).isBlank())
                    
                    /* orm.Read(i, 1) -- Artista **/

                    if(!orm.Read(i, 1).isBlank() && !orm.Read(i, 1).equalsIgnoreCase(orm.Read(i, 0))){
                        
                        htm += ";";
                        htm += orm.Read(i, 1);

                    }/*if(!orm.Read(i, 1).isBlank()) */
                    
                    /* orm.Read(i, 10) -- Arquivo **/
                    String nome_arq = orm.Read(i, 10);
                    
                    int ext_arq = nome_arq.contains(".") ? nome_arq.lastIndexOf(".") : nome_arq.length();
                        
                    if(ext_arq < nome_arq.length()-1){
                        
                        htm += ";";

                        switch(nome_arq.substring(ext_arq+1).toLowerCase()){
                            
                            case "m4a" ->{
                                
                                htm += nome_arq.substring(0, ext_arq);

                            }//case "m4a"
                            
                            default ->{
                                    
                                htm += nome_arq.substring(0, ext_arq).toUpperCase();
                                htm += nome_arq.substring(ext_arq).toLowerCase();
                                    
                            }//default
                            
                        }//switch(nome_arq.substring(ext_arq+1).toLowerCase())
                        
                    }//if(ext_arq < nome_arq.length()-1)
                    
                    /* orm.Read(i, 10) -- Arquivo */
                    
                }//for(int i = 0; i < orm.Tot(); i++)
                
            }//if(orm.Tot() >= 0) - 2
            
            // Diretório
            
            String out = ouput;
            
            // Nome do arquivo
            
            out += new Data().Load();
            out += "_";
            out += new Hora(true).Load();
            
            html export = new html(out,htm);
            
            System.out.println(aply ? export.Export() : export.Ready("\n"));
            
        } else {
            
            JOptionPane.showMessageDialog(null, "O arquivo:\n\"" + 
                    name + 
                    "\"\nNão é .CSV", 
                    "Só aceita .CSV", 
                    JOptionPane.INFORMATION_MESSAGE);
            
        }
        
    }//Converter(String file)

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        file = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        file.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fileMouseClicked(evt);
            }
        });
        file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileActionPerformed(evt);
            }
        });
        file.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fileKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(file, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(file, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fileKeyPressed
        
        //String code = file.getCurrentDirectory().getName();
        //System.out.println(code);
        
    }//GEN-LAST:event_fileKeyPressed

    private void fileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMouseClicked
        
        //String code = file.getCurrentDirectory().getName();
        //System.out.println(code);
        
    }//GEN-LAST:event_fileMouseClicked

    private void fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileActionPerformed
        
        //dispose();
        
        try{
            
            String files = file.getSelectedFile().toString();
            
            if(files.contains(".") && files.contains("\\")){
                
                Converter(files);
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Só aceita CSV", 
                        "Arquivo Inválido", 
                        JOptionPane.INFORMATION_MESSAGE);
                
            }//if(files.contains(".") && files.contains("\\"))
            
        }catch(HeadlessException ev){
            
            JOptionPane.showMessageDialog(null, "HeadlessException\n" +  
                    ev.getMessage().replaceAll(" \"","\n").replaceAll("\" ","\n"), 
                    "Código: " + ev.hashCode(), 
                    JOptionPane.INFORMATION_MESSAGE);
            
        }catch(Exception ev){
            
           System.out.println(ev.getLocalizedMessage());
            
        }finally{
            
            System.exit(0);
            
        }
        
    }//GEN-LAST:event_fileActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser file;
    // End of variables declaration//GEN-END:variables

    private void JOptionPane(Object object, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
