package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginAnalyzer {
    //counts failed logins per IP and flags IPs at or over the threshold.

    private final int threshold;
    public LoginAnalyzer(int threshold) {
        this.threshold = threshold;

    }

    public Map<String, Integer> countByIp(List<FailedLogin> failedLogins) {
        
        //create a map to hold the counts of failed logins per IP
        Map<String, Integer> counts = new HashMap<>();
        for (FailedLogin failedLogin : failedLogins) {
            String ip = failedLogin.ip();
            counts.put(ip, counts.getOrDefault(ip, 0) + 1);
        }
        return counts;
    }
    
    public List<String> flaggedIps(Map<String, Integer> counts) {
        //return a list of IPs that have failed logins at or over the threshold
    
        List<String> flagged = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() >= threshold) {
                flagged.add(entry.getKey());

            }
        }
        return flagged; 

    }
}
