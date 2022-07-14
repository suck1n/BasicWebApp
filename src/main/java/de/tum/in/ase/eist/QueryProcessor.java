package de.tum.in.ase.eist;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryProcessor {

    private int fibo(int n) {
        if (n <= 1) {
            return n;
        }
        return fibo(n-1) + fibo(n-2);
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        for(int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public String process(String query) {
		query = query.toLowerCase();
        if (query.contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        } else if (query.contains("name")) {
           return "Mattia Leon";
        } else if (query.contains("minus")) {
            List<String> args = new ArrayList<>(List.of(query.split(" ")));
            int index = args.indexOf("minus");
            int result = Integer.parseInt(args.get(index-1));
            args.remove(index);
            while (args.contains("minus")) {
                index = args.indexOf("minus");
                result -= Integer.parseInt(args.get(index-1));
                args.remove(index);
            }
            result -= Integer.parseInt(args.get(index));
            return result + "";
        } else if (query.contains("plus")) {
            List<String> args = new ArrayList<>(List.of(query.split(" ")));
            int index = args.indexOf("plus");
            int result = Integer.parseInt(args.get(index-1));
            args.remove(index);
            while (args.contains("plus")) {
                index = args.indexOf("plus");
                result += Integer.parseInt(args.get(index-1));
                args.remove(index);
            }
            result += Integer.parseInt(args.get(index));
            return result + "";
        } else if (query.contains("largest")) {
            try {
                List<String> args = List.of(query.split(":")[2].trim().split(","));
                return args.stream()
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .max(Integer::compareTo) + "";
            } catch (Exception e) {
                return "Invalid request: " + e.getMessage();
            }
        } else if (query.contains("multiplied ")) {
            try {
                List<String> args = List.of(query.split(" "));
                int index = args.indexOf("multiplied by");
                int num1 = Integer.parseInt(args.get(index-1));
                int num2 = Integer.parseInt(args.get(index+1));
                return num1 * num2 + "";
            } catch (Exception e) {
                return "Invalid request";
            }
        } else if (query.contains("square and a cube:")) {
            try {
                List<String> args = List.of(query.split("square and a cube:")[1].trim().split(","));
                return args.stream()
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .filter(n -> {
                            double number = n;
                            double r = Math.sqrt(number);
                            double p = Math.pow(number, 2);
                            return Math.round(r)- r < 0.0000000000001 && Math.round(p) - p < 0.0000000000001;
                        }).findFirst().orElse(-1) + "";
            } catch (Exception e) {
                return "Invalid request: " + e.getMessage();
            }
        } else if (query.contains("banana")) {
            return "yellow";
        } else if (query.contains("eiffel tower")) {
            return "paris";
        } else if (query.contains("fibonacci")) {
            try {
                List<String> args = List.of(query.split(" "));
                int index = args.indexOf("number");
                int num1 = Integer.parseInt(args.get(index - 1).replace("th", ""));
                return fibo(num1) + "";
            } catch (Exception e) {
                return "Invalid request";
            }
        } else if (query.contains("primes")) {
            try {
                List<String> args = List.of(query.split("primes:")[1].trim().split(","));
                return args.stream()
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .filter(this::isPrime).findFirst().orElse(-1) + "";
            } catch (Exception e) {
                return "Invalid request: " + e.getMessage();
            }
        } else if (query.contains("james bond")) {
            return "Sean Connery";
        } else {
            return "";
        }
    }
}
