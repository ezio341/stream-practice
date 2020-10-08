/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import views.assignment2;
/**
 *
 * @author ASUS
 */
public class Assignment2 {
    private final assignment2 view;
    private List<Integer> list=new ArrayList<>();
    
    public Assignment2(assignment2 view) {
        this.view = view;
    }
    
     public void save() throws IOException, BadLocationException{
         JFileChooser loadFile = view.getLoadFile();
         if (JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)) {
             OutputStream os = null;
             try {
                 
                 if (!list.isEmpty()) {
                     os = new FileOutputStream(loadFile.getSelectedFile());
                     byte[] dt = new byte[list.size()];
                     for (int i = 0; i < list.size(); i++) {
                         dt[i] = list.get(i).byteValue();
                     }
                     os.write(dt);
                 }else{
                     JOptionPane.showMessageDialog(view, "empty set", "Error", JOptionPane.ERROR_MESSAGE);
                 }
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(Assignment2.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(Assignment2.class.getName()).log(Level.SEVERE, null, ex);
             } finally {
                 if (os != null) {
                     try {
                         os.flush();
                         os.close();
                         list.clear();
                     } catch (IOException ex) {
                         Logger.getLogger(Assignment2.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             }
         }
     }
     
    public void read() throws BadLocationException{
        JFileChooser loadFile = view.getLoadFile();
         Document doc = view.getTxtArea().getDocument();
         if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
             InputStream inputStream = null;
             try {
                 inputStream = new FileInputStream(loadFile.getSelectedFile());
                 int read = inputStream.read();
                 doc.insertString(0, "", null);
                 while (read != -1) {
                    list.add(read);
                     doc.insertString(doc.getLength(), "" + read, null);
                     System.out.println("" + read);
                     read = inputStream.read();
                 }
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(Assignment2.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException | BadLocationException ex) {
                 Logger.getLogger(Assignment2.class.getName()).log(Level.SEVERE, null, ex);
             } finally {
                 if (inputStream != null) {
                     try {
                         inputStream.close();
                     } catch (IOException ex) {
                         Logger.getLogger(Assignment2.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             }
         }
    }
}
