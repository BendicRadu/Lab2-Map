package utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import exceptions.DictException;

public class MyDict<TKey, T> implements ImyDict<TKey, T>{
	private Map<TKey, T> dict;
	
	public MyDict(){
		dict = new HashMap<TKey, T>();
	}
	
	public T get(TKey key) throws DictException{
		T elem = dict.get(key);
		if(elem == null){
			throw new DictException("No such key");
		}
		return elem;
	}
	
	public Boolean isEmpty(){
		return dict.isEmpty();
	}
	
	public T putIfAbsent(TKey key, T elem) throws DictException{
		
		T elemAux = dict.get(key);
		if(elemAux != null){
			throw new DictException("Key not available");
		}
		
		return dict.putIfAbsent(key, elem);
	}
	
	public T put(TKey key, T elem){
		return dict.put(key, elem);
	}
	
	public Iterator<HashMap.Entry<TKey, T>> getIterator(){
		return dict.entrySet().iterator();
	}
	
	@Override
	public String toString(){
		
		if(dict.isEmpty()){
			return "Dict is empty\n\n";
		}
		
		String str = "SymTable:\n";
		for ( Entry<TKey, T> entry : dict.entrySet() ) {
		    TKey key = entry.getKey();
		    T value = entry.getValue();
		    str += key + "->" + value + "\n";
		}
		str += "\n";
		return str;
	}
}
