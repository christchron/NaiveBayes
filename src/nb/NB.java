/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nb;

import java.util.ArrayList;

/**
 *
 * @author X450
 */
public class NB {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DSParse dset;
        dset = new DSParse("src/car.csv");
        ArrayList<String> test= new ArrayList<String>();
        test.add("low");
        test.add("low");
        test.add("4");
        test.add("more");
        test.add("small");
        test.add("low");
        NBAlgo Freq = new NBAlgo(dset,test);
        //System.out.println("aaas");
        System.out.println(Freq.getResult());
        //Freq.print();
    }   
}
