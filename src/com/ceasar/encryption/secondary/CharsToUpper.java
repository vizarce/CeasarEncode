package com.ceasar.encryption.secondary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ceasar.arrays.IntArraysOperations;

public class CharsToUpper {
	private int start;
	private int stop;
	private ArrayList<Integer> source;

	public CharsToUpper() {}
	
	public CharsToUpper(int start, int stop) {
		this.start = start;
		this.stop = stop;
		this.source = IntArraysOperations.createIntegerArrayList(start, stop);
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getStop() {
		return stop;
	}
	public void setStop(int stop) {
		this.stop = stop;
	}
	public ArrayList<Integer> getSource() {
		return source;
	}
	public static ArrayList<Integer> getSource(int start, int stop) {
		return IntArraysOperations.createIntegerArrayList(start, stop);
	}
	public void setSource(ArrayList<Integer> source) {
		this.source = source;
	}
	public static ArrayList<Integer> getIndexes(int stringLength, int shift, int addon) {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		int left = 0; int right = stringLength; int mid = right / 2;
		indexes.add(mid);
		int newIndexLeft;
		int newIndexRight;
		while (left <= mid) {
			newIndexLeft = left + shift - addon;
			indexes.add(newIndexLeft);
			left = newIndexLeft;
			left++;
			if (mid - shift < shift)
				break;
		}
		while (right >= mid) {
			newIndexRight = right - shift + addon;
			indexes.add(newIndexRight);
			right = newIndexRight;
			right--;
			if (mid - shift < shift)
				break;
		}
		return indexes;
	}
	
	public static String changeToUpperCase(String data, ArrayList<Integer> indexes) {
		ArrayList<Character> dataChars = data.chars()
				.mapToObj(c -> (char) c)
				.collect(Collectors.toCollection(ArrayList::new));
		for (Integer i : indexes) {
			if (Character.isLetter(dataChars.get(i)) && Character.isLowerCase(dataChars.get(i))) {
			    dataChars.set(i, Character.toUpperCase(Character.valueOf((char) dataChars.get(i))));
			} 
		}
		String string = dataChars.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
		return string;
		
	}
}
