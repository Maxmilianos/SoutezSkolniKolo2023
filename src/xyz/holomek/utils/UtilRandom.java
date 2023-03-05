package xyz.holomek.utils;

import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class UtilRandom<E> implements Cloneable {
	
	private NavigableMap<Double, E> map = new TreeMap<Double, E>();
	
	private Random random = new Random();
	
	private Double total = 0.0D;
	
	public void add(Double db, E e) {
		if (db <= 0.0) {
			return;
		}
		total += db;
		map.put(total, e);
	}
	
	public E next() {
		return map.ceilingEntry(random.nextDouble() * total).getValue();
	}
	
	public E next(ArrayList<E> list) {
		E next = null;
		while (next == null) {
			E n = map.ceilingEntry(random.nextDouble() * total).getValue();
			if (!list.contains(n))
				next = n;
		}
		return next;
	}
	
	public UtilRandom<E> clone() {
		try {
			return (UtilRandom<E>) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}
}
