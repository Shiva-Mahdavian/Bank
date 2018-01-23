public class Stack<T> {
    private int size;
    private int top;
    private T data[];


    public Stack() {
        this.size = 20;
        this.top = 0;
        data = (T[]) new Object[size];
    }

    public void push (T input){
        if (top <= size) {
            data[top] = input;
            top++;
        }
        else {
            size *= 2;
            T newData[] = (T[]) new Object[size];
            for (int i = 0; i < top; i++)
                newData[i] = data[i];
            data = newData;
            push(input);
        }
    }
     public T pop() {
        if (top > 0){
            top --;
            return data[top];
        }
        System.err.println("There is no element to pop from stack");
        return  null;
     }

     public boolean Empty(){
        return (top == 0);
     }
     public void print() {
         while (!Empty()){
             System.out.print(this.pop());
         }
         System.out.println();
     }
}


