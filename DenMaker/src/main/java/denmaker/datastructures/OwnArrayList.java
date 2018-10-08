/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.datastructures;

/**
 * This will be my own ArrayList
 *
 * @author apndx
 */
public class OwnArrayList<T> {

    private T[] values;
    private int counter;

    public OwnArrayList() {
        this.values = (T[]) new Object[10];
    }

    public void add(T value) {
        if (this.counter == this.values.length) {
            grow();
        }

        this.values[this.counter] = value;
        this.counter++;
    }

    private void grow() {

        T[] biggerOne = (T[]) new Object[this.values.length * 3 / 2 + 1];
        for (int i = 0; i < this.values.length; i++) {
            biggerOne[i] = this.values[i];
        }
        this.values = biggerOne;
    }

    public boolean includes(T value) {
        for (int i = 0; i < this.counter; i++) {
            if (value == this.values[i] || this.values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    public int valueIndex(T value) {
        for (int i = 0; i < this.counter; i++) {
            if (value == this.values[i] || this.values[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    private void moveToLeft(int fromIndex) {
        for (int i = fromIndex; i < this.counter - 1; i++) {
            this.values[i] = this.values[i + 1];
        }
    }

    public void remove(T value) {
        int valueIndex = valueIndex(value);
        if (valueIndex < 0) {
            return; // not found
        }
        moveToLeft(valueIndex);
        this.counter--;
    }

}
