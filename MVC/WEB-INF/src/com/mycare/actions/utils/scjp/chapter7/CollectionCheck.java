package com.mycare.actions.utils.scjp.chapter7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionCheck{
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<Integer>();
		List<List<Integer>> list1 = new ArrayList<List<Integer>>();
		list.add(10);
		list.add(20);
		list.add(15);
		list.add(4);
		Collections.sort(list);
		Collections.sort(list,new ComparatorClassNewone());
		System.out.println(4>>>1);				
		
		Object[] a={40,20,15,45,22};		
		int runLen = countRunAndMakeAscending(a,0,a.length);
		System.out.println(runLen);
		binarySort(a,0,a.length,0+runLen);
		for(int count=0;count<a.length;count++)
			System.out.println(a[count]);
		
		Object[] a1={10,20,30,40,60,12};
		System.arraycopy(a1, 1, a1, 2, 4);		
		for(int count1=0;count1<a.length;count1++)
		System.out.println(a1[count1]);
		
		System.out.println(Collections.binarySearch(list, 9));	//soritng shud be done
		System.out.println(Arrays.binarySearch(a1, 9));	//soritng shud be done
		
	}	
    private static int countRunAndMakeAscending(Object[] a, int lo, int hi) {
        assert lo < hi;
        int runHi = lo + 1;
        if (runHi == hi)
            return 1;

        // Find end of run, and reverse range if descending
        if (((Comparable) a[runHi++]).compareTo(a[lo]) < 0) { // Descending
            while (runHi < hi && ((Comparable) a[runHi]).compareTo(a[runHi - 1]) < 0)
                runHi++;
            //reverseRange(a, lo, runHi);
        } else {                              // Ascending
            while (runHi < hi && ((Comparable) a[runHi]).compareTo(a[runHi - 1]) >= 0)
                runHi++;
        }

        return runHi - lo;
    }
    private static void binarySort(Object[] a, int lo, int hi, int start) {
        assert lo <= start && start <= hi;
        if (start == lo)
            start++;
        for ( ; start < hi; start++) {
            @SuppressWarnings("unchecked")
            Comparable<Object> pivot = (Comparable) a[start];

            // Set left (and right) to the index where a[start] (pivot) belongs
            int left = lo;
            int right = start;
            assert left <= right;
            /*
             * Invariants:
             *   pivot >= all in [lo, left).
             *   pivot <  all in [right, start).
             */
            //System.out.println(pivot);
           // System.out.println();
            while (left < right) {
                int mid = (left + right) >>> 1;
         //   System.out.println(right);
                if (pivot.compareTo(a[mid]) < 0)
                    right = mid;
                else
                    left = mid + 1;
            }            
            assert left == right;

            /*
             * The invariants still hold: pivot >= all in [lo, left) and
             * pivot < all in [left, start), so pivot belongs at left.  Note
             * that if there are elements equal to pivot, left points to the
             * first slot after them -- that's why this sort is stable.
             * Slide elements over to make room for pivot.
             */
            int n = start - left;  // The number of elements to move
            
            // Switch is just an optimization for arraycopy in default case            
            switch (n) {
                case 2:  a[left + 2] = a[left + 1];
                case 1:                	
                	a[left + 1] = a[left];
                    break;
                default: System.arraycopy(a, left, a, left + 1, n);
            }
            a[left] = pivot;
        }
    }
    void reverseArray(){
    	Object[] o2 ={10,20,40};
		int lo=0,hi=o2.length-1;		
		while(lo<hi){
			Object t = o2[lo];
			o2[lo++] = o2[hi];
			o2[hi--] = t;			
		}
		for(int count=0;count<o2.length;count++)
			System.out.println(o2[count]);
    }
}
class ComparatorClassNewone implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}