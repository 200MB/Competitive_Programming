import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FastScanner {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

    String next() {
        while (!st.hasMoreTokens())
            try {
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
        for (int i = 0; i < n; i++) a[i] = nextInt();
        return a;
    }

    // 2 3 4 5 arr
    // 2 5 9 14 getPrefixSumOf(arr)
    int[] prefixSumOf(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i] + arr[i - 1];
        }
        return arr;
    }

    int[] reverseArray(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
        return arr;
    }


    //assumes arr is prefixSumArr
    int getSumInRange(int l, int r, int[] arr) {
        if (l == 0) {
            return arr[r];
        }
        return arr[r] - arr[l - 1];
    }

    public static int calculateDiagonals(int[][] arr, int i1, int j1) {
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

    int binarySearch(Integer[] arr, int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            if (arr[mid] == x)
                return mid;


            if (arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);


            return binarySearch(arr, mid + 1, r, x);
        }


        return -1;
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    boolean nextBoolean() {
        return Boolean.parseBoolean(next());
    }
}

