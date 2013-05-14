package com.mycare.actions.utils.scjp.chapter7;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TestClass {
	public static void main(String[] args) {
		Set set = new HashSet();
		set.add(10);
		set.add(20);
		set.add(30);
		set.addAll(set);
		
		System.out.println(set);
	}
}
interface Hungry<E> { void munch(E x); }
interface Carnivore<E extends Animal> extends Hungry<E> {}
interface Herbivore<E extends Plant> extends Hungry<E> {}
abstract class Plant {}
class Grass extends Plant {}
abstract class Animal {}
class Sheep extends Animal implements Herbivore<Plant> {
public void munch(Plant x) {}
}
class Wolf extends Animal implements Carnivore<Sheep> {
public void munch(Sheep x) {}
}

