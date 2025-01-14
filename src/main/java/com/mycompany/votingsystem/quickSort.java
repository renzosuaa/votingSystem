package com.mycompany.votingsystem;

import java.util.*;

public class quickSort {

    
//quick sort to sort the Hashmap
    private  <K, V extends Comparable<V>> int partition(List<Map.Entry<K, V>> list, int low, int high) {
        V pivot = list.get(high).getValue(); 
        int i = (low - 1); 

        for (int j = low; j < high; j++) {
            if (list.get(j).getValue().compareTo(pivot) <= 0) {
                i++; 

                Map.Entry<K, V> temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        Map.Entry<K, V> temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return (i + 1);
    }
    
    public  <K, V extends Comparable<V>> void quickSort(List<Map.Entry<K, V>> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high); 

            quickSort(list, low, pi - 1); 
            quickSort(list, pi + 1, high); 
        }
    }

    public  <K, V extends Comparable<V>> HashMap<K, V> sortByV(HashMap<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        quickSort(list, 0, list.size() - 1);

        HashMap<K, V> result = new LinkedHashMap<>();

        for(int i = list.size() - 1; i >= 0;i--){
                Map.Entry<K,V> entry = list.get(i);
                result.put(entry.getKey(),entry.getValue());
            }


        return result;
    }
}