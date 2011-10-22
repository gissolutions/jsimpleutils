package org.gissolutions.jsimpleutils.utils;

public class Pair<K, T> {
	protected final K key;
	protected final T value;
	
	public Pair(K key, T value) {
		super();
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public T getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pair<?,?>) { 
		    Pair<?, ?> p1 = (Pair<?, ?>) obj;
		    if ( p1.key.equals( this.key ) && p1.value.equals( this.value ) ) { 
		      return(true);
		    }
		  }
		  return(false);

	}
	
	
	
}
