package utils;

public class MyTuple<T1, T2> implements ImyTuple<T1, T2>{
	
	public final T1 first;
	public final T2 second;
	
	public MyTuple(T1 first, T2 second){
		this.first = first;
		this.second = second;
	}
	@Override
	public T1 getFirst() {
		return this.first;
	}
	@Override
	public T2 getSecond() {
		return this.second;
	}
	
	@Override
	public String toString(){
		return first.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object other){
		if( other == null ) return false;
		if(!(other instanceof MyTuple)) return false;
		if(first == ((ImyTuple<T1, T2>) other).getFirst()) return true;
		return false; 
	}
	
}
