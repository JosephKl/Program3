import java.util.LinkedList;
import java.util.Random;

/**
 * @author Joseph Klaips
 * March 2020 
 * Linked list class for wheel items. 
 */
public class MyList {

    private MyNode head;
    private MyNode tail;
    private boolean spinning;


    public MyList(){
        spinning = false;
    }
    
    public MyList(MyList obj){
        head = obj.head;
        tail = obj.tail;
    }

    /**
     * @param str String to be appended 
     */
    public void addToEnd( String str) {
        MyNode n = new MyNode(str);
        // case 1: empty list
        if( head == null) {
            head = n;
            tail = n;
        }
        // case 2: add to end of existing list
        else {
            n.setPrev( tail);
            tail.setNext( n);
            tail = n;
        }
    }

    /**
     * @return The first item in the wheel 
     */
    public String getFirst() {
        String s = null;
        if( head != null) {
            s = head.getValue();
        }
        return s;
    }

    /**
     * @return The final item in the wheel 
     */
    public String getLast() {
        String s = null;
        if( tail != null) {
            s = tail.getValue();
        }
        return s;
    }

    /**
     * @return The size of the wheel 
     */
    public int numItems(){
        int count = 0;
        for(MyNode n = head; n != null; n = n.getNext()){
            count++;
        }
        return count;
    }
    
    /**
     * Clears the wheel 
     */
    public void clear() {
        head = null;
        tail = null;
    }

    /**
     * @return The items in the wheel 
     */
    public String toString(){
        String result = "";
        MyNode current = head;
        while(current != null){
            result = result + current.getValue() + "\n";
            current = current.getNext();
        }
        return result;
    }

    /**
     * @param index The location of a wheel item
     * @return The wheel item at the specified index 
     */
    public String get(int index){
        String result = "";
        MyNode current = head;
        int count = 0;
        while(current != null){
            result = current.getValue();
            current = current.getNext();
            count++;
            if(count == index){
                break;
            }
        }
        return result;
    }

    /**
     * @param str A wheel item 
     * @return True if that item is in the wheel, false otherwise 
     */
    public boolean get(String str){
        MyNode n = head;
        while(n != null){
            if(n.getValue().equals(str)){
                return true;
            }
            n = n.getNext();
        }
        return false;
    }

    //Reverse function is not operating as intended 
    public void reverse(){
        MyNode current = head;
        MyNode prev = null;
        MyNode next = null;
        while(current != null){
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
    }
    
    /**
     * @param i Index of an item in the wheel 
     * @return True if the item was removed from the wheel 
     */
    public boolean remove(int i){
        MyNode n = head;
        MyNode temp;
        int count = 1;
        if(i == 1){
            temp = head;
            head = head.getNext();
            temp.setNext(null);
            return true;
        }
        while(count < i-1){
            n = n.getNext();
            count++;
        }
        MyNode current = n.getNext();
        n.setNext(current.getNext());
        current.setNext(null);
        spinning = true;
        return true;
    }

    /**
     * @return True if the wheel is empty, false otherwise 
     */
    public boolean isEmpty(){
        if(head==null){
            return true;
        }
        return false;
    }   
}

