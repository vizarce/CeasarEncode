package com.ceasar.arrays;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntArraysOperations {

	private static int[] intArray;
	private ArrayList<Integer> integerObjArray;
	private int startValue;
	private int endValue; 
	private int deleteIndex;
	private int addValue;
	private ArrayList<Integer> addValues;

	/**
	 * default constructor
	 */
	public IntArraysOperations() {}

	
	/**
	 * @param intArray
	 * @param integerObjArray
	 * @param startValue
	 * @param endValue
	 * @param deleteIndex
	 * @param addValue
	 * @param addValues
	 */
	public IntArraysOperations(int startValue, int endValue, int deleteIndex, int addValue, ArrayList<Integer> addValues) {
		this.intArray = intArray;
		this.integerObjArray = integerObjArray;
		this.startValue = startValue;
		this.endValue = endValue;
		this.deleteIndex = deleteIndex;
		this.addValue = addValue;
		this.addValues = addValues;
	}

	/**
	 * @return the intArray
	 */
	public int[] getIntArray() {
		return intArray;
	}
	
	/**
	 * @return the integerObjArray
	 */
	public ArrayList<Integer> getIntegerObjArray() {
		return integerObjArray;
	}

	/**
	 * @return the startValue
	 */
	public int getStartValue() {
		return startValue;
	}

	/**
	 * @return the endValue
	 */
	public int getEndValue() {
		return endValue;
	}

	/**
	 * @return the deleteIndex
	 */
	public int getDeleteIndex() {
		return deleteIndex;
	}

	/**
	 * @return the addValue
	 */
	public int getAddValue() {
		return addValue;
	}

	/**
	 * @return the addValues
	 */
	public ArrayList<Integer> getAddValues() {
		return addValues;
	}

	public static ArrayList<Integer> createIntegerArrayList(int startValue, int endValue) {
		IntStream intStream = IntStream.rangeClosed(startValue, endValue);
		Stream<Integer> objIntegerStream = intStream.mapToObj(Integer::valueOf);
		ArrayList<Integer> integerArrayList = objIntegerStream.collect(Collectors.toCollection(ArrayList<Integer>::new));
		return integerArrayList;
	}
	
	public static int[] createIntArray(int startValue, int endValue) {
		ArrayList<Integer> integerArrayList = createIntegerArrayList(startValue, endValue);
		int[] intArray = integerArrayList.stream().mapToInt(Integer::valueOf).toArray();
		return intArray;
		
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		char[] chars = {65, 11, 15, 36, 35, 171, 178, 204, 2011, 219, 248, 254, 184, 182, 3, 109, 145, 142, 153, 32, 124, 126, 253, 232};
		for (int i = 0; i < chars.length; i++) {
			System.out.println("char" + String.valueOf(i) + ": " + Character.toString((char) chars[i]));
			 //Character.TYPE.cast(Character.getName( chars[i]))
             // (char) chars[i])
			//Character.highSurrogate(chars[i]);//.charCount(i)
			Character c = Character.valueOf(chars[i]);
			//c.toChars(i).getClass()
			System.out.println("char" + String.valueOf(i + 10) + "-class: " + c.getClass());
			System.out.println("char" + String.valueOf(i + 10) + "::::: " + Character.getName(chars[i]));
			System.out.println("char" + String.valueOf(i + 10) + " --- " + Character.highSurrogate(chars[i]));
			System.out.println(Arrays.toString(createIntArray(1, 10)));
		}

	}

}
