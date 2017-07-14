/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathproject;

import org.apache.commons.math3.geometry.euclidean.threed.*;

/**
 *
 * @author Jeff
 */
public class mathMethods 
{
private double P1[];
private double P2[];
private double D[];
    
    //turns input into an array of 3 which holds the x,y,z components of a point or vector
    public static double[] getArray(String input)
    {
        String[] arrayString = input.split(",");
        double[] someDoubleArray = new double[arrayString.length];
        for(int i=0; i<arrayString.length; i++)
        {
            someDoubleArray[i]= Double.parseDouble(arrayString[i]);
        }
        return someDoubleArray;
    }
    
    //checks if some vector is on a line by checking the ratio of line P1P2 and the vector V
    //If they are the same ratio, return true, otherwise false
    public static boolean onLine(double P1[], double P2[], double V[])
    {
        //String onLine=new String("The Point is not on the Line");
        //P1=R, P2=P
        double S[] = new double[3];
        for(int i=0; i<3; i++)
        {
            S[i]=P1[i]-P2[i];
        }


        Vector3D vector = new Vector3D(V);
        double[] check = vector.crossProduct(new Vector3D(S)).toArray();
        System.out.println(check);
        if(check[0]==check[1] && check[1]==check[2] && check[2] == 0)
            return true;
        else
            return false;
        
    }
    
    // creates the normal vector for vector P1P2 (named S here) and vector V using cross product
    public static String planeEquation(double P1[], double P2[], double V[])
    {
        String equation;
        double S[] = new double[3];
        
        //Make vector P1P2
        for(int i=0; i<3; i++)
        {
            S[i]=P1[i]-P2[i];
        }

        Vector3D vector = new Vector3D(V);
        double[] normalVector = vector.crossProduct(new Vector3D(S)).toArray();

        //Cross product
        //crossProduct(V,S);
        System.out.println("------"+normalVector[0]+" "+normalVector[1]+" "+normalVector[2]);
        return planeToString(normalVector,P1);
    }
    
    /*
    Finds the reflection of a, to determine the symmetric point to P1 about about line L, 
    we began by finding S again, which is the vector from point P2, on the line, to point P1. 
    We then took the projection of S on V. Using this projection along with the original vector S, 
    the equations S-projection gave us the vector that is orthogonal to line L and pointed 
    towards our point P. To find the mirrored point, P’, we multiplied the orthogonal 
    vector by -2, and added that vector to point P to finally get P’.
    */
    public static String reflection(double[] P1, double[] P2, double V[])
    {
        String result="";
        double[] S = new double[3];
        //vector from P2 to P1 will be called S
        for(int i=0;i<3;i++)
        {
            S[i]=P1[i]-P2[i];
        }

        //Calc comp and proj of S on direction vector V
        double[] reflection = new double[3];
        double absOfV = absoluteValue(V);
        double absOfS = absoluteValue(S);
        
        boolean onLine = onLine(P1,P2,V);
        
        if(onLine==true)
            return "The point is on the line!";
        else 
        {
            //When the angle between S and V is greater than 0 and less than 90
            
            //double[] projSonV = new double[3];
            
            double[] projSonV = new double[3];
            for(int i=0;i<3;i++)
            {
                projSonV[i]=(dotProduct(S,V)/(absOfV*absOfV))*(V[i]);
            }
            
            
            for(int i=0;i<3;i++)
            {
                reflection[i]=P1[i]-2*(S[i]-projSonV[i]);

            }
            
            result = "The symmetric point to ("+P1[0]+", "+P1[1]+", "+P1[2]+") is ("+String.format( "%.2f", reflection[0] )+", "+
                        String.format( "%.2f", reflection[1] )+", "+String.format( "%.2f", reflection[2] )+")";
            return result;
            
        }
    }
    
    public static String planeToString(double[] vector, double[] point)
    {
        String equation = "";
        double normalVector[] = new double[3];
        double[] P1 = new double[3];
        
        P1=point;
        normalVector=vector;
        
        double coefficient = normalVector[0]*-P1[0]+normalVector[1]*-P1[1]+normalVector[2]*-P1[2];
        
        if(normalVector[0]==0)
            equation+= "";
        else
            equation+= normalVector[0]+"x ";
        
        if(normalVector[1]>0)
            equation+= "+"+normalVector[1]+"y ";
        else if(normalVector[1]==0)
            equation+= "";
        else
            equation+= normalVector[1]+"y ";
        
        if(normalVector[2]>0)
            equation+= "+"+normalVector[2]+"z ";
        else if(normalVector[2]==0)
            equation+= "";
        else
            equation+= normalVector[2]+"z ";
        
        if(coefficient>0)
            equation+= "+"+coefficient+" = 0";
        else if(coefficient==0)
            equation+= "";
        else
            equation+= coefficient+" = 0";
        
        return equation;
    }
    
    public static double dotProduct(double[] a, double[] b)
    {
        System.out.println("PR: "+a[0]+" "+a[1]+" "+a[2]);
        System.out.println("q: "+b[0]+" "+b[1]+" "+b[2]);
        
        
        return (a[0]*b[0])+(a[1]*b[1])+(a[2]*b[2]);
    }
    
    public static double absoluteValue(double[] a)
    {
        return Math.sqrt( Math.pow(a[0], 2) + Math.pow(a[1], 2) + Math.pow(a[2], 2) );
    }
}
