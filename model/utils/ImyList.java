package utils;

import java.util.ListIterator;

import exceptions.ListException;

public interface ImyList<T> {

	/*Add elements to the list 
	(*pos - index at which the element is inserted*/
	public void add(int pos, T elem) throws ListException;
	public void add(T elem);

	//Remove either by object or by index
	public void remove(T obj) throws ListException;
	T remove(int pos) throws ListException;
	
	//Replaces an element at given pos
	public void set(int pos, T elem) throws ListException;
	
	//Get element on pos
	public T get(int pos) throws ListException;
	
	//Get size of list
	public int getSize();
	
	//Get the list iterator
	public ListIterator<T> getIterator();
}
