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
public class Sphere implements ObjInterface{
    private String type = "Sphere";
    private String name;
    private int radius; //radius
    private double mass; //mass
    private int x; //coordinates
    private int y; 
    private int z;
    private int volume;
    
    public Sphere(String namei, int radiusi, float massi, int xi, int yi, int zi){
        name = namei;
        radius = radiusi;
        mass = massi;
        x = xi;
        y = yi;
        z = zi;
        volume = (int)((4/3) * radius*radius*radius * 3.14);
    }
    
    public Sphere(ObjInterface o){
        if(o.getType().equals("Sphere")){
            name = o.getName();
            radius = o.getRadius();
            mass = o.getMass();
            x = o.getX();
            y = o.getY();
            z = o.getZ();
        }
    }
    
    @Override
    public void print(){
        System.out.println("\t\tType: Sphere");
        System.out.println("\t\tName: "+name);
        System.out.println("\t\tRadius: "+radius);
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
        double fricF =  (friction/70) * (gravity * this.getMass()); //divide by 70 to get the rolling coefficient from the sliding coefficient (point surface area)
        double accel = (force - fricF) / this.getMass(); //find the net force and divide by mass to get acceleration
        double initV = accel * 0.5; //multiply the acceloration from the push with the duration of the push to get the starting velocity
        int distance = abs((int)((0 - (initV * initV)) / (2 * accel))); //get the distance moved based on the velocity and acceloration
        return distance; //return the distance moved
    }

    @Override
    public String getPos(){
        String pos = ("Sphere: "+ this.name + " is at position: "+x+", "+y+", "+z+ ".");
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
        if(gravity == 0){
            return 0;
        }
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
        return 0;
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
    
    @Override
    public int getVolume(){
        return volume;
    }
}
