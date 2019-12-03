/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * 
 */
public class LoadMenu implements Menu{
    
    private ArrayList<ObjInterface> objects;
    private double gravity;
    private double friction;
    private CareTaker caretaker;
    Scanner in = new Scanner(System.in);    
    
    LoadMenu(CareTaker ct){
        caretaker = ct;
    }
    
    @Override
    public void displayOptions(){
        System.out.println("\nLoad Menu:");
        System.out.println("\t 1) Load Simulation.");
        System.out.println("\t 2) Back to Main Menu.");
        System.out.println("\t 3) Exit.");
    }
    
    @Override
    public void getInput(){
        int num = 999;
        
        while(num != 1 && num != 2 && num != 3){
            System.out.println("\nPlease choose an option listed above.");
            num = in.nextInt();
        }

        switch (num) {
            case 1:
                caretaker.listFiles();
                System.out.println("Please input the name of the file you would like to load: ");
                in.nextLine();
                String fileName = in.nextLine()+".physX";
        
        {
            try {
                objects = caretaker.serializeDataIn(fileName);
            } catch (IOException ex) {
                System.out.println("Error: Unable to load file.");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error: Unable to load file.");
            }
        }

                System.out.println("Loading Settings Menu...\n");
                Menu gm = new GlobalMenu(objects);
                gm.start();
                break;
            case 2:
                System.out.println("Returning to Main Menu...\n");
                Menu mm = new MainMenu();
                mm.start();
                break;
            case 3:
                System.out.println("Exiting Program...\n");
                System.exit(0);
                break;
            default:
                break;
        }
    }
    
    @Override
    public void start(){
        while(true){
            this.displayOptions();
            this.getInput();
        }
    }

}
    


