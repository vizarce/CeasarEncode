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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ceasar.exceptions.ClassIsAlreadyInstantiatedException;
import com.ceasar.exceptions.NotAuthenticatedException;


public class SD implements Serializable {
	/**
	 * @serial serialVersionUID
	 */
	private static final long serialVersionUID = 5203240019856868717L;
	
	public static final Logger logger = LoggerFactory.getLogger(SD.class);
	/**
	 * @hidden private static class SourceDriverLoader
	 * @see Vault for the final fields pasword, token and origin
	 * @category private Inner Class
	 */
	protected static final class SourceDriverLoader {
		/**
		 * @hidden private static final fields
		 */
        private static final SD INSTANCE = new SD();
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
    private SD() {
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
    public static SD getInstance() {
    	logger.info("[{}---{}---info: {}]", 
			Thread.currentThread(), LocalDateTime.now(), "...SourceDriver Class INSTANTIATED!...");
        return SourceDriverLoader.INSTANCE;
    }
    
    /**
     * Second method getInstance() with password protection
     * @param password
     * @return SD INSTANCE
     */
    public static SD getInstance(String password) {
    	if (!password.equals(SD.SourceDriverLoader.password)) {
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
    	if (!password.equals(SD.SourceDriverLoader.password)) {
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
    private SD readResolve() {
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
     * @return deseerialized Object
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object readObjectFromFile(String fullFilePath, 
    		                                Object deseerialized) throws FileNotFoundException, IOException, ClassNotFoundException {
    	FileInputStream fileInputStream = new FileInputStream(fullFilePath);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		//@SuppressWarnings("unchecked")
		deseerialized = (Object) objectInputStream.readObject();
		objectInputStream.close();
		fileInputStream.close();
		logger.info("[{}---{}---info: {}]", 
				Thread.currentThread(), LocalDateTime.now(), 
				"...readObjectFromFile() - Object was successfully deserialized...");
		return deseerialized;
    } 
	
	/**private String userPassword;
	private final String password = "777VIStudio555";
	protected final String origin = "(<~<<VIStudio1975 ©2024>>~>)";
	private String token = "Bearer 4b44782b5044785753564e3064575270627a45354e7a556777716b794d444930506a352b50696b3d";*/
	/**
	 * @category Default SD Constructor
	 */
	//private SD() {
	//}
	/**
	 * Additional SD Constructor 
	 * to retrieve the token
	 * @param String userPassword
	 */
	/**private SD(String userPassword) {
		this.userPassword = userPassword;
		this.token = this.token;
	}*/
	/**
	 * Static method to create instance of SD Class
	 * @return single_instance
	 */
	/**public static synchronized SD getInstance() {
		if (single_instance == null)
			single_instance = new SD();
		return single_instance;
	}*/
	/**
	 * @return the serialVersionUid
	 */
	/**public static long getSerialversionuid() {
		return serialVersionUID;
	}*/
	/**
	 * @return the token
	 */
	/**public String getToken() {
		if (this.userPassword.equals(this.origin)) {
			return this.token;
		} else {
			throw new IllegalArgumentException("User\'s password provided is wrong or expired");
		}
	}*/
	/**
	 * @return the password
	 */
	//public String getPassword() {
		//return password;
	//}
   // public static boolean 
    
    

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		
		SD sd01 = SD.getInstance();
		logger.info("[Logging SD::: processing: {}, --- tread: {}, --- time: {}, main Class: {} message: {}]", 
				SD.getInstance().readResolve().getClass().getCanonicalName().toString(),
				Thread.currentThread(), LocalDateTime.now(), SD.class.getName().toString(),
				("object01-hash-code: " + sd01.hashCode()));
		SD sd02 = SD.getInstance();
		logger.info("[Logging SD::: processing: {}, --- tread: {}, --- time: {}, main Class: {} message: {}]", 
				SD.getInstance().readResolve().getClass().getCanonicalName().toString(),
				Thread.currentThread(), LocalDateTime.now(), SD.class.getName().toString(),
				("object02-hash-code: " + sd02.hashCode()));
		SD sd03 = SD.getInstance();
		logger.info("[Logging SD::: processing: {}, --- tread: {}, --- time: {}, main Class: {} message: {}]", 
				SD.getInstance().readResolve().getClass().getCanonicalName().toString(),
				Thread.currentThread(), LocalDateTime.now(), SD.class.getName().toString(),
				("object03-hash-code: " + sd03.hashCode()));
		try {
			SD sd04 = SD.getInstance("777VIStudio55511");
		} catch (NotAuthenticatedException e) {
			logger.error("Password provided is wrong!");
			logger.error("ERROR!!!!!!!!!!!!!!!!!!!" + Arrays.toString(e.getStackTrace()), e.getClass().getName().toString());
		}
		SD sd04 = SD.getInstance("777VIStudio555");
		logger.info("[Logging SD::: processing: {}, --- tread: {}, --- time: {}, main Class: {} message: {}]", 
				SD.getInstance().readResolve().getClass().getCanonicalName().toString(),
				Thread.currentThread(), LocalDateTime.now(), SD.class.getName().toString(),
				("object04-hash-code: " + sd04.hashCode()));
		SD sd05 = SD.getInstance("777VIStudio555");
		logger.info("[Logging SD::: processing: {}, --- tread: {}, --- time: {}, main Class: {} message: {}]", 
				SD.getInstance().readResolve().getClass().getCanonicalName().toString(),
				Thread.currentThread(), LocalDateTime.now(), SD.class.getName().toString(),
				("serial-version-id: " + sd05.serialVersionUID));
		String t = SD.getToken("777VIStudio555");
		System.out.println("token: " + t);
		logger.debug("[Logging SD::: processing: {}, --- tread: {}, --- time: {}, main Class: {} message: {}]", 
				SD.getInstance().readResolve().getClass().getCanonicalName().toString(),
				Thread.currentThread(), LocalDateTime.now(), SD.class.getName().toString(),
				("access-token: " + t));
		System.out.println("sd01-hash: " + sd01.hashCode());
		System.out.println("sd02-hash: " + sd02.hashCode());
		System.out.println("sd03-hash: " + sd03.hashCode());
		System.out.println("sd04-hash: " + sd04.hashCode());
		System.out.println("sd05-hash: " + sd05.hashCode());
		System.out.println(sd01.getClass().getName().toString());
		System.out.println(sd04.getClass().getName().toString());
		System.out.println(sd01.equals(sd02));
		logger.warn("[Logging SD::: processing: {}, --- tread: {}, --- time: {}, main Class: {} message: {}]", 
				SD.getInstance().readResolve().getClass().getCanonicalName().toString(),
				Thread.currentThread(), LocalDateTime.now(), SD.class.getName().toString(),
				("sd01.equals(sd02): " + sd01.equals(sd02)));
		System.out.println(sd01.equals(sd05));
		System.out.println(sd04.equals(sd05));
		System.out.println("-------------------------------------------------");
		System.out.println("current-thread: " + Thread.currentThread());
		logger.trace("[Logging SD::: processing: {}, --- tread: {}, --- time: {}, main Class: {} message: {}]", 
				SD.getInstance().readResolve().getClass().getCanonicalName().toString(),
				Thread.currentThread(), LocalDateTime.now(), SD.class.getName().toString(),
				("current-thread-name: " + Thread.currentThread().getName().toString()));
		System.out.println("current-thread-max-hriority: " + Thread.MAX_PRIORITY);
		System.out.println("current-thread-id: " + Thread.currentThread().getId());
		System.out.println("current-thread-name: " + Thread.currentThread().getName());
		System.out.println("current-thread-state: " + Thread.currentThread().getState());
		System.out.println("current-thread-priority: " + Thread.currentThread().getPriority());
		logger.trace("[Logging SD::: processing: {}, --- tread: {}, --- time: {}, main Class: {} message: {}]", 
				SD.getInstance().readResolve().getClass().getCanonicalName().toString(),
				Thread.currentThread(), LocalDateTime.now(), SD.class.getName().toString(),
				("current-thread-priority: " + Thread.currentThread().getPriority()));
		System.out.println("current-thread-get-parent-name: " + Thread.currentThread().getThreadGroup().getParent().getName());
		System.out.println("system-time-millis: " + System.currentTimeMillis());
		logger.trace("[tread: {}, --- timstamp: {}, message: {}]", 
				Thread.currentThread(), LocalDateTime.now(),
				("system-current-time-millis: " + System.currentTimeMillis()));
		System.out.println("system-get-property-logger: " + System.getProperty("org.slf4j.Logger"));
		System.out.println("system-get-property-library: " + System.getProperty("org.slf4j"));
		System.out.println("system-time-nano: " + System.nanoTime());
		//System.out.println("system-read-password: " + new String(System.console().readPassword(SD.SourceDriverLoader.getPassword())));
		System.out.println("system-map-library: " + System.mapLibraryName("org.slf4j"));
		System.out.println("system-map-library-logger: " + System.mapLibraryName("org.slf4j.Logger"));
		
		Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                              envName,
                              env.get(envName));
            logger.info("[processing: {}, --- tread: {}, --- time: {}, main Class: {} message: {}]", 
    				SD.getInstance().readResolve().getClass().getCanonicalName().toString(),
    				Thread.currentThread(), LocalDateTime.now(), SD.class.getName().toString(),
    				("ENV-NAME: " + envName + ", VALUE: " + env.get(envName)));
        }
        
        
        File props = new File("myProperties.txt");
        props.createNewFile();
        // set up new properties object
        // from file "myProperties.txt"
        FileInputStream propFile =
            new FileInputStream("myProperties.txt");
        Properties p = new Properties(System.getProperties());
        p.load(propFile);

        // set the system properties
        //System.setProperties(p);
        // display new properties
        System.getProperties().list(System.out);
		
		
		//System.out.println("sd01-token: " + sd04.getToken());
		//System.out.println("sd01-token: " + sd05.getToken());
		
		
		/**Student student = new Student(117L, "James", "Cameron", 20, "Male", "IT Technologies", 91.99, "New York");*/
		//System.out.println(student);
		
		FileOutputStream fileOutputStream = new FileOutputStream("bin/envvariables.ser");
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
			objectOutputStream.writeObject(env);
			objectOutputStream.flush();
		}
		fileOutputStream.close();
		System.out.println("Object was successfully serialized!");
		
		FileInputStream fileInputStream = new FileInputStream("bin/envvariables.ser");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		@SuppressWarnings("unchecked")
		Map<String,String> deseerialized = (Map<String,String>) objectInputStream.readObject();
		objectInputStream.close();
		fileInputStream.close();
		deseerialized.entrySet().stream().forEach(e -> System.out.println("new-env: " + e.getKey() + "new-value: " + e.getValue()));
		/**for (Entry<String, String> e : deseerialized.entrySet())
		    logger.trace("[tread: {}, --- timstamp: {}, message: {}]", Thread.currentThread(), LocalDateTime.now(), ("env: " + e.getKey() + ", value: " + e.getValue()));*/
		logger.error("[{}---{}, id: {}, info: {}]", 
				Thread.currentThread(), LocalDateTime.now(),
				Thread.currentThread().getId(), "...TESTING LOGGER PATTERN...");
		System.out.println(Arrays.toString("bin/dir".split("/")));
		System.out.println(Arrays.toString("bin/dir/folder/one".split("/")));
		System.out.println(createDirectory("users/name"));
		System.out.println(createDirectory("users/name-new/ivan/nick"));
		System.out.println(createFile("users/name", "my-new-file", "xlsx"));
		
	}
}
