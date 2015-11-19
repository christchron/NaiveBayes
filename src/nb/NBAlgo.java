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
public class NBAlgo {
    
    private ArrayList<ArrayList<String>> Frequent;
    private ArrayList<String> Class;
    private String res;
    private ArrayList<ArrayList<String>> ClassProb;
    
    public NBAlgo(DSParse dataset,ArrayList<String> dataTest){
        ArrayList<String> Variant = new ArrayList<String>();
        Class = new ArrayList<String>();
        Frequent= new ArrayList<ArrayList<String>>();
        Class = checkVariant(dataset,dataset.getDS().get(1).length - 1);
        int colNum = 1;
        for(int i = 0;i<dataset.getDS().get(1).length;i++){
            Variant=checkVariant(dataset,i);
            for (int j = 0;j<Variant.size();j++){
                for (int k = 0;k<Class.size();k++){
                    int count = 0;
                    ArrayList<String> element = new ArrayList<String>();
                    for (int l = 0;l<dataset.getDS().size();l++){
                        if (dataset.getDS().get(l)[i].equals(Variant.get(j)) && dataset.getDS().get(l)[dataset.getDS().get(1).length - 1].equals(Class.get(k))){
                            count++;
                        }
                    }
                    element.add(String.valueOf(colNum));
                    element.add(Variant.get(j));
                    element.add(Class.get(k));
                    element.add(String.valueOf(count));
                    Frequent.add(element);
                }
            }
            colNum++;
        }
        ClassProb = new ArrayList<ArrayList<String>>();
        Variant = checkVariant(dataset,0);
        for (int i = 0;i<Class.size();i++){
            int countClass=0;
            for (int j = 0;j< Class.size()* Variant.size();j++){
                //System.out.println(countClass);
                if (Frequent.get(j).get(2).equals(Class.get(i))){
                    countClass += Integer.parseInt(Frequent.get(j).get(3));
                    //System.out.println(countClass);
                }
            }
            ArrayList<String> ClassElement = new ArrayList<String>();
            ClassElement.add(Class.get(i));
            ClassElement.add(String.valueOf(countClass));
            ClassProb.add(ClassElement);
        }
        toProbTable();
        
        for(int i = 0 ; i<ClassProb.size();i++){
            double prob = 1.0;
            for (int j = 0;j<dataTest.size();j++){
                for(int k = 0;k<Frequent.size();k++){
                    if (Frequent.get(k).get(1).equals(dataTest.get(j)) && Frequent.get(k).get(2).equals(ClassProb.get(i).get(0))){
                        prob *= Double.parseDouble(Frequent.get(k).get(3));
                    }
                }
            }
            prob *= Double.parseDouble(ClassProb.get(i).get(1));
            ClassProb.get(i).set(1,Double.toString(prob));
        }
        
        double DominantClass = 0.0;
        res = new String();
        for (ArrayList<String> cprob : ClassProb){
            if (Double.parseDouble(cprob.get(1)) >= DominantClass){
                DominantClass = Double.parseDouble(cprob.get(1));
                res = cprob.get(0);
            }
        }
        
    };
    
    public ArrayList<String> checkVariant(DSParse dataset, int index){
        ArrayList<String> variant = new ArrayList<String>();
        for (int i = 0;i<dataset.getDS().size();i++){
           if (!variant.contains(dataset.getDS().get(i)[index])){
               variant.add(dataset.getDS().get(i)[index]);
           }
        }
        return variant;
    }
    
    public void toProbTable() {
        int colNum = 1;
        int Idx = 0;
        String classType;
        int count = 0;
        ArrayList<ArrayList<Integer>> denominator = new ArrayList<ArrayList<Integer>>();
        for (int i = 0;i<Frequent.size();) {
            ArrayList<Integer> countList = new ArrayList<Integer>();
            while (countList.size() < Class.size()) {
                classType = Frequent.get(i).get(2);
                while (i < Frequent.size() && Frequent.get(i).get(0).equals(String.valueOf(colNum))) {
                    if (Frequent.get(i).get(2).equals(classType))
                        count += Integer.parseInt(Frequent.get(i).get(3));
                    i++;
                }
                countList.add(count);
                count = 0;
                i = Idx + 1;
                Idx++;
            }
            while(i < Frequent.size() && Frequent.get(i).get(0).equals(String.valueOf(colNum))) {
                i++;
                Idx = i;
            }
            denominator.add(countList);
            colNum++;
        }

        int k = 0; colNum = 1;
        for (int i = 0; i < Frequent.size();i++) {
            if (!(Frequent.get(i).get(0).equals(String.valueOf(colNum)))) {
                k++;
                colNum++;
            }
            for (int j = 0; j < Class.size(); j++) {
                //System.out.println("test");
                if (Frequent.get(i).get(2).equals(Class.get(j))){
                    //freqTable.get(i).set(3, freqTable.get(i).get(3) + "/" + String.valueOf(countList.get(k).get(j)));
                    Double Prob = Double.parseDouble(Frequent.get(i).get(3)) / denominator.get(k).get(j);
                    Frequent.get(i).set(3, String.valueOf(Prob));
                }
            }
        }
        int countClassProb=0;
        for (ArrayList<String> cprob : ClassProb ){
            countClassProb += Integer.parseInt(cprob.get(1));
            //System.out.println(cprob.get(1));
        }
        
        for (int i =0;i<ClassProb.size();i++){
            double probclass = Double.parseDouble(ClassProb.get(i).get(1)) / (double) countClassProb;
            ClassProb.get(i).set(1, Double.toString(probclass));
        }
    }
    
    public String getResult(){
        return res;
    }
    
    public void print(){
        for(ArrayList<String> data : Frequent){
            System.out.println(data.get(0)+ " "+data.get(1)+ " "+data.get(2)+ " " + data.get(3));
        }
        /*for (ArrayList<String> data : ClassProb){
            System.out.println(data.get(0)+ " "+data.get(1));
        }*/
    }
    
}
