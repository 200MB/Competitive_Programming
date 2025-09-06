import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class Main {
    static PrintWriter out = new PrintWriter(System.out, true);

    static Scanner ins = new Scanner(System.in);

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }


        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        long[] readArray(long n) {
            long[] a = new long[(int) n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }

        // 2 3 4 5 arr
        // 2 5 9 14 getPrefixSumOf(arr)
        static int[] prefixSumOf(int[] arr) {
            for (int i = 1; i < arr.length; i++) {
                arr[i] = arr[i] + arr[i - 1];
            }
            return arr;
        }


        static long[] prefixSumOf(long[] arr) {
            for (int i = 1; i < arr.length; i++) {
                arr[i] = arr[i] + arr[i - 1];
            }
            return arr;
        }

        static int[] reverseArray(int[] arr) {
            for (int i = 0; i < arr.length / 2; i++) {
                int temp = arr[i];
                arr[i] = arr[arr.length - i - 1];
                arr[arr.length - i - 1] = temp;
            }
            return arr;
        }


        //assumes arr is prefixSumArr
        static int getSumInRange(int l, int r, int[] arr) {
            if (l == 0) {
                return arr[r];
            }
            return arr[r] - arr[l - 1];
        }

        public static int calculateDiagonalSum(int[][] arr, int i1, int j1) {
            int count = arr[i1][j1];
            int diagi = i1, diagj = j1;
            while (true) {
                try {
                    diagi--;
                    diagj--;
                    count += arr[diagi][diagj];
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }
            diagi = i1;
            diagj = j1;
            while (true) {
                try {
                    diagi++;
                    diagj++;
                    count += arr[diagi][diagj];
                } catch (ArrayIndexOutOfBoundsException e1) {
                    break;
                }

            }
            diagi = i1;
            diagj = j1;
            while (true) {
                try {
                    diagi--;
                    diagj++;
                    count += arr[diagi][diagj];
                } catch (ArrayIndexOutOfBoundsException e1) {
                    break;
                }

            }
            diagi = i1;
            diagj = j1;
            while (true) {
                try {
                    diagi++;
                    diagj--;
                    count += arr[diagi][diagj];
                } catch (ArrayIndexOutOfBoundsException e1) {
                    break;
                }

            }
            return count;
        }

        public static int getSubStringAmount(String str, String substring) {
            int cnt = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.substring(i, i + substring.length()).equalsIgnoreCase(substring)) {
                    i = i + substring.length() - 1;
                    cnt++;
                }
                if (i + substring.length() == str.length()) break;
            }
            return cnt;
        }

        public static int getLongestSubstring(String str, String substring) {
            int cnt = 0;
            int maxCnt = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.substring(i, i + substring.length()).equalsIgnoreCase(substring)) {
                    i += substring.length() - 1;
                    cnt++;
                } else {
                    cnt = 0;
                }
                if (i + substring.length() == str.length()) break;
                maxCnt = Math.max(cnt, maxCnt);
            }
            maxCnt = Math.max(cnt, maxCnt);
            return (maxCnt);
        }

        public static String multiplyStrings(String str, int x, StringBuilder builder) {
            if (x == 0) {
                return builder.toString();
            }
            builder.append(str);
            return multiplyStrings(str, x - 1, builder);
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        boolean nextBoolean() {
            return Boolean.parseBoolean(next());
        }

    }

    static FastScanner in = new FastScanner();

    public static int countPalindromes(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        int counter = 0;
        Set<String> set = new HashSet<>();
        StringBuilder builder = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                StringBuilder revStr = new StringBuilder(builder.substring(i, j + 1));
                if (revStr.toString().equalsIgnoreCase(revStr.reverse().toString()) && !set.contains(revStr.toString())) {
                    set.add(revStr.toString());
                    counter++;
                }
            }
        }
        return counter;
    }


    //subFunction for createCombinations
    public static int[] addOne(int[] number, int index) {
        if (number[index] == 9 && index == 0) return number;
        if (number[index] == 9) {
            number[index] = 0;
            return addOne(number, index - 1);
        }
        ++number[index];
        return number;
    }

    //repeating numbers

    public static ArrayList<int[]> createCombinations(int[] number) {
        ArrayList<int[]> save = new ArrayList<>();
        double digit = Math.pow(10, number.length);
        save.add(number);
        for (int i = 0; i < digit; i++) {
            number = addOne(number, number.length - 1);
            save.add(Arrays.copyOf(number, number.length));
        }
        return save;

    }

    public static int countEdges(int[][] tree) {
        int counter = 0, edges = 0;
        int[] ignored = new int[tree.length];
        for (int i = 0; i < tree.length; i++) {
            for (int j = 0; j < tree[i].length; j++) {
                if (tree[i][j] != 0) {
                    int finalJ = j;
                    if (!Arrays.stream(ignored).anyMatch(e -> e == finalJ)) {
                        edges++;
                        ignored[counter++] = j;
                    }
                }
            }
        }
        return edges;
    }

    public static int[][] most_significant_edge(int[][] map) {
        int[] a = map[0], b = map[0];
        for (int i = 0; i < map.length; i++) {
            if (map[i].length >= b.length) {
                b = map[i];
                continue;
            }
            if (map[i].length >= a.length) {
                a = map[i];
            }
        }
        // first index is smallest size second is largest
        int[][] rer = new int[2][];
        rer[0] = a;
        rer[1] = b;
        return rer;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static int fact(int n) {
        int mul = 1;
        for (int i = 1; i <= n; i++) {
            mul *= i;
        }
        return mul;
    }

    public static void solve(int n) {

    }


    public static class ListNodeM {
        int val;
        ListNodeM next;

        ListNodeM() {
        }

        ListNodeM(int val) {
            this.val = val;
        }

        ListNodeM(int val, ListNodeM next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNodeM addTwoNumbers(ListNodeM l1, ListNodeM l2) {
        ListNodeM sum = new ListNodeM();
        ListNodeM summ = sum;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int a, b, c;
            a = l1 == null ? 0 : l1.val;
            b = l2 == null ? 0 : l2.val;
            c = a + b + carry;
            sum.val = c > 9 ? c - 10 : c;

            carry = a + b > 9 ? 1 : 0;
            sum.next = new ListNodeM();
            sum = sum.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        printS(summ);
        return sum;
    }

    public static void printS(ListNodeM a) {
        while (a != null) {
            System.out.print(a.val + " ");
            a = a.next;
        }
    }



    public static void main(String[] args) {

    }


}



