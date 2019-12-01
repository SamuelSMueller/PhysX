/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel
 */
public class CareTaker {
    
     public ArrayList<ObjInterface> serializeDataIn(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        ArrayList<ObjInterface> objects = (ArrayList<ObjInterface>) ois.readObject();
        ois.close(); 
        return objects;
    }
     
    
    public void serializeDataOut(ArrayList<ObjInterface> objects, File file)throws IOException{
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
            System.out.println("Error: Unable to save file.");
            }
        }
        FileOutputStream fos = new FileOutputStream(file);
    
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(objects);
        oos.close();
    }   
    
    public void listFiles(){

        Path path = FileSystems.getDefault().getPath(".").toAbsolutePath();
        String s = path.toAbsolutePath().toString();
        String directory = s.substring(0, s.length() - 1);
        String filename;
        int i = 1;
        //ArrayList<String> textFiles = new ArrayList<String>();
        System.out.println("Current PhysX Files: ");
        File dir = new File(directory);
        for (File file : dir.listFiles()) {
            if (file.getName().endsWith(("physX"))) {
                filename = file.getName();
                filename = filename.substring(0, filename.length() -6);
                System.out.println(i++ + ": " + filename);
                //textFiles.add(file.getName());
            }
        }
    }
    
}
