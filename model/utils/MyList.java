package utils;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import exceptions.ListException;

public class MyList<T> implements ImyList<T> {

	private List<T> list;
	
	public MyList(){
		list = new LinkedList<T>();
	}
	
	public void add(int pos, T elem) throws ListException {
		if(pos < 0 || pos > list.size()){
			throw new ListException("Invalid index");
		}
		list.add(pos, elem);
	}
	
	public void add(T elem){
		list.add(elem);
	}

	public void set(int pos, T elem) throws ListException{
		
		if (pos < 0 || pos > list.size()){
			throw new ListException("Invalid index");
		}
		list.set(pos, elem);
	}
	
	public void remove(T obj) throws ListException{
		if(!list.remove(obj)){
			throw new ListException("Object not in list");
		}
	}
	
	public T remove(int pos) throws ListException{
		if(pos < 0 || pos > list.size()){
			throw new ListException("Invalid index");
		}
		return list.remove(pos);
	}

	public T get(int pos) throws ListException{
		if(pos < 0 || pos > list.size()){
			throw new ListException("Invalid index");
		}
		return list.get(pos);
	}

	public int getSize() {
		return list.size();
	}
	
	public ListIterator<T> getIterator(){
		return list.listIterator();
	}
		
	@Override
	public String toString(){
			
			if(list.isEmpty()){
				return "List is empty:\n\n";
			}
			String str = "Out:\n";
			Iterator<T> it = this.getIterator();
			while (it.hasNext()) {
				str += it.next() + "\n";		
			}
			str += "\n";
			return str;
	}
	

}
