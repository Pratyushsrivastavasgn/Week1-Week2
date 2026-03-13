import java.util.*;

public class UsernameChecker {

    private HashMap<String, Integer> users = new HashMap<>();

    private HashMap<String, Integer> attempts = new HashMap<>();


    public boolean checkAvailability(String username) {

        attempts.put(username, attempts.getOrDefault(username, 0) + 1);

        return !users.containsKey(username);
    }


    public void registerUser(String username, int userId) {

        if (checkAvailability(username)) {
            users.put(username, userId);
            System.out.println("User registered: " + username);
        } else {
            System.out.println("Username already taken");
        }
    }


    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String suggestion = username + i;

            if (!users.containsKey(suggestion)) {
                suggestions.add(suggestion);
            }
        }

        // Replace underscore with dot
        String dotVersion = username.replace("_", ".");

        if (!users.containsKey(dotVersion)) {
            suggestions.add(dotVersion);
        }

        return suggestions;
    }


    public String getMostAttempted() {

        String mostAttempted = "";
        int max = 0;

        for (Map.Entry<String, Integer> entry : attempts.entrySet()) {

            if (entry.getValue() > max) {
                max = entry.getValue();
                mostAttempted = entry.getKey();
            }
        }

        return mostAttempted + " (" + max + " attempts)";
    }


    public static void main(String[] args) {

        UsernameChecker checker = new UsernameChecker();

        // Register users
        checker.registerUser("Pratyush", 101);
        checker.registerUser("Sample1", 102);
        checker.registerUser("Sample2", 103);

        // Check availability
        System.out.println("Pratyush available: " + checker.checkAvailability("Pratyush"));
        System.out.println("Sample1 available: " + checker.checkAvailability("Sample1"));

        // Suggest alternatives
        System.out.println("Suggestions for Pratyush: " + checker.suggestAlternatives("Pratyush"));

        // Simulate attempts
        checker.checkAvailability("Pratyush");
        checker.checkAvailability("Sample1");
        checker.checkAvailability("Sample2");

        // Most attempted username
        System.out.println("Most attempted username: " + checker.getMostAttempted());
    }
}