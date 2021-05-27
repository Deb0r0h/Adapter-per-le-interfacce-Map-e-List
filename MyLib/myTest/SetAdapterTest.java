package myTest;

import myAdapter.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


    
public class SetAdapterTest 
{
    //mappa usata in tutti i test
    MapAdapter map;
    
    
    // riempio la mappa con 20 valori che vanno da 0 a 19, analogo per le chiavi
    @Before
    public void create() 
    {
        map= new MapAdapter();
        

        int val = 0;
        for(int i =0;i<20;i++)
        {
            map.put(i,val);
            val++;
        }
    }
  
//-----------------------------------------------------------------------------------------

                                         // TEST CLASSE SETENTRY

    /**
     * test metodo clear()
     */
    @Test
    public void clearSetEntryTest()
    {
        HSet setEntry = map.entrySet();
        assertEquals(map.size(), setEntry.size());
        setEntry.clear();
        assertEquals(map.size(), setEntry.size());
        assertEquals(setEntry.size(), 0);
    }

    /**
     * test metodo isEmpty()
     */
    @Test
    public void isEmptySetEntryTest()
    {
        HSet setEntry = map.entrySet();
        assertEquals(map.size(), setEntry.size());
        map.clear();
        assertEquals(setEntry.size(), 0);
        assertEquals(map.size(), setEntry.size());
    }

    /**
     * test metodo retainAll(HCollection c)
     */
    @Test
    public void retainAllSetEntryTest()
    {
        HSet setEntry = map.entrySet();
        HMap map2 = new MapAdapter(map);
        HSet EntryCollection = map2.entrySet();
        map2.put(20, 20);
        map2.put(21,21);
        assertNotEquals(setEntry.size(), EntryCollection.size());
        assertEquals(EntryCollection.size(),22);
        setEntry.retainAll(EntryCollection);
        assertFalse(map.containsValue(20));
        assertFalse(map.containsValue(21));
        assertFalse(setEntry.contains(20));
        assertFalse(setEntry.contains(21));
        assertEquals(setEntry.size(), 20);
    }
    /**
     * test eccezione retainAll(HCollection c)
     */
    @Test
    public void retainAllSetEntryExceptionTest()
    {
        HSet entrySet = map.entrySet();
        assertThrows(NullPointerException.class, () -> {entrySet.retainAll(null);});
        entrySet.clear();
        assertThrows(NullPointerException.class, () -> {entrySet.retainAll(null);});

    }

    /**
     * test metodo toArray()
     */
    @Test
    public void toArrayEntrySetTest()
    {
        HSet entrySet = map.entrySet();
        Object[] a = entrySet.toArray();
        assertEquals(entrySet.size(), a.length);
        for(int i=0;i<a.length;i++)
            assertTrue(entrySet.contains(a[i]));
    }

    /**
     * test metodo toArray(Object a) 
     */
    @Test
    public void toArrayAEntrySetTest()
    {
        HSet setEntry = map.entrySet();
        Object[] a = new Object[setEntry.size()];
        for (int i = 0; i < a.length; i++)
            a[i] = i;
        assertEquals(20, a.length);
        a = setEntry.toArray(a);
        assertEquals(20, a.length);
        for(int i=0;i<a.length;i++)
            assertTrue(setEntry.contains(a[i]));
        
    }
    /**
     * test eccezione toArray(Object a)
     */
    @Test
    public void toArrayExceptionEntrySetTest() 
    {
        HSet setEntry = map.entrySet();
        Object[] a = new String[setEntry.size()];
        assertThrows(ArrayStoreException.class, () -> {setEntry.toArray(a);});

        assertThrows(NullPointerException.class, () -> {setEntry.toArray(null);});
    }

    /**
     * test metodo contains(Object o)
     */
    @Test
    public void containsEntrySetTest()
    {
        HSet entrySet = map.entrySet();
        HIterator it = entrySet.iterator();
        while (it.hasNext())
            assertTrue(entrySet.contains(it.next()));
    }
    /**
     * test eccezione contains(Object o)
     */
    @Test
    public void containsEntrySetExceptionTest()
    {
        HSet entrySet = map.entrySet();
        assertThrows(NullPointerException.class, () -> entrySet.contains(null));
    }

    /**
     * test metodo containsAll(HCollection)
     */
    @Test
    public void containsAllEntrySetTest()
    {
        HSet entrySet = map.entrySet();
        HMap map2 = new MapAdapter();
        HSet SetCollection = map2.entrySet();
        int val =0;
        for(int i = 0;i<20;i++)
        {
            map2.put(i,val);
            val++;
        }
        assertTrue(entrySet.containsAll(SetCollection));
        map.put(10,"combino guai");
        assertFalse(entrySet.containsAll(SetCollection));
    }
    /**
     * test eccezione containsAll(HCollection c)
     */
    @Test
    public void containsAllEntrySetExceptionTest()
    {
        HSet entrySet = map.entrySet();
        assertThrows(NullPointerException.class, () -> entrySet.containsAll(null));
    }

    /**
     * test metodo hashCode()
     */
    @Test
    public void hashCodeEntrySetTest()
    {
        HSet entrySet = map.entrySet();
        HMap map2 = new MapAdapter();
        int val =0;
        for(int i = 0;i<20;i++)
        {
            map2.put(i,val);
            val++;
        }
        assertTrue(entrySet.hashCode() == map2.entrySet().hashCode());
        map.put(10, "combino guai");
        assertFalse(entrySet.hashCode() == map2.entrySet().hashCode());
    }

    /**
     * test metodo remove(Object o)
     */
    @Test
    public void removeEntrySetTest()
    {
        HSet entrySet = map.entrySet();
        HMap.Entry elem = (HMap.Entry) entrySet.iterator().next();
        entrySet.remove(elem);
        assertFalse(entrySet.contains(elem));
        assertEquals(entrySet.size(),19);
    }
    /**
     * test eccezione remove(Object o)
     */
    @Test
    public void removeEntrySetExceptionTest()
    {
        HSet entrySet = map.entrySet();
        assertThrows(NullPointerException.class, () -> entrySet.remove(null));
        entrySet.clear();
        assertThrows(NullPointerException.class, () -> entrySet.remove(null));
    }

    /**
     * test metodo removeAll(HCollection c)
     */
    @Test
    public void removeAllEntrySetTest()
    {
        HSet entrySet = map.entrySet();
        MapAdapter map2 = new MapAdapter();
        int val =0;
        for(int i = 0;i<10;i++)
        {
            map2.put(i,val);
            val++;
        }
        HSet entrySet2 = map2.entrySet();
        entrySet.removeAll(entrySet2);
        assertEquals(entrySet.size(), 10);
        for(int i =0;i<10;i++)
            assertFalse(entrySet.contains(i));
    }
    /**
     * test eccezione removeAll(HCollection)
     */
    @Test
    public void removeAllEntrySetExceptionTest()
    {
        HSet entrySet = map.entrySet();
        assertThrows(NullPointerException.class, () -> entrySet.remove(null));
        entrySet.clear();
        assertThrows(NullPointerException.class, () -> entrySet.remove(null));
    }

    /**
     * test metodo size()
     */
    @Test
    public void sizeEntrySetTest()
    {
        HSet entrySet = map.entrySet();
        assertEquals(map.size(), entrySet.size());
        assertEquals(map.size(), 20);
        assertEquals(entrySet.size(),20);
        map.put(20, 20);
        assertEquals(entrySet.size(),21);
    }

    /**
     * test metodo iterator()
     */
    @Test
    public void entrySetIteratorTest()
    {
        HSet entrySet = map.entrySet();
        HIterator iter = entrySet.iterator();
        int i =0;
        while(iter.hasNext())
        {
            iter.next();
            i++;
        }
        assertTrue(i==entrySet.size());

    }

    /**
     * test metodo equals()
     */
    @Test
    public void equalsEntrySetTest()
    {
        HSet entrySet = map.entrySet();
        HMap map2 = new MapAdapter();
        HSet entrySet2 = map2.entrySet();
        int val =0;
        for(int i = 0;i<20;i++)
        {
            map2.put(i,val);
            val++;
        }
        assertEquals(entrySet.size(),entrySet2.size());
        assertTrue(entrySet.equals(entrySet2));
        map.put(20, "ti renderò falso");
        assertFalse(entrySet.equals(entrySet2));
    }

    /**
     * test eccezione add(Object o) e addAll(HCollection c)
     */
    @Test
    public void addAndAddAllEntrySetExceptionTest()
    {
        HSet entrySet = map.entrySet();
        assertThrows(UnsupportedOperationException.class,()->{entrySet.add("tanto non vai");});
        
        HMap map2 = new MapAdapter();
        HSet entrySet2 = map2.entrySet();
        map2.put(20,20);
        map2.put(21,21);
        assertThrows(UnsupportedOperationException.class,()->{entrySet.addAll(entrySet2);});
    }


                                 //  TEST DI ENTRYITERATOR E DEI RELATIVI METODI

    @Test
    public void nextAndHasNextEntrySetIteratorTest()
    {
        HSet entrySet = map.entrySet();
        HIterator iter = entrySet.iterator();
        assertTrue(entrySet.size()==20);
        int i=0;
        while (iter.hasNext())
        {
          Object elem = iter.next();
           assertTrue(entrySet.contains(elem));
           i++;
        }
        assertTrue(i==entrySet.size());
    }
    /**
     * test eccezione next()
     */
    @Test
    public void nextEntrySetIteratorExceptionTest()
    {
        HSet entrySet = map.entrySet();
        HIterator iter = entrySet.iterator();
        while(iter.hasNext())
            iter.next();

        assertThrows(NoSuchElementException.class,()->{iter.next();});
    }
    
    /**
     * test metodo remove()
     */
    @Test 
    public void removeEntrySetIteratorTest()
    {
        HSet entrySet = map.entrySet();
        HIterator iter = entrySet.iterator();
        assertTrue(entrySet.size()==20);
        Object temp = iter.next();
        iter.remove();
        assertFalse(entrySet.contains(temp));
    }
    /**
     * test eccezione remove()
     */
    @Test
    public void removeEntrySetIteratorExceptionTest() 
    {
        HSet entrySet = map.entrySet();
        HIterator iter = entrySet.iterator();
        assertThrows(IllegalStateException.class, () -> {iter.remove();});
    }


    //--------------------------------------------------------------------------------------------------

                                                // TEST CLASSE SETVALUE E SETKEY

    
    /* alcuni test sono in comune poichè l'implementazione dei relativi metodi coincidono. Entrambi i set nei vari metodi hanno
    * Object o HCollection come argomento di conseguenza (ad eccezione di null) sono equivalenti l'uno con l'altro.
    * Quindi volte userò oggetti setKey o setValue indistintamente.
    * Ad eccezione dei metodi di remove(Object o) e iterator()
    * I metodi ereditati da setEntry: equals(Object o);retainAll(HCollection),add(Object o);addAll(HCollection);
    * size();clear();isEmpty() sono già stati testati in setEntry "test"
    */

    /**
     * test metodo contains(Object o)
     */
    @Test
    public void containsSetKeyValueTest()
    {
        HSet setKey = map.keySet();
        HIterator it = setKey.iterator();
        while (it.hasNext())
            assertTrue(setKey.contains(it.next()));
        HSet setval = (HSet)map.values();
        HIterator iter = setval.iterator();
        while (iter.hasNext())
            assertTrue(setval.contains(iter.next()));
    }
    /**
     * test eccezione contains(Object o)
     */
    @Test
    public void containsSetKeyValueExceptionTest()
    {
        HSet setValue =(HSet) map.values();
        HSet setKey = map.keySet();
        assertThrows(NullPointerException.class, () -> setKey.contains(null));
        assertThrows(NullPointerException.class, () -> setValue.contains(null));
    }

    
    /**
     * test metodo containsAll(HCollection)
     */
    @Test
    public void containsAllSetKeyValueTest()
    {
        HSet setValue =(HSet) map.values();
        HMap map2 = new MapAdapter();
        HSet SetCollection = (HSet) map2.values();
        int val =0;
        for(int i = 0;i<20;i++)
        {
            map2.put(i,val);
            val++;
        }
        assertTrue(setValue.containsAll(SetCollection));
        
    }
    /**
     * test eccezione containsAll(HCollection c)
     */
    @Test
    public void containsAllSetKeyValueExceptionTest()
    {
        HSet setValue =(HSet) map.values();
        HSet setKey = map.keySet();
        assertThrows(NullPointerException.class, () -> setKey.containsAll(null));
        assertThrows(NullPointerException.class, () -> setValue.containsAll(null));
    }

    /**
     * test metodo hashCode()
     */
    @Test
    public void hashCodeSetKeyValueTest()
    {
        HSet setKey = map.keySet();
        HMap map2 = new MapAdapter();
        int val =0;
        for(int i = 0;i<20;i++)
        {
            map2.put(i,val);
            val++;
        }
        HSet setKey2 = map2.keySet();
        assertTrue(setKey.hashCode() == setKey2.hashCode());
        map2.put(21, "rompo");
        assertFalse(setKey.hashCode()==setKey2.hashCode());
    }


    /**
     * test removeAll(HCollection)
     */
    @Test
    public void removeAllSetKeyValueTest()
    {
        HSet setValue = (HSet)map.values();
        MapAdapter map2 = new MapAdapter();
        int val =0;
        for(int i = 0;i<10;i++)
        {
            map2.put(i,val);
            val++;
        }
        HSet setValue2 =(HSet) map2.values();
        setValue.removeAll(setValue2);
        assertEquals(setValue.size(), 10);
        for(int i =0;i<10;i++)
            assertFalse(setValue.contains(i));
    }

    /**
     * test eccezione removeAll(HCollection c)
     */
    @Test
    public void removeAllSetKeyValueExceptionTest()
    {
        HSet setValue =(HSet) map.values();
        HSet setKey = map.keySet();
        assertThrows(NullPointerException.class, () -> setKey.remove(null));
        setKey.clear();
        assertThrows(NullPointerException.class, () -> setKey.remove(null));

        assertThrows(NullPointerException.class, () -> setValue.remove(null));
        setValue.clear();
        assertThrows(NullPointerException.class, () -> setValue.remove(null));
    }

    /**
     * test toArray()
     */
    @Test
    public void toArraySetKeyValueTest()
    {
        HSet valuSet =(HSet) map.values();
        Object[] a1 = valuSet.toArray();
        assertEquals(valuSet.size(), a1.length);
        for(int i=0;i<a1.length;i++)
            assertTrue(valuSet.contains(a1[i]));
    }

    /**
     * test metodo toArray(Object a)
     */
    @Test
    public void toArrayASetKeyValueTest()
    {
        HSet setValue = (HSet)map.values();
        Object[] a = new Object[setValue.size()];
        for (int i = 0; i < a.length; i++)
            a[i] = i+1;
        assertEquals(20, a.length);
        a = setValue.toArray(a);
        assertEquals(20, a.length);
        for(int i=0;i<a.length;i++)
            assertTrue(setValue.contains(a[i]));
    }
    /**
     * test eccezione toArray(Object a)
     */
    @Test
    public void toArraySetKeyValueExceptionTest() 
    {
        HSet setValue =(HSet) map.values();
        HSet setKey = map.keySet();
        Object[] a = new String[map.size()];
        assertThrows(ArrayStoreException.class, () -> {setKey.toArray(a);});
        assertThrows(NullPointerException.class, () -> {setKey.toArray(null);});

        assertThrows(ArrayStoreException.class, () -> {setValue.toArray(a);});
        assertThrows(NullPointerException.class, () -> {setValue.toArray(null);});
    }

    /**
     * test remove(Object o) di setValue
     */
    @Test
    public void removeSetValueTest()
    {
        HSet setValue = (HSet)map.values();
        assertTrue(setValue.size()==20);
        assertTrue(setValue.contains(2));
        assertTrue(setValue.contains(10));
        setValue.remove(2);
        assertFalse(setValue.contains(2));
        assertTrue(setValue.size()==19);
    }
    /**
     * test eccezione remove(object o) di setValue
     */
    @Test
    public void removeSetValueExceptionTest()
    {
        HSet setValue = (HSet)map.values();
        assertThrows(NullPointerException.class, () -> {setValue.remove(null);});
    }

    /**
     * test remove(Object o) di setKey
     */
    @Test
    public void removeSetKeyTest()
    {
        HSet setKey = map.keySet();
        assertTrue(setKey.size()==20);
        assertTrue(setKey.contains(2));
        assertTrue(setKey.contains(10));
        setKey.remove(2);
        assertFalse(setKey.contains(2));
        assertTrue(setKey.size()==19);
    }
    /**
     * test eccezione remove(object o) di setKey
     */
    @Test
    public void removeSetKeyExceptionTest()
    {
        HSet setKey = map.keySet();
        assertThrows(NullPointerException.class, () -> {setKey.remove(null);});
    }

    /**
     * test iterator() di setValue
     * la classe SetValueIterator non comprende altri metodi ad esclusione di hasNext()
     * e next() poichè estende EntryIterator, di conseguenza testo 
     * tutto in questo scope
     */
    @Test
    public void iteratorSetValueTest()
    {
        HSet setValue = (HSet)map.values();
        HIterator iter = setValue.iterator();
        int size = 0;
        assertTrue(setValue.size()==20);
        while(iter.hasNext())
        {
            iter.next();
            size++;
        }
        assertTrue(size==20);
    }

    /**
     * test iterator() di setKey (compeso hasNext di SetKeyIterator)
     */
    @Test
    public void iteratorSetKeyTest()
    {
        HSet setKey = map.keySet();
        HIterator iter = setKey.iterator();
        int size = 0;
        assertTrue(setKey.size()==20);
        while(iter.hasNext())
        {
            iter.next();
            size++;
        }
        assertTrue(size==20);
    }

    //  TEST SETKEYITERATOR

    /**
     * test metodo next()
     */
    @Test
    public void nextSetKeyIteratorTest()
    {
        HSet setKey = map.keySet();
        HIterator iter = setKey.iterator();
        while(iter.hasNext())
            assertTrue(setKey.contains(iter.next())); 
    }

    /**
     * test remove()
     */
    @Test
    public void removeSetKeyIteratorTest()
    {
        HSet setKey = map.keySet();
        HIterator iter = setKey.iterator();
        assertTrue(setKey.size()==20);
        while(iter.hasNext())
        {   iter.next();
            iter.remove();
        }
        assertTrue(setKey.size()==0);
    }
    /**
     * test eccezione remove()
     */
    @Test
    public void removeSetKeyExceptionIteratorTest()
    {
        HSet setKey = map.keySet();
        HIterator iter = setKey.iterator();
        setKey.clear();
        assertThrows(IllegalStateException.class, () -> {iter.remove();});
    }

}
