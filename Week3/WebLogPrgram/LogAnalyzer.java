
/**
 * (1) to read information from a web server log.
 * (2) to count the number of unique visitors to your website. 
 * (3) to count the number of times each visitor uses your website.
 * 
 * @author Joyce Lin
 * @version Apr,6, 2022
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;
     
    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<LogEntry>();
    }
        
    public void readFile(String filename) {
        // complete method
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            WebLogParser parser = new WebLogParser();
            LogEntry entry = parser.parseEntry(line);
            records.add(entry);
        }
    }
        
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
     
    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            if (!uniqueIPs.contains(le.getIpAddress())) {
                uniqueIPs.add(le.getIpAddress());
            }
        }
        return uniqueIPs.size();
    }
     
    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            int status = le.getStatusCode();
            if (status > num) {
                System.out.println(status);
            }
        }
    }
     
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPsOnDay = new ArrayList<String>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            String ip = le.getIpAddress();
            if (date.contains(someday)) {
                if (!uniqueIPsOnDay.contains(ip)) {
                    uniqueIPsOnDay.add(ip);
                }
            }
        }
        return uniqueIPsOnDay;
    }
    
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPsInRange = new ArrayList<String>();
        for (LogEntry le : records) {
            int status = le.getStatusCode();
            String ip = le.getIpAddress();
            if (status >= low && status <= high) {
                if (!uniqueIPsInRange.contains(ip)) {
                uniqueIPsInRange.add(ip);
                }
            }
        }
        return uniqueIPsInRange.size();
    }
    
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int maxVisits = Integer.MIN_VALUE;
        for (String key : counts.keySet()) {
            int number = counts.get(key);
            if (number > maxVisits) {
                maxVisits = number;
            }
        }
        return maxVisits;
    }
    // returns an ArrayList of Strings of IP addresses that all have the maximum number of visits to this website
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        ArrayList<String> mostVisits = new ArrayList<String>();
        int maxVisits = mostNumberVisitsByIP(counts);
        for (String key : counts.keySet()) {
            if (counts.get(key) == maxVisits) {
                mostVisits.add(key);
            }
        }
        return mostVisits;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> iPsForDays = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            String ip = le.getIpAddress();
            String day = date.substring(4, 10);
            ArrayList<String> ipList;
            if (!iPsForDays.containsKey(day)) {
                ipList = new ArrayList<String>();
            } else {
                ipList = iPsForDays.get(day);
            }
            ipList.add(ip);
            iPsForDays.put(day, ipList);
        }
        return iPsForDays;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsForDays) {
        int maxVisits = Integer.MIN_VALUE;
        String maxVisitsDay = "";
        for (String key : iPsForDays.keySet()) {
            int numberOfVisits = iPsForDays.get(key).size();
            if (numberOfVisits > maxVisits) {
                maxVisits = numberOfVisits;
                maxVisitsDay = key;
            }
        }
        return maxVisitsDay;       
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsForDays, String day) {
        HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
        ArrayList<String> ipList = iPsForDays.get(day);       
        for (String ip : ipList) {
             if (!ipCounts.containsKey(ip)) {
                ipCounts.put(ip, 1);
            } else {
                ipCounts.put(ip, ipCounts.get(ip) + 1);
            }
        }        
        return iPsMostVisits(ipCounts);       
    }
}
