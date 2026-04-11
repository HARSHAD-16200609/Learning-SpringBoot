import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> {

	private static final class Node<E> {
		private E data;
		private Node<E> next;

		private Node(E data) {
			this.data = data;
		}
	}

	private Node<T> head;
	private Node<T> tail;
	private int size;

	public void addFirst(T data) {
		Node<T> newNode = new Node<>(data);
		newNode.next = head;
		head = newNode;

		if (tail == null) {
			tail = newNode;
		}

		size++;
	}

	public void addLast(T data) {
		Node<T> newNode = new Node<>(data);

		if (tail == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}

		size++;
	}

	public void add(T data) {
		addLast(data);
	}

	public void insert(int index, T data) {
		checkPositionIndex(index);

		if (index == 0) {
			addFirst(data);
			return;
		}

		if (index == size) {
			addLast(data);
			return;
		}

		Node<T> previous = nodeAt(index - 1);
		Node<T> newNode = new Node<>(data);
		newNode.next = previous.next;
		previous.next = newNode;
		size++;
	}

	public T removeFirst() {
		ensureNotEmpty();

		T data = head.data;
		head = head.next;
		size--;

		if (head == null) {
			tail = null;
		}

		return data;
	}

	public T removeLast() {
		ensureNotEmpty();

		if (size == 1) {
			return removeFirst();
		}

		Node<T> previous = nodeAt(size - 2);
		T data = tail.data;
		previous.next = null;
		tail = previous;
		size--;
		return data;
	}

	public T removeAt(int index) {
		checkElementIndex(index);

		if (index == 0) {
			return removeFirst();
		}

		if (index == size - 1) {
			return removeLast();
		}

		Node<T> previous = nodeAt(index - 1);
		Node<T> current = previous.next;
		previous.next = current.next;
		size--;
		return current.data;
	}

	public boolean remove(T data) {
		if (head == null) {
			return false;
		}

		if (Objects.equals(head.data, data)) {
			removeFirst();
			return true;
		}

		Node<T> current = head;
		while (current.next != null) {
			if (Objects.equals(current.next.data, data)) {
				if (current.next == tail) {
					tail = current;
				}
				current.next = current.next.next;
				size--;
				return true;
			}
			current = current.next;
		}

		return false;
	}

	public T get(int index) {
		checkElementIndex(index);
		return nodeAt(index).data;
	}

	public T set(int index, T data) {
		checkElementIndex(index);
		Node<T> target = nodeAt(index);
		T oldValue = target.data;
		target.data = data;
		return oldValue;
	}

	public int indexOf(T data) {
		int index = 0;
		Node<T> current = head;

		while (current != null) {
			if (Objects.equals(current.data, data)) {
				return index;
			}
			current = current.next;
			index++;
		}

		return -1;
	}

	public boolean contains(T data) {
		return indexOf(data) != -1;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[");
		Node<T> current = head;

		while (current != null) {
			builder.append(current.data);
			current = current.next;
			if (current != null) {
				builder.append(", ");
			}
		}

		return builder.append(']').toString();
	}

	private Node<T> nodeAt(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private void ensureNotEmpty() {
		if (isEmpty()) {
			throw new NoSuchElementException("LinkedList is empty");
		}
	}

	private void checkElementIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}

	private void checkPositionIndex(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}
}
