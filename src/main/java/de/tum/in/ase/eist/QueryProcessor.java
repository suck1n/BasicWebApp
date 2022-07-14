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
            List<String> args = List.of(query.split(" "));
            int index = args.indexOf("plus");
            try {
                int num1 = Integer.parseInt(args.get(index-1));
                int num2 = Integer.parseInt(args.get(index+1));
                return num1 + num2 + "";
            } catch (Exception e) {
                return "Invalid request";
            }
        } else {
            return "";
        }
    }
}
