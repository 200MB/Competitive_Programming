import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solutions extends Helpers {
    private static final FastScanner in = new FastScanner();
    private static final PrintWriter out = new PrintWriter(System.out, true);

    public static void vasya_and_coins(int a, int b) {
        //accepted
        if (a == 0) {
            System.out.println(1);
            return;
        }
        if (b == 0) {
            System.out.println(a + 1);
            return;
        }
        System.out.println(a + (b * 2) + 1);
    }


    public static void boredom() {
        //err on test 9
        //if one is more than others pick that else pick the biggest
        int points = 0;
        int pickedNumber = 0;
        int n = in.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            list.add(m);
            if (map.containsKey(m)) {
                map.put(m, map.get(m) + 1);
                continue;
            }

            map.put(m, 1);
        }
        while (list.size() > 0) {
            pickedNumber = Helpers.pickNext(list, map);
            int finalPickedNumber = pickedNumber;
            list.removeIf(n1 -> Objects.equals(n1, finalPickedNumber + 1));
            int finalPickedNumber1 = pickedNumber;
            list.removeIf(n1 -> Objects.equals(n1, finalPickedNumber1 - 1));
            map.remove(pickedNumber - 1);
            map.remove(pickedNumber + 1);
            map.put(pickedNumber, map.get(pickedNumber) - 1);
            list.remove((Integer) pickedNumber);
            points += pickedNumber;
        }
        System.out.println(points);
    }

    public static void tit_for_tat() {
        int t = in.nextInt();
        int[] arr = new int[0];
        for (int i = 0; i < t; i++) {
            int ind = 0;
            int n = 0;
            int k = 0;
            n = in.nextInt();
            k = in.nextInt();
            arr = new int[n];
            for (int j = 0; j < n; j++) {
                int tmp = in.nextInt();
                arr[j] = tmp;
            }
            for (int j = 0; j < k; j++) {
                if (arr[ind] > 0) {
                    arr[ind] -= 1;
                } else {
                    ind++;
                }
                arr[arr.length - 1] += 1;

            }
            Arrays.stream(arr).forEach(System.out::println);
        }


    }

    public static void string_building() {
        //accepted
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            boolean grant = true;
            int a = -1;
            int b = -1;
            String input = in.next();
            a = (int) input.chars().filter(e -> e == 'a').count();
            b = (int) input.chars().filter(e -> e == 'b').count();
            if (a == 1 || b == 1) {
                System.out.println("NO");
                continue;
            }
            for (int j = 1; j < input.length(); j++) {
                if (input.charAt(j) != input.charAt(j - 1)) {
                    if (j - 1 == 0 || j + 1 == input.length()) {
                        System.out.println("NO");
                        grant = false;
                        break;
                    }
                    if (input.charAt(j + 1) != input.charAt(j) && input.charAt(j - 2) != input.charAt(j)) {
                        System.out.println("NO");
                        grant = false;
                        break;
                    }
                }
            }
            if (grant) System.out.println("YES");
        }

    }

    public static void ZeroAndOneGame() {
        //accepted
        int moves;
        int n = in.nextInt();
        StringBuilder tmp;
        char lastChar;
        for (int i = 0; i < n; i++) {
            moves = 0;
            tmp = new StringBuilder(in.next());
            lastChar = tmp.charAt(0);
            for (int j = 0; j < tmp.length(); j++) {
                if (tmp.charAt(j) != lastChar) {
                    moves++;
                    tmp.deleteCharAt(tmp.indexOf(String.valueOf(lastChar)));
                    tmp.deleteCharAt(j - 1);
                    j = 0;
                }
                if (tmp.length() == 0) {
                    break;
                }
                lastChar = tmp.charAt(j);
            }
            if (moves % 2 != 0) System.out.println("DA");
            else System.out.println("NET");
        }

    }

    public static void fence() {
        //accepted rating:1200
        int n = in.nextInt();
        int k = in.nextInt();
        int max;
        int ind = 1, savedInd = 0;
        int count = 0, lastNum, firstNums;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        if (Arrays.stream(arr).allMatch(value -> value == arr[0]) || arr.length == 1 || n == k) {
            System.out.println(1);
            return;
        }
        if (k == 1) {
            System.out.println(Arrays.stream(arr).boxed().collect(Collectors.toList()).indexOf(Arrays.stream(arr).min().getAsInt()) + 1);
            return;
        }
        firstNums = arr[0];
        lastNum = arr[k];
        for (int i = 0; i < arr.length && i < k; i++) {
            count += arr[i];
        }
        max = count;
        int kdup = k;
        while (ind + k <= arr.length) {
            if (count - firstNums + lastNum < max) {
                max = count - firstNums + lastNum;
                savedInd = ind;
            }
            firstNums += arr[ind];
            count += lastNum;
            if (kdup + 1 >= arr.length) {
                break;
            }
            kdup += 1;
            lastNum = arr[kdup];
            ind++;
        }
        savedInd++;
        System.out.println(savedInd);
    }


    public static void ThreeDoors(int testKey) {
        if (testKey == 0) {
            return;
        }
        int keyNumber = in.nextInt() - 1;
        int[] doors = new int[3];
        doors[0] = in.nextInt();
        doors[1] = in.nextInt();
        doors[2] = in.nextInt();

        for (int i = 0; i < 3; i++) {
            int currentKey = doors[keyNumber];
            doors[keyNumber] = -1;
            keyNumber = currentKey - 1;
            if (currentKey == 0 && Arrays.stream(doors).allMatch(e -> e == -1)) {
                System.out.println("YES");
                break;
            } else if (currentKey == 0 && !Arrays.stream(doors).allMatch(e -> e == -1)) {
                System.out.println("NO");
                break;
            }
        }
        ThreeDoors(testKey - 1);
    }


    public static void TryMinecraft() {
        //TT HELL YEAH
        //PREFIX SUM
        int columns = in.nextInt();
        int quests = in.nextInt();
        int[] heights = in.readArray(columns); //was Integer
        long[] aToB = new long[columns];
        long[] bToA = new long[columns];
        int a, b;
        long damageCounter = 0;
        for (int i = 1; i < heights.length; i++) {
            if (heights[i - 1] > heights[i]) damageCounter += (heights[i - 1] - heights[i]);
            aToB[i] = damageCounter;
        }
        damageCounter = 0;
        for (int i = heights.length - 2; i >= 0; i--) {
            if (heights[i] < heights[i + 1]) damageCounter += (heights[i + 1] - heights[i]);
            bToA[i] = damageCounter;
        }
        for (int i = 0; i < quests; i++) {
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;
            out.println(Math.abs(a < b ? aToB[b] - aToB[a] : bToA[a] - bToA[b]));
        }
    }

    public static void cypher(int testCase) {
        if (testCase == 0) {
            return;
        }

        boolean a, b;
        int numberOfWheels = in.nextInt();
        int[] sequence = new int[numberOfWheels];
        for (int i = 0; i < numberOfWheels; i++) {
            sequence[i] = in.nextInt();
        }
        for (int i = 0; i < numberOfWheels; i++) {
            a = false;
            b = false;
            int delim = in.nextInt();
            String moves = in.next();

            for (int j = 0; j < moves.length(); j++) {
                if (moves.charAt(j) == 'U') {
                    if (sequence[i] - 1 < 0) {
                        sequence[i] = 9;
                        continue;
                    }
                    sequence[i] -= 1;
                }
                if (moves.charAt(j) == 'D') {
                    if (sequence[i] + 1 > 9) {
                        sequence[i] = 0;
                        continue;
                    }
                    sequence[i] += 1;
                }
            }

        }

        Arrays.stream(sequence).forEach(e -> System.out.print(e + " "));
        System.out.println();

        cypher(testCase - 1);
    }

    public static void perfectPerm(int testCase) {
        if (testCase == 0) {
            return;
        }

        int n = in.nextInt();

        int[] perm = new int[n];
        ArrayList<Integer> allNums = IntStream.rangeClosed(1, n).collect(ArrayList::new, List::add, List::addAll);
        for (int i = 1; i <= perm.length; i++) {
            int idup = i;
            int insert = allNums.stream().filter(e -> idup % e != 0).findFirst().orElse(allNums.stream().filter(e -> e != idup).findAny().orElse(idup));
            allNums.remove((Integer) insert);
            perm[i - 1] = insert;
        }

        Arrays.stream(perm).forEach(e -> System.out.print(e + " "));
        System.out.println();
        perfectPerm(testCase - 1);
    }

    public static void FAILbeautifulPicture() {
        int testcase = in.nextInt();
        for (int j = 0; j < testcase; j++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int namravli = n * m;
            int k = in.nextInt();
            List<Integer> pigments = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                pigments.add(in.nextInt());
            }
            boolean enough = pigments.stream().anyMatch(e -> e == namravli);
            int[] divisors = new int[pigments.size()];
            int divisor = pigments.get(0) % n == 0 ? n : m;
            if (enough) {
                System.out.println("Yes");
                continue;
            }
            for (int i = 0; i < pigments.size(); i++) {
                int number = pigments.get(i);
                divisors[i] = number / divisor;
            }

            divisors = Arrays.stream(divisors).sorted().toArray();

            if (divisors[0] * divisor * divisors.length == namravli) {
                System.out.println("Yes");
                continue;
            }

            System.out.println("No");

        }
    }

    public static void sleepRUNTIME() {
        int t = in.nextInt();
        int n;
        int h;
        int m;
        int h1;
        int m1;
        int toMin;
        int toMin1;
        boolean cont = false;
        ArrayList<Integer> min;
        for (int j = 0; j < t; j++) {
            min = new ArrayList<>();
            n = in.nextInt();
            h = in.nextInt();
            m = in.nextInt();
            toMin = (h * 60) + m;
            for (int i = 0; i < n; i++) {
                h1 = in.nextInt();
                m1 = in.nextInt();
                toMin1 = (h1 * 60) + m1;
                if (toMin == toMin1) {
                    out.println("0 0");
                    cont = true;
                    break;
                }
                min.add(toMin1);
            }
            if (cont) {
                cont = false;
                continue;
            }
            min.add(toMin);
            if (min.size() == 2) {
                int closestMin = Math.abs(toMin - min.get(0));
                out.println(closestMin / 60 + " " + (closestMin - (closestMin / 60) * 60));
                continue;
            }

            Collections.sort(min);
            if (min.indexOf(toMin) == min.size() - 1) {
                int left = 1440 - toMin;
                int closestMin = left + min.get(0);
                out.println(closestMin / 60 + " " + (closestMin - (closestMin / 60) * 60));
                continue;
            }

            int closestMin = Math.abs(min.get(min.indexOf(toMin) + 1) - toMin);
            out.println(closestMin / 60 + " " + (closestMin - (closestMin / 60) * 60));

        }
    }

    public static void prefix() {
        int t = in.nextInt();
        int n;
        int maxIndex;
        HashMap<Integer, Integer> hashMap;
        for (int j = 0; j < t; j++) {
            maxIndex = 0;
            hashMap = new HashMap<>();
            n = in.nextInt();
            for (int i = 0; i < n; i++) {
                int toAppend = in.nextInt();
                if (hashMap.containsKey(toAppend))
                    maxIndex = hashMap.get(toAppend) > maxIndex ? hashMap.get(toAppend) : maxIndex;
                hashMap.put(toAppend, i + 1);

            }
            out.println(maxIndex);

        }

    }

    public static void TwoThreeMoves(int n) {
        int count;
        if (n == 1) {
            System.out.println(2);
            return;
        }
        count = n / 3;
        count--;
        System.out.println(count + (n - 3 * count) / 2);
    }

    //solve(1, 1000000, 1,0); for guessing num from 1 to mil
    public static void numberGuesser(int start, int finish, int counter, int savedInt) {
        int mid = ((start + finish) / 2);

        out.println(mid);
        String hint = in.next();
        System.out.flush();
        if (mid == savedInt) counter++;
        if (counter == 2) {
            out.println("! " + mid);
            System.exit(1);
        }
        if (hint.equalsIgnoreCase("<")) {
            finish = mid;
        }
        if (hint.equalsIgnoreCase(">=")) {
            start = mid;
        }
        savedInt = mid;

        numberGuesser(start, finish, counter, savedInt);
    }

    public static void stringTask() {
        String str = in.next().toLowerCase(Locale.ROOT);
        List<Character> vowels = Arrays.asList('a', 'o', 'y', 'e', 'u', 'i');
        List<Character> arr = str.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        for (int i = 0; i < arr.size(); i++) {
            if (vowels.contains(arr.get(i))) {
                arr.remove(i);
                i -= 1;
            }
        }
        final int size = arr.size();
        for (int i = 0; i < size * 2; i += 2) {
            arr.add(i, '.');
        }

        for (Character character : arr) {
            System.out.print(character);
        }

    }

    public static void optimalRedFAIL() {
        int t = in.nextInt();
        int counter1;
        ArrayList<Integer> arr = new ArrayList<>();
        List<Integer> arr1;
        for (int j = 0; j < t; j++) {
            arr.clear();
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                arr.add(in.nextInt());
            }
            arr1 = arr.stream().sorted().collect(Collectors.toList());
            arr1.sort(Collections.reverseOrder());
            if (arr1.equals(arr)) {
                System.out.println("YES");
                continue;
            }
            counter1 = arr1.get(0);
            arr1 = arr;
            while (arr1.stream().noneMatch(e -> e == 0)) {
                arr1 = arr1.stream().mapToInt(e -> e -= 1).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            }
            if (arr1.subList(0, arr1.indexOf(0)).stream().max(Integer::compareTo).orElse(arr1.get(0)) + arr1.subList(arr1.indexOf(0), arr1.size()).stream().max(Integer::compareTo).get() > counter1) {
                System.out.println("No");
                continue;
            }

            System.out.println("Yes");
        }
    }

    public static void markThePhotographer() {
        ArrayList<Integer> arr;
        List<Integer> secondHalf;
        int t = in.nextInt();
        int count;
        int n;
        int x;
        for (int j = 0; j < t; j++) {
            count = 0;
            arr = new ArrayList<>();
            n = in.nextInt();
            x = in.nextInt();
            for (int i = 0; i < n * 2; i++) {
                arr.add(in.nextInt());
            }
            int finalX = x;
            Collections.sort(arr);
            secondHalf = arr.subList(n, arr.size());
            for (int i = 0; i < n; i++) {
                int ind = arr.get(i);
                int upper = secondHalf.stream().filter(e -> e >= ind + finalX).findAny().orElse(-1);
                if (upper != -1) {
                    secondHalf.remove((Integer) upper);
                    count++;
                }
            }
            out.println(count == n ? "YES" : "NO");
        }
    }

    public static void TwoBinarySequence() {
        int t = in.nextInt();
        int n, m;
        StringBuilder a;
        StringBuilder b;
        for (int j = 0; j < t; j++) {
            n = in.nextInt();
            m = in.nextInt();
            a = new StringBuilder(in.next());
            b = new StringBuilder(in.next());
            if (a.chars().filter(e -> e == '1').count() < b.chars().filter(e -> e == '1').count() || a.chars().filter(e -> e == '0').count() < b.chars().filter(e -> e == '0').count()) {
                out.println("NO");
                continue;
            }
            if (m == 1) {
                out.println("YES");
                continue;
            }
            int numToInsert;
            int numToFollow = b.charAt(0) - '0';
            while (a.length() >= b.length()) {
                if (a.toString().equalsIgnoreCase(b.toString())) {
                    out.println("YES");
                    break;
                }
                numToInsert = Math.min(a.charAt(0) - '0', a.charAt(1) - '0');
                if (numToFollow == 1) {
                    numToInsert = Math.max(a.charAt(0) - '0', a.charAt(1) - '0');
                }
                a.deleteCharAt(0);
                a.deleteCharAt(0);
                a.insert(0, numToInsert);
            }
            if (a.length() < b.length()) {
                out.println("NO");
            }

        }
    }

    public static void maxPairChain() {
        int[][] pairs = new int[8][2];
        for (int i = 0; i < pairs.length; i++) {
            pairs[i][0] = in.nextInt();
            pairs[i][1] = in.nextInt();
        }
        class Inner {
            int key, value;

            public Inner(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public int getKey() {
                return key;
            }

            public int getValue() {
                return value;
            }

            @Override
            public String toString() {
                return "Inner{" + "key=" + key + ", value=" + value + '}';
            }
        }

        ArrayList<Inner> classList = new ArrayList<>();
        for (int i = 0; i < pairs.length; i++) {
            classList.add(new Inner(pairs[i][0], pairs[i][1]));
        }
        final int[] max = {0};
        classList.sort(Comparator.comparing(Inner::getValue));
        int compare = classList.get(0).value;
        for (int i = 1; i < classList.size(); i++) {
            if (compare < classList.get(i).key) {
                compare = classList.get(i).value;
                max[0]++;
            }

        }
        System.out.println(max[0] + 1);
    }

    public static void divisibilityProblem() {
        int t = in.nextInt();
        long a, b;
        while (t > 0) {
            t--;
            a = in.nextLong();
            b = in.nextLong();
            if (a % b == 0) {
                System.out.println(0);
                continue;
            }
            if (a < b) {
                System.out.println(b - a);
                continue;
            }
            long div = a / b;
            System.out.println(Math.abs(a - (b * (div + 1))));

        }
    }

    public static void amusingJokes() {
        String a, b, c;
        a = in.next();
        b = in.next();
        c = in.next();
        if (c.length() != a.length() + b.length()) {
            System.out.println("NO");
            return;
        }
        List<Character> dashlili = (a + b).chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        for (int i = 0; i < c.length(); i++) {
            if (dashlili.contains(c.charAt(i))) {
                dashlili.remove((Character) c.charAt(i));
            }
        }
        System.out.println(dashlili.size() == 0 ? "YES" : "NO");
    }

    public static void iLoveUserName() {
        int n = in.nextInt();
        int max = 0, min = 0, amazingScore = 0;
        if (n == 1) {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < n; i++) {
            int number = in.nextInt();
            if (i == 0) {
                min = number;
                max = number;
                continue;
            }
            if (number > max) {
                amazingScore++;
                max = number;
            }
            if (number < min) {
                amazingScore++;
                min = number;
            }
        }
        System.out.println(amazingScore);
    }

    public static void dragon() {
        class Pair {
            int key;
            int value;

            public Pair(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public int getKey() {
                return key;
            }

            public int getValue() {
                return value;
            }
        }
        int s, n;
        s = in.nextInt();
        n = in.nextInt();
        ArrayList<Pair> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(new Pair(in.nextInt(), in.nextInt()));
        }
        arr.sort(Comparator.comparing(Pair::getKey));
        for (Pair pair : arr) {
            if (pair.key >= s) {
                System.out.println("NO");
                return;
            }
            s += pair.getValue();
        }
        System.out.println("YES");
    }

    public static void miracleOfSleeper() {
        int t = in.nextInt();
        long l, r;
        for (int i = 0; i < t; i++) {
            l = in.nextLong();
            r = in.nextLong();
            long max = Math.max(l, r);
            long maxRemainder = 0;
            if (r == 1) {
                System.out.println(0);
                continue;
            }
            long min = Math.max(max / 2, Math.min(l, r));
            for (long j = min; j < max; j++) {
                if (max % j < maxRemainder) {
                    break;
                }
                maxRemainder = max % j;
            }
            System.out.println(maxRemainder);
        }
    }

    public static void Xsum() {
        int t = in.nextInt();
        while (t > 0) {
            t--;
            int n, m;
            n = in.nextInt();
            m = in.nextInt();
            int[][] chess = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    chess[i][j] = in.nextInt();
                }
            }
            if (m == 1) {
                int shhax = 0;
                for (int i = 0; i < n; i++) {
                    if (chess[i][0] > shhax) {
                        shhax = chess[i][0];
                    }
                }
                System.out.println(shhax);
                continue;
            }
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int diag = Main.FastScanner.calculateDiagonalSum(chess, i, j);
                    if (diag > count) {
                        count = diag;
                    }
                }
            }
            System.out.println(count);

        }
    }

    public static void candy() {
        int t = in.nextInt(); //was Integer
        int n, q; //was Integer
        while (t > 0) {
            t--;
            n = in.nextInt();
            q = in.nextInt();
            int[] arr = new int[n]; //was Integer
            long sum = 0L;
            for (int i = 0; i < n; i++) {
                int num = in.nextInt();
                sum += num;
                arr[i] = num;
            }
            Collections.shuffle(Arrays.asList(arr));
            Arrays.sort(arr);
            arr = in.reverseArray(arr);
            int[] prefSum = in.prefixSumOf(arr); //was Integer
            for (int i = 0; i < q; i++) {
                long full = in.nextLong(); //was Long
                if (full > sum) {
                    out.println(-1);
                    continue;
                }
                int count = 0;
                int lef = 1, rig = n;
                while (lef <= rig) {
                    int mid = (lef + rig) / 2;
                    if (prefSum[mid - 1] >= full) {
                        count = mid;
                        rig = mid - 1;
                    } else {
                        lef = mid + 1;
                    }
                }
                out.println(count);

            }
        }
    }

    public static void crossMarket() {
        int t = in.nextInt();
        while (t > 0) {
            t--;
            int n, m, minimalEnergy;
            n = in.nextInt();
            m = in.nextInt();
            if (n == 1 & m == 1) {
                System.out.println(0);
                continue;
            }
            minimalEnergy = Math.max(n, m) + ((Math.min(n, m) - 1) * 2);
            System.out.println(minimalEnergy);
        }
    }

    public static void tempBeautifulArray() {
        int t = in.nextInt();
        while (t > 0) {
            t--;
            long n, k, b, s, p;
            n = in.nextLong();
            k = in.nextLong();
            b = in.nextLong();
            s = in.nextLong();
            if (s / n > b) {
                System.out.println(-1);
                continue;
            }
            if (s / k == b) {
                for (int j = 0; j < n - 1; j++) {
                    System.out.print(0 + " ");
                }
                System.out.print(s);
            }
            p = k * b;
            for (int i = 0; i < n - 2; i++) {
                System.out.print((s - p) / (n - 2) + " ");
            }
            System.out.print(Math.abs((s - p) - ((s - p) / (n - 2) * n - 2)) + " ");
            System.out.print(p);
            System.out.println();
        }
    }

    public static void fafa_and_his_company() {
        int n = in.nextInt();
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) count++;
        }
        System.out.println(count);
    }

    public static void spellCheck() {
        int t = in.nextInt();
        String name = "Timur";
        char[] chars = name.toCharArray();
        Arrays.sort(chars);
        name = Arrays.toString(chars);
        while (t > 0) {
            t--;
            int n = in.nextInt();
            String s = in.next();
            char[] chars1 = s.toCharArray();
            Arrays.sort(chars1);
            if (Arrays.toString(chars1).equals(name)) {
                out.println("YES");
                continue;
            }
            out.println("NO");

        }
    }

    public static void colorBlindness() {
        int t = in.nextInt();
        outer:
        while (t > 0) {
            t--;
            int n = in.nextInt();
            String a = in.next();
            String b = in.next();
            for (int i = 0; i < n; i++) {
                if (a.charAt(i) != b.charAt(i) && (a.charAt(i) == 'R' || b.charAt(i) == 'R')) {
                    out.println("NO");
                    continue outer;
                }
            }
            out.println("YES");
        }
    }

    public static void wordGame() {
        int t = in.nextInt();

        while (t > 0) {
            HashMap<String, Integer> allWords = new HashMap<>();
            ArrayList<String> list1 = new ArrayList<>();
            ArrayList<String> list2 = new ArrayList<>();
            ArrayList<String> list3 = new ArrayList<>();
            int count1 = 0, count2 = 0, count3 = 0;
            t--;
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                String s = in.next();
                list1.add(s);
                if (allWords.containsKey(s)) {
                    allWords.put(s, allWords.get(s) + 1);
                    continue;
                }
                allWords.put(s, 1);
            }
            for (int i = 0; i < n; i++) {
                String s = in.next();
                list2.add(s);
                if (allWords.containsKey(s)) {
                    allWords.put(s, allWords.get(s) + 1);
                    continue;
                }
                allWords.put(s, 1);
            }
            for (int i = 0; i < n; i++) {
                String s = in.next();
                list3.add(s);
                if (allWords.containsKey(s)) {
                    allWords.put(s, allWords.get(s) + 1);
                    continue;
                }
                allWords.put(s, 1);
            }
            for (int i = 0; i < n; i++) {
                if (allWords.get(list1.get(i)) == 1) count1 += 3;
                if (allWords.get(list1.get(i)) == 2) count1 += 1;
            }
            for (int i = 0; i < n; i++) {
                if (allWords.get(list2.get(i)) == 1) count2 += 3;
                if (allWords.get(list2.get(i)) == 2) count2 += 1;
            }
            for (int i = 0; i < n; i++) {
                if (allWords.get(list3.get(i)) == 1) count3 += 3;
                if (allWords.get(list3.get(i)) == 2) count3 += 1;
            }
            out.println(count1 + " " + count2 + " " + count3);
        }
    }

    public static void perfectPermutation() {
        int t = in.nextInt();
        while (t > 0) {
            t--;
            int r = in.nextInt();
            int[] arr = IntStream.rangeClosed(1, r).toArray();
            if (r % 2 == 0) {
                for (int i = 1; i < arr.length; i += 2) {
                    try {
                        System.out.print(arr[i] + " " + arr[i - 1] + " ");
                    } catch (ArrayIndexOutOfBoundsException ignored) {

                    }

                }
                continue;
            }
            for (int i = 1; i < arr.length; i++) {
                System.out.print(arr[i] + " ");

            }
            System.out.println(arr[0]);

        }
    }

    public static void juicer() {
        int n, b, d;
        n = in.nextInt();
        b = in.nextInt();
        d = in.nextInt();
        int[] arr = in.readArray(n);
        int[] arr1 = Arrays.stream(arr).filter(e -> e <= b).toArray();
        if (arr1.length == 0) {
            System.out.println(0);
            return;
        }
        int count = arr1[0], waste = 0;
        for (int i = 1; i < arr1.length; i++) {
            if (count + arr1[i] > d) {
                count = 0;
                waste++;
                continue;
            }
            count += arr1[i];
        }
        System.out.println(waste);

    }

    public static void amoeba() {
        //https://atcoder.jp/contests/abc274/tasks/abc274_c
        long n = in.nextLong();
        long[] arr = in.readArray(n);

        ArrayList<Map.Entry<Long, Map.Entry<Long, Long>>> mapList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            long leftNode = 2L * (i + 1);
            long rightNode = leftNode + 1;
            mapList.add(new AbstractMap.SimpleEntry<>(arr[i], new AbstractMap.SimpleEntry<>(leftNode, rightNode)));
        }
        for (int k = 1; k <= (2 * n) + 1; k++) {
            if (k == 1) {
                out.println(0);
                continue;
            }

            //find currentParent
            long currentParent = -1, generation = 1;
            for (Map.Entry<Long, Map.Entry<Long, Long>> longEntryEntry : mapList) {
                if (longEntryEntry.getValue().getKey() == k || longEntryEntry.getValue().getValue() == k) {
                    currentParent = longEntryEntry.getKey();
                    break;
                }

            }

            //find generation from 1 to k (currentParent)
            while (currentParent != 1) {
                for (Map.Entry<Long, Map.Entry<Long, Long>> longEntryEntry : mapList) {
                    if (longEntryEntry.getValue().getKey() == currentParent || longEntryEntry.getValue().getValue() == currentParent) {
                        currentParent = longEntryEntry.getKey();
                        generation++;
                        break;
                    }
                }

            }
            out.println(generation);
        }

    }

    public static void trafficLight() {
        //https://codeforces.com/contest/1744/problem/C
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            long n = in.nextLong();
            char currentLight = in.next().charAt(0);
            String s = in.next();
            long[] currentLightLoc = new long[(int) s.chars().filter(e -> e == currentLight).count()];
            long[] greenLightLoc = new long[(int) s.chars().filter(e -> e == 'g').count()];
            int index = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == currentLight) {
                    currentLightLoc[index] = j;
                    index++;
                }
            }
            index = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == 'g') {
                    greenLightLoc[index] = j;
                    index++;
                }
            }
            long max = 0;
            for (long l : currentLightLoc) {
                long min = Integer.MAX_VALUE;
                long totalDistance;
                for (long greenInd : greenLightLoc) {
                    totalDistance = Math.abs(l - greenInd);
                    if (greenInd < l) {
                        totalDistance = s.length() - l + greenInd;
                    }
                    if (totalDistance < min) min = totalDistance;

                }
                if (min > max) {
                    max = min;
                }
            }
            System.out.println(max);
        }
    }

    public static void biggestBinaryGap() {
        long n = in.nextLong();
        StringBuilder s = new StringBuilder();
        while (n > 0) {
            s.append(n % 2);
            n /= 2;
        }
        String binary = s.reverse().toString();
        String[] split = binary.split("1");
        var a = Arrays.stream(split).sorted().toArray();
        System.out.println(a[a.length - 1]);
    }

    //https://codeforces.com/problemset/problem/1754/A
    public static void technicalSupport() {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int throwAway = in.nextInt();
            String s = in.next();
            if (s.chars().filter(e -> e == 'Q').count() > s.chars().filter(e -> e == 'A').count()) {
                System.out.println("No");
                continue;
            }
            int questions = 0;
            for (int j = 0; j < throwAway; j++) {
                if (s.charAt(j) == 'Q') questions++;
                if (s.charAt(j) == 'A' && questions > 0) questions--;
            }
            if (questions == 0) {
                System.out.println("Yes");
                continue;
            }
            System.out.println("No");

        }
    }

    //https://codeforces.com/problemset/problem/4/C
    public static void database() {
        HashMap<String, Integer> database = new HashMap<>();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            String name = in.next();
            int num;
            try {
                num = database.get(name);
                database.put(name, ++num);
                System.out.println(name + num);
            } catch (NullPointerException e) {
                System.out.println("OK");
                database.put(name, 0);
            }

        }
    }

    //https://codeforces.com/contest/1795/problem/A
    public static void twoTowers() {
        int t = in.nextInt();
        testcase:
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            String a = in.next();
            String b = in.next();
            for (int j = 1; j < n; j++) {
                if (a.charAt(j) == a.charAt(j - 1)) {
                    StringBuilder builder = new StringBuilder(b);
                    StringBuilder reverse = new StringBuilder(a.substring(j));
                    builder.append(reverse.reverse());

                    for (int k = 1; k < builder.length(); k++) {
                        if (builder.charAt(k) == builder.charAt(k - 1)) {
                            System.out.println("No");
                            continue testcase;
                        }
                    }

                }
            }
            for (int j = 1; j < m; j++) {
                if (b.charAt(j) == b.charAt(j - 1)) {
                    StringBuilder builder = new StringBuilder(a);
                    StringBuilder reverse = new StringBuilder(b.substring(j));
                    builder.append(reverse.reverse());
                    for (int k = 1; k < builder.length(); k++) {
                        if (builder.charAt(k) == builder.charAt(k - 1)) {
                            System.out.println("No");
                            continue testcase;
                        }
                    }

                }
            }

            System.out.println("Yes");
        }

    }

    //https://codeforces.com/problemset/problem/1807/B
    public static void grabTheCandies() {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int[] arr = in.readArray(n);
            int evenSum = Arrays.stream(arr).filter(e -> e % 2 == 0).sum();
            int oddSum = Arrays.stream(arr).filter(e -> e % 2 != 0).sum();
            if (evenSum > oddSum) {
                System.out.println("YES");
                continue;
            }
            System.out.println("NO");

        }
    }

    //https://atcoder.jp/contests/abc320/tasks/abc320_b
    public static int longestPalindromee(String str){
        int ind = 0;
        while (str.length() != 0) {
            StringBuilder builder = new StringBuilder(str);
            if (str.contentEquals(builder.reverse())) {
                return str.length();
            }
            str = str.substring(0, str.length() - (ind++));
        }
        return 0;
    }


//https://codeforces.com/problemset/problem/996/A
    public static void hitTheLottery(){
        int n = in.nextInt();
        // 100 20 10 5 1 125
        int amount = 0;
        int current = n;
        amount += current / 100;
        current = current % 100;
        amount += current / 20;
        current = current % 20;
        amount += current / 10;
        current = current % 10;
        amount += current / 5;
        current = current % 5;
        amount += current;

        System.out.println(amount);
    }


    //twoSum
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        int n = 0;
        int[] ans = new int[2];
        int ignore = 0;
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(target - nums[i])){
                ans[1] = i;
                ignore = i;
                n = target - nums[i];
            }

            hash.put(nums[i], target - nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == n && ignore != i){
                ans[0] = i;
            }
        }

        return ans;
    }


}
