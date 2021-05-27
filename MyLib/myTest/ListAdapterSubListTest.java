package myTest;

import myAdapter.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListAdapterSubListTest 
{
    //lista usata per creare la subList
    private ListAdapter l = putValues();

    //SubList usata in tutti i test
    private HList sl;

    /**
     * crea lista contenente valore da 0 a 9 per riemprire la lista "list" 
     * o come argomento di funzioni che richiedono una collection.
    */
    private ListAdapter putValues() 
    {
       ListAdapter l = new ListAdapter();
       for (int i = 0; i < 10; i++)
           l.add(i, i);
       return l;
   }

    @Before
    public void create() 
    {
        sl = l.subList(2, 8);
    }

    /**
     * test metodo add(Object o) in SubList
     */
    @Test
    public void addTest() 
    {
        sl.add(12);
        assertEquals(7, sl.size());
        assertEquals(11, l.size());
        assertTrue(sl.contains(12));
        assertTrue(l.contains(12));
    }

    /**
     * test eccezione lanciata da add(Object o) in SubList
     */
    @Test
    public void addExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {sl.add(null);});
    }

    /**
     * test metodo add(int index, Object element) in SubList
     */
    @Test
    public void addIndexTest() 
    {
        sl.add(0,10);
        assertTrue(sl.get(0).equals(10));
        sl.add(sl.size(), 30);
        assertTrue(sl.get(sl.size() - 1).equals(30));
        assertEquals(12, l.size());
        assertEquals(8, sl.size());
    }

    /**
     * test eccezioni lanciate dal metodo add(int index, Object element) in SubList
     */
    @Test
    public void addIndexExceptionTest() 
    {
        assertThrows(IndexOutOfBoundsException.class, () -> {sl.add(sl.size() + 1, 111);});

        assertThrows(IndexOutOfBoundsException.class, () -> {sl.add(-1, 111);});

        assertEquals(6, sl.size());

        assertThrows(NullPointerException.class, () -> {sl.add(100, null);});
    }

    /**
     * test metodo addAll(HCollection c) in SubList
     */
    @Test
    public void addAllTest() 
    {
        assertTrue(sl.addAll(putValues()));
        assertEquals(16, sl.size());
        assertEquals(20, l.size());
        assertTrue(sl.get(sl.size() - 1).equals(9));
        assertTrue(sl.get(sl.size() - 10).equals(0));
    }

    /**
     * test eccezione lanciata da addAll(HCollection c) in SubList
     */
    @Test
    public void addAllExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {sl.addAll(null);});
    }

    /**
     * test metodo addAll(int index, HCollection c) in SubList
     */
    @Test
    public void addAllIndexTest() 
    {
        sl.addAll(0,putValues());
        assertEquals(16, sl.size());
        assertEquals(20, l.size());
        for (int i = 0; i < sl.size(); i++)
            assertTrue(sl.get(i).equals(l.get(i + 2)));
    }

    /**
     * test eccezioni lanciate da addAll(int index, HCollection c) in SubList
     */
    @Test
    public void addAllIndexExceptionsTest() 
    {
        assertThrows(NullPointerException.class, () -> {sl.addAll(sl.size()-1,null);});

        assertThrows(IndexOutOfBoundsException.class, () -> {sl.addAll(-1, putValues());});
    }

    /**
     * test metodo contains(Object o) in SubList
     */
    @Test
    public void containsTest() 
    {
        assertTrue(sl.contains(5));
        assertFalse(sl.contains(20));
        assertFalse(l.contains(20));
    }

    /**
     * test eccezione lanciata da contains(Object o) in SubList
     */
    @Test
    public void containsExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {sl.contains(null);});
    }

    
    /**
     * test metodo containsAll(HCollection c) in SubList
     */
    @Test
    public void containsAllTest() 
    {
        HCollection c = putValues();
        assertFalse(sl.containsAll(c));
        sl.addAll(c);
        assertTrue(sl.containsAll(c));
    }

    /**
     * test dell'eccezione lanciata da containsAll(HCollection c) in SubList
     */
    @Test
    public void containsAllExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {sl.containsAll(null);});
    }

    /**
     * test metodo clear() in SubList
     */
    @Test
    public void clearTest() 
    {
        sl.clear();
        assertEquals(0, sl.size());
        assertEquals(4, l.size());
        sl.addAll(putValues());
        assertEquals(10, sl.size());
        assertEquals(14, l.size());
        sl.clear();
        assertEquals(0, sl.size());
        assertEquals(4, l.size());
    }

    /**
     * test metodo equals(Object o) in SubList
     */
    @Test
    public void equalsTest() 
    {
        HCollection c = putValues();
        l.addAll(4, c);
        HList sl2 = l.subList(4, 4 + c.size());
        ListAdapter list = new ListAdapter();
        list.addAll(c);
        assertTrue((sl2.equals(list)));
    }

    /**
     * test metodo get(int index) in SubList
     */
    @Test
    public void getTest() 
    {
        assertEquals(2, sl.get(0));
        assertEquals(5, sl.get(3));
        assertEquals(7, sl.get(sl.size() - 1));
    }

    /**
     * test eccezione lanciata dal metodo get(int index) in SubList
     */
    @Test
    public void getExceptionTest() 
    {
        assertThrows(IndexOutOfBoundsException.class, () -> {sl.get(-1);});

        assertThrows(IndexOutOfBoundsException.class, () -> {sl.get(sl.size());});

        sl.clear();
        assertThrows(IndexOutOfBoundsException.class, () -> {sl.get(0);});
    }

    /**
     * test metodo hashCode() in SubList
     */
    @Test
    public void hashCodeTest() 
    {
        ListAdapter l2 = new ListAdapter();
        l2.addAll(putValues());
        HList sl2 = l2.subList(2, 8);
        assertTrue(sl.equals(sl2));
        assertTrue(sl.hashCode() == sl2.hashCode());
        sl.set(0, 9);
        sl.set(1, 4);
        assertFalse(sl.hashCode() == sl2.hashCode());
    }

    /**
     * test metodo isEmpty() in SubList
     */
    @Test
    public void isEmptyTest() 
    {
        assertFalse(sl.isEmpty());
        sl.clear();
        assertTrue(sl.isEmpty());
        sl.add(100);
        assertFalse(sl.isEmpty());
    }

    /**
     * test metodo indexOf(Object o) in SubList
     */
    @Test
    public void indexOfTest() 
    {
        assertNotEquals(0, sl.indexOf(4));
        assertEquals(3, sl.indexOf(5));
    }

    /**
     * test dell'eccezione lanciata dal metodo indexOf(Object o) in SubList
     */
    @Test
    public void indexOfExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {sl.indexOf(null);});

        sl.clear();
        assertThrows(NullPointerException.class, () -> {sl.indexOf(null);});
    }

     /**
     * test metodo lastIndexOf(Object o) in SubList
     */
    @Test
    public void lastIndexOfTest() 
    {
        assertEquals(-1, sl.lastIndexOf("tanto non mi trovi :)"));
        sl.addAll(putValues());
        assertEquals(11, sl.lastIndexOf(5));
    }

    /**
     * test eccezione lanciata dal metodo lastIndexOf(Object o) in SubList
     */
    @Test
    public void lastIndexOfExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {sl.lastIndexOf(null);});
    }

    /**
     * test metodo remove(int index) in SubList
     */
    @Test
    public void removeIndexTest() 
    {
        assertTrue(sl.get(0).equals(2));
        sl.remove(0);
        assertFalse(sl.contains(2));
        assertEquals(9, l.size());
        assertEquals(5,sl.size());
    }

    /**
     * test eccezione lanciata dal metodo remove(int index) in SubList
     */
    @Test
    public void removeIndexExceptionTest() 
    {
        assertThrows(IndexOutOfBoundsException.class, () -> {sl.remove(-1);});

        assertThrows(IndexOutOfBoundsException.class, () -> {sl.remove(sl.size());});
    }


    /**
     * test metodo remove(Object o) in SubList
     */
    @Test
    public void removeObjectTest() 
    {
        assertTrue(sl.remove((Object)5));
        assertFalse(l.contains(5));
        assertFalse(sl.remove((Object) 5));
        assertEquals(9, l.size());
        assertEquals(5, sl.size());
    }

    /**
     * test eccezione lanciata da remove(Object o) in SubList
     */
    @Test
    public void removeObjectExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {sl.remove(null);});

        sl.clear();
        assertThrows(NullPointerException.class, () -> {sl.remove(null);});
    }

    /**
     * test metodo removeAll(HCollection c) in SubList
     */
    @Test
    public void removeAllTest() 
    {
        sl.add(11);
        sl.add(12);
        sl.add(13);
        assertTrue(sl.removeAll(putValues()));
        assertEquals(3, sl.size());
        assertEquals(7, l.size());
    }

    /**
     * test eccezione lanciata dal metodo removeAll(HCollection c) in SubList
     */
    @Test
    public void removeAllExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {sl.removeAll(null);});

        sl.clear();
        assertThrows(NullPointerException.class, () -> {sl.removeAll(null);});
    }

    
    /**
     * test metodo subList() in SubList e relativa eccezione
     */
    @Test
    public void subListTest() 
    {
        assertTrue(sl.size()==6);
        HList subsub = sl.subList(0, 2);
        assertTrue(subsub.contains(2));
        assertTrue(subsub.size()==2);

        assertThrows(IndexOutOfBoundsException.class, () -> {sl.subList(sl.size(), 1);});

    }




    /**
     * test metodo retainAll(HCollection c) in SubList
     */
    @Test
    public void retainAllTest() 
    {
        HCollection c = putValues();
        assertFalse(sl.retainAll(c));
        sl.add(20);
        assertTrue(sl.retainAll(c));
        assertFalse(sl.contains(20));
    }

    /**
     * test eccezione lanciata dal metodo retainAll(HCollection c) in SubList
     */
    @Test
    public void retainAllExceptionTest() 
    {
        assertThrows(NullPointerException.class, () -> {sl.retainAll(null);});

        sl.clear();
        assertThrows(NullPointerException.class, () -> {sl.retainAll(null);});
    }




    /**
     * test metodo set(int index, Object element) in SubList
     */
    @Test
    public void setTest() 
    {
        assertEquals(6, sl.size());
        sl.set(0, 100);
        assertEquals(6, sl.size());
        assertEquals(10, l.size());
        assertTrue(sl.contains(100));
        assertTrue(l.contains(100));  
    }

    /**
     * test eccezioni lanciate dal metodo set(int index, Object element) in SubList
     */
    @Test
    public void setExceptionsTest() 
    {
        assertThrows(NullPointerException.class, () -> {sl.set(0, null);});

        sl.addAll(putValues());
        assertThrows(IndexOutOfBoundsException.class, () -> {sl.set(-1, 1);});

        assertThrows(IndexOutOfBoundsException.class, () -> {sl.set(sl.size(), 2);});

        assertThrows(NullPointerException.class, () -> {sl.set(sl.size()-1, null);});
    }

    /**
     * test metodo size() in SubList
     */
    @Test
    public void sizeTest() 
    {
        assertEquals(6, sl.size());
        sl.addAll(putValues());
        assertEquals(16, sl.size());
        sl.add(1);
        assertEquals(17, sl.size());
        assertEquals(21, l.size());
    }

    /**
     * test metodo toArray() in SubList
     */
    @Test
    public void toArrayTest() 
    {
        Object[] a = sl.toArray();
        assertEquals(sl.size(), a.length);
        for (int i = 0; i < a.length; i++)
            assertEquals(a[i],sl.get(i));
    }

    /**
     * test metodo toArray(Object[] a) 
     */
    @Test
    public void toArrayATest() 
    {
        Object[] a = new Object[sl.size()];
        for (int i = 0; i < a.length; i++)
           a[i] = i+1;
        a = sl.toArray(a);
        assertEquals(sl.size(), a.length);
        for (int i = 0; i < a.length; i++)
            assertEquals(a[i],sl.get(i));
    }

    
    /**
     * test eccezioni lanciate da toArray(Object[] a) in SubList
     */
    @Test
    public void toArrayAExceptionsTest() 
    {
        Object[] a = new String[sl.size()];
        assertThrows(ArrayStoreException.class, () -> {sl.toArray(a);});

        assertThrows(NullPointerException.class, () -> {sl.toArray(null);});
    }


    //___________________________________________________________________________________________________
    // test di listIterator/SubListIterator in SubList e di tutti i metodi ad esso correlati


    /**
     * test metodo listIterator() più hasNext()
     */
    @Test
    public void listIteratorTest() 
    {
        HListIterator iter = sl.listIterator();
        int size = 0;
        while (iter.hasNext()) 
        {
            iter.next();
            size++;
        }
        assertEquals(sl.size(), size);
        assertFalse(iter.hasNext());
    }

    /**
     * test metodo listIterator(int index)
     */
    @Test
    public void listIteratorIndexTest() 
    {
        int index = 4;
        HListIterator iter = sl.listIterator(index);
        while (iter.hasNext()) 
        {
            assertTrue(sl.get(index).equals(iter.next()));
            index++;
        }
        assertEquals(sl.size(), index);
    }

    /**
     * test eccezione listIterator(int index)
     */
    @Test
    public void listIteratorIndexExceptionTest() 
    {
        assertThrows(IndexOutOfBoundsException.class, () -> {sl.listIterator(-1);});
        assertThrows(IndexOutOfBoundsException.class, () -> {sl.listIterator(sl.size() + 1);});
    }

    /**
     * test metodo add(Object o) con relativa eccezione
     */
    @Test
    public void listIteratorAddTest() 
    {
        HListIterator iter = sl.listIterator();
        iter.add(11);
        assertTrue(sl.contains(11));
        assertTrue(l.contains(11));
        assertEquals(7, sl.size());
        assertEquals(11, l.size());

        assertThrows(IllegalArgumentException.class, () -> {iter.add(null);});
    }

    /**
     * test metodo hasPrevious()
     */
    @Test
    public void listIteratorHasPreviousTest() 
    {
        HListIterator iter = sl.listIterator();
        assertFalse(iter.hasPrevious());
        while (iter.hasNext()) 
        {
            iter.next();
            assertTrue(iter.hasPrevious());
        }
        assertTrue(iter.hasPrevious());
        assertFalse(iter.hasNext());
    }


    /**
     * test del metodo next() e relativa eccezione
     */
    @Test
    public void listIteratorNextTest() 
    {
        HListIterator iter = sl.listIterator();
        int count = 0;
        while (iter.hasNext()) 
        {
            assertTrue(iter.next().equals(sl.get(count)));
            count++;
        }
        assertEquals(sl.size(), count);

        assertThrows(NoSuchElementException.class, () -> {iter.next();});
    }
    
   
    /**
     * test metodo nextIndex()
     */
    @Test
    public void listIteratorNextIndexTest() 
    {
        HListIterator iter = sl.listIterator();
        int count = 0;
        assertEquals(count, iter.nextIndex());
        while (iter.hasNext()) 
        {
            assertEquals(count, iter.nextIndex());
            count++;
            iter.next();
        }
        assertEquals(sl.size(), iter.nextIndex());
        assertEquals(sl.size(),count);
    }

    /**
     * test metodo previous() con relativa eccezione
     */
    @Test
    public void listIteratorPreviousTest() 
    {
        HListIterator iter = sl.listIterator(sl.size());
        int index = sl.size()-1 ;
        while (iter.hasPrevious()) 
        {
            assertTrue(sl.get(index).equals(iter.previous()));
            index--;
        }
        assertFalse(iter.hasPrevious());

        assertThrows(NoSuchElementException.class, () -> {iter.previous();});
    }

    /**
     * test metodo previousIndex()
     */
    @Test
    public void listIteratorPreviousIndexTest() 
    {
        HListIterator iter = sl.listIterator(sl.size());
        int index = sl.size() - 1;
        while (iter.hasPrevious()) 
        {
            assertEquals(index, iter.previousIndex());
            index--;
            iter.previous();
        }
    }

    /**
     * test del metodo set(Object o)
     */
    @Test
    public void listIteratorSetTest() 
    {
        HListIterator iter = sl.listIterator();
        iter.next();
        iter.set(20);
        assertEquals(6, sl.size());
        assertTrue(sl.contains(20));
    }

    /**
     * test eccezioni set(Object o)
     */
    @Test
    public void listIteratorSetExceptionsTest() 
    {
        HListIterator it = sl.listIterator();
        assertThrows(IllegalArgumentException.class, () -> {it.set(null);});
        assertThrows(IllegalStateException.class, () -> {it.set("tanto non va :)");});
    }


    /**
     * test del metodo remove() caso next()
     */
    @Test
    public void listIteratorRemoveNextTest() 
    {
        HListIterator iter = sl.listIterator();
        while (iter.hasNext()) 
        {
           Object elem = iter.next();
           iter.remove();
           assertFalse(sl.contains(elem));
        }
        assertEquals(0, sl.size());
        assertEquals(4, l.size());
    }

    /**
     * test del metodo remove() caso previous()
     */
    @Test
    public void listIteratorRemovePreviousTest() 
    {
        HListIterator it = sl.listIterator(sl.size());
        while (it.hasPrevious()) 
        {
            Object elem = it.previous();
            it.remove();
            assertFalse(sl.contains(elem));
        }
        assertEquals(0, sl.size());
        assertEquals(4, l.size());
    }

    /**
     * test eccezione lanciata dal metodo remove() di listIterator()
     */
    @Test
    public void listIteratorRemoveExceptionTest() 
    {
        HListIterator iter = sl.listIterator();
        assertThrows(IllegalStateException.class, () -> {iter.remove();});
    }
}
