package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class EntryAdapterTest 
{
    //mappa usata in tutto il test
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


    /**
     * test metodo getKey()
     */
    @Test
    public void getKeyEntryTest()
    {
        HSet set = map.entrySet();
        assertEquals(set.size(), map.size());
        HIterator iter = set.iterator();
        while(iter.hasNext())
        {
            HMap.Entry elem = (HMap.Entry)iter.next();
            assertTrue(map.containsKey(elem.getKey()));
        }
    }

    /**
     * test metodo getValue()
     */
    @Test
    public void getValueEntryTest()
    {
        HSet set = map.entrySet();
        assertEquals(set.size(), map.size());
        HIterator iter = set.iterator();
        while(iter.hasNext())
        {
            HMap.Entry elem = (HMap.Entry)iter.next(); 
            assertTrue(map.containsKey(elem.getValue()));
        }
    }

    /**
     * test setValue(Object o)
     */
    @Test
    public void setValueEntryTest()
    {
        HSet set = map.entrySet();
        assertEquals(set.size(), map.size());
        HIterator iter = set.iterator();
        HMap.Entry elem = (HMap.Entry)iter.next();
        elem.setValue(100);
        assertTrue(map.containsValue(100));
        map.remove(elem.getKey());
        assertFalse(map.containsValue(100));
        assertFalse(set.contains(100));
    }
    /**
     * test eccezione setValues(Object o)
     */
    @Test
    public void setValueEntryExceptionTest()
    {
        HSet set = map.entrySet();
        HIterator iter = set.iterator();
        HMap.Entry elem = (HMap.Entry)iter.next();
        assertThrows(NullPointerException.class, () -> {elem.setValue(null);});
    }

    /**
     * test metodo equals(Object o)
     */
    @Test
    public void equalsEntryTest()
    {
        HSet set = map.entrySet();
        assertEquals(set.size(), map.size());
        HIterator iter = set.iterator();
        HSet otherSet = map.entrySet();
        assertEquals(set.size(), otherSet.size());
        assertTrue(set.equals(otherSet));
    }

    /**
     * test metodo hashCode()
     */
    @Test
    public void hashCodeEntryTest()
    {
        HSet set = map.entrySet();
        assertEquals(set.size(), map.size());
        MapAdapter map2 = new MapAdapter(map);
        HSet set2= map2.entrySet();
        assertTrue(set.hashCode()==set2.hashCode());
        map2.put(21,"rompo tutto");
        assertFalse(set.hashCode()==set2.hashCode());
    }
}
