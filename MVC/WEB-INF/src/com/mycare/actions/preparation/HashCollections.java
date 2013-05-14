package com.mycare.actions.preparation;

import java.util.*;

public class HashCollections<K,V> {
	public transient Entry[] table;
	public transient int modCount=0;
	public transient int count=0;
	public transient int threshold;
	public HashCollections() {
		table=new Entry[10];
		threshold = (int)(11 * 0.75f);
	}
	public static void main(String[] args) {
		HashMap map = new HashMap();
		map.put(1, null);
		Hashtable table = new Hashtable();
		table.put("1", "hello");		
		System.out.println();
	}
	public V put(K key,V value){
        // Make sure the value is not null
        if (value == null) {
            throw new NullPointerException();
        }

        // Makes sure the key is not already in the hashtable.
        Entry tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (Entry<K,V> e = tab[index] ; e != null ; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                V old = e.value;
                e.value = value;
                return old;
            }
        }

        modCount++;
        if (count >= threshold) {
            // Rehash the table if the threshold is exceeded
            //rehash();

            tab = table;
            index = (hash & 0x7FFFFFFF) % tab.length;
        }

        // Creates the new entry.
        Entry<K,V> e = tab[index];
        tab[index] = new Entry<>(hash, key, value, e);
        count++;
        return null;
	}
	 private static class Entry<K,V> implements Map.Entry<K,V> {
	        int hash;
	        K key;
	        V value;
	        Entry<K,V> next;

	        protected Entry(int hash, K key, V value, Entry<K,V> next) {
	            this.hash = hash;
	            this.key = key;
	            this.value = value;
	            this.next = next;
	        }

	        protected Object clone() {
	            return new Entry<>(hash, key, value,
	                                  (next==null ? null : (Entry<K,V>) next.clone()));
	        }

	        // Map.Entry Ops

	        public K getKey() {
	            return key;
	        }

	        public V getValue() {
	            return value;
	        }

	        public V setValue(V value) {
	            if (value == null)
	                throw new NullPointerException();

	            V oldValue = this.value;
	            this.value = value;
	            return oldValue;
	        }

	        public boolean equals(Object o) {
	            if (!(o instanceof Map.Entry))
	                return false;
	            Map.Entry e = (Map.Entry)o;

	            return (key==null ? e.getKey()==null : key.equals(e.getKey())) &&
	               (value==null ? e.getValue()==null : value.equals(e.getValue()));
	        }

	        public int hashCode() {
	            return hash ^ (value==null ? 0 : value.hashCode());
	        }

	        public String toString() {
	            return key.toString()+"="+value.toString();
	        }
	    }
}
