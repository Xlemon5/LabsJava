public class container<T> {
    private Object[] array;
    private int size;
    private int capacity;

    public container() {
        capacity = 10;
        array = new Object[capacity];
        size = 0;
    }

    public void add(T elem){
        if(size == capacity){
            resize();
        }
        array[size] = elem;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона");
        }
        return (T) array[index];
    }

    public void remove(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона");
        }
        for (int i = index; i < size-1; i++) {
            array[i] = array[i+1];
        }
        array[size-1] = null;
        size--;
    }

    public int size(){
        return size;
    }

    private void resize(){
        capacity *= 2;
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    public void printElems(){
        System.out.print("Элементы контейнера: ");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
