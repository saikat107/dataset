/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thesis_mdpvrp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author define
 */
public class dataConv {
    
    public dataConv(){
        String fileName="pr10";
        try {
            Scanner  inputFile=new Scanner(new File("original/"+fileName));
            FileWriter outputFile=new FileWriter(new File("compiled/"+fileName+".txt"));
            int num_vehicle,num_customers,num_days,num_depots;
            String line=inputFile.nextLine();
            StringTokenizer tok=new StringTokenizer(line," \t");
            tok.nextToken();
            num_vehicle=Integer.parseInt(tok.nextToken());
            num_customers=Integer.parseInt(tok.nextToken());
            num_days=Integer.parseInt(tok.nextToken());
            num_depots=Integer.parseInt(tok.nextToken());
            
            double []max_duration=new double[num_days*num_depots];
            double []max_load=new double[num_days*num_depots];
            for(int i=0;i<num_days*num_depots;i++){
                line=inputFile.nextLine();
                tok=new StringTokenizer(line," \t");
                max_duration[i]=Double.parseDouble(tok.nextToken());
                max_load[i]=Double.parseDouble(tok.nextToken());
            }
            
            node_description []nodes=new node_description[num_depots+num_customers];
            
            for(int i=0;i<num_customers+num_depots;i++){
                line=inputFile.nextLine();
                System.out.println(line);
                tok=new StringTokenizer(line," \t");
                int node_num=Integer.parseInt(tok.nextToken());
                double x_c=Double.parseDouble(tok.nextToken());
                double y_c=Double.parseDouble(tok.nextToken());
                double service_duration=Double.parseDouble(tok.nextToken());;
                double demand=Double.parseDouble(tok.nextToken());;
                int frequency=Integer.parseInt(tok.nextToken());
                nodes[i]=new node_description(i, x_c, y_c,service_duration, demand, frequency);
                
            }
            
            outputFile.write("1 ; number of test cases ; for now it is ignored\n");
            outputFile.write(num_days+" ; number of periods\n");
            outputFile.write(num_depots+" ; number of depots\n");
            outputFile.write((num_depots*num_vehicle)+" ; number of total vehicles\n");
            for(int i=0;i<num_depots;i++){
                outputFile.write(num_vehicle+" ");
            }
            outputFile.write(" ; number of vehicle per depot\n");
            
            
            
            /****************************** this portion of code is done to make coding easy based on intuition :-p ******************/
            for(int i=0;i<num_depots*num_vehicle;i++){
                outputFile.write(max_load[0]+" ");
            }
            outputFile.write(" ; Maximum allowable load of each vehicle \n");
            
            outputFile.write(" ; t(total period) lines containg  v (total vehicle) values each referring maximum time limit (route duration) for that day for that vehicle (NEW)\n");
            for(int i=0;i<num_days;i++){
                for(int j=0;j<num_depots*num_vehicle;j++){
                    outputFile.write(max_duration[0]+" ");
                }
                outputFile.write("\n");
            }
            
            outputFile.write(num_customers+" ; number of clients\n");
            /***************************************************************************************************************************/
            
            
            
            for(int i=0;i<num_customers;i++){
                outputFile.write(nodes[i+num_depots].freq+" ");
            }
            outputFile.write(" ; frequency\n");
            
            for(int i=0;i<num_customers;i++){
                outputFile.write(nodes[i+num_depots].ser_dur+" ");
            }
            outputFile.write(" ; service time for every client \n");
            
            for(int i=0;i<num_customers;i++){
                outputFile.write(nodes[i+num_depots].deman+" ");
            }
            outputFile.write(" ; demand for every client \n");
            
            
            outputFile.write("; COST MATRIX\n");
            outputFile.flush();
            
            
            
            
            for(int i=0;i<num_customers+num_depots;i++){
                for(int j=0;j<num_customers+num_depots;j++){
                    double dist=Math.sqrt((nodes[i].x-nodes[j].x)*(nodes[i].x-nodes[j].x)+(nodes[i].y-nodes[j].y)*(nodes[i].y-nodes[j].y));
                    outputFile.write(dist+" ");
                }
                outputFile.write("\n");
            }
            outputFile.flush();
            outputFile.close();
            
           
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(dataConv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}


class node_description{
    public int node_num,freq;
    public double x,y;
    public double ser_dur,deman;
    public node_description(int a,double b,double c,double d,double e, int f){
        node_num=a;
        x=b;
        y=c;
        ser_dur=d;
        deman=e;
        freq=f;
    }
    
}
