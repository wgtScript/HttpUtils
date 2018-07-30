package com.wgt.test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Lambda {
	public static void main(String[] args) {
        for_test();
        for_newMethod();
        for_lambda();
    }
     
    public static void for_test(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
         
        for(Integer i : list){
            System.out.println(i);
        }
    }
     
    public static void for_newMethod(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
         
        list.forEach(new Consumer<Integer>() {
 
            @Override
            public void accept(Integer t) {
                System.out.println(t);
            }
             
        });
    }
     
    public static void for_lambda(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        list.forEach(i -> System.out.println(i));
    }
}
