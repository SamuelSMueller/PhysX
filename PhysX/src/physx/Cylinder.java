/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physx;

import static java.lang.Math.abs;

/**
 *
 * 
 */
public class Cylinder implements ObjInterface{
    private String type = "Cylinder";
    private String name;
    private int radius; //radius
    private int height; //height
    private double mass; //mass
    private int x; //coordinates
    private int y; 
    private int z;
    
    public Cylinder(String namei, int radiusi, int heighti, float massi, int xi, int yi, int zi){
        name = namei;
        radius = radiusi;
        height = heighti;
        mass = massi;
        x = xi;
        y = yi;
        z = zi;
    }
    
    
    public Cylinder(ObjInterface o){
        if(o.getType().equals("Cylinder")){
            name = o.getName();
            height = o.getHeight();
            radius = o.getRadius();
            mass = o.getMass();
            x = o.getX();
            y = o.getY();
            z = o.getZ();
        }
    }
    
    @Override
    public void print(){
        System.out.println("\t\tType: Cylinder");
        System.out.println("\t\tName: "+name);
        System.out.println("\t\tRadius: "+radius);
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
        double fricF =  (friction/35) * (gravity * this.getMass()); //divide by 25 to get the rolling coefficient from the sliding coefficient (wider surface area)
        double accel = (force - fricF) / this.getMass(); //find the net force and divide by mass to get acceleration
        double initV = accel * 0.5; //multiply the acceloration from the push with the duration of the push to get the starting velocity
        int distance = abs((int)((0 - (initV * initV)) / (2 * accel))); //get the distance moved based on the velocity and acceloration
        return distance; //return the distance moved
    }
    
    @Override
    public String getPos(){
        String pos = ("Cylinder: "+ this.name + " is at position: "+x+", "+y+", "+z+ ".");
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
        return radius;
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
