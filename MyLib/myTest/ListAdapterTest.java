package myTest;

import myAdapter.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListAdapterTest 
{
    //lista usata in tutti i test
    private ListAdapter list;

    /**
     * crea lista contenente valore da 0 a 9 per riemprire la lista "list" 
     * o come argomento di funzioni che richiedono una Collection.
    */
     private ListAdapter putValues()
    {
        ListAdapter l = new ListAdapter();
        for (int i = 0; i < 10; i++)
            l.add(i, i);
        return l;
    }

    //inizializza la lista vuota, si riempirà eventualmente con putValues
    @Before
    public void create() 
    {
        list = new ListAdapter();
    }

    /**
     * test metodo add(int index, Object element)
     */
    @Test
    public void addIndexTest() 
    {
        list.addAll(putValues());
        assertEquals(10, list.size());
        list.add(0, 1);
        list.add(5, 2);
        list.add(list.size(),3 );
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(list.size() - 1));
        assertEquals(2, list.get(5));
        assertEquals(13, list.size());
    }

    /**
     * test eccezioni lanciate dal metodo add(int index, Object element)
     */
    @Test
    public void addIndexObjextExceptionTest() 
    {
        list.addAll(putValues());

        assertThrows(NullPointerException.class, () -> {list.add(10000, null);});

        assertThrows(NullPointerException.class, () -> {list.add(list.size()-1, null);});

        assertEquals(putValues().size(), list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> {list.add(list.size() + 1, "errore");});

        assertThrows(IndexOutOfBoundsException.class, () -> {list.add(-1, "errore");});

        assertEquals(putValues().size(), list.size());
    }


    /**
     * test metodo add(Object o)
     */
    @Test
    public void addTest() 
    {
        assertTrue(list.isEmpty());
        assertTrue(list.add(100));
        assertEquals(1, list.size());
        assertTrue(list.add(1));
        assertEquals(2, list.size());
        assertTrue(list.contains(100));
        assertFalse(list.contains(2));
        assertTrue(list.add(1));
        assertEquals(1, list.get(1));
        assertEquals(1, list.get(2));
        assertEquals(3, list.size());
    }

    /**
     * test eccezione add(Object o)
     */
    @Test
    public void addExceptionTest()
    {
        assertThrows(NullPointerException.class, () -> {list.add(null);});
        list.addAll(putValues());
        assertThrows(NullPointerException.class, () -> {list.add(null);});
    }

    /**
     * test metodo addAll(HCollection c)
     */
    @Test
    public void addAllTest() 
    {
        assertEquals(0, list.size());
        HCollection collection = putValues();
        list.addAll(collection);
        assertEquals(10, list.size());
        assertEquals(collection.size(), list.size());
        HIterator iter = collection.iterator();
        for (int i = 0; i < collection.size(); i++)
            assertEquals(iter.next(), list.get(i));
    }
    /**
     * test eccezione lanciata da addAll(HCollection c)
     */
    @Test
    public void addAllExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {list.addAll(null);});
        assertEquals(0, list.size());
    }

    /**
     * test metodo addAll(int index, HCollection c)
     */
    @Test
    public void addAllIndexTest() 
    {
        HCollection collection = putValues();
        list.add(100);
        list.add(27);
        list.add(200);
        int idx = 1;
        assertTrue(list.addAll(idx, collection));
        HIterator it = collection.iterator();
        for (idx = 1; idx < collection.size(); idx++)
            assertEquals(it.next(), list.get(idx));
        assertTrue(list.size()==13);
    }

    /**
     * test eccezioni lanciate da addAll(int index, HCollection c)
     */
    @Test
    public void addAllIndexExceptionsTest() 
    {
        list.addAll(putValues());

        assertThrows(NullPointerException.class, () -> {list.addAll(list.size()-1,null);});

        assertThrows(IndexOutOfBoundsException.class, () -> {list.addAll(-1, putValues());});

        assertThrows(IndexOutOfBoundsException.class, () -> {list.addAll(list.size()+1, putValues());});

    }


    /**
     * test metodo contains(Object o)
     */
    @Test
    public void containsTest() 
    {
        list.addAll(putValues());
        assertTrue(list.contains(4));
        assertFalse(list.contains(83));
        assertTrue(list.contains(1));
        assertFalse(list.contains(111));
    }

    /**
     * test eccezione lanciata da contains(Object o)
     */
    @Test
    public void containsExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {list.contains(null);});
        list.addAll(putValues());
        assertThrows(NullPointerException.class, () -> {list.contains(null);});
    }

    /**
     * test metodo containsAll(HCollection c)
     */
    @Test
    public void containsAllTest() 
    {
        HCollection c = putValues();
        assertFalse(list.containsAll(c));
        list.addAll(c);
        assertTrue(list.containsAll(c));
    }

    /**
     * test eccezione lanciata da containsAll(HCollection c)
     */
    @Test
    public void containsAllExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {list.containsAll(null);});
        list.addAll(putValues());
        assertThrows(NullPointerException.class, () -> {list.containsAll(null);});
    }


    /**
     * test metodo clear()
     */
    @Test
    public void clearTest() 
    {
        assertEquals(0, list.size());
        list.clear();
        assertEquals(0, list.size());
        list.addAll(putValues());
        list.clear();
        assertEquals(0, list.size());
    }

     /**
     * test metodo equals(Object o)
     */
    @Test
    public void equalsTest() 
    {
        HCollection c = putValues();
        list.addAll(c);
        assertTrue(list.equals(c));
        assertTrue(c.equals(list));
        assertFalse(list.equals(null));
        list.remove(4);
        assertFalse(list.equals(c));
    }

    /**
     * test metodo get(int index)
     */
    @Test
    public void getTest() 
    {
        list.addAll(putValues());
        assertEquals(9, list.get(list.size() - 1));
    }

    /**
     * test eccezione lanciata da get(int index)
     */
    @Test
    public void getExceptionTest() 
    {
        assertThrows(IndexOutOfBoundsException.class, () -> {list.get(0);});
        list.addAll(putValues());
        assertThrows(IndexOutOfBoundsException.class, () -> {list.get(-1);});
        assertThrows(IndexOutOfBoundsException.class, () -> {list.get(list.size());});
    }

    @Test
    public void hashCodeTest() 
    {
        HCollection c = putValues();
        list.addAll(c);
        ListAdapter list2 = new ListAdapter();
        list2.addAll(c);
        assertEquals(list, list2);
        assertEquals(list.hashCode(), list2.hashCode());
        list2.set(0, 100);
        assertNotEquals(list.hashCode(), list2.hashCode());
    }

    /**
     * test metodo isEmpty()
     */
    @Test
    public void isEmptyTest() 
    {
        assertTrue(list.isEmpty());
        list.addAll(putValues());
        assertFalse(list.isEmpty());
    }

    /**
     * test metodo indexOf(Object o)
     */
    @Test
    public void indexOfTest() 
    {
        list.addAll(putValues());
        assertEquals(4, list.indexOf(4));
        assertEquals(0, list.indexOf(0));
    }

    /**
     * test eccezione lanciata da indexOf(Object o)
     */
    @Test
    public void indexOfExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {list.indexOf(null);});
        list.addAll(putValues());
        assertThrows(NullPointerException.class, () -> {list.indexOf(null);});
    }



    /**
     * test metodo remove(Object o)
     */
    @Test
    public void removeObjectTest() 
    {
        list.addAll(putValues());
        assertTrue(list.remove((Object)1));
        assertFalse(list.remove((Object) 1));
        assertEquals(9, list.size());
    }

    /**
     * test eccezione lanciata dal metodo remove(Object o)
     */
    @Test
    public void removeObjectExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {list.remove(null);});
        
        list.addAll(putValues());
        assertThrows(NullPointerException.class, () -> {list.remove(null);});
    }

     /**
     * test metodo remove(int index)
     */
    @Test
    public void removeIndexTest() 
    {
        list.addAll(putValues());
        assertTrue(list.get(3).equals(3));
        assertFalse(list.contains(list.remove(3)));
        assertEquals(9, list.size());
        list.remove(0);
        list.remove(list.size() - 1);
        assertEquals(7, list.size());
    }

    /**
     * test eccezione lanciata dal metodo remove(int index)
     */
    @Test
    public void removeIndexExceptionTest() 
    {
        list.addAll(putValues());
        assertThrows(IndexOutOfBoundsException.class, () -> {list.remove(-1);});

        assertThrows(IndexOutOfBoundsException.class, () -> {list.remove(list.size());});
    }


    /**
     * test metodo lastIndexOf(Object o)
     */
    @Test
    public void lastIndexOfTest() 
    {
        list.addAll(putValues());
        assertEquals(5, list.lastIndexOf(5));
        assertEquals(-1, list.lastIndexOf("tanto non c'è"));
    }

    /**
     * test eccezione lanciata dal metodo lastIndexOf(Object o)
     */
    @Test
    public void lastIndexOfExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {list.lastIndexOf(null);});
        list.addAll(putValues());
        assertThrows(NullPointerException.class, () -> {list.lastIndexOf(null);});
    }

    /**
     * test metodo removeAll(HCollection c)
     */
    @Test
    public void removeAllTest() 
    {

        list.add(27);
        list.add(28);
        list.add(29);
        list.addAll(putValues());
        assertTrue(list.removeAll(putValues()));
        assertEquals(3,list.size());
    }

    /**
     * test eccezione lanciata dal metodo removeAll(HCollection c)
     */
    @Test
    public void removeAllExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {list.removeAll(null);});

        list.addAll(putValues());
        assertThrows(NullPointerException.class, () -> {list.removeAll(null);});
    }


    /**
     * test metodo retainAll(HCollection c)
     */
    @Test
    public void retainAllTest() 
    {
        list.add(33);
        list.add(333);
        list.add(3333);
        assertTrue(list.retainAll(putValues()));
        assertEquals(0, list.size());
    }

    /**
     * test eccezione lanciata dal metodo retainAll(HCollection c)
     */
    @Test
    public void retainAllExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {list.retainAll(null);});

        list.addAll(putValues());
        assertThrows(NullPointerException.class, () -> {list.retainAll(null);});
    }

    /**
     * test metodo set(int index, Object element)
     */
    @Test
    public void setTest() 
    {
        list.addAll(putValues());
        list.set(3, 30);
        assertFalse(list.contains(3));
        assertTrue(list.contains(30));
        assertEquals(10, list.size());
    }

    /**
     * test eccezioni lanciate dal metodo set(int index, Object element)
     */
    @Test
    public void setExceptionsTest() 
    {
        list.add("prova");
        assertThrows(NullPointerException.class, () -> {list.set(0, null);});

        list.clear();
        assertThrows(IndexOutOfBoundsException.class, () -> {list.set(0, "prova");});
    }

    /**
     * test metodo size()
     */
    @Test
    public void sizeTest() 
    {
        assertEquals(0, list.size());
        list.addAll(putValues());
        assertEquals(10, list.size());
        list.add(1);
        assertEquals(11, list.size());
    }

    /**
     * test metodo subList()
     */
    @Test
    public void subListTest() 
    {
        list.addAll(putValues());
        HList slist = list.subList(0, 0);
        assertEquals(0, slist.size());
        slist = list.subList(0, 3);
        assertEquals(3, slist.size());
    }

    /**
     * test eccezione lanciata dal metodo subList()
     */
    @Test
    public void subListExceptionTest() 
    {
        list.addAll(putValues());
        assertThrows(IndexOutOfBoundsException.class, () -> {HList slist = list.subList(-1, 0);});

        assertThrows(IndexOutOfBoundsException.class, () -> {HList slist = list.subList(1, 0);});

        assertThrows(IndexOutOfBoundsException.class, () -> {HList slist = list.subList(5, list.size() + 1);});
    }

    /**
     * test metodo toArray()
     */
    @Test
    public void toArrayTest() 
    {
        list.addAll(putValues());
        Object[] a = list.toArray();
        for (int i = 0; i < a.length; i++)
            assertEquals(list.get(i), a[i]);
        assertEquals(list.size(), a.length);
    }

    /**
     * test metodo toArray(Object[] a) 
     */
    @Test
    public void toArrayATest() 
    {
        list.addAll(putValues());
        Object[] a1 = new Object[list.size()];
        a1 = list.toArray(a1);
        assertEquals(10, a1.length);
        for (int i = 0; i < a1.length; i++)
            assertEquals(list.get(i), a1[i]);
    }

    /**
     * test eccezioni lanciate da toArray(Object[] a)
     */
    @Test
    public void toArrayAExceptionsTest() 
    {
        list.addAll(putValues());
        Object[] a = new Double[list.size()];
        assertThrows(ArrayStoreException.class, () -> {list.toArray(a);});

        assertThrows(NullPointerException.class, () -> {list.toArray(null);});
    }

                                    // TEST DI ITERATOR E DEI METODI CORRELATI ALL'ITERATORE
    /**
     * test metodo iterator() accompagnato dal test di hasNext e next() e relativa eccezione di next()
     */
    @Test
    public void iteratorTest() 
    {
        HIterator iter = list.iterator();
        assertFalse(iter.hasNext());
        list.addAll(putValues());
        int elem = 0;
        while (iter.hasNext()) 
        {
            assertEquals(list.get(elem), iter.next());
            elem++;
        }
        
        assertThrows(NoSuchElementException.class, () -> {iter.next();});
    }

    /**
     * test metodo remove() di iterator
     */
    @Test
    public void iteratorRemoveTest() 
    {
         list.addAll(putValues());
         HIterator it = list.iterator();
         Object temp = it.next();
         it.remove();
        assertFalse(list.contains(temp));
        for (int i = 0; i < 5; i++)
        {
            temp = it.next();
            it.remove();
        }
         assertFalse(list.contains(temp));
         assertEquals(4, list.size());
    }

    /**
     * test eccezione remove()
     */
    @Test
    public void iteratorRemoveExceptionTest() 
    {
        HIterator it = list.iterator();
        assertThrows(IllegalStateException.class, () -> {it.remove();});

        list.addAll(putValues());
        assertThrows(IllegalStateException.class, () -> {it.remove();});
    }

                        // TEST DI LISTITERATOR E DEI METODI CORRELATI
    
     /**
     * test metodo listIterator() e relativa eccezione
     */
    @Test
    public void listIteratorTest() 
    {
        HListIterator it = list.listIterator();
        assertFalse(it.hasNext());
        list.addAll(putValues());
        int elem = 0;
        while (it.hasNext()) 
        {
            assertEquals(list.get(elem), it.next());
            elem++;
        }
        assertFalse(it.hasNext());

        assertThrows(NoSuchElementException.class, () -> {it.next();});
    }

    /**
     * test metodo listIterator(int index)
     */
    @Test
    public void listIteratorIndexTest() 
    {
        list.addAll(putValues());
        int index = 1;
        HListIterator it = list.listIterator(index);
        assertTrue(it.hasNext());
        int elem = 0;
        for (int i = index; i < list.size(); i++) 
        {
            assertEquals(list.get(elem + index), it.next());
            elem++;
        }
        assertEquals(list.size(), elem + index);
        assertFalse(it.hasNext());
    }

    /**
     * test eccezione lanciata da listIterator(int index)
     */
    @Test
    public void listIteratorIndexExceptionTest() 
    {
        assertThrows(IndexOutOfBoundsException.class, () -> {list.listIterator(1);});
    }

    /**
     * test metodo add(Object o) di listIterator e relativa eccezione
     */
    @Test
    public void listIteratorAddTest() 
    {
        HListIterator iter = list.listIterator();
        iter.add(1);
        assertEquals(1, list.size());
        assertFalse(iter.hasNext());
        assertTrue(iter.hasPrevious());
        assertThrows(IllegalArgumentException.class, () -> {iter.add(null);});
    }

    /**
     * test metodo hasPrevious() di listIterator()
     */
    @Test
    public void listIteratorHasPreviousTest() 
    {
        list.addAll(putValues());
        HListIterator iter = list.listIterator();
        assertFalse(iter.hasPrevious());
        iter.next();
        assertTrue(iter.hasPrevious());
        while (iter.hasNext())
            iter.next();
        assertTrue(iter.hasPrevious());
    }

    /**
     * test metodo nextIndex() di listIterator()
     */
    @Test
    public void listIteratorNextIndexTest() 
    {
        HListIterator iter = list.listIterator();
        assertEquals(0, iter.nextIndex());
        list.addAll(putValues());
        assertEquals(0, iter.nextIndex());
        iter.next();
        assertEquals(1, iter.nextIndex());
    }

    /**
     * test metodo previous() listIterator() e relativa eccezione
     */
    @Test
    public void listIteratorPreviousTest() 
    {
        HListIterator iter1 = list.listIterator();
        assertThrows(NoSuchElementException.class, () -> {iter1.previous();});
        
        list.addAll(putValues());
        HListIterator iter2 = list.listIterator(list.size());
        int prevElems = 0;
        while (iter2.hasPrevious())
        {
            prevElems++;
            assertTrue(list.get(list.size() - prevElems).equals(iter2.previous()));
        }

        assertThrows(NoSuchElementException.class, () -> {iter2.previous();});
    }

    /**
     * test metodo previousIndex() di listIterator()
     */
    @Test
    public void listIteratorPreviousIndexTest() 
    {
        HListIterator iter = list.listIterator();
        assertEquals(-1, iter.previousIndex());
        list.addAll(putValues());
        iter.next();
        assertEquals(0, iter.previousIndex());
        while (iter.hasNext())
            iter.next();
        assertEquals(list.size() - 1, iter.previousIndex());
    }

    /**
     * test metodo set(Object o) di listIterator()
     */
    @Test
    public void listIteratorSetTest()
    {
        HListIterator it = list.listIterator();
        assertTrue(list.isEmpty());
        list.add(1);
        list.add(2);
        it.next();
        it.set(100);
        assertTrue(list.size() == 2);
        assertTrue(list.contains(100));
        it.previous();
        it.set(200);
        assertFalse(list.contains(100));
        assertTrue(list.contains(200));
    }

    /**
     * test eccezioni lanciate dal metodo set() di listIterator()
     */
    @Test
    public void listIteratorSetExceptionTest() 
    {
        list.addAll(putValues());
        HListIterator iter = list.listIterator();
        assertThrows(IllegalArgumentException.class, () -> {iter.set(null);});
        assertThrows(IllegalStateException.class, () -> {iter.set("non va");});

        iter.next();
        assertThrows(IllegalArgumentException.class, () -> {iter.set(null);});
    }

    /**
     * test metodo remove() di listIterator()
     */
    @Test
    public void listIteratorRemoveTest() 
    {
        list.addAll(putValues());
        HListIterator iter = list.listIterator();
        Object elem1 = iter.next();
        iter.remove();
        assertEquals(9, list.size());
        assertFalse(list.contains(elem1));
        iter.next();
        Object elem2 = iter.previous();
        iter.remove();
        assertEquals(8, list.size());
        assertFalse(list.contains(elem2));
    }

    /**
     * test eccezione lanciata dal metodo remove() di listIterator()
     */
    @Test
    public void listIteratorRemoveExceptionTest() 
    {
        list.addAll(putValues());
        HListIterator iter = list.listIterator();
        assertThrows(IllegalStateException.class, () -> {iter.remove();});
    }

}
