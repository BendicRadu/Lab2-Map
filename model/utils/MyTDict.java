package utils;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class MyTDict<TKey, T extends ImyTuple<String, BufferedReader>> extends MyDict<TKey, T> implements ImyDict<TKey, T>{
	
	private int Id;
	
	public MyTDict(){
		super();
		this.Id = 0;
	}
	
	public int generateId(){
		this.Id += 1;
		return this.Id;
	}
	
	// Checks if the first value of the tuple is in the dict
	public Boolean isInFirst(String elem){
		Iterator<HashMap.Entry<TKey, T>> it = this.getIterator();
		while (it.hasNext()){
			if( it.next().getValue().getFirst() == elem){
				return true;
			}
		}
		return false;
	}
	
	// Checks if the first value of the tuple is in the dict
	public Boolean isInSecond(BufferedReader elem){
		Iterator<HashMap.Entry<TKey, T>> it = this.getIterator();
		while (it.hasNext()){
			if( it.next().getValue().getSecond() == elem){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString(){
		
		if(dict.isEmpty()){
			return "File table is empty\n\n";
		}
		
		String str = "File table:\n";
		for ( Entry<TKey, T> entry : dict.entrySet() ) {
		    TKey key = entry.getKey();
		    T value = entry.getValue();
		    str += key + "-->" + value + "\n";
		}
		str += "\n";
		return str;
	}
	

}
