import java.util.*;

public class comparater {
        public static void main(String[] args) {

            List<Integer> list = new ArrayList<>();
            list.add(10);
            list.add(50);
            list.add(30);
            list.add(90);
            Collections.sort(list,(a,b)->b-a);
          // b-a descending order
          //a-b ascending order
            System.out.println(list);
            Set<Integer> s = new TreeSet<>();
            s.add(22);
            s.add(1);
            s.add(13);
            System.out.println("After sorting desc: " + s);
            Set<Integer> ss = new TreeSet<>((a, b) -> b - a);
            ss.add(22);
            ss.add(1);
            ss.add(13);
            System.out.println("After manual sorting desc: " + ss);

        }
}
