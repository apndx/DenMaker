/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package denmaker.datastructures;

/**
 * This is my OwnArrayList
 *
 * @author apndx
 * @param <T> object type to store
 */
public class OwnArrayList<T> {

    private T[] values;
    private int size;
    private int removeCount;

    public OwnArrayList() {
        this.values = (T[]) new Object[10];
        this.size = 0;
        this.removeCount = 0;
    }

    /**
     * Adds object in the end of the list, grows the list if needed
     *
     * @param value object to add
     */
    public void add(T value) {
        if (this.size == this.values.length) {
            grow();
        }

        this.values[this.size] = value;
        this.size++;
    }

    /**
     * Grows the list
     *
     * @param value object to add
     */
    private void grow() {

        T[] biggerOne = (T[]) new Object[this.values.length * 3 / 2 + 1];
        for (int i = 0; i < this.values.length; i++) {
            biggerOne[i] = this.values[i];
        }
        this.values = biggerOne;
    }

    /**
     * Is the object on the list?
     *
     * @param value object to search
     * @return true if found, false if not
     */
    public boolean includes(T value) {
        for (int i = 0; i < this.size; i++) {
            if (value == this.values[i] || this.values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the index for the object
     *
     * @return value returns the index of the object
     * @param value object to check
     */
    public int valueIndex(T value) {
        for (int i = 0; i < this.size; i++) {
            if (value == this.values[i] || this.values[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Moves all objects to left
     *
     * @param fromIndex How many steps we move
     */
    private void moveToLeft(int fromIndex) {
        for (int i = fromIndex; i < this.size - 1; i++) {
            this.values[i] = this.values[i + 1];
        }
    }

    /**
     * Removes an object from the list
     *
     * @param value The object to remove
     */
    public void remove(T value) {

        int valueIndex = valueIndex(value);
        if (valueIndex < 0) {
            return; // not found
        }
        moveToLeft(valueIndex);
        this.removeCount++;
        this.size--;

        if ((size / 10) < removeCount && size <= 100) {

            T[] newValues = (T[]) new Object[size];

            for (int i = 0; i < size; i++) {
                newValues[i] = values[i];
            }
            this.values = newValues;
            removeCount = 0;
        }
    }

    /**
     * Returns the object from this index
     *
     * @param index The index of the object to return
     * @return T The object to return
     */
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " outside [0, " + this.size + "] area.");
        }
        return this.values[index];
    }

    /**
     * Returns the size of the OwnArrayList, meaning how many objects there are
     *
     * @return int the size
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns the amount of empty slots in the values array
     *
     * @return int the amount of empties
     */
    public int removeCount() {
        return this.removeCount;
    }

    /**
     * Returns the length of the values array
     *
     * @return int the lenght of the values array
     */
    public int valuesLenght() {
        return this.values.length;
    }

}
