public class BinaryHeap {


    private int size, MAX_SIZE;
    private int[] array;

    public BinaryHeap(){

        MAX_SIZE = 10;
        size = 0;
        array = new int[MAX_SIZE];

    }

    public void add(int data){

        if(size == array.length){
            grow_array();
            MAX_SIZE*=2;
        }

        array[size++] = data;
        int current = size - 1;
        int parent = ((current-1) / 2);

        while(array[current] < array[parent]) {
            swap(current, parent);
            current = parent;
            parent = (current - 1) / 2;
        }
    }

    public int remove(){

        if(size == 0){
            throw new ArrayIndexOutOfBoundsException("ERROR: Binary Heap is Empty");
        }

        else {
            //first swap the first and last indices in the array
            swap( 0, size -1);
            size--;

            if(size > 0){
                shift_heap(0);
            }


        }

        return array[size] ;


    }

    //shift down from one position starting at the root
    private void shift_heap(int position) {  //position starts at 0, the root
        // -compare amongst children and swap with lower children

        int left_child, right_child;
        int priority;

        while (!isLeaf(position)) {  //iterates throughout
            left_child = (position * 2) + 1;
            right_child = (position * 2) + 2;
            priority= left_child;   //priority represents the higher priority node, node < parent, < of the 2 children 

            if ((right_child < size) && (array[left_child] > array[right_child])) {
                priority = left_child + 1;
            }

            if (array[position] <= array[priority]) {
                break;
            }

            swap(position, priority);
            position = priority;
        }
    }

    private boolean isLeaf(int position) {
        //returns true if array[position] is a leaf node
        if ((position >= (size / 2)) && (position <= size)){
            return true;
        }
        return false;
    }


    private void swap(int a, int b){

        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;


    }


    public void grow_array(){

        int[] temp = new int[array.length*2];   //grows the size of the array by 2 when MAX_SIZE is reached
        for(int i = 0; i < array.length; i++){
            temp[i] = array[i];
        }

        array = temp;

    }

}
