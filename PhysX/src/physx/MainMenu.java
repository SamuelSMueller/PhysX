/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physx;
import java.util.Scanner;

/**
 *
 * @author Samuel
 */
public class MainMenu implements Menu{
    
    CareTaker ct = new CareTaker();
    
    @Override
    public void displayOptions(){
        System.out.println("\nMain Menu:");
        System.out.println("\t 1) New Simulation.");
        System.out.println("\t 2) Load Simulation.");
        System.out.println("\t 3) Delete Simulation.");
        System.out.println("\t 4) Exit.");
    };

    @Override
    public void getInput(){
        int num = 999;
        Scanner in = new Scanner(System.in);
        
        while(num != 1 && num != 2 && num != 3 && num != 4){
            System.out.println("\nPlease choose an option listed above.");
            num = in.nextInt();
        }

        switch (num) {
            case 1:
                System.out.println("Starting New Simulation...\n");
                Menu gm = new GlobalMenu(ct);
                gm.start();
                break;
            case 2:
                Menu lm = new LoadMenu(ct);
                lm.start();
                break;
            case 3:
                System.out.println("Deleting Simulation...\n");
                break;
            case 4:
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
            displayOptions();
            getInput();
        }
    }
    
}
