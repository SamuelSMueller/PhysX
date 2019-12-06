/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physx;

import java.io.Serializable;

/**
 *
 * 
 */
public interface ObjInterface extends Serializable{
    public void print();
    public void move(int x, int y, int z);
    public String getName();
    public double getMass();
    public String getPos();
    public String getType();
    public int getRadius();
    public int getHeight();
    public int getX();
    public int getY();
    public int getZ();
    public int calcMovement(double force, double gravity, double friction);
    public int calcLift(double force, double gravity);
    public int calcDrop(double gravity);
    public int getVolume();
}
