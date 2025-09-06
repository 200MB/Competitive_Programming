import java.util.*;

public class Helpers {
    public static int pickNext(ArrayList<Integer> list, HashMap<Integer, Integer> map) {
        if (list.size() == 1) {
            return list.get(0);
        }
        int pickedNumber = -1;
        Collections.sort(list);
        pickedNumber = list.get(list.size() - 1);
        int maxKey = (map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey());
        int maxValue = map.get(maxKey);
        map.remove(maxKey);
        if (map.values().stream().noneMatch(e -> e == maxValue)) {
            pickedNumber = maxKey;
        }
        map.put(maxKey, maxValue);
        return pickedNumber;
    }

    public static int confirmAndGetOccurrences(String str, String sub) {
        int count = str.split(sub, -1).length - 1;
        if (count * sub.length() == str.length()) {
            return count * sub.length();
        }
        return -1;
    }

    public static String findMaxString(LinkedList<Character> slidingWindow, LinkedList<Character> lookAheadBuffer) {
        StringBuilder window = new StringBuilder();
        StringBuilder buffer = new StringBuilder();
        Arrays.stream(slidingWindow.toArray()).forEach(window::append);
        Arrays.stream(lookAheadBuffer.toArray()).forEach(buffer::append);
        for (int i = buffer.length(); i > 0; i--) {
            if (String.valueOf(window).contains(buffer.substring(0, i))) {
                return (window.length() - (window.lastIndexOf(buffer.substring(0, i))) + //offset
                        " " + buffer.substring(0, i).length()); //length
            }
        }
        return null;
    }
    public static int findIntDiff(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            count += Math.abs(a.charAt(i) - b.charAt(i));
        }
        return count;
    }

    public static String LZSS_ENCODE(String a, int x, int y, LinkedList<Character> lookAheadBuffer
            , LinkedList<Character> slidingWindow, int index) {
        StringBuilder encodedText = new StringBuilder();
        if (lookAheadBuffer == null || slidingWindow == null) {
            index = y;
            lookAheadBuffer = new LinkedList<>();
            slidingWindow = new LinkedList<>();
            for (int i = 0; i < y; i++) {
                lookAheadBuffer.addLast(a.charAt(i));
            }
        }
        if (index >= a.length()) return ""; //close recursion

        String cycle = findMaxString(slidingWindow, lookAheadBuffer);
        if (cycle == null) {
            slidingWindow.addLast(lookAheadBuffer.get(0));
            encodedText.append(lookAheadBuffer.get(0));
            lookAheadBuffer.removeFirst();
            lookAheadBuffer.addLast(a.charAt(index));
            index++;
            if (slidingWindow.size() > x - y) {
                slidingWindow.removeFirst();
            }
            if (index >= a.length()) {
                index -= 2;
            }
        } else {
            encodedText.append("[");
            encodedText.append(cycle.replace(" ", "|"));
            encodedText.append("]");
            for (int i = 0; i < Integer.parseInt(cycle.split(" ")[1]); i++) {
                if (index >= a.length()) index -= y;

                if (slidingWindow.size() > x - y) {
                    slidingWindow.removeFirst();
                }
                slidingWindow.addLast(lookAheadBuffer.getFirst());
                lookAheadBuffer.addLast(a.charAt(index));
                lookAheadBuffer.removeFirst();
                index++;
            }

        }

        return encodedText + LZSS_ENCODE(a, x, y, lookAheadBuffer, slidingWindow, index);
    }
}
