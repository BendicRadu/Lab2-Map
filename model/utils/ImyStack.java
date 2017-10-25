package utils;

import exceptions.StackException;

public interface ImyStack<T>{
	
	//Return the first element of the stack without removing it
	public T top() throws StackException;
	
	//Return the first element
	public T pop() throws StackException;
	
	//Adds an element to the stack
	public void push(T elem);
	
	//Checks if the stack is Empty
	public Boolean isEmpty();

}
