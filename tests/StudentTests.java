/*NAME: Andres Mbouh
 *COURSE: CMSC132 - 0203
 *DUE: 3/09/2015
 *
 *HONOR PLEDGE: I pledge on my honor that I have not given or received any 
 *unauthorized assistance on this assignment/examination. 
 *
 *PURPOSE: Tests all possibilities for cases not encountered in publicTest*/
package tests;

import list.CharacterComparator;
import list.IntegerComparator;
import list.OrderedList;
import list.UnorderedList;

import org.junit.*;

import static org.junit.Assert.*;

public class StudentTests {
	// multiple tests can use these
	static IntegerComparator intComparator = new IntegerComparator();
	static CharacterComparator charComparator = new CharacterComparator();

	/*
	 * makes sure removeRange satisfies: - fromPos < 0 and toPos!= 0. fromPos
	 * will = 0 or to be same as 0-toPos - toPos > last and fromPos!=last.
	 * topPos = 0 or same as fromPos - last
	 */
	@Test
	public void testPublic1() {
		UnorderedList<Integer> list = sampleList1();

		/* toPos < fromPos (negative) */
		list.removeRange(-1, -3);
		/* toPos > fromPos (negative) */
		list.removeRange(-2, -1);
		/* topPos, fromPos > total-size (positive) */
		list.removeRange(6, 7);
		/* no changes in list expected */
		assertEquals("30 18 68 45 40 23", list.toString());
	}

	/*
	 * testing removeRange with 0 to total inclusion. it removes all elements
	 * successfully
	 */
	@Test
	public void testPublic2() {
		UnorderedList<Integer> list = sampleList1();

		list.removeRange(0, 7);

		list = sampleList1();
		list.removeRange(-100, 100);
		assertEquals("", list.toString());
	}

	/*
	 * testing orderList's add() method, it should add element in order
	 * successfully
	 */
	@Test
	public void testPublic3() {
		OrderedList<Integer> list = new OrderedList<Integer>(intComparator);

		list.add(5);
		list.add(1);
		list.add(-3);
		list.add(2);
		list.add(3);
		list.add(4);

		assertEquals("-3 1 2 3 4 5", list.toString());
	}

	/*
	 * testing removeNumOccurences with ordered list. should satisfy all
	 * constraints
	 */
	@Test
	public void testPublic4() {
		OrderedList<Integer> list = new OrderedList<Integer>(intComparator);

		list.add(5);
		list.add(5);
		list.add(5);
		list.add(5);
		list.add(5);
		list.add(5);
		list.add(2);
		list.add(3);
		list.add(4);

		/* removes less than available */
		list.removeNumOccurrences(5, 2);

		assertEquals("2 3 4 5 5 5 5", list.toString());

		/* removes more than available */
		list.removeNumOccurrences(5, 10000000);

		assertEquals("2 3 4", list.toString());

	}

	/*
	 * testing append(), makes sure new list is independent from list being
	 * appended
	 */
	@Test
	public void testPublic5() {
		OrderedList<Integer> list = new OrderedList<Integer>(intComparator);

		list.add(5);
		list.add(5);
		list.add(5);
		list.add(5);
		list.add(5);
		list.add(5);
		list.add(2);
		list.add(3);
		list.add(4);

		UnorderedList<Integer> list1 = sampleList1();
		list1.append(list);

		/* removes all 5s from list1 and list 2 should still have all 5s */
		list1.removeNumOccurrences(5, 100);
		int result = list1.countElement(5);

		/* list1 is has none */
		assertEquals(result, 0);

		result = list.countElement(5);

		/* list is intact */
		assertEquals(result, 6);
	}

	/*
	 * testing compareTo() to make sure the right integers are coming out equal
	 * - 0 less than - (-1) greater than - 1
	 */
	@Test
	public void testPublic6() {
		OrderedList<Integer> list = new OrderedList<Integer>(intComparator);

		list.add(2);
		list.add(3);
		list.add(4);

		UnorderedList<Integer> list1 = 
				new UnorderedList<Integer>(intComparator);

		// new list1 should look like the beginning of list
		list1.add(2);
		list1.add(3);
		list1.add(4);

		// list has list + sampleList1()
		list.append(sampleList1());

		// result, they be identical but list will be longer: 1
		int result = list.compareTo(list1);

		assertEquals(result, 1);
	}

	/*
	 * testing compareTo() to make sure the right integers are coming out equal
	 * - 0 less than - (-1) greater than - 1
	 */
	@Test
	public void testPublic7() {
		OrderedList<Integer> list = new OrderedList<Integer>(intComparator);

		list.add(2);
		list.add(3);
		list.add(4);

		UnorderedList<Integer> list1 = 
				new UnorderedList<Integer>(intComparator);

		// new list1 should look like the beginning of list
		list1.add(2);
		list1.add(3);
		list1.add(4);

		// result, they are identical so 0
		int result = list.compareTo(list1);

		assertEquals(result, 0);
	}

	/*
	 * testing compareTo() to make sure the right integers are coming out equal
	 * - 0 less than - (-1) greater than - 1
	 */
	@Test
	public void testPublic8() {

		OrderedList<Integer> list = new OrderedList<Integer>(intComparator);

		list.add(2);
		list.add(3);
		list.add(4);

		UnorderedList<Integer> list1 = new UnorderedList<Integer>(intComparator);

		// new list1 should look like the beginning of list
		list1.add(2);
		list1.add(3);
		list1.add(5);

		// result, list < list1 so -1
		int result = list.compareTo(list1);

		assertEquals(result, -1);

		// clears list to test compareTo with null list
		list.clear();
		// result, list < list1 so -1
		result = list.compareTo(list1);
		assertEquals(result, -1);

	}

	/* use as much methods as possible and testing both lists */
	@Test
	public void testPublic9() {
		OrderedList<Integer> list = new OrderedList<Integer>(intComparator);
		UnorderedList<Integer> list1 = 
				new UnorderedList<Integer>(intComparator);

		list.add(2);
		list.add(2);
		list.add(2);

		list1.add(2);
		list1.add(4);

		assertEquals(list1.length(), 2);

		list.removeNumOccurrences(2, 2);
		list1.removeRange(1, 1);

		assertEquals(list1.toString(), list.toString());
		assertEquals(list1.countElement(2), list.countElement(2));

	}

	/* testing elementAtPos() method that throws out-bound 
	 * exception (Positive) */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testPublic10() {
		OrderedList<Integer> list = new 
				OrderedList<Integer>(intComparator);

		list.elementAtPos(2);

	}

	/* testing elementAtPos() method that throws out-bound exception 
	 * (negative) */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testPublic11() {
		OrderedList<Integer> list = new OrderedList<Integer>(intComparator);
		list.add(2);
		list.elementAtPos(-400);

	}

	/*-----------------------------------------------------*/
	/* PRIVATE UTILITY METHOD */
	private static <T> UnorderedList<T> 
	initList(UnorderedList<T> list, T[] arr) {
		for (T elt : arr)
			list.add(elt);

		return list;
	}

	/* ORDERED AND UNORDERED LIST OBJECTS */
	private static UnorderedList<Integer> sampleList1() {
		UnorderedList<Integer> list = new UnorderedList<Integer>(intComparator);

		initList(list, new Integer[] { 30, 18, 68, 45, 40, 23 });

		return list;
	}

}
