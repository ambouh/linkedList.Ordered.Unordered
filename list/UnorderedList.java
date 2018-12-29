/*NAME: Andres Mbouh
 *COURSE: CMSC132 - 0203
 *DUE: 3/09/2015
 *
 *HONOR PLEDGE: I pledge on my honor that I have not given or received any 
 *unauthorized assistance on this assignment/examination. 
 *
 *PURPOSE: This class creates an unordered linked list. it's purpose is to store
 *data that uses an unlimited amount space, and have a list that is able to store 
 *an unlimited amount of elements. The class implements Iterable<T> which has a 
 *method, iterator(): used to iterate and return the element the list stores. 
 *it also implements the comparable interface, which method compareTo() is used 
 *to compare current list with another unordered list. The class has an inner 
 *class Node which handles all T element and has a reference to the next 
 *element.**/
package list;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;

public class UnorderedList<T> implements Iterable<T>,
		Comparable<UnorderedList<T>> {
	protected class Node<D> {
		D data;
		Node<D> next;

		public Node(D data) {
			this.data = data;
			next = null;
		}
	}

	// PRIVATE FIELDS
	protected Comparator<T> comparator;
	protected Node<T> head;

	public UnorderedList(Comparator<T> comparator) {
		this.comparator = comparator;
		head = null;
	}

	/*
	 * add() appends element in passed in parameter to the end of the unordered
	 * object list
	 */
	public void add(T newElt) {
		Node<T> current = head, prev = null, n;

		while (current != null) { // iterates over list to get to the end
			prev = current;
			current = current.next;
		}

		n = new Node<T>(newElt); // assigns new element to a node
		if (current == head) { // inserts the node to a new list
			n.next = head;
			head = n;
		} else {// inserts the node to the end of the list
			prev.next = n;
			n.next = current;
		}
	}

	/* length() returns the number of elements in the current object list */
	public int length() {
		int count = 0;
		Node<T> current = head;

		while (current != null) {
			// increment the count
			count++;
			// iterates over next element
			current = current.next;
		}

		return count;
	}

	/*
	 * clear() removes all elements from the current object list while still
	 * making it possible to add elements
	 */
	public void clear() {
		head = null;
	}

	/*
	 * toString() returns a String representation of the current object list.
	 * each values will be followed by a blank space. No space in front or at
	 * the end of the String. if empty, method returns a ""
	 */
	public String toString() {
		String str = "";
		Node<T> current = head;

		while (current != null) { // iterates through list
			// appends the current element in representation of String
			str += current.data.toString();
			current = current.next;

			if (current != null) {// determines if space is necessary
				str += " ";
			}

		}
		return str;
	}

	/* elementAtPos() returns an object at a particular index. Starting from 0 */
	public T elementAtPos(int index) throws IndexOutOfBoundsException {
		int position = 0;
		Node<T> current = head;

		if ((index < this.length()) && (index > -1)) {// checks boundaries.
			while (position < index) { // increments position and current
										// element
				// if less than index.
				current = current.next;
				position++;
			}
			// if element at particular index is found, it returns it.
			return current.data;
		}

		// throws exception if index passed-in is unbounded
		throw new IndexOutOfBoundsException();
	}

	/*
	 * countElement() returns number of occurrence T element shows up in list.
	 * maybe 0 and up
	 */
	public int countElement(T element) {
		int count = 0;

		for (T elm : this) {
			if (comparator.compare(elm, element) == 0)
				count++;
		}

		return count;
	}

	/*
	 * append() appends otherList to end of current list w/o modifying it. -
	 * lists must be totally independent from each other after changing current
	 * list. otherList should be the same and independent.
	 */
	public void append(UnorderedList<T> otherList) {
		// initiating a new list
		UnorderedList<T> newList = new UnorderedList<T>(otherList.comparator);

		// duplicating otherList
		for (T elm : otherList)
			newList.add(elm);

		Node<T> previous = null;
		Node<T> current = head;

		// iterating to the end of the list
		while (current != null) {
			previous = current;
			current = current.next;
		}

		// appending last linked element to the head of the new list
		previous.next = newList.head;

	}

	/*
	 * compareTo() compares two lists elements one at a time.return 0, if same
	 * order, same height, same element(that compare identically).return -1, if
	 * lists aren't identical but the first current element is lessthan first
	 * element of passed-in list or if the lists are identical butpassed-in list
	 * is longer*return -1, if lists aren't identical but the first current
	 * element isgreater than first element of passed-in list or if the lists
	 * are identicalbut current list is longer
	 */
	public int compareTo(UnorderedList<T> otherList) {
		Node<T> firstCurrent = this.head;
		Node<T> scndCurrent = otherList.head;
		int result;
		while ((firstCurrent != null) && (scndCurrent != null)) {
			result = comparator.compare(firstCurrent.data, scndCurrent.data);
			if (result < 0)
				return -1;
			else if (result > 0)
				return 1;

			firstCurrent = firstCurrent.next;
			scndCurrent = scndCurrent.next;
		}
		// if list is identical thus far, compare by size
		if (this.length() < otherList.length())
			return -1;
		else if (this.length() > otherList.length())
			return 1;

		return 0;
	}

	/*
	 * iterator() returns an iterator capable of iterating through elements in
	 * the current list
	 */
	public Iterator<T> iterator() {
		return new ListIterator<T>();
	}

	/*
	 * removeNumOccurences() - removes number of occurrence T element shows up
	 * in list
	 */
	public int removeNumOccurrences(T element, int num) {
		int numAvail = this.countElement(element); // amount of element
													// available
		int numExact = 0; // keeps actual # taken off

		if (num < numAvail) // remove first ones, if num less than available
							// amount
			numExact = num;
		else
			numExact = numAvail;

		// removes all T element(s)
		for (int i = numExact; i > 0; i--)
			removeElement(element);

		return numExact;
	}

	/*
	 * removeRange() removes elements in-between fromPos to toPos in the current
	 * Object list
	 */
	public void removeRange(int fromPos, int toPos) {
		int size = this.length();
		fromPos = (fromPos < 0 && toPos != 0) ? 0 : fromPos;
		toPos = (toPos > size && fromPos != size) ? (size - 1) : toPos;

		// takes care of illegal constraints
		if (!(fromPos > toPos) && !(fromPos < 0) && !(toPos > size)) {
			for (int i = toPos; i >= fromPos; i--)
				removeElement(this.elementAtPos(i));
		}

	}

	/* HELPER METHODS */

	/* removeElement() removes T element from current object list */
	private boolean removeElement(T element) {
		Node<T> previous = null;
		Node<T> current = head;

		for (T elm : this) {
			if (comparator.compare(elm, element) == 0) {
				if (elm == head.data) // deletes first element
					head = head.next;
				else if (current.next != null) // deletes any element in-between
					previous.next = current.next;
				else
					// deletes last element
					previous.next = null;

				return true;
			}
			// keeps track of previous and next element
			previous = current;
			current = current.next;

		}
		return false;
	}

	/* The LIST ITERATOR INNER CLASS */
	protected class ListIterator<T> implements Iterator<T> {
		Node<T> current = (Node<T>) head;
		int initial = 0;

		/*
		 * hasNext() returns true if there is a next element that hasn't been
		 * seen returns false if there are no more new element.
		 */
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}

		/*
		 * next() returns first element and sets up next element as current.
		 * throws a noSuckElement if no more elements are available
		 */
		public T next() throws NoSuchElementException {
			// TODO Auto-generated method stub
			if (!hasNext())
				throw new NoSuchElementException();

			T currentData = current.data;
			current = current.next;
			return currentData;
		}

		// Don't have to write this method
		public void remove() {
		}

	}

}
