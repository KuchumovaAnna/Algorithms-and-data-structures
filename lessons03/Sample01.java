package lessons03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Sample01 {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(10000);
        list.add(1);
        list.ensureCapacity(1000);
        HashSet<Integer> list2 = new HashSet<>();
        list2.add(1);
        LinkedList<Integer> list3 = new LinkedList<>();

        list3.offerFirst(1);
        list3.offerLast(1);
        list3.pollFirst();
        list3.peekLast();
        list3.peekFirst();
        list3.peekLast();


    }

}

class MyCollection {

    private int[] arr;
    private int capacity;
    private int length = 0;

    public  MyCollection(int capacity){
        this.capacity = capacity;
        arr = new int[capacity];
    }

    public  MyCollection(){
        capacity = 10;
        arr = new int[capacity];
    }

    public void add(int i){
        arr[length] = i;
        length++;
    }



}