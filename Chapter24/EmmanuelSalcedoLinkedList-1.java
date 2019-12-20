/*
 * Emmanuel Salcedo
 * LinkedLists
 * Ch 24: Implementing Lists
 */
public class EmmanuelSalcedoLinkedList<E> extends MyAbstractList<E> {
	public static void main(String[] args) {
		//initialize the String LinkedList
		EmmanuelSalcedoLinkedList<String> list = new EmmanuelSalcedoLinkedList<>();
		//add 10 names to the linked list
		list.add("Emmanuel");
		System.out.println(list);
		list.add("Mike");
		System.out.println(list);
		list.add("Jocelyn");
		System.out.println(list);
		list.add("Vicente");
		System.out.println(list);
		list.add("Jake");
		System.out.println(list);
		list.add("JackSparrow");
		System.out.println(list);
		list.add("Mike");
		System.out.println(list);
		list.add("Avery");
		System.out.println(list);
		list.add("Nano");
		System.out.println(list);
		list.add("Christian");
		//print linkedList
		System.out.println(list);
		
		//Remove index 3 and print
		list.remove(3);
		System.out.println("Removed Index 3: "+list);
		
		//Remove last Index
		list.removeLast();
		System.out.println("RemovedLast:  "+list);
		//Contains Test
		System.out.println("Contains Nano: "+list.contains("Nano"));
		//Get TEst
		System.out.println("Get Index 3: "+list.get(3));
		//IndedOf Test
		System.out.println("Index of Mike: "+ list.indexOf("Mike"));
		//lastIndexof
		System.out.println("Last Index of Mike: "+list.lastIndexOf("Mike"));
		//Set Test
		System.out.println(list.set(0, "Ashley"));
		System.out.println(list);
	}

	private Node<E> head, tail;

	/** Create a default list */
	public EmmanuelSalcedoLinkedList() {
	}

	/** Create a list from an array of objects */
	public EmmanuelSalcedoLinkedList(E[] objects) {
		super(objects);
	}

	/** Return the head element in the list */
	public E getFirst() {
		if (size == 0) {
			return null;
		} else {
			return head.element;
		}
	}

	/** Return the last element in the list */
	public E getLast() {
		if (size == 0) {
			return null;
		} else {
			return tail.element;
		}
	}

	/** Add an element to the beginning of the list */
	public void addFirst(E e) {
		Node<E> newNode = new Node<E>(e); // Create a new node
		newNode.next = head; // link the new node with the head
		head = newNode; // head points to the new node
		size++; // Increase list size

		if (tail == null) // the new node is the only node in list
			tail = head;
	}

	/** Add an element to the end of the list */
	public void addLast(E e) {
		Node<E> newNode = new Node<E>(e); // Create a new for element e

		if (tail == null) {
			head = tail = newNode; // The new node is the only node in list
		} else {
			tail.next = newNode; // Link the new with the last node
			tail = tail.next; // tail now points to the last node
		}

		size++; // Increase size
	}

	@Override /**
				 * Add a new element at the specified index in this list. The index of the head
				 * element is 0
				 */
	public void add(int index, E e) {
		if (index == 0) {
			addFirst(e);
		} else if (index >= size) {
			addLast(e);
		} else {
			Node<E> current = head;
			for (int i = 1; i < index; i++) {
				current = current.next;
			}
			Node<E> temp = current.next;
			current.next = new Node<E>(e);
			(current.next).next = temp;
			size++;
		}
	}

	/**
	 * Remove the head node and return the object that is contained in the removed
	 * node.
	 */
	public E removeFirst() {
		if (size == 0) {
			return null;
		} else {
			Node<E> temp = head;
			head = head.next;
			size--;
			if (head == null) {
				tail = null;
			}
			return temp.element;
		}
	}

	/**
	 * Remove the last node and return the object that is contained in the removed
	 * node.
	 */
	public E removeLast() {
		if (size == 0) {
			return null;
		} else if (size == 1) {
			Node<E> temp = head;
			head = tail = null;
			size = 0;
			return temp.element;
		} else {
			Node<E> current = head;

			for (int i = 0; i < size - 2; i++) {
				current = current.next;
			}

			Node<E> temp = tail;
			tail = current;
			tail.next = null;
			size--;
			return temp.element;
		}
	}

	@Override /**
				 * Remove the element at the specified position in this list. Return the element
				 * that was removed from the list.
				 */
	public E remove(int index) {
		if (index < 0 || index >= size) {
			return null;
		} else if (index == 0) {
			return removeFirst();
		} else if (index == size - 1) {
			return removeLast();
		} else {
			Node<E> previous = head;

			for (int i = 1; i < index; i++) {
				previous = previous.next;
			}

			Node<E> current = previous.next;
			previous.next = current.next;
			size--;
			return current.element;
		}
	}

	@Override /** Override toString() to return elements in the list */
	public String toString() {
		StringBuilder result = new StringBuilder("[");

		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if (current != null) {
				result.append(", "); // Separate two elements with a comma
			} else {
				result.append("]"); // Insert the closing ] in the string
			}
		}

		return result.toString();
	}

	@Override /** Clear the list */
	public void clear() {
		size = 0;
		head = tail = null;
	}

	@Override /** Return true if this list contains the element e */
	public boolean contains(E e) {
		if (size == 0) // check the size of the list if empty return false
			return false;
		else {
			// traverse the list starting at the head and moving to the tail
			Node<E> current = head;
			while (current != null) {
				// if the element exists return true and exit
				if (current.element == e)
					return true;
				// move on to the next value in the list
				current = current.next;
			}
		}
		// return false if is not in the list
		return false;
	}

	@Override /** Return the element at the specified index */
	public E get(int index) {
		if (index < 0 || index >= size)
			return null; // if the index is outside the range of the list
		else if (index == 0)// if the index is 0 get the first value
			return getFirst();
		else if (index == size - 1)// if it is the end of the list get the lastvalue
			return getLast();
		else { // traverse the list until you reach the index desired
			Node<E> current = head.next;// start at the head
			for (int i = 1; i < index; i++)
				current = current.next;
			return current.element;
		}
	}

	@Override /**
				 * Return the index of the head matching element in this list. Return -1 if no
				 * match.
				 */
	public int indexOf(E e) {
		if (head.element == e)
			return 0;// if the head element is e then return 0
		else if (tail.element == e)
			return size - 1; // if e is the tail return the index of the end
		else {
			Node<E> current = head.next; // loop through the list while there is still a next item
			int index = 1;
			while (current != null) { // check if the element is the one being looked for
				if (current.element == e) // if the same element return index
					return index;
				current = current.next;
				index++; // increment the current index and move to the next value
			}
		}
		return -1; // if it does not exist return a -1 to show no match
	}

	@Override /**
				 * Return the index of the last matching element in this list. Return -1 if no
				 * match.
				 */
	public int lastIndexOf(E e) {
		int index = -1; // if there is no index of e return -1 to show no instance of element e
		Node<E> current = head; // Set current node to start at the head
		for (int i = 0; i < size; i++) { // loop to the end of the list looking for another instance of e
			if (current.element == e) // if another instance of e is found set that index to the index
				index = i;
			current = current.next;
		}

		return index; // return the index of the last instance of e
	}

	@Override /**
				 * Replace the element at the specified position in this list with the specified
				 * element.
				 */
	public E set(int index, E e) {
		if (index < 0 || index > size - 1)
			return null; // if the index is outside the bounds of the list
		else {
			Node<E> current = head; // start at the head of the list
			for (int i = 0; i < index; i++) // loop though the list until you reach the index
				current = current.next;

			current.element = e; // set the element in that index as E and return the current element
			return current.element;
		}
	}

	@Override /** Override iterator() defined in Iterable */
	public java.util.Iterator<E> iterator() {
		return new LinkedListIterator();
	}

	private void checkIndex(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	}

	private class LinkedListIterator implements java.util.Iterator<E> {
		private Node<E> current = head; // Current index

		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public E next() {
			E e = current.element;
			current = current.next;
			return e;
		}

		public E remove(int index) {
			if (index < 0 || index >= size) { // check for bounds
				return null;
			} else if (index == 0) { // if head remove the head
				return removeFirst();
			} else if (index == size - 1) { // if the tail remove the tail
				return removeLast();
			} else { // loop though the list to find the index
				Node<E> previous = head;

				for (int i = 1; i < index; i++) {
					previous = previous.next;
				}

				Node<E> current = previous.next; // remove the element for the index
				previous.next = current.next;
				size--; // reduce size
				return current.element;
			}
		}

	}

	private static class Node<E> {
		E element;
		Node<E> next;

		public Node(E element) {
			this.element = element;
		}
	}
}

