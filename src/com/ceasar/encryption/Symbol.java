package com.ceasar.encryption;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.ceasar.exceptions.CharacterIsNotASymbolException;

final public class Symbol implements Serializable, Comparable<Symbol> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2751528674285579029L;
	
	private final static Character[] symbols = {'!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', 
			'.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~', '¡', 
			'¢', '£', '¤', '¥', '¦', '§', '¨', '©', 'ª', '«', '¬', '­', '®', '¯', '°', '±', '²', '³', '´', '¶', 
			'·', '¸', '¹', 'º', '»', '¼', '½', '¾', '¿', '÷', 'ǀ', 'ǁ', 'ǂ'};
	private final static Integer[] codePoints = {33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 
			59, 60, 61, 62, 63, 64, 91, 92, 93, 94, 95, 96, 123, 124, 125, 126, 161, 162, 163, 164, 165, 166, 
			167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 182, 183, 184, 185, 186, 187, 
			188, 189, 190, 191, 247, 448, 449, 450};
	private final static List<Integer> all = Arrays.asList(codePoints);

	private char smb;
	private int codePoint;
	private char ch;
	private Character character;

	public Symbol() {}
	
	public Symbol(int codePoint) {
		// this.codePoint = codePoint;
		if (all.contains(Integer.valueOf(codePoint))) {
			this.codePoint = codePoint;
			this.smb = (char) codePoint;
			this.ch = this.smb;
			this.character = Character.valueOf(smb);
		} else {
			throw new CharacterIsNotASymbolException("Character is not a Symbol!");
		}
	}
	public Symbol(char ch) {
		int index = all.indexOf((int) Character.valueOf(ch));
		if (all.contains(Integer.valueOf(ch))) {
			this.codePoint = Integer.valueOf(ch).byteValue();
			this.smb = (char) all.get(index).intValue();
			this.ch = this.smb;
			this.character = Character.valueOf(smb);
		} else {
			throw new CharacterIsNotASymbolException("Character is not a Symbol!");
		}
	}
	public Symbol(Character character) {
		this.character = character;
		if (all.contains(Integer.valueOf(this.character))) {
			this.smb = character.charValue();
			this.ch = this.smb;
			this.character = Character.valueOf(smb);
		} else {
			throw new CharacterIsNotASymbolException("Character is not a Symbol!");
		}
	}
	public char getSmb() {
		return smb;
	}
	public void setSmb(char smb) {
		this.smb = smb;
	}
	public char charValue() {
		return character.charValue();
	}
	public int getCodePoint() {
		return codePoint;
	}
	public void setCodePoint(int codePoint) {
		this.codePoint = codePoint;
	}
	public char getCh() {
		return ch;
	}
	public void setCh(char ch) {
		this.ch = ch;
	}
	public Character getCharacter() {
		return character;
	}
	public void setCharacter(Character character) {
		this.character = character;
	}
	public static boolean isSymbol(char ch) {
		if (all.contains(Integer.valueOf(ch))) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isSymbol(Symbol smb) {
		if (all.contains(Integer.valueOf((char) smb.getCodePoint()))) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isSymbol(Integer codePoint) {
		if (all.contains(codePoint)) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isSymbol(Character character) {
		if (all.contains((int) character.charValue())) {
			return true;
		} else {
			return false;
		}
	}
	public static Symbol valueOf(Integer codePoint) {
		if (all.contains(codePoint)) {
			return new Symbol(codePoint.intValue());
		} else {
			throw new CharacterIsNotASymbolException("Character is not a Symbol!");
		} 
	}
	public static Symbol valueOf(char ch) {
		if (all.contains(Integer.valueOf(ch))) {
			return new Symbol(ch);
		} else {
			throw new CharacterIsNotASymbolException("Character is not a Symbol!");
		}
	}
	@Override
	public int compareTo(Symbol other) {
		return Integer.compare(this.codePoint, other.codePoint);
	}
	@Override
	public int hashCode() {
		return Objects.hash(ch, character, codePoint, smb);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Symbol)) {
			return false;
		}
		Symbol other = (Symbol) obj;
		return ch == other.ch && Objects.equals(character, other.character) && codePoint == other.codePoint
				&& Objects.equals(smb, other.smb);
	}
	@Override
	public String toString() {
		return String.format("%s", smb);
	}
	public Character print() {
		return this.smb;
	}

	public static <T> T[] concatenateArrays(T[] a, T[] b) {
	    int aLen = a.length;
	    int bLen = b.length;
	    @SuppressWarnings("unchecked")
	    T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
	    System.arraycopy(a, 0, c, 0, aLen);
	    System.arraycopy(b, 0, c, aLen, bLen);
	    return c;
	}


	public static void main(String[] args) {
		Symbol s = new Symbol(35);
		System.out.println(s);
		//Integer[] all = concatenateArrays(codePoints128, codePoints156to255);
		//System.out.println(Arrays.toString(all));
		System.out.println(isSymbol(s));
        System.out.println(Symbol.valueOf(':'));
        System.out.println(Symbol.valueOf(36));
        System.out.println(new Symbol('.'));
        System.out.println(new Symbol(Character.valueOf(')')));
        System.out.println(isSymbol(36));
        System.out.println(isSymbol(Character.valueOf('!')));
        Symbol sym = new Symbol(93);
        System.out.println(sym);
        System.out.println(sym.print());
        System.out.println(isSymbol(sym));
        //
        Map<Map<Character, Integer>, String> symbols = new HashMap<Map<Character, Integer>, String>();
        Map<Character, Integer> tempChars = new HashMap<Character, Integer>();
        /**for (Integer i : all) {
        	// Map<Character, Integer> temp = new HashMap<Character, Integer>();
        	temp.put(Character.valueOf((char) i.intValue()), i);
        	System.out.println(temp);
        	symbols.put(temp, Character.getName(i));
        	//temp.clear();
        }
        System.out.println(symbols);*/
        ArrayList<Character> chars = new ArrayList<>();
        ArrayList<Integer> charsInt = new ArrayList<>();
        for (Integer i = 0; i <= 500; i++) {
        	chars.add(Character.valueOf((char) i.intValue()));
        	charsInt.add(i);
        	tempChars.put(Character.valueOf((char) i.intValue()), i);
        }
        System.out.println(chars);
        System.out.println(charsInt);
        System.out.println(tempChars);
        char[] chrs = {'!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~', '¡', '¢', '£', '¤', '¥', '¦', '§', '¨', '©', 'ª', '«', '¬', '­', '®', '¯', '°', '±', '²', '³', '´', '¶', '·', '¸', '¹', 'º', '»', '¼', '½', '¾', '¿', '÷', 'ǀ', 'ǁ', 'ǂ'};
        System.out.println(chrs);
        System.out.println(chrs.length);
        ArrayList<Integer> codePoints = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        for (char c : chrs) {
        	int cp = (int) c;
        	codePoints.add(cp);
        	names.add(Character.getName(cp));
        }
        System.out.println(codePoints);
        System.out.println(names);
	}
	

}
