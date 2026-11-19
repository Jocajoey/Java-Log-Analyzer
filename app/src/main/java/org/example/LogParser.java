package org.example;
import java.util.Optional;

public class LogParser {
    // This class will parse the log file and return a list of FailedLogin records
    public static Optional<FailedLogin> parseLine(String line) {
        if (!line.contains("Failed password")) {
            return Optional.empty();
        }else {
            String[] parts = line.trim().split("\\s+");
            //-1 "not found yet"
            int fromIndex = -1;

            //count backward: last index down to 0. 
            // Search for "from" in the log line, which indicates the IP address. User is right before "from".
            for (int i = parts.length - 1; i >= 0; i--) {
                if (parts[i].equals("from")) {
                    fromIndex = i;
                    break;
                }

            }
            // If "from" is not found or if the from index is out of bounds, return an empty Optional.
            if (fromIndex < 1 || fromIndex +1 >= parts.length) {
                return Optional.empty();
            }
            String user = parts[fromIndex - 1];
            String ip = parts[fromIndex + 1];
            return Optional.of(new FailedLogin(user, ip));
        }
    }
}
