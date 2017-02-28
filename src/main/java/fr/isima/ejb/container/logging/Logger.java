package fr.isima.ejb.container.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Logger {

    private static List<String> logs = new ArrayList<String>();

    public static boolean contains(String methodName){
            return logs.contains(methodName);
    }

    public static void log(String name){
            logs.add(name);
    }
	
    public static String getTime(){
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();
    	return dateFormat.format(date);
    }
    
    public static String getClass(String fullName){
    	return fullName.substring(fullName.lastIndexOf(".")+1);
    }
    
    public static int size(){
    	return logs.size();
    }
    
    public static void reset(){
    	logs.clear();
    }
    
    
}
