package com.ceasar.encryption.primary;

import java.io.File;
import java.io.IOException;
import java.lang.Character.Subset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.ceasar.arrays.IntArraysOperations;
import com.ceasar.encryption.interfaces.EncodingType;
import com.ceasar.encryption.interfaces.Mode;
import com.ceasar.encryption.interfaces.StringEncodable;

public class CeasarCipher implements StringEncodable {
    /**
     * Given Method of Encoding-Decoding of the Input String Message partly taken from Ceasar Cipher Method
     * was improved Efficacy
     *
     * Here present Arrays and Lists which reproduce interchange within the Cipher
     * @ ANC = AlphaNumericCharacters
     * @ nonANC = nonAlphaNumericCharacters
     */
    private static final Integer[] allDigits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final String[] allDigitsAsStrings = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final Character[] allDigitsAsChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final Character[] allDigitsAsCharsInversed = {'Þ', 'ä', 'ù', 'ø', 'ñ', 'é', 'ç', 'ß', 'ò', 'î'};//{'À', 'Ý', 'Á', 'Ü', 'Â', 'Û', 'Ã', 'Ø', 'Ä', 'Ò'};
    private static final Character[] allDigitsAsCharsInversedAsIntegers = {222, 228, 249, 248, 241, 233, 231, 223, 242, 238}; //{192, 221, 193, 220, 194, 219, 195, 216, 196, 210};
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final Character[] nonANC = {'!', '%', '&', ',', '-', '.', ':', ';', '?', '@'};
    private static final String[] nonANCAsStrings = {"!", "%", "&", ",", "-", ".", ":", ";", "?", "@"};
    private static final String[] nonANCCharsInversedAsStrings = {"Þ", "ä", "ù", "ø", "ñ", "é", "ç", "ß", "ò", "î"};
    private static final Character[] nonANCCharsInversed = {'À', 'Ý', 'Á', 'Ü', 'Â', 'Û', 'Ã', 'Ø', 'Ä', 'Ò'}; //{'Þ', 'ä', 'ù', 'ø', 'ñ', 'é', 'ç', 'ß', 'ò', 'î'};
    private static final Character[] nonANCCharsInversedAsIntegers = {192, 221, 193, 220, 194, 219, 195, 216, 196, 210}; //{222, 228, 249, 248, 241, 233, 231, 223, 242, 238};
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final List<Character> nonANCharacters = Arrays.asList(nonANC);
    private static final List<String> nonANCharactersAsStrings = Arrays.asList(nonANCAsStrings);
    private static final List<Character> nonANCharactersInversed = Arrays.asList(nonANCCharsInversed);
    private static final List<String> nonANCharactersInversedAsStrings = Arrays.asList(nonANCCharsInversedAsStrings);
    private static final List<Character> nonANCharactersInversedAsIntegers = Arrays.asList(nonANCCharsInversedAsIntegers);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final List<Integer> digits = Arrays.asList(allDigits);
    private static final List<Character> digitsAsCharacters = Arrays.asList(allDigitsAsChars);
    private static final List<String> digitsAsStrings = Arrays.asList(allDigitsAsStrings);
    private static final List<Character> digitsAsCharactersInversed = Arrays.asList(allDigitsAsCharsInversed);
    private static final List<Character> digitsAsCharactersInversedAsIntegers = Arrays.asList(allDigitsAsCharsInversedAsIntegers);
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String spaceString = "æ";
    private static final Character spaceCharacter = 230; // 'æ'

    private final static int MIN_KEY_SIZE = 1;
    private final static int MAX_KEY_SIZE = 26;
  
    /**
	 * Default CeasarCypher Class Constructor
	 */
	public CeasarCipher() {
		super();
	}
	/**
	 * @param action
	 * @see java.lang.Iterable#forEach(java.util.function.Consumer)
	 */
	public void forEach(Consumer<? super Integer> action) {
		digits.forEach(action);
	}
	/**
	 * @return
	 * @see java.util.List#size()
	 */
	public int size() {
		return digits.size();
	}
	/**
	 * @param o
	 * @return
	 * @see java.util.List#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		return digits.contains(o);
	}
	/**
	 * @return
	 * @see java.util.List#toArray()
	 */
	public Object[] toArray() {
		return digits.toArray();
	}
	/**
	 * @param index
	 * @param element
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	public void add(int index, Integer element) {
		digits.add(index, element);
	}
	/**
	 * @return
	 * @see java.util.Collection#stream()
	 */
	public Stream<Integer> stream() {
		return digits.stream();
	}
	/**
	 * @param index
	 * @param element
	 * @return
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	public Character set(int index, Character element) {
		return digitsAsCharacters.set(index, element);
	}
	/**
	 * @param e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(Character e) {
		return digitsAsCharactersInversed.add(e);
	}
	/**
	 * @param o
	 * @return
	 * @see java.util.List#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return digitsAsCharactersInversedAsIntegers.equals(o);
	}
	/**
	 * @return
	 * @see java.util.List#hashCode()
	 */
	public int hashCode() {
		return digitsAsCharactersInversedAsIntegers.hashCode();
	}
	/**
	 * @param index
	 * @return
	 * @see java.lang.String#charAt(int)
	 */
	public char charAt(int index) {
		return spaceString.charAt(index);
	}
	/**
	 * @param index
	 * @return
	 * @see java.lang.String#codePointAt(int)
	 */
	public int codePointAt(int index) {
		return spaceString.codePointAt(index);
	}
	/**
	 * @return
	 * @see java.lang.String#getBytes()
	 */
	public byte[] getBytes() {
		return spaceString.getBytes();
	}
	/**
	 * @param anotherString
	 * @return
	 * @see java.lang.String#equalsIgnoreCase(java.lang.String)
	 */
	public boolean equalsIgnoreCase(String anotherString) {
		return spaceString.equalsIgnoreCase(anotherString);
	}
	/**
	 * @param anotherString
	 * @return
	 * @see java.lang.String#compareTo(java.lang.String)
	 */
	public int compareTo(String anotherString) {
		return spaceString.compareTo(anotherString);
	}
	/**
	 * @param prefix
	 * @return
	 * @see java.lang.String#startsWith(java.lang.String)
	 */
	public boolean startsWith(String prefix) {
		return spaceString.startsWith(prefix);
	}
	/**
	 * @param suffix
	 * @return
	 * @see java.lang.String#endsWith(java.lang.String)
	 */
	public boolean endsWith(String suffix) {
		return spaceString.endsWith(suffix);
	}
	/**
	 * @param ch
	 * @return
	 * @see java.lang.String#indexOf(int)
	 */
	public int indexOf(int ch) {
		return spaceString.indexOf(ch);
	}
	/**
	 * @param ch
	 * @param fromIndex
	 * @return
	 * @see java.lang.String#indexOf(int, int)
	 */
	public int indexOf(int ch, int fromIndex) {
		return spaceString.indexOf(ch, fromIndex);
	}
	/**
	 * @param ch
	 * @return
	 * @see java.lang.String#lastIndexOf(int)
	 */
	public int lastIndexOf(int ch) {
		return spaceString.lastIndexOf(ch);
	}
	/**
	 * @param str
	 * @return
	 * @see java.lang.String#indexOf(java.lang.String)
	 */
	public int indexOf(String str) {
		return spaceString.indexOf(str);
	}
	/**
	 * @param beginIndex
	 * @return
	 * @see java.lang.String#substring(int)
	 */
	public String substring(int beginIndex) {
		return spaceString.substring(beginIndex);
	}
	/**
	 * @param str
	 * @return
	 * @see java.lang.String#concat(java.lang.String)
	 */
	public String concat(String str) {
		return spaceString.concat(str);
	}
	/**
	 * @param oldChar
	 * @param newChar
	 * @return
	 * @see java.lang.String#replace(char, char)
	 */
	public String replace(char oldChar, char newChar) {
		return spaceString.replace(oldChar, newChar);
	}
	/**
	 * @param regex
	 * @return
	 * @see java.lang.String#matches(java.lang.String)
	 */
	public boolean matches(String regex) {
		return spaceString.matches(regex);
	}
	/**
	 * @param s
	 * @return
	 * @see java.lang.String#contains(java.lang.CharSequence)
	 */
	public boolean contains(CharSequence s) {
		return spaceString.contains(s);
	}
	/**
	 * @param regex
	 * @return
	 * @see java.lang.String#split(java.lang.String)
	 */
	public String[] split(String regex) {
		return spaceString.split(regex);
	}
	/**
	 * @return
	 * @see java.lang.String#toLowerCase()
	 */
	public String toLowerCase() {
		return spaceString.toLowerCase();
	}
	/**
	 * @return
	 * @see java.lang.String#toUpperCase()
	 */
	public String toUpperCase() {
		return spaceString.toUpperCase();
	}
	/**
	 * @return
	 * @see java.lang.String#trim()
	 */
	public String trim() {
		return spaceString.trim();
	}
	/**
	 * @return
	 * @see java.lang.String#strip()
	 */
	public String strip() {
		return spaceString.strip();
	}
	/**
	 * @return
	 * @see java.lang.String#stripLeading()
	 */
	public String stripLeading() {
		return spaceString.stripLeading();
	}
	/**
	 * @return
	 * @see java.lang.String#stripTrailing()
	 */
	public String stripTrailing() {
		return spaceString.stripTrailing();
	}
	/**
	 * @return
	 * @see java.lang.String#isBlank()
	 */
	public boolean isBlank() {
		return spaceString.isBlank();
	}
	/**
	 * @return
	 * @see java.lang.String#chars()
	 */
	public IntStream chars() {
		return spaceString.chars();
	}
	/**
	 * @return
	 * @see java.lang.String#codePoints()
	 */
	public IntStream codePoints() {
		return spaceString.codePoints();
	}
	/**
	 * @return
	 * @see java.lang.String#toCharArray()
	 */
	public char[] toCharArray() {
		return spaceString.toCharArray();
	}
	/**
	 * @param count
	 * @return
	 * @see java.lang.String#repeat(int)
	 */
	public String repeat(int count) {
		return spaceString.repeat(count);
	}




	/**
     * The Method Encodes the Message in special combined way
     * @param message - the string to encode and for further the estimating its length
     * @param key - the integer value on which the first shift will be carried out
     * @param divider - the integer on which divides the number received after Math.sqrt(initial length)
     * @param shift - the integer on which the second shift will be carried out after getPosition() function
     * @param commonDivider - the integer on which the total length of the string divides (can be 3 or 4)
     * @return Encoded Message String
     */
    public static String encodeCeasar(String message, int key, int divider, int shift, int commonDivider) {
        if (key < MIN_KEY_SIZE || key > MAX_KEY_SIZE || shift < MIN_KEY_SIZE || shift > MAX_KEY_SIZE) {
            throw new IllegalArgumentException("Key or Shift Integer Values must be equal or more than 1 and equal or less than 26.");
        } else {
            String encoded = getShiftingEncodeDecode(Mode.ENCODE, message, key);
            Set<Integer> positions = getPositions(encoded, divider, commonDivider);
            char[] encodedToCharArray = encoded.toCharArray();
            for (Integer i : positions) {
                encodedToCharArray[i] = getCharacterShiftingEncodeDecode(Mode.ENCODE, encodedToCharArray[i], shift);
            }
            String encodedTwice = new String(encodedToCharArray);
            String encodedWhiteSpaces = encodeWhiteSpaces(Mode.ENCODE, encodedTwice);
            String encodedDigits = encodeDigits(Mode.ENCODE, encodedWhiteSpaces);
            return encodeSpecialCharacters(Mode.ENCODE, encodedDigits);
        }
    }

    /**
     * The Method Encodes the Message in special combined way, in addition Encodes it in Hex or Base64 String
     * @param encodingType - the value of enum EncodingType (can be HEX or BASE64)
     * @param message - the string to encode and for further the estimating its length
     * @param key - the integer value on which the first shift will be carried out
     * @param divider - the integer on which divides the number received after Math.sqrt(initial length)
     * @param shift - the integer on which the second shift will be carried out after getPosition() function
     * @param commonDivider - the integer on which the total length of the string divides (can be 3 or 4)
     * @return Hex or Base64 Encoded Message String, previously Encoded in usual way
     */
    public static String encodeCeasarIntoBinaryString(EncodingType encodingType, String message, int key, int divider, int shift, int commonDivider) {
        if (key < MIN_KEY_SIZE || key > MAX_KEY_SIZE || shift < MIN_KEY_SIZE || shift > MAX_KEY_SIZE) {
            throw new IllegalArgumentException("Key or Shift Integer Values must be equal or more than 1 and equal or less than 26.");
        } else {
            String encoded = encodeCeasar(message, key, divider, shift, commonDivider);
            String binaryEncoded = null;
            if (encodingType.equals(EncodingType.BASE64)) {
                binaryEncoded = toBase64(encoded);
            } else if (encodingType.equals(EncodingType.HEX)) {
                binaryEncoded = getHeX(encoded);
            } else {
                throw new IllegalArgumentException("Invalid Binary Encoding Type was provided.");
            }
            return binaryEncoded;
        }
    }

    /**
     * The Method Decodes the Message in special combined way
     * @param encodedMessage - the encoded string to encode and for further the estimating its length
     * @param key - the integer value on which the first shift will be carried out
     * @param divider - the integer on which divides the number received after Math.sqrt(initial length)
     * @param shift - the integer on which the second shift will be carried out after getPosition() function
     * @param commonDivider - the integer on which the total length of the string divides (can be 3 or 4)
     * @return Decoded Message String
     */
    public static String decodeCeasar(String encodedMessage, int key, int divider, int shift, int commonDivider) {
        if (key < MIN_KEY_SIZE || key > MAX_KEY_SIZE || shift < MIN_KEY_SIZE || shift > MAX_KEY_SIZE) {
            throw new IllegalArgumentException("Key or Shift Integer Values must be equal or more than 1 and equal or less than 26.");
        } else {
            Set<Integer> positions = getPositions(encodedMessage, divider, commonDivider);
            String decodedSpecialCharacters = encodeSpecialCharacters(Mode.DECODE, encodedMessage);
            String decodedDigits = encodeDigits(Mode.DECODE, decodedSpecialCharacters);
            String decodedWhiteSpaces = encodeWhiteSpaces(Mode.DECODE, decodedDigits);
            char[] encodedToCharArray = decodedWhiteSpaces.toCharArray();
            for (Integer i : positions) {
                encodedToCharArray[i] = getCharacterShiftingEncodeDecode(Mode.DECODE, encodedToCharArray[i], shift);
            }
            String decodedFirstly = new String(encodedToCharArray);
            return getShiftingEncodeDecode(Mode.DECODE, decodedFirstly, key);
        }
    }

    /**
     * The Method Decodes Encoded String from Hex or Base64 String and Decodes the Message in special combined way afterwards
     * @param encodingType - the value of enum EncodingType (can be HEX or BASE64)
     * @param encodedMessage - the string to encode and for further the estimating its length
     * @param key - the integer value on which the first shift will be carried out
     * @param divider - the integer on which divides the number received after Math.sqrt(initial length)
     * @param shift - the integer on which the second shift will be carried out after getPosition() function
     * @param commonDivider - the integer on which the total length of the string divides (can be 3 or 4)
     * @return Decoded Message String
     */
    public static String decodeCeasarFromBinaryString(EncodingType encodingType, String encodedMessage, int key, int divider, int shift, int commonDivider) {
        if (key < MIN_KEY_SIZE || key > MAX_KEY_SIZE || shift < MIN_KEY_SIZE || shift > MAX_KEY_SIZE) {
            throw new IllegalArgumentException("Key or Shift Integer Values must be equal or more than 1 and equal or less than 26.");
        } else {
            String binaryDecoded = null;
            if (encodingType.equals(EncodingType.BASE64)) {
                binaryDecoded = fromBase64(encodedMessage);
            } else if (encodingType.equals(EncodingType.HEX)) {
                binaryDecoded = getStringfromHex(encodedMessage);
            } else {
                throw new IllegalArgumentException("Invalid Binary Encoding Type was provided.");
            }
            return decodeCeasar(binaryDecoded, key, divider, shift, commonDivider);
        }
    }

    /**
     * The Method Provides Shifting on the Character Level
     * @param mode - the value of enum Mode (ENCODE, DECODE)
     * @param symbol - current Character symbol
     * @param shift - the integer value on which the first shift will be carried out
     * @return Encoded-Decoded Character (depending on Mode value)
     */
    private static char getCharacterShiftingEncodeDecode(Mode mode, char symbol, int shift) {
        if (mode.equals(Mode.DECODE))
            shift = -shift;
        char encoded = 0;
        if (Character.isAlphabetic(symbol)) {
            int ascii = symbol;
            ascii += shift;
            if (Character.isUpperCase(symbol)) {
                if (ascii > 'Z')
                    ascii -= MAX_KEY_SIZE;
                else if (ascii < 'A')
                    ascii += MAX_KEY_SIZE;
            } else if (Character.isLowerCase(symbol)) {
                if (ascii > 'z')
                    ascii -= MAX_KEY_SIZE;
                else if (ascii < 'a')
                    ascii += MAX_KEY_SIZE;
            }
            encoded = (char) ascii;
        } else {
            encoded = symbol;
        }
        return encoded;
    }

    /**
     * The Method Provides Shifting on the String Level
     * @param mode - the value of enum Mode (ENCODE, DECODE)
     * @param string - encoded-decoded current String
     * @param shift - the integer value on which the first shift will be carried out
     * @return Encoded-Decoded String (depending on Mode value)
     */
    private static String getShiftingEncodeDecode(Mode mode, String string, int shift) {
        if (mode.equals(Mode.DECODE))
            shift = -shift;
        StringBuilder encoded = new StringBuilder();
        char[] messageToCharArray = string.toCharArray();
        for (char symbol : messageToCharArray) {
            if (Character.isAlphabetic(symbol)) {
                int ascii = symbol;
                ascii += shift;
                if (Character.isUpperCase(symbol)) {
                    if (ascii > 'Z')
                        ascii -= MAX_KEY_SIZE;
                    else if (ascii < 'A')
                        ascii += MAX_KEY_SIZE;
                } else if (Character.isLowerCase(symbol)) {
                    if (ascii > 'z')
                        ascii -= MAX_KEY_SIZE;
                    else if (ascii < 'a')
                        ascii += MAX_KEY_SIZE;
                }
                encoded.append((char) ascii);
            } else {
                encoded.append(symbol);
            }
        }
        return encoded.toString();
    }

    /**
     * The Method Encodes-Decodes WhiteSpaces in current String
     * @param mode - the value of enum Mode (ENCODE, DECODE)
     * @param message - encoded-decoded current String
     * @return Encoded-Decoded String with Encoded-Decoded WhiteSpaces (depending on Mode value)
     */
    private static String encodeWhiteSpaces(Mode mode, String message) {
        char whiteSpaceSymbol = (char) 230;
        StringBuilder encodedDecoded = new StringBuilder();
        char[] messageToCharArray = message.toCharArray();
        if (mode.equals(Mode.ENCODE)) {
            for (char symbol : messageToCharArray) {
                if (Character.isWhitespace(symbol)) {
                    symbol = whiteSpaceSymbol;
                }
                encodedDecoded.append(symbol);
            }
        } else {
            for (char symbol : messageToCharArray) {
                if (symbol == 230) {
                    symbol = ' ';
                }
                encodedDecoded.append(symbol);
            }
        }
        return encodedDecoded.toString();
    }

    /**
     * The Method Encodes-Decodes Digits in current String
     * @param mode - the value of enum Mode (ENCODE, DECODE)
     * @param message - encoded-decoded current String
     * @return Encoded-Decoded String with Encoded-Decoded Digits (depending on Mode value)
     */
    private static String encodeDigits(Mode mode, String message) {
        StringBuilder encodedDecoded = new StringBuilder();
        char[] messageToCharArray = message.toCharArray();
        if (mode.equals(Mode.ENCODE)) {
            for (char symbol : messageToCharArray) {
                if (Character.isDigit(symbol)) {
                    symbol = allDigitsAsCharsInversed[digitsAsCharacters.indexOf(symbol)];
                }
                encodedDecoded.append(symbol);
            }
        } else {
            for (char symbol : messageToCharArray) {
                if (digitsAsCharactersInversed.contains(symbol)) {
                    symbol = allDigitsAsChars[digitsAsCharactersInversed.indexOf(symbol)];
                }
                encodedDecoded.append(symbol);
            }
        }
        return encodedDecoded.toString();
    }

    /**
     * The Method Encodes-Decodes Special Characters in current String
     * @param mode - the value of enum Mode (ENCODE, DECODE)
     * @param message - encoded-decoded current String
     * @return Encoded-Decoded String with Encoded-Decoded Special Characters (depending on Mode value)
     */
    private static String encodeSpecialCharacters(Mode mode, String message) {
        StringBuilder encodedDecoded = new StringBuilder();
        char[] messageToCharArray = message.toCharArray();
        if (mode.equals(Mode.ENCODE)) {
            for (char symbol : messageToCharArray) {
                if (!Character.isAlphabetic(symbol) && !Character.isWhitespace(symbol)) {
                    symbol = nonANCCharsInversed[nonANCharacters.indexOf(symbol)];
                }
                encodedDecoded.append(symbol);
            }
        } else {
            for (char symbol : messageToCharArray) {
                if (nonANCharactersInversed.contains(symbol)) {
                    symbol = nonANC[nonANCharactersInversed.indexOf(symbol)];
                }
                encodedDecoded.append(symbol);
            }
        }
        return encodedDecoded.toString();
    }

    /**
     * The Method Finds the pseudo-random Index Positions in current String for Making the Second Shift
     * @param string - the string to encode for the estimating its length
     * @param divider - the integer on which divides the number received after Math.sqrt(initial length)
     * @param commonDivider - the integer on which the total length of the string divides (can be 3 or 4)
     * @return The Set of Integers - pseudo-random Index Positions in current String for Making the Second Shift
     */
    private static Set<Integer> getPositions(String string, int divider, int commonDivider) {
        List<Integer> positions = new ArrayList<Integer>();
        int fourthSecond = string.length() / 4;
        int fourthThird = fourthSecond + fourthSecond;
        int fourthFourth = fourthThird + fourthSecond;
        int thirdSecond = string.length() / 3;
        int thirdThird = thirdSecond * 2;

        int init = string.length();
        while (init > MIN_KEY_SIZE) {
            int pos = (int) Math.round(Math.sqrt(init));
            positions.add(pos);
            init = init / divider;
        }
        Set<Integer> distinctPosition = new HashSet<Integer>(positions);
        List<Integer> distinctList = new ArrayList<Integer>(distinctPosition);
        List<Integer> finalList = new ArrayList<Integer>(distinctList);
        int incrementFirst, incrementSecond, incrementThird = 0;
        if (commonDivider == 4) {
            for (Integer i : distinctList) {
                incrementFirst = fourthSecond + i;
                finalList.add(incrementFirst);
                incrementSecond = fourthThird + i;
                finalList.add(incrementSecond);
                incrementThird = fourthFourth + i;
                finalList.add(incrementThird);
            }
        } else if (commonDivider == 3) {
            for (Integer i : distinctList) {
                incrementFirst = thirdSecond + i;
                finalList.add(incrementFirst);
                incrementSecond = thirdThird + i;
                finalList.add(incrementSecond);
            }
        } else {
            throw new IllegalArgumentException("Value of the commonDivider must be equal 3 or 4.");
        }
        finalList.sort(Comparator.naturalOrder());
        return new LinkedHashSet<Integer>(finalList);
    }

    /**
     * The Method Encodes the current String in Base64 String Without Padding
     * @param string - the current String
     * @return Base64 Encoded String Without Padding
     */
    public static String toBase64(String string) {
        return Base64.getEncoder().encodeToString(string.getBytes());
    }

    /**
     * The Method Decodes the Base64 String Without Padding in the Simple String
     * @param encodedStringWithoutPadding - the Base64 Encoded String Without Padding
     * @return Decoded String from Base64
     */
    public static String fromBase64(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }

    /**
     * The Method Encodes the current String in Hex String
     * @param string - the current String
     * @return Hex Encoded String
     */
    public static String getHeX(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] charArray = string.toCharArray();
        for (char c : charArray) {
            String charToHex = Integer.toHexString(c);
            stringBuilder.append(charToHex);
        }
        return stringBuilder.toString();
    }

    /**
     * The Method Decodes the Hex String in the Simple String
     * @param hexString - the Hex Encoded String
     * @return Decoded String from Hex
     */
    public static String getStringfromHex(String hexString) {
        StringBuilder result = new StringBuilder();
        char[] charArray = hexString.toCharArray();
        for (int i = 0; i < charArray.length; i += 2) {
            String twoChars = "" + charArray[i] + "" + charArray[i + 1];
            char character = (char) Integer.parseInt(twoChars, 16);
            result.append(character);
        }
        return result.toString();
    }

}
