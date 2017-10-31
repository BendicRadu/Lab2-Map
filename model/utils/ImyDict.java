package utils;
import java.util.Iterator;

import exceptions.DictException;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.HashMap;

public interface ImyDict<TKey, T> {
	
	public T get(TKey key) throws DictException;
	public Collection<T> values();
	public Boolean isEmpty();
	public Boolean containsValue(T value);
	public T putIfAbsent(TKey key, T elem) throws DictException;
	public T put(TKey key, T elem);
	public void remove(TKey key) throws DictException;
	public Iterator<HashMap.Entry<TKey, T>> getIterator();
	
	public Boolean isInFirst(String elem) throws DictException;
	public Boolean isInSecond(BufferedReader elem) throws DictException;
	
	public int generateId();
}
