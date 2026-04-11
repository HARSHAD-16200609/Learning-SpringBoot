import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Streams {
    static int sum1 ;
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum = 0;

        for (int i : array) {
            sum += i;
        }
        System.out.println("Sum using for loop: " + sum);



        Arrays.stream(array).forEach((i)->{
            sum1 += i;

      });
        System.out.println("Sum using stream: " + sum1);

        List<String> List = Arrays.asList("apple", "banana", "cherry");
        Stream<String> myStream = List.stream();
        String[] arr_String = {"apple", "banana", "cherry"};
        Stream<String> stream = Arrays.stream(arr_String);
        Stream<Integer> integerStream = Stream.of( 1, 2, 3);
        Stream<Integer> Limit = Stream.iterate( 0, n -> n + 1).limit(  100);
    }

}