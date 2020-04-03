/**
 * @author Joseph Klaips 
 * March 2020 
 * Node class for wheel items. 
 */
public class MyNode {

    private String value;
    private MyNode next;
    private MyNode prev;

    /**
     * @param value Holds the name of an item 
     */
    public MyNode( String value) {
        this.value = value;
    }

    /**
     * @return The name of the item 
     */
    public String getValue() {
        return this.value;
    }

    /**
     * @return The next item in the wheel 
     */
    public MyNode getNext() {
        return this.next;
    }

    /**
     * @return The previous item in the wheel 
     */
    public MyNode getPrev() {
        return this.prev;
    }

    /**
     * @param n Changes the next itme in the wheel 
     */
    public void setNext( MyNode n) {
        this.next = n;
    }

    /**
     * @param n Changes the previous item in the wheel 
     */
    public void setPrev( MyNode n) {
        this.prev = n;
    }
}
