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
public class Push implements Command{
    private ArrayList<ObjInterface> objects;
    private String cName;
    private double force;
    private double friction;
    private double gravity;
    private int distance;
    private Scanner in = new Scanner(System.in);
    private ObjInterface cObject = null;
    private ObjInterface rObject = null;
    Push(ArrayList<ObjInterface> objs, double grav, double fric){
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
        force = in.nextFloat();
        
        System.out.println("Which direction would you like to push?");
        System.out.println("\t 1) Forward (y+).");
        System.out.println("\t 2) Backward (y-).");
        System.out.println("\t 3) Right (x+).");
        System.out.println("\t 4) Left (x-)."); 
    }
    
    public void getInput(){
        int num = 999;
        
        
        while(num != 1 && num != 2 && num != 3 && num != 4){
            System.out.println("\nPlease an option listed above.");
            num = in.nextInt();
        }
        
        distance = cObject.calcMovement(force, gravity, friction);
                    
        switch (num) {
            case 1:
                System.out.println("Pushing " + cObject.getName() + " forward... ");
                cObject.move(0, distance, 0);
                break;
            case 2:
                System.out.println("Pushing " + cObject.getName() + " backward... ");
                cObject.move(0, -1 * distance, 0);
                break;
            case 3:
                System.out.println("Pushing " + cObject.getName() + " right... ");
                cObject.move(distance, 0, 0);
                break;
            case 4:
                System.out.println("Pushing " + cObject.getName() + " left... ");
                cObject.move(-1 * distance, 0, 0);
                break;
            default:
                break;
        }
    }
    
    public void printResult(){
        System.out.println(cObject.getName() + " is moved " + distance + " meters." );
        System.out.println(cObject.getPos());
    }
    
    public ObjInterface checkName(){
        System.out.println("Which object would you like to push?");
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
        System.out.println("\tObject \'"+ cObject.getName() + "\' was pushed with " + force +" N of force.");
    }
        
}
