package utils;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import exceptions.DictException;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.HashMap;

public interface ImyDict<TKey, T> {
	
	public T get(TKey key) throws DictException;
	
	public void setContent(Map<TKey, T> newMap);
	public Map<TKey, T> getContent();
	
	public Collection<T> values();
	public Boolean isEmpty();
	
	public Boolean containsValue(T value);
	public Boolean containsKey(TKey key);
	
	public T putIfAbsent(TKey key, T elem) throws DictException;
	public T put(TKey key, T elem);
	public TKey putKey(T elem) throws DictException;
	
	public void remove(TKey key) throws DictException;
	public Iterator<HashMap.Entry<TKey, T>> getIterator();
	
	public Boolean isInFirst(String elem) throws DictException;
	public Boolean isInSecond(BufferedReader elem) throws DictException;
	
	public Set<HashMap.Entry<TKey, T>> entrySet();
	
	public int generateId();
}
