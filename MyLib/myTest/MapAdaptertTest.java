package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapAdaptertTest 
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


    /**
     * test metodo clear()
     */
    @Test
    public void clearTest()
    {
        assertEquals(20,map.size());
        map.clear();
        assertNotEquals(20,map.size());
        assertEquals(0, map.size());
    }


    /**
     * test metood size()
     */
    @Test
    public void sizeTest()
    {
        assertEquals(20,map.size());
        map.put(21,21);
        assertEquals(21, map.size());
        map.clear();
        assertNotEquals(21,map.size());
        map.put(1,1);
        assertEquals(map.size(),1);

    }

    /**
     * test metodo containsKey(Object key)
     */
    @Test
    public void containsKeyTest()
    {
        assertTrue(map.containsKey(2));
        assertFalse(map.containsKey(1000));
        map.put(21,21);
        assertTrue(map.containsKey(21));
        map.clear();
        assertFalse(map.containsKey(2));
    }

    /**
     * test eccezione lanciata da containsKey(Object value)
     */
    @Test
    public void containsKeyException()
    {
        assertThrows(NullPointerException.class, () -> {map.containsKey(null);});
        map.clear();
        assertThrows(NullPointerException.class, () -> {map.containsKey(null);});

    }

    /**
     * test metodo containsValue(Object value)
     */
    @Test
    public void containsValueTest()
    {
        assertTrue(map.containsValue(2));
        assertFalse(map.containsValue(1000));
        map.put(21,21);
        assertTrue(map.containsValue(21));
        map.clear();
        assertFalse(map.containsValue(2));
    }
    /**
     * test eccezione lanciata da containsValue(Object value)
     */
    @Test
    public void containsValueException()
    {
        assertThrows(NullPointerException.class, () -> {map.containsKey(null);});
        map.clear();
        assertThrows(NullPointerException.class, () -> {map.containsKey(null);});
    }

    /**
     * test metodo entrySet()
     */
    @Test
    public void entrySetTest()
    {
        HSet set = map.entrySet();
        assertEquals(set.size(), map.size());
        map.put(21,21);
        assertEquals(set.size(), map.size());
        map.remove(21);
        map.remove(20);
        assertFalse(set.contains(21));
        assertFalse(set.contains(20));
    }

    /**
     * test metodo equals(Object o)
     */
    @Test
    public void equalsTest()
    {
        MapAdapter map2 = new MapAdapter(map);
        assertEquals(map2.size(), map.size());
        for(int i =0;i<20;i++)
        {
            assertTrue(map.containsKey(i));
            assertTrue(map2.containsKey(i));
            assertTrue(map.containsValue(i));
            assertTrue(map2.containsValue(i));
        }
    }
   
    /**
     * test metodo get(Object key)
     */
    @Test
    public void getTest()
    {
        assertEquals(map.get(2),2);
        assertNotEquals(map.get(12),1);
        map.remove(2);
        assertEquals(19, map.get(map.size()));
    }
    /**
     * test eccezione get(Object key)
     */
    @Test
    public void getExceptionTest()
    {
        assertThrows(NullPointerException.class, () -> {map.get(null);});
        map.clear();
        assertThrows(NullPointerException.class, () -> {map.get(null);});
    }


    /** 
     * test metodo hashCode()
     */
    @Test
    public void hashCodeTest()
    {
        MapAdapter map2 = new MapAdapter(map);
        assertEquals(map.hashCode(), map2.hashCode());
        map.put(21, "ciao");
        assertNotEquals(map.hashCode(), map2.hashCode());
    }

    /**
     * test metodo isEmpty()
     */
    @Test
    public void isEmptyTest()
    {
        assertEquals(20,map.size());
        assertFalse(map.isEmpty());
        map.clear();
        assertTrue(map.isEmpty());
        map.put(0,0);
        assertFalse(map.isEmpty());
        map.remove(0);
        assertTrue(map.isEmpty());
    }

    /**
     * test metodo keySet()
     */
    @Test
    public void keySetTest()
    {
        HSet set = map.keySet();
        assertEquals(set.size(), map.size());
        for(int i =0;i<20;i++)
        {
            assertTrue(map.containsKey(i));
            assertTrue(set.contains(i));
        }
        map.put(21,21);
        assertTrue(set.contains(21));
        
    }

    /**
     * test metodo put(Object key, Object value)
     */
    @Test
    public void putTest()
    {
        assertEquals(map.size(), 20);
        map.put(21,"ciao");
        assertEquals(map.size(), 21);
        assertTrue(map.containsKey(21));
        assertTrue(map.containsValue("ciao"));
    }
    /**
     * test eccezione put(Object key,Object value)
     */
    @Test
    public void putExceptionTest()
    {
        assertThrows(NullPointerException.class, () -> {map.put(1,null);});
        assertThrows(NullPointerException.class, () -> {map.put(null,null);});
        assertThrows(NullPointerException.class, () -> {map.put(null,3);});
    }

    /**
     * test metodo putAll(HMap t)
     */
    @Test
    public void putAllTest()
    {
        MapAdapter map1 = new MapAdapter();
        assertNotEquals(map1.size(), map.size());
        map1.putAll(map);
        assertTrue(map.equals(map1));
    }
    /**
     * test eccezione putAll(hMap t)
     */
    @Test
    public void putAllExceptionTest()
    {
        assertThrows(NullPointerException.class, () -> {map.putAll(null);});
    }

    /**
     * test metodo remove(Object key)
     */
    @Test
    public void removeTest()
    {
        assertEquals(map.size(),20);
        map.remove(19);
        assertEquals(19, map.size());
        assertFalse(map.containsKey(19));
    }
    /**
     * test eccezione remove(Object key)
     */
    @Test
    public void removeExceptionTest()
    {
        assertThrows(NullPointerException.class, () -> {map.remove(null);});
    }

    /**
     * test metodo values()
     */
    @Test
    public void valuesTest()
    {
        HCollection valueSet = map.values();
        assertEquals(valueSet.size(),map.size());
        map.put(21,21);
        assertEquals(valueSet.size(),map.size());
        assertTrue(valueSet.contains(21));
    }

}
