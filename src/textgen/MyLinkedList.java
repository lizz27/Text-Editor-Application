package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
		head = new LLNode<E> (null);
		tail = new LLNode<E> (null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element) 
	{
		if (element == null) {
			throw new NullPointerException("Object cannot store null pointers.");
		}
		
		LLNode<E> c = new LLNode(element);
		
		if (head.next.next == null) { // add an element to an empty list
			head.next = c;
			tail.prev = c;
			c.prev = head;
			c.next = tail;
		} else { // add an element to a nonempty list
			c.prev = tail.prev;
			c.next = tail;
			tail.prev.next = c;
			tail.prev = c;
		}
		
		size++;
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{	
		// check for empty list and index greater/less than the upper/lower bounds
		if (index < 0 || head.next.next == null || index >= size) {
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		} 
		
		//  In a non-empty linked list, index 0 should refer to the first data node
		// which is the node pointed to by the next pointer of the head sentinel node.
		LLNode<E> c = head.next; 

		for (int i = 0; i < index; i++) {
			c = c.next;
		}

		return c.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (element == null) {
			throw new NullPointerException("Object cannot store null pointers.");
		}
		
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		} 
		
		LLNode<E> c = head.next;
		LLNode<E> x = new LLNode(element);
		
		if (head.next.next == null) { // insert to an empty list
			head.next = x;
			x.prev = head;
			x.next = tail;
			tail.prev = x;
		} else { // insert to a nonempty list
			for (int i = 0; i <= index; i++) {
				if (i == index) { // insert
					c.prev.next = x;
					x.prev = c.prev;
					x.next = c;
					c.prev = x;
				}
				c = c.next;
			}
		}
		
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (index < 0 || index >= size || size == 0) {
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		} 
		
		LLNode<E> c = head;
		
		for (int i = 0; i <= index; i++) {
			c = c.next;
			if (i == index) { // remove
				c.prev.next = c.next;
				c.next.prev = c.prev;
				size --;
			}
		}
		
		return c.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (element == null) {
			throw new NullPointerException("Object cannot store null pointers.");
		}
		
		if (index < 0 || index >= size || size == 0) {
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		} 
		
		LLNode<E> x = new LLNode(element);
		LLNode<E> c = head;
		
		for (int i = 0; i <= index; i++) {
			c = c.next;
			if (i == index) { // set
				c.prev.next = x;
				x.prev = c.prev;
				x.next = c.next;
				c.next.prev = x;
			}
		}
		
		return c.data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
