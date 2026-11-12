package org.example.Day2Assessment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q2Hashing {

    static String makeKey(String policyNumber, String incidentDate) {
        String policy = policyNumber.replaceAll("\\s+", "").toUpperCase();
        String date = incidentDate.replaceAll("\\s+", "");
        return policy + "|" + date;
    }

    static void detect(String[][] claims) {
        Map<String, List<String>> table = new HashMap<>();
        int duplicates = 0;

        for (String[] claim : claims) {
            String claimId = claim[0];
            String key = makeKey(claim[1], claim[2]);

            if (table.containsKey(key)) {
                duplicates++;
                System.out.println("Duplicate: claim " + claimId + " matches claim " + table.get(key).get(0));
                table.get(key).add(claimId);
            } else {
                List<String> ids = new ArrayList<>();
                ids.add(claimId);
                table.put(key, ids);
            }
        }

        System.out.println("Claims checked: " + claims.length + ", duplicates found: " + duplicates);
    }

    public static void main(String[] args) {
        System.out.println("Test 1: empty input");
        detect(new String[0][0]);

        System.out.println("\nTest 2: exact and near duplicates");
        String[][] claims = {
                {"C1", "POL 1001", "2026-03-10"},
                {"C2", "pol1001", "2026-03-10"},
                {"C3", "POL1002", "2026-03-10"},
                {"C4", " Pol  1001 ", "2026-03-10"},
                {"C5", "POL1001", "2026-03-11"},
                {"C6", "POL1002", "2026-03-10"}
        };
        detect(claims);

        System.out.println("\nTest 3: no duplicates");
        String[][] unique = {
                {"C7", "POL2001", "2026-04-01"},
                {"C8", "POL2002", "2026-04-01"}
        };
        detect(unique);
    }
}
