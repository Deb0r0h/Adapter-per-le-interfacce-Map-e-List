package myAdapter;


/**
* An ordered collection (also known as a sequence). The user of this interface has precise control over where in the list each element is inserted. The user can access elements by their integer index (position in the list), and search for elements in the list.
* Unlike sets, lists typically allow duplicate elements. More formally, lists typically allow pairs of elements e1 and e2 such that e1.equals(e2), and they typically allow multiple null elements if they allow null elements at all. It is not inconceivable that someone might wish to implement a list that prohibits duplicates, by throwing runtime exceptions when the user attempts to insert them, but we expect this usage to be rare.
* The List interface places additional stipulations, beyond those specified in the Collection interface, on the contracts of the iterator, add, remove, equals, and hashCode methods. Declarations for other inherited methods are also included here for convenience.
* The List interface provides four methods for positional (indexed) access to list elements. Lists (like Java arrays) are zero based. Note that these operations may execute in time proportional to the index value for some implementations (the LinkedList class, for example). Thus, iterating over the elements in a list is typically preferable to indexing through it if the caller does not know the implementation.
* The List interface provides a special iterator, called a ListIterator, that allows element insertion and replacement, and bidirectional access in addition to the normal operations that the Iterator interface provides. A method is provided to obtain a list iterator that starts at a specified position in the list.
* The List interface provides two methods to search for a specified object. From a performance standpoint, these methods should be used with caution. In many implementations they will perform costly linear searches.
* The List interface provides two methods to efficiently insert and remove multiple elements at an arbitrary point in the list.
* Note: While it is permissible for lists to contain themselves as elements, extreme caution is advised: the equals and hashCode methods are no longer well defined on a such a list.
* Some list implementations have restrictions on the elements that they may contain. For example, some implementations prohibit null elements, and some have restrictions on the types of their elements. Attempting to add an ineligible element throws an unchecked exception, typically NullPointerException or ClassCastException. Attempting to query the presence of an ineligible element may throw an exception, or it may simply return false; some implementations will exhibit the former behavior and some will exhibit the latter. More generally, attempting an operation on an ineligible element whose completion would not result in the insertion of an ineligible element into the list may throw an exception or it may succeed, at the option of the implementation. Such exceptions are marked as "optional" in the specification for this interface.
 */
public interface HList extends HCollection 
{
    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements to the
     * right (adds one to their indices).
     *
     * @param   index index at which the specified element is to be inserted.
     * @param   element element to be inserted.
     * @throws  IndexOutOfBoundsException if the index is out of range {@code (index < 0 || index > size())}.
     * @throws  NullPointerException if the specified element is null.
     */
    void add(int index, Object element);

    /**
     * Appends the specified element to the end of this list.
     *
     * Lists that support this operation may place limitations on what elements may be added
     * to this list. In particular, this list will refute the insertion of null objects.
     *
     * @param   o element to be appended to this list.
     * @return  {@code true} (as specified by {@link HCollection#add}).
     * @throws  NullPointerException if the specified element is null.
     */
    boolean add(Object o);

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position.
     * Shifts the element currently at that position (if any) and any subsequent elements to the
     * right (increases their indices). The new elements will appear in this list in the order
     * that they are returned by the specified collection's iterator. The behavior of this operation
     * is unspecified if the specified collection is modified while the operation is in progress.
     * (Note that this will occur if the specified collection is this list, and it's nonempty.)
     *
     * @param   index index at which to insert first element from the specified collection.
     * @param   c elements to be inserted into this list.
     * @return  {@code true} if this list changed as a result of the call.
     * @throws  IndexOutOfBoundsException if the index is out of range {@code (index < 0 || index > size())}.
     * @throws  NullPointerException if the specified collection is {@code null}
     *          or contains one or more null objects.
     */
    boolean addAll(int index, HCollection c);

    /**
     * Appends all of the elements in the specified collection to the end of this list,
     * in the order that they are returned by the specified collection's iterator.
     * The behavior of this operation is unspecified if the specified collection is modified
     * while the operation is in progress. (Note that this will occur if the specified collection is
     * this list, and it's nonempty.)
     *
     * @param   c collection whose elements are to be added to this list.
     * @return  {@code true} if this list changed as a result of the call.}
     * @throws  NullPointerException if the specified collection is {@code null}
     *          or contains one or more null objects.
     */
    boolean addAll(HCollection c);

     /**
     * Removes all of the elements from this list.
     * This list will be empty after this call returns (unless it throws an exception).
     */
    void clear();

    /**
     * Returns {@code true} if this list contains the specified element. More formally,
     * returns {@code true} if and only if this list contains at least one element {@code e} such
     * that {@code (o==null ? e==null : o.equals(e))}.
     *
     * @param   o element whose presence in this list is to be tested.
     * @return  {@code true} if this list contains the specified element.
     * @throws  NullPointerException if the specified element is {@code null}.
     */
    boolean contains(Object o);

    /**
     * Returns {@code true} if this list contains the specified element. More formally,
     * returns {@code true} if and only if this list contains at least one element {@code e} such
     * that {@code (o==null ? e==null : o.equals(e))}.
     *
     * @param   c element whose presence in this list is to be tested.
     * @return  {@code true} if this list contains the specified element.
     * @throws  NullPointerException if the specified element is {@code null}.
     */
    boolean containsAll(HCollection c);

    /**
     * Compares the specified object with this list for equality. Returns {@code true} if
     * and only if the specified object is also a list, both lists have the same size, and
     * all corresponding pairs of elements in the two lists are <i>equal</i>. (Two
     * elements {@code e1} and {@code e2} are <i>equal</i> if {@code (e1==null ? e2==null :
     * e1.equals(e2)).)} In other words, two lists are defined to be <i>equal</i>
     * if they contain the same elements in the same order.
     *
     * @param   o the object to be compared for equality with this list.
     * @return  {@code true} if the specified object is equal to this list.
     */
    boolean equals(Object o);

    /**
     * Returns the element at the specified position in this list.
     *
     * @param   index index of element to return.
     * @return  the element at the specified position in this list.
     * @throws  IndexOutOfBoundsException if the index is out of range
     *          {@code (index < 0 || index >= size())}.
     */
    Object get(int index);

    /**
     * Returns the hash code value for this list. The hash code of a list is defined to be the
     * result of the following calculation:
     *  {@code
     *   hashCode = 1;
     *   Iterator i = list.iterator();
     *   while (i.hasNext()) {
     *       Object obj = i.next();
     *       hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
     *   }
     *  }
     * This ensures that {@code list1.equals(list2)} implies that {@code list1.hashCode()==list2.hashCode()}
     * for any two lists, {@code list1} and {@code list2}, as required by the general contract
     * {@code of Object.hashCode}.
     *
     * @return  the hash code value for this list.
     */
    int hashCode();

    /**
     * Returns the index in this list of the first occurrence of the specified element,
     * or -1 if this list does not contain this element. More formally, returns the lowest
     * index i such that {@code (o==null ? get(i)==null : o.equals(get(i)))},
     * or -1 if there is no such index.
     *
     * @param   o element to search for.
     * @return  the index in this list of the first occurrence of the specified
     *          element, or -1 if this list does not contain this element.
     * @throws  NullPointerException if the specified element is null.
     */
    int indexOf(Object o);

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return  {@code true} if this list contains no elements.
     */
    boolean isEmpty();

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return  an iterator over the elements in this list in proper sequence.
     */
    HIterator iterator();

     /**
     * Returns the index in this list of the last occurrence of the specified element,
     * or -1 if this list does not contain this element. More formally, returns the highest index
     * i such that {@code (o==null ? get(i)==null : o.equals(get(i)))}, or -1 if there is no such index.
     *
     * @param   o element to search for.
     * @return  the index in this list of the last occurrence of the specified element,
     *          or -1 if this list does not contain this element.
     * @throws  NullPointerException if the specified element is null.
     */
    int lastIndexOf(Object o);

    /**
     * Returns a list iterator of the elements in this list (in proper sequence).
     * @return a list iterator of the elements in this list (in proper sequence).
     */
    HListIterator listIterator();

    /**
     * Returns a list iterator of the elements in this list (in proper sequence),
     * starting at the specified position in this list. The specified index indicates the
     * first element that would be returned by an initial call to the {@code next} method. An
     * initial call to the {@code previous} method would return the element with the specified
     * index minus one
     * @param   index index of first element to be returned from
     *          the list iterator (by a call to the next method).
     * @return  a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     * @throws  IndexOutOfBoundsException if the index is out of range
     */
    HListIterator listIterator(int index);

    /**
     * Removes the element at the specified position in this list. Shifts any
     * subsequent elements to the left (subtracts one from their indices). Returns
     * the element that was removed from the list.
     *
     * @param   index the index of the element to removed.
     * @return  the element previously at the specified position.
     * @throws  IndexOutOfBoundsException if the index is out of range 
     */
    Object remove(int index);

    /**
     * Returns a view of the portion of this list between the specified {@code fromIndex},
     * inclusive, and {@code toIndex}, exclusive. (If {@code fromIndex} and {@code toIndex}
     * are equal, the returned list is empty.) The returned list is backed by this list, so
     * non-structural changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported by this list.
     * This method eliminates the need for explicit range operations (of the sort that commonly
     * exist for arrays). Any operation that expects a list can be used as a range operation by passing
     * a subList view instead of a whole list. For example, the following idiom removes a range of
     * elements from a list:
     * list.subList(from, to).clear();
     * Similar idioms may be constructed for {@code indexOf} and {@code lastIndexOf}, and all
     * of the algorithms in the {@code Collections} class can be applied to a subList.
     * The semantics of the list returned by this method become undefined if the backing list
     * (i.e., this list) is <i>structurally modified</i> in any way other than via the returned
     * list. (Structural modifications are those that change the size of this list, or otherwise
     * perturb it in such a fashion that iterations in progress may yield incorrect results.)
     *
     * @param   fromIndex low endpoint (inclusive) of the subList.
     * @param   toIndex high endpoint (exclusive) of the subList.
     * @return  a view of the specified range within this list.
     * @throws  IndexOutOfBoundsException for an illegal endpoint index value
     *          {@code (fromIndex < 0 || toIndex > size || fromIndex > toIndex)}.
     */
    HList subList(int fromIndex, int toIndex);

    /**
     * Removes the first occurrence in this list of the specified element. If this
     * list does not contain the element, it is unchanged. More formally, removes
     * the element with the lowest index i such that {@code (o==null ? get(i)==null : o.equals(get(i)))}
     * (if such an element exists).
     *
     * @param   o element to be removed from this list, if present.
     * @return  {@code true} if this list contained the specified element.
     * @throws  NullPointerException if the specified element is null.
     */
    boolean remove(Object o);

    /**
     * Removes from this list all the elements that are contained in the specified collection.
     *
     * @param   c collection that defines which elements will be removed from this list.
     * @return  {@code true} if this list changed as a result of the call.
     * @throws  NullPointerException if the specified collection is null or contains null elements.
     */
    boolean removeAll(HCollection c);

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection. In other words, removes from this list all
     * the elements that are not contained in the specified collection.
     *
     * @param   c collection that defines which elements this set will retain.
     * @return  {@code true} if this list changed as a result of the call.
     * @throws  NullPointerException if the specified collection is null or contains null elements.
     */
    boolean retainAll(HCollection c);

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list.
     */
    int size();

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param   index index of element to replace.
     * @param   element element to be stored at the specified position.
     * @return  the element previously at the specified position.
     * @throws  NullPointerException if the specified element is null.
     * @throws  IndexOutOfBoundsException if the index is out of range
     */
    Object set(int index, Object element);

    /**
     * Returns an array containing all of the elements in this list in proper sequence.
     *
     * @return an array containing all of the elements in this list in proper sequence.
     */
    Object[] toArray();

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence; the runtime type of the returned array is that of the specified array.
     *
     * @param   a the array into which the elements of this list are to be
     *          stored, if it is big enough; otherwise, a new {@code Object[]} array
     *          is created for this purpose.
     * @return  an array containing the elements of this list.
     * @throws  ArrayStoreException if the runtime type of the specified array is
     *          not a supertype of the runtime type of every element in this list.
     * @throws  NullPointerException if the specified array is {@code null}.
     */
    Object[] toArray(Object[] a);
}
