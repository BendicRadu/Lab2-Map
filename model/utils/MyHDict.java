package utils;

import java.util.Map.Entry;

public class MyHDict<T> extends MyDict<Integer, T> implements ImyDict<Integer, T>{

	private Integer firstFree;
	
	public MyHDict(){
		super();
		this.setFirstFree(new Integer(1));
	}
	
	public Integer getFirstFree(){
		return this.firstFree;
	}

	public void setFirstFree(Integer firstFree) {
		this.firstFree = firstFree;
	}

	public Integer findFirstFree() {
		Integer newKey = new Integer(1);
		
		while(true){
			if (! this.dict.containsKey(newKey)){
				this.setFirstFree(newKey);
				return this.firstFree;
			}
			newKey += 1;
		}
	}

	@Override
	public Integer putKey(T elem){
		Integer k = this.getFirstFree();
		Integer inc = new Integer(1);
		this.dict.put(k, elem);
		this.setFirstFree(k + inc);
		return k;
	}
	
	@Override
	public String toString(){
		
		if(dict.isEmpty()){
			return "Heap is empty\n\n";
		}
		
		String str = "Heap :\n";
		for ( Entry<Integer, T> entry : dict.entrySet() ) {
		    Integer key = entry.getKey();
		    T value = entry.getValue();
		    str += key + "-->" + value + "\n";
		}
		str += "\n";
		return str;
	}
	
	
}
