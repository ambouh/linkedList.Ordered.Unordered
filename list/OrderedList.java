/*NAME: Andres Mbouh
 *COURSE: CMSC132 - 0203
 *DUE: 3/09/2015
 *
 *HONOR PLEDGE: I pledge on my honor that I have not given or received any 
 *unauthorized assistance on this assignment/examination. 
 *
 *PURPOSE: This class creates an ordered linked list. it's purpose is to store
 *data that uses an unlimited amount space, and have a list that is able to store 
 *an unlimited amount of elements. The class extends UnorderedList<T>, override
 *the add method to sort this list into an ordered list**/
package list;

import java.lang.Iterable;
import java.util.Comparator;

public class OrderedList<T> extends UnorderedList<T> {

  /*
   * Implement (i.e., override) whatever methods from the superclass that
   * you find are necessary to have this list be sorted, as well as any
   * methods that would be more efficient if overridden in this subclass.
   */
	  public OrderedList(Comparator<T> comparator) {
		  super(comparator);
	  }
	  /*add() appends element in passed in parameter in order of comparator*/
	public void add(T newElt) {
		Node<T> current = head, prev = null, n;

		while (current != null && !(comparator.compare(current.data,newElt) > 0) ) {
			prev = current;
			current = current.next;
		}

		n = new Node<T>(newElt);
		/*In case, the list is empty, 'n' variable takes the place of 'head'*/
		if (current == head) {
			n.next = head;
			head = n;
		} else {
			/*In case, the list is populated, the 'n' variable fits in-between
			 * prev and current node*/
			prev.next = n;
			n.next = current;
		}
		
	}

}
