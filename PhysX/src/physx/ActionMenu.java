/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;



public class ActionMenu implements Menu{
    
    private ArrayList<ObjInterface> objects;
    private double gravity;
    private double friction;
    private ObjInterface rObj;
    private ObjInterface uObj;
    private Command rCmd;
    private Command uCmd;
    private CareTaker caretaker;
    
    ActionMenu(ArrayList<ObjInterface> objs, double grav, double fric, CareTaker ct){
        objects = objs;
        gravity = grav;
        friction = fric;
        caretaker = ct;
    }
    
    @Override
    public void displayOptions(){
        System.out.println("\nAction Menu:");
        System.out.println("\t 1) List Last Command.");
        System.out.println("\t 2) Undo.");
        System.out.println("\t 3) Redo.");
        System.out.println("\t 4) List Objects.");
        System.out.println("\t 5) Command: Push.");
        System.out.println("\t 6) Command: Lift.");
        System.out.println("\t 7) Command: Drop.");
        System.out.println("\t 8) Save Simulation.");
        System.out.println("\t 9) Back to Settings Menu.");
        System.out.println("\t 10) Exit.");
    };

     private void reset(){
        this.displayOptions();
        this.getInput();
    }
     
    @Override
    public void getInput(){
        int num = 999;
        Scanner in = new Scanner(System.in);
        
        while(num != 1 && num != 2 && num != 3 && num != 4 && num != 5 && num != 6 && num != 7 && num != 8 && num != 9 && num != 10){
            System.out.println("\nPlease an option listed above.");
            num = in.nextInt();
        }

        switch (num) {
            case 1:
                System.out.println("Most Recent Command: ");
                if(rCmd == null){
                    System.out.println("No recent command.");
                }
                else{
                 rCmd.print();
                }
                break;
            case 2:
                if(rCmd == null){
                    System.out.println("No recent command.");
                }
                else{
                    System.out.println("Undoing most recent command... ");
                    String cName = rObj.getName(); //get the name of the object the most recent command effected
                    nameCheck: for(int i = 0; i<objects.size(); ++i){ //look through all objects and find the one that matches
                        ObjInterface obj = objects.get(i);
                        if(obj.getName().equals(cName)){ //for the one that matches
                            
                            if(obj.getType().equals("Sphere")){ //if its a sphere
                                uObj = new Sphere(obj); //undone object is a copy of its current condition
                                obj = new Sphere(rObj); //then change its current condition to its previous condition (rObj)
                                objects.set(i, obj);
                            }
                            else if(obj.getType().equals("Cube")){
                                uObj = new Cube(obj); //undone object is a copy of its current condition (uObj gets obj info)
                                obj = new Cube(rObj); //then change its current condition to its previous condition (obj gets rObj info))
                                objects.set(i, obj);
                            }
                            else{
                                uObj = new Cylinder(obj); //same as above
                                obj = new Cylinder(rObj);
                                objects.set(i, obj);
                            }
                        }//at this point, uObj holds the post-command info and obj holds the pre-command info
                        System.out.println("Command undone.");
                        obj.print();
                        uCmd = rCmd;
                        rCmd = null;
                        break nameCheck;
                    }
                }
                                
                break;
            case 3:
                if(uCmd == null){
                    System.out.println("No recently undone command.");
                }
                else{
                    System.out.println("Redoing most recently undone command... ");
                    String cName = uObj.getName();
                    nameCheck: for(int i = 0; i<objects.size(); ++i){ //look through all objects and find the one that matches
                        ObjInterface obj = objects.get(i);
                        if(obj.getName().equals(cName)){
                            if(obj.getType().equals("Sphere")){ //if its a sphere
                                obj = new Sphere(uObj);
                                objects.set(i, obj);
                            }
                            else if(obj.getType().equals("Cube")){
                                obj = new Cube(uObj); 
                                objects.set(i, obj);
                            }
                            else{
                                obj = new Cylinder(uObj);
                                objects.set(i, obj);
                            }
                            rCmd = uCmd;
                            uCmd = null;
                            System.out.println("Command redone.");
                            obj.print();
                            break nameCheck;
                        }
                    }
                }
                break;
                
            case 4:
                System.out.println("Objects currently created are: \n");
                int i = 1;
                
                if(objects.isEmpty()){
                    System.out.println("There are currently no objects.");
                    break;
                }
                
                for(ObjInterface obj : objects){
                    System.out.println("\nObject "+i++ +": ");
                    System.out.println("\t");
                    obj.print();
                    
                }
                break;
            case 5:
                System.out.println("Running Command: Push."); 
                Command push = new Push(objects, gravity, friction);
                rCmd = push;
                rObj = push.execute();
                break;
            case 6:
                System.out.println("Running Command: Lift.");  
                Command lift = new Lift(objects, gravity, friction);
                rCmd = lift;
                rObj = lift.execute();
                break;
            case 7:
                System.out.println("Running Command: Drop.");
                Command drop = new Drop(objects, gravity, friction);                
                rCmd = drop;
                rObj = drop.execute();
                break;
            case 8:
                Menu sm = new SaveMenu(objects, gravity, friction, caretaker);
                sm.start();
                break;
            case 9:
                System.out.println("Exiting to Settings Menu.");
                rCmd = null;
                rObj = null;
                uCmd = null;
                uObj = null;
                
                Menu gm = new GlobalMenu(caretaker);
                gm.start();
                break;
            case 10:
                System.out.println("Exiting Program... ");
                System.exit(0);
                break;
            default:
                break;
        }

    }
    
    @Override
    public void start(){
        while(true){
            reset();
        }
    }
    
}

