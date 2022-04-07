
import java.util.*;

public class Tester
{   
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIPs() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("short-test_log");
        la.readFile("weblog2_log");
        System.out.println("Unique IPs are: " + la.countUniqueIPs());
    }
    
    public void testHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("Unique IPs On Day are: " + la.uniqueIPVisitsOnDay("Sep 27").size());
        //System.out.println("Unique IPs On Day are: " + la.uniqueIPVisitsOnDay("Sep 27"));       
    }
    
    public void testUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("Unique IPs between 200-299: " + la.countUniqueIPsInRange(200,299));
        //System.out.println("Unique IPs between 300-399: " + la.countUniqueIPsInRange(300,399));
    }
    
    public void tester() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("weblog3-short_log");
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        HashMap<String, ArrayList<String>> iPsForDays = la.iPsForDays();
        System.out.println("MostNumberVisitsByIP: " + la.mostNumberVisitsByIP(counts));
        System.out.println("iPsMostVisits: " + la.iPsMostVisits(counts));
        //System.out.println("iPsForDays: " + la.iPsForDays());
        System.out.println("dayWithMostIPVisits: " + la.dayWithMostIPVisits(iPsForDays));
        System.out.println("iPsWithMostVisitsOnDay: " + la.iPsWithMostVisitsOnDay(iPsForDays, "Sep 29"));
    }
}
