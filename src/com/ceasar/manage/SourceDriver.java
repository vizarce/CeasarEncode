package com.ceasar.manage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ceasar.exceptions.ClassIsAlreadyInstantiatedException;
import com.ceasar.exceptions.NotAuthenticatedException;


public class SourceDriver implements Serializable {
	/**
	 * @serial serialVersionUID
	 */
	private static final long serialVersionUID = 5203240019856868717L;
	
	public static final Logger logger = LoggerFactory.getLogger(SourceDriver.class);
	/**
	 * @hidden private static class SourceDriverLoader
	 * @see Vault for the final fields pasword, token and origin
	 * @category private Inner Class
	 */
	protected static final class SourceDriverLoader {
		/**
		 * @hidden private static final fields
		 */
        private static final SourceDriver INSTANCE = new SourceDriver();
        private final static String password = "777VIStudio555";
		protected final String origin = "(<~<<VIStudio1975 ©2024>>~>)";
    	private static String token = "Bearer 4b44782b5044785753564e3064575270627a45354e7a556777716b794d444930506a352b50696b3d";
		/**
		 * @return the token
		 */
		public static String getToken() {
			logger.info("[{}---{}---info: {}]", 
					Thread.currentThread(), LocalDateTime.now(), "...getToken() processed...");
			return token;
		}
    	
    	protected static String getPassword() {
    		return password;
    	}
    }
    /**
     * @version 1.0.0
     * @category private SD Constructor
     * @exception IllegalStateException
     */
    private SourceDriver() {
        if (SourceDriverLoader.INSTANCE != null) {
        	logger.error("[{}---{}---error: {}]", 
					Thread.currentThread(), LocalDateTime.now(), "...SourceDriver is already INSTANTIATED!...");
            throw new ClassIsAlreadyInstantiatedException("SD is already instantiated!");
        }
    }
    
    /**
     * First default method getInstance()
     * @return SD INSTANCE
     */
    public static SourceDriver getInstance() {
    	logger.info("[{}---{}---info: {}]", 
			Thread.currentThread(), LocalDateTime.now(), "...SourceDriver Class INSTANTIATED!...");
        return SourceDriverLoader.INSTANCE;
    }
    
    /**
     * Second method getInstance() with password protection
     * @param password
     * @return SD INSTANCE
     */
    public static SourceDriver getInstance(String password) {
    	if (!password.equals(SourceDriver.SourceDriverLoader.password)) {
    		logger.error("[{}---{}---error: {}]", 
					Thread.currentThread(), LocalDateTime.now(), "...Password provided: " + password + " is wrong!...");
    		throw new NotAuthenticatedException("Password provided is wrong or termiated!");
    	} else {
    		logger.info("[{}---{}---info: {}]", 
    				Thread.currentThread(), LocalDateTime.now(), "...SourceDriver Class INSTANTIATED!...");
    		return SourceDriverLoader.INSTANCE;
    	}
    }
    
    /**
     * Static method to obtain the token
     * @author vizarce
     * @throws IllegalArgumentException
     * @param password
     * @return String token
     */
    public static String getToken(String password) {
    	if (!password.equals(SourceDriver.SourceDriverLoader.password)) {
    		logger.error("[{}---{}---error: {}]", 
    				Thread.currentThread(), LocalDateTime.now(), "...getToken() - Password provided: " + password + " is wrong!...");
    		throw new NotAuthenticatedException("Password provided is wrong or terminated!");
    	} else {
    		logger.info("[{}---{}---info: {}]", 
    				Thread.currentThread(), LocalDateTime.now(), "...getToken() - Token was accessed. Password provided: " + password + " is right!...");
    		return SourceDriverLoader.getToken();
    	}
    }

    @SuppressWarnings("unused")
    private SourceDriver readResolve() {
        return SourceDriverLoader.INSTANCE;
    }
    
    /**
     * Create Directory Function
     * @param String dirName
     * @return boolean
     */
    public static boolean createDirectory(String dirName) {
    	File file = new File(dirName);
    	boolean result;
    	boolean newResult;
    	if (dirName.split("/").length <= 2) {
    		logger.info("[{}---{}---info: {}]", 
    				Thread.currentThread(), LocalDateTime.now(), 
    				"...createDirectory() - It was one Directory provided: " + dirName + "...");
    		result = file.mkdir();
    		if (result) {
    			logger.info("[{}---{}---info: {}]", 
        				Thread.currentThread(), LocalDateTime.now(), 
        				"...createDirectory() = true; - Directory: " + dirName + " created...");
    		} else {
    			logger.warn("[{}---{}---warn: {}]", 
        				Thread.currentThread(), LocalDateTime.now(), 
        				"...createDirectory() = false; - Directory: " + dirName + " was not created or alredy exists...");
    		}
    		return result;
    	} else if (dirName.split("/").length > 2) {
    		logger.info("[{}---{}---info: {}]", 
    				Thread.currentThread(), LocalDateTime.now(), 
    				"...createDirectory() - It was more than one Directory provided: " + dirName + "...");
    		newResult = file.mkdirs();
    		if (newResult) {
    			logger.info("[{}---{}---info: {}]", 
        				Thread.currentThread(), LocalDateTime.now(), 
        				"...createDirectory() = true; - Directory: " + dirName + " created...");
    		} else {
    			logger.warn("[{}---{}---warn: {}]", 
        				Thread.currentThread(), LocalDateTime.now(), 
        				"...createDirectory() = false; - Directory: " + dirName + " was not created or alredy exists...");
    		}
    	    return newResult;
    	} else {
    		logger.error("[{}---{}---error: {}]", 
    				Thread.currentThread(), LocalDateTime.now(), 
    				"...createDirectory() - Error occurred while creating Directory: " + dirName + "...");
    		return false;
    	}
    }
    
    /**
     * Create File Function
     * @param String dirName
     * @param String fileName
     * @param String extension
     * @return boolean
     * @throws IOException
     */
    public static boolean createFile(String dirName, 
    		                         String fileName, 
    		                         String extension) throws IOException {
    	boolean result;
    	File file = new File(dirName + "/" + fileName + "." + extension);
    	result = file.createNewFile();
    	if (result) {
    		logger.info("[{}---{}---info: {}]", 
    				Thread.currentThread(), LocalDateTime.now(), 
    				"...createFile() = true; - File: " + file + " was created...");
    	} else {
    		logger.warn("[{}---{}---warn: {}]", 
    				Thread.currentThread(), LocalDateTime.now(), 
    				"...createFile() = false; - File: " + file + " was not created or alredy exists...");
    	}
    	return result;
    }
    
    /**
     * Object Serialization Function
     * @param Object o
     * @param String dirName
     * @param String fileName
     * @param String extension
     * @throws IOException
     */
    public static void writeObjectToFile(Object o, 
    		                             String dirName, 
    		                             String fileName, 
    		                             String extension) throws IOException {
    	FileOutputStream fileOutputStream = new FileOutputStream(dirName + "/" + fileName + "." + extension);
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
			objectOutputStream.writeObject(o);
			objectOutputStream.flush();
		}
		fileOutputStream.close();
		logger.info("[{}---{}---info: {}]", 
				Thread.currentThread(), LocalDateTime.now(), 
				"...writeObjectToFile() - Object was successfully serialized on the path: " + dirName + "/" + fileName + "." + extension + "...");
    }
    
    /**
     * Object Deserialization Function
     * @param String fullFilePath
     * @param Object deseerialized
     * @return deserialized Object
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object readObjectFromFile(String fullFilePath) throws FileNotFoundException, IOException, ClassNotFoundException {
    	FileInputStream fileInputStream = new FileInputStream(fullFilePath);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		//@SuppressWarnings("unchecked")
		Object deserialized = (Object) objectInputStream.readObject();
		objectInputStream.close();
		fileInputStream.close();
		logger.info("[{}---{}---info: {}]", 
				Thread.currentThread(), LocalDateTime.now(), 
				"...readObjectFromFile() - Object was successfully deserialized...");
		return deserialized;
    } 
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SourceDriver sd01 = SourceDriver.getInstance();
		SourceDriver sd02 = SourceDriver.getInstance();
		SourceDriver sd03 = SourceDriver.getInstance();
		logger.info("[{}---{}---error: {}]", 
				Thread.currentThread(), LocalDateTime.now(), "equals-sd01-sd02|sd02-sd03: " + sd01.equals(sd02) + ", " + sd02.equals(sd03));
		try {
			SourceDriver sd04 = SourceDriver.getInstance("777VIStudio55511");
		} catch (NotAuthenticatedException e) {
			logger.error("[{}---{}---error: {}]", 
					Thread.currentThread(), LocalDateTime.now(), "...Password provided is wrong!...");
			logger.error("ERROR!!!" + Arrays.toString(e.getStackTrace()), e.getClass().getName().toString());
		}
		SourceDriver sd04 = SourceDriver.getInstance("777VIStudio555");
		SourceDriver sd05 = SourceDriver.getInstance("777VIStudio555");
		logger.info("[{}---{}---error: {}]", 
				Thread.currentThread(), LocalDateTime.now(), "equals-sd04-sd05|sd02-sd05: " + sd04.equals(sd05) + ", " + sd02.equals(sd05));
		String t = SourceDriver.getToken("777VIStudio555");
		logger.info("token: " + t);
		List<Integer> list = Arrays.asList(1, 2, 3, 55, 7, 17, 98, 50);
		createDirectory("users/name");
		createDirectory("users/name-new/ivan/nick");
		createFile("users/name", "my-new-file", "pdf");
		createDirectory("users/vizarce/keys/arrays");
		writeObjectToFile(list, "users/vizarce/keys/arrays", "integerList", "ser");
		@SuppressWarnings("unchecked")
		List<Integer> deserialized = (List<Integer>) readObjectFromFile("users/vizarce/keys/arrays/integerList.ser");
		logger.info("List<Integer> list = " + list);
		logger.info("List<Integer> deserialized = " + deserialized);
		
	}
}
