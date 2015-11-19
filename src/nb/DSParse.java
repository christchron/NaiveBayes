/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.*;
/**
 *
 * @author X450
 */
public class DSParse {
    private String filename;
    private ArrayList<String[]> dataset;
    @SuppressWarnings("empty-statement")
    public DSParse(String fl){
        String csvFile = fl;
        BufferedReader br = null;
        String sCurrentLine;
        String[] temp;
        int tempLength = 0;
        int j=0;
        try{
            //Map<String, String> maps = new HashMap<String, String>();
            br = new BufferedReader(new FileReader(csvFile));
                    /*while ((line = br.readLine()) != null) {
                            // use comma as separator
                            String[] country = line.split(cvsSplitBy);
                            maps.put(country[4], country[5]);
                    }
                    //loop map
                    for (Map.Entry<String, String> entry : maps.entrySet()) {
                            System.out.println("Country [code= " + entry.getKey() + " , name="
                                    + entry.getValue() + "]");
                    }*/
            dataset = new ArrayList<String[]>();
            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                temp = sCurrentLine.split(",");
                tempLength = temp.length;
                    //dataset[j][i] = temp[i];
                    //System.out.println(temp[i]);
                    dataset.add(temp);
                    
                    //System.out.println((String[])dataset.get(i));
                
                //System.arraycopy(temp, 0, dataset[j], 0, tempLength);
            }
            for (int i = 0;i<dataset.size();i++){
                for (int p=0;p<dataset.get(i).length;p++){
                   // System.out.print(dataset.get(i)[p]);
                }
                //System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Done");
    }
    
    public ArrayList<String[]> getDS(){
        return dataset;
    }
}
