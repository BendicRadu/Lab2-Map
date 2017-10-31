package utils;
import java.util.Iterator;
import java.util.Stack;
import exceptions.StackException;

public class MyStack<T> implements ImyStack<T>{
	Stack<T> stack;
	
	public MyStack(){
		this.stack = new Stack<T>();
	}

	public T top() throws StackException{
		if (stack.isEmpty()){
			throw new StackException("Stack is empty");
		}
		return stack.peek();
	}
	
	public T pop() throws StackException {
		if (stack.isEmpty()){
			throw new StackException("Stack is empty");
		}
		return stack.pop();
	}

	public void push(T elem) {
		stack.push(elem);
	}
	
	public Boolean isEmpty(){
		return stack.empty();
	}
	
	public Iterator<T> getIterator(){
		return stack.iterator();
	}
	
	@Override
	public String toString(){
		if(stack.empty()){
			return "Stack is empty\n\n";
		}		
		String str = "ExeStack: \n";

		Object[] vals = stack.toArray();
        for(int i = stack.size() - 1; i >= 0; i--){
        	str += vals[i].toString() + "\n";
        }
		str += "\n";
		return str;
	}
}
