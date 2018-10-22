package com.calc;


import java.util.Random;
import java.lang.Math;
public class Main {

    public static double[] SimulatedAnnealing()//Simulated Annealing Function
    {
        double[] array = new double[3];

        double xZero=1.5;
        double yZero=1.5;
        double tZero=140;
        double alpha=0.98;
        int m=50;
        int n=20;


        double t=tZero;
        double x=xZero;
        double y=yZero;
        double xFinal=xZero;
        double yFinal=yZero;

        Random ranat= new Random();
        for (int i=1; i<=m;i++){
            for (int j=1; j<=n;j++){
                double xTemp=moveOpX(x); double yTemp=moveOpY(y);
                if (func(xTemp,yTemp)<=func(x,y)){
                    x=xTemp;y=yTemp;
                }else{
                    if (ranat.nextGaussian()<=metroPol(func(xTemp,yTemp),func(x,y),t)){
                        x=xTemp;y=yTemp;
                    }
                }
                if(func(x,y)<=func(xFinal,yFinal)){
                    xFinal=x; yFinal=y;
                }
            }
            t=t*alpha;
        }
        array[0]=xFinal;
        array[1]=yFinal;
        array[2]=func(xFinal,yFinal);
        return array;
    }
    public static double moveOpX(double num){//Move Operator for X
        Random random= new Random();
        double neigh = num+random.nextGaussian();
        return neigh;
    }
    public static double moveOpY(double num2){//Move Operator Y
        Random random2= new Random();
        double neigh2 = num2+random2.nextGaussian();
        return neigh2;
    }
    static double func(double x, double y) {//Function that is operated
        return (4-2.1*Math.pow(x,2)+Math.pow(x,4)/3)*Math.pow(x,2)+x*y+(-4+4*Math.pow(y,2))*Math.pow(y,2);
    }
    static double metroPol(double z1, double z2,double temperature){//Metropolis statement
        double res = Math.pow(Math.E,-((z1-z2)/temperature));
        return res;
    }
    public static void main(String[] args){//Main function
        double[] arr = new double[3];
        arr = SimulatedAnnealing();
        System.out.println("X final is: "+ arr[0]);
        System.out.println("Y final is: "+ arr[1]);
        System.out.println("Z is: "+ arr[2]);
    }
}
