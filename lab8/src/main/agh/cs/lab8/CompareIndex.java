package agh.cs.lab8;

public class CompareIndex {
    public static boolean containsInRange(String curr, String start, String end) {
        curr = curr.replaceAll("[.]", "").replaceAll("art", "").replaceAll("[a-z]", "");
        start = start.replaceAll("[.]", "").replaceAll("art", "").replaceAll("[a-z]", "");
        end = end.replaceAll("[.]", "").replaceAll("art", "").replaceAll("[a-z]", "");

        int currInt = Integer.parseInt(curr);
        int startInt = Integer.parseInt(start);
        int endInt = Integer.parseInt(end);

        if (currInt >= startInt && currInt < endInt) {
            return true;
        }else {
            return false;
        }
    }
}
