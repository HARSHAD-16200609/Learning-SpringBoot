import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>(Arrays.asList(50, 70, 60));

        log("Initial list: " + list);
        log("isEmpty: " + list.isEmpty());
        log("size: " + list.size());

        // add(E)
        list.add(80);
        log("After add(80): " + list);

        // add(int, E)
        list.add(1, 55);
        log("After add(1, 55): " + list);

        // get(int)
        log("Element at index 2: " + list.get(2));

        // set(int, E)
        list.set(2, 65);
        log("After set(2, 65): " + list);

        // contains(Object), indexOf(Object), lastIndexOf(Object)
        log("Contains 70: " + list.contains(70));
        log("Index of 70: " + list.indexOf(70));
        log("Last index of 70: " + list.lastIndexOf(70));

        // addAll(Collection)
        list.addAll(Arrays.asList(90, 100));
        log("After addAll([90, 100]): " + list);

        // addAll(int, Collection)
        list.addAll(2, Arrays.asList(58, 59));
        log("After addAll(2, [58, 59]): " + list);

        log("Contains 55 and 65: " + (list.contains(55) && list.contains(65)));

        // remove(int) removes by index
        list.remove(1);
        log("After remove(1): " + list);

        // remove(Object) removes by value
        list.remove(Integer.valueOf(90));
        log("After remove(Integer.valueOf(90)): " + list);

        // removeAll(Collection)
        list.removeAll(Arrays.asList(58, 100));
        log("After removeAll([58, 100]): " + list);

        // retainAll(Collection)
        list.retainAll(Arrays.asList(50, 55, 65, 80));
        log("After retainAll([50, 55, 65, 80]): " + list);

        // listIterator() and listIterator(int)
        StringBuilder iteratorOutput = new StringBuilder("Forward using iterator: ");
        Iterator<Integer> iterator = list.iterator();
        iterator.forEachRemaining(value -> iteratorOutput.append(value).append(' '));
        log(iteratorOutput.toString().trim());

        StringBuilder listIteratorOutput = new StringBuilder("Using listIterator from index 1: ");
        ListIterator<Integer> listIterator = list.listIterator(1);
        listIterator.forEachRemaining(value -> listIteratorOutput.append(value).append(' '));
        log(listIteratorOutput.toString().trim());

        // subList(int, int)
        log("SubList(0, 2): " + list.subList(0, Math.min(2, list.size())));

        // replaceAll and sort
        list.replaceAll(n -> n + 1);
        log("After replaceAll(n -> n + 1): " + list);

        list.sort(Integer::compareTo);
        log("After sort(): " + list);

        // toArray()
        Object[] array = list.toArray();
        log("toArray(): " + Arrays.toString(array));

        // clear() and isEmpty()
        list.clear();
        log("After clear(): " + list);
        log("isEmpty after clear: " + list.isEmpty());

        // Custom linked list demo
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(20);
        linkedList.addLast(40);
        linkedList.add(60);
        log("Custom LinkedList after adds: " + linkedList);
        log("Custom LinkedList size: " + linkedList.size());

        linkedList.insert(1, 30);
        log("After insert(1, 30): " + linkedList);

        log("Element at index 2: " + linkedList.get(2));
        log("set(2, 35) old value: " + linkedList.set(2, 35));
        log("After set(2, 35): " + linkedList);

        log("Contains 40: " + linkedList.contains(40));
        log("Index of 35: " + linkedList.indexOf(35));

        log("removeAt(1): " + linkedList.removeAt(1));
        log("After removeAt(1): " + linkedList);

        log("remove(60): " + linkedList.remove(60));
        log("After remove(60): " + linkedList);

        linkedList.clear();
        log("Custom LinkedList after clear(): " + linkedList);
        log("Custom LinkedList isEmpty: " + linkedList.isEmpty());
    }

    private static void log(String message) {
        LOGGER.info(message);
    }
}