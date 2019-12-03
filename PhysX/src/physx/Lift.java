/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physx;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * 
 */
public class Lift implements Command{
    private ArrayList<ObjInterface> objects;
    private String cName;
    private double force;
    private double friction;
    private double gravity;
    private int distance;
    private Scanner in = new Scanner(System.in);
    private ObjInterface cObject = null;
    private ObjInterface rObject = null;
    
    Lift(ArrayList<ObjInterface> objs, double grav, double fric){
        objects = objs;
        gravity = grav;
        friction = fric;
    }
    
    @Override
    public ObjInterface execute(){
        displayOptions();
        getInput();
        printResult();
        return rObject;
    }
    
    
    public void displayOptions(){

        while(rObject == null){
            rObject = checkName();
        } 
        
        System.out.println("How much force?");
    }
    
    public void getInput(){
        force = in.nextFloat();
        distance = cObject.calcLift(force, gravity);
        cObject.move(0, 0, distance);
    }
    
    public void printResult(){
        System.out.println(cObject.getName() + " is lifted " + distance + " meters." );
        System.out.println(cObject.getPos());
    }
    
       public ObjInterface checkName(){
        System.out.println("Which object would you like to lift?");
        cName = in.nextLine();
        for(ObjInterface obj : objects){
            if(obj.getName().equals(cName)){
                cObject = obj;
                if(obj.getType().equals("Sphere")){
                    ObjInterface nObject = new Sphere(obj);
                    rObject = nObject;
                }
                else if(obj.getType().equals("Cube")){
                    ObjInterface nObject = new Cube(obj);
                    rObject = nObject;
                }
                else{
                    ObjInterface nObject = new Cylinder(obj);
                    rObject = nObject;
                }
                System.out.println("Object found.");
                return rObject;
            }
        }
        System.out.println("Object not found.");
        return null;
    }
    
    @Override
    public void print(){
        if(cObject == null){
            System.out.println("No object was selected.");
            return;
        }
        System.out.println("\tObject \'"+ cObject.getName() + "\' was lifted with " + force +" N of force.");
    }

        
}
