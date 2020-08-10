////Salam Fazil
//V00935894

public class A4Stack<T> implements Stack<T> {
	
	private static final int DEFAULT_CAPACITY = 5;
	private T[] data;
	private int top;

	public A4Stack() {
		data = (T[]) new Object[DEFAULT_CAPACITY];
		top = 0;
	}
	
	public A4Stack(int size) {
		data = (T[]) new Object[size];
		top = 0;
	}
	
	public void push(T v)throws FullStackException {
		if(isFull()){
			throw new FullStackException("Array is full sir");
		}
		data[top] = v;
		top++;
	}
	
	public T pop() throws EmptyStackException{
		if(isEmpty()){
			throw new EmptyStackException("Array is empty sir");
		}

		top--;
		T retVal = data[top];
		data[top] = null;

		return retVal;
	}
	
	public void popAll() {
		while(top > 0){
			pop();
			top --;
		}
	}
	
	public boolean isEmpty() {
		return top == 0;
	}
	
	public boolean isFull() {
		return top >= data.length;
	}
	
	public T top() throws EmptyStackException{
		if(isEmpty() || top < 0){
			throw new EmptyStackException("Array is empty sir");
		}

		return data[top - 1];
	}
}