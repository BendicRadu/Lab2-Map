package utils;
import java.util.Iterator;

import exceptions.DictException;

import java.util.HashMap;

public interface ImyDict<TKey, T> {
	
	public T get(TKey key) throws DictException;
	public Boolean isEmpty();
	public T putIfAbsent(TKey key, T elem) throws DictException;
	public T put(TKey key, T elem);
	public Iterator<HashMap.Entry<TKey, T>> getIterator();
}
