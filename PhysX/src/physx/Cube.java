/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physx;

import static java.lang.Math.abs;

/**
 *
 * @author Samuel
 */
public class Cube implements ObjInterface{
    private String type = "Cube";
    private String name;
    private int height; //height
    private double mass; //mass
    private int x; //coordinates
    private int y; 
    private int z;
    
    public Cube(String namei, int heighti, float massi, int xi, int yi, int zi){
        name = namei;
        height = heighti;
        mass = massi;
        x = xi;
        y = yi;
        z = zi;
    }
    
    
    public Cube(ObjInterface o){
        if(o.getType().equals("Cube")){
            name = o.getName();
            height = o.getHeight();
            mass = o.getMass();
            x = o.getX();
            y = o.getY();
            z = o.getZ();
        }
    }
    
    @Override
    public void print(){
        System.out.println("\t\tType: Cube");
        System.out.println("\t\tName: "+name);
        System.out.println("\t\tHeight: "+height);
        System.out.println("\t\tMass: "+mass);
        System.out.println("\t\tPosition: "+x+", "+y+", "+z);
    }
    
    
    @Override
    public void move(int xi, int yi, int zi){
        x += xi;
        y += yi;
        z += zi;
    }
    
    @Override
    public String getName(){
        return name;
    }
    
    
    @Override
    public double getMass(){
        return mass;
    }
    
    
        @Override
    public int calcMovement(double force, double gravity, double friction){
        double fricF =  (friction) * (gravity * this.getMass()); //don't divide the sliding friction coefficient (not rolling, sliding)
        double accel = (force - fricF) / this.getMass(); //find the net force and divide by mass to get acceleration
        double initV = accel * 0.5; //multiply the acceloration from the push with the duration of the push to get the starting velocity
        int distance = abs((int)((0 - (initV * initV)) / (2 * accel))); //get the distance moved based on the velocity and acceloration
        return distance; //return the distance moved
    }
    
    @Override
    public String getPos(){
        String pos = ("Cube: "+ this.name + " is at position: "+x+", "+y+", "+z+ ".");
        return pos;
    }

    
    @Override
    public int calcLift(double force, double gravity){
        int distance;
        double accel = force / this.getMass();
        if(accel <= gravity){
            distance = 0;
        }
        else{
            double initV = (accel - gravity) * 0.5;
            distance = abs((int) ((0 - (initV * initV)) / (2 * (accel - gravity))));
        }
        return distance;
    }
    
    @Override
    public int calcDrop(double gravity){
        int distance;
        double fallForce = this.z * gravity;
        distance = this.z; 
        return distance;
    }

    
    @Override
    public String getType(){
        return type;        
    }
    
    @Override
    public int getRadius(){
        return 0;
    }

    @Override    
    public int getHeight(){
        return height;
    }
    
    @Override    
    public int getX(){
        return x;
    }
    
    @Override    
    public int getY(){
        return y;
    }

    @Override    
    public int getZ(){
        return z;
    }


}
