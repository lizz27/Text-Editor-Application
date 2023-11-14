/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// test removing from empty list
		try {
			emptyList.remove(0);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}
		
		// test index out of bounds exception 
		try {
			shortList.remove(-2);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}		
		
		try {
			shortList.remove(100);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}
		
		// test removing from nonempty list
		String b = shortList.remove(1);
		assertEquals("Remove: check b is correct ", "B", b);
		assertEquals("Remove: check element 0 is correct ", "A", shortList.get(0));
		assertEquals("Remove: check size is correct ", 1, shortList.size());
	}

	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		// test adding null
		try {
			shortList.add(null);
			fail("Check null");
		}
		catch (NullPointerException e) {
		}
		
		// test adding to an empty list
		emptyList.add(7);
		assertEquals("Add to End: check size is correct ", 1, emptyList.size);
		assertEquals("Add to End: last element is correct ", (Integer)7, emptyList.get(0));
		
		// test adding to a nonempty list
		list1.add(27);
		assertEquals("Add to End: check size is correct ", 4, list1.size);
		assertEquals("Add to End: last element is correct ", (Integer)27, list1.get(3));
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Size: check size is correct ", 0, emptyList.size());
		assertEquals("Size: check size is correct ", 2, shortList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		// test adding null
		try {
			shortList.add(0, null);
			fail("Check null");
		}
		catch (NullPointerException e) {
		}
		
		// test adding to an empty list
		try {
			emptyList.add(1, 7);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}
		
		try {
			emptyList.add(-1, 7);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}
		
		emptyList.add(0, 7);
		assertEquals("Add to End: check size is correct ", 1, emptyList.size);
		assertEquals("Add to End: last element is correct ", (Integer)7, emptyList.get(0));
		
		// test adding to a nonempty list
		list1.add(1, 27);
		assertEquals("Add to End: check size is correct ", 4, list1.size);
		assertEquals("Add to End: last element is correct ", (Integer)27, list1.get(1));
		
		list1.add(3, 81);
		assertEquals("Add to End: check size is correct ", 5, list1.size);
		assertEquals("Add to End: last element is correct ", (Integer)81, list1.get(3));		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		
		// test setting null
		try {
			shortList.set(0, null);
			fail("Check null");
		}
		catch (NullPointerException e) {
		}
		
		// test out of bounds
		try {
			shortList.set(-2, "D");
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}		
		
		try {
			shortList.set(200, "D");
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}		
		
		try {
			emptyList.set(1, 0);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}		
		
		// test empty list
		try {
			emptyList.set(0, 1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}		
		
		// test nonempty list
		int a = list1.set(0, 66);
		assertEquals("Set: check a is correct ", 65, a);
		assertEquals("Set: check element 0 is correct ", (Integer)66, list1.get(0));
		assertEquals("Set: check size is correct ", 3, list1.size());
	    
		int b = list1.set(2, 67);
		assertEquals("Set: check a is correct ", 42, b);
		assertEquals("Set: check element 1 is correct ", (Integer)21, list1.get(1));
		assertEquals("Set: check size is correct ", 3, list1.size());
	}
	
	
	// TODO: Optionally add more test methods.
	
}
