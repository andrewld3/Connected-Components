// This code was used from http://www.algolist.net/Data_structures/Hash_table/Chaining
// The owner of this code holds all rights.

public class LinkedHashEntry {

    private int key;
    private Pixel value;
    private LinkedHashEntry next;

    LinkedHashEntry(int key, Pixel value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public Pixel getValue() {
        return value;
    }

    public void setValue(Pixel value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public LinkedHashEntry getNext() {
        return next;
    }

    public void setNext(LinkedHashEntry next) {
        this.next = next;
    }

}