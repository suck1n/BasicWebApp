package de.tum.in.ase.eist;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryProcessor {

    public String process(String query) {
		query = query.toLowerCase();
        if (query.contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        } else if (query.contains("name")) {
           return "Mattia Leon";
        } else if (query.contains("plus")) {
            try {
                List<String> args = List.of(query.split(" "));
                int index = args.indexOf("plus");
                int num1 = Integer.parseInt(args.get(index-1));
                int num2 = Integer.parseInt(args.get(index+1));
                return num1 + num2 + "";
            } catch (Exception e) {
                return "Invalid request";
            }
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
        } else {
            return "";
        }
    }
}
