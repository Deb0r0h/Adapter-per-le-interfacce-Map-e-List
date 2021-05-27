package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * la classe crea un contenitore mappa basandosi su un HashTable per il salvataggio degli elementi
 * e dei suoi metodi, è accettato ogni tipo di elemento ad eccezione di null
 * all'interno della classe sono presenti altre 4 classi. In particolare 2 di esse sono classi che 
 * implementano un set( uno di entry e uno di chiavi) e 1 implementa una collection( istanziata come set)di valori
 * Le entry,chiavi,valori corrispondono a tutti quelli presenti nella mappa
 * ogni azione nei set si riperquote sulla mappa e vicerversa. Ad eccezione delle operazioni di aggiunta che non sono
 * ammesse all'interno dei set, si può aggiungere un oggetto solo tramite la mappa
 */
public class MapAdapter implements HMap
{
    private Hashtable htb;
    private HSet entrySet,valueSet,keySet = null;
    
    public MapAdapter()
    {
        htb = new Hashtable();
    }
    public MapAdapter(HMap m)
    {
        htb = new Hashtable();
        putAll(m);
    }

    public void clear()
    {
        htb.clear();
    }

    /**
     * @throws NullPointerException
     */
    public boolean containsKey(Object key)
    {
        if(key ==null)
            throw new NullPointerException();
        return htb.containsKey(key);
    }

    /**
     * @throws NullPointerException
     */
    public boolean containsValue(Object value)
    {
        if(value==null)
            throw new NullPointerException();
        return htb.contains(value);  
    }
    public HSet entrySet()
    {
        if(entrySet==null)
            entrySet = new MapAdapter.SetEntry();
        return entrySet;
    }
    public boolean equals(Object o)
    {
        if(o==null)
            return false;
        if (!(o instanceof HMap))
            return false;
        HMap m = (HMap)o;
        if (entrySet().equals(m.entrySet()))
            return true;
        return false;
    }

    /**
     * @throws NullPointerException
     */
    public Object get(Object key)
    {
        if(key==null)
            throw new NullPointerException();
        return htb.get(key);
    }

    public int hashCode()
    {
        HSet h = entrySet();
        return h.hashCode();
    }
    public boolean isEmpty()
    {
        return htb.isEmpty();
    }
    public HSet keySet()
    {
       if(keySet==null)
            keySet = new SetKey();
        return keySet;
    }
    
    /**
     * @throws NullPointerException
     */
    public Object put(Object key, Object value)
    {
        if(value==null)
            throw new NullPointerException();
        if(key==null)
            throw new NullPointerException();
        
        return htb.put(key, value);
    }

    /**
     * @throws NullPointerException
     */
    public void putAll(HMap t)
    {
        if(t==null)
            throw new NullPointerException();
        HIterator iterator = t.entrySet().iterator();
        while (iterator.hasNext()) 
        {
            HMap.Entry entry = (HMap.Entry)iterator.next();
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * @throws NullPointerException
     */
    public Object remove(Object key)
    {
        if(key== null)
            throw new NullPointerException();
        return htb.remove(key);
    }
    public int size()
    {
        return htb.size();
    }
    public HCollection values()
    {
      if(valueSet==null)
            valueSet = new SetValue();
        return valueSet;
    }
        
    /**
     * classe che crea un oggetto entry, contenente la coppia chiave-valore
     */
        private  class MyEntry implements HMap.Entry
        {
            /**
             * variabile usata per la memorizzazione delle chiavi
             */
            private Object key;

            /**
             * variabile usata per la memorizzazione dei valori
             */
            private Object value;

            public MyEntry(Object k, Object v)
            {
                key=k;
                value=v;
            }


            /**
             * @throws NullPointerException
            */
            public boolean equals(Object o)
            {
                if(o==null)
                    throw new NullPointerException();
                if(!(o instanceof HMap.Entry))
                    return false;
                HMap.Entry en = (HMap.Entry)o;
                if(en.getKey()==getKey() && en.getValue()==getValue())
                    return true;
                else
                    return false;
            }
            public Object getKey()
            {
                return key;
            }
            public Object getValue()
            {
                return value;
            }
            public int hashCode()
            {
                return (getKey()==null   ? 0 : getKey().hashCode()) ^ (getValue()==null ? 0 : getValue().hashCode());
            }

            /**
             * @throws NullPointerException
             */
            public Object setValue(Object v)
            {
                if(v==null)
                    throw new NullPointerException();
                Object oldValue= getValue();
                MapAdapter.this.put(key,v);
                return oldValue;
            }
        }
        /** 
         * classe necessaria per l'implementazione del metodo entrySet()
         */
        protected class SetEntry implements HSet
        {
            /**
             * @throws UnsupportedOperationException
             */
            public boolean add(Object o)
            {
                throw new UnsupportedOperationException();
            }

            /**
             * @throws UnsupportedOperationException
             */
            public boolean addAll(HCollection c)
            {
                throw new UnsupportedOperationException();
            }
            public void clear()
            {
                MapAdapter.this.clear();
            }

            /**
             * @throws NullPointerException
             */
            public boolean contains(Object o)
            {
                if(o==null)
                    throw new NullPointerException();
                if(!(o instanceof HMap.Entry))
                    return false;
                HMap.Entry en = (HMap.Entry)o;
                
                if(MapAdapter.this.containsKey(en.getKey()) && en.getValue() == MapAdapter.this.get(en.getKey()))
                    return true;
                return false;
            }

            /**
             * @throws NullPointerException
             */
            public boolean containsAll(HCollection c)
            {
                if(c ==null)
                    throw new NullPointerException();
                HIterator iter = c.iterator();
                while(iter.hasNext())
                {
                    if(!contains(iter.next()))
                        return false;
                }
                return true;
            }
            public boolean equals(Object o)
            {
                if(o==null)
                    return false;
                HSet s = (HSet)o;
                if(!(o instanceof HSet))
                    return false;
                else if(size()!=s.size())
                    return false;
                else
                    if(containsAll(s) && s.containsAll(this))
                        return true;
                return false;
            }
            public int hashCode()
            {
                int sum =0;
                HIterator iter = iterator();
                while(iter.hasNext())
                {
                    HMap.Entry entry = (HMap.Entry) iter.next();
                    sum = sum + entry.hashCode();
                }
                return sum;
            }
            public boolean isEmpty()
            {
                return MapAdapter.this.isEmpty();
            }
            public HIterator iterator()
            {
                return new EntryIterator();
            }

            /**
             * @throws NullPointerException
             */
            public boolean remove(Object o)
            {
                if(o==null)
                    throw new NullPointerException();
                HMap.Entry en = (HMap.Entry)o;
                if(contains(o))
                {
                    MapAdapter.this.remove(en.getKey());
                    return true;
                }
                return false;
                
            }

            /**
             * @throws NullPointerException
             */
            public boolean removeAll(HCollection c)
            {
                if(c==null)
                    throw new NullPointerException();
                boolean retVal = false;
                HIterator iter = iterator();
                while(iter.hasNext())
                {
                    if(c.contains((HMap.Entry)iter.next()))
                    {
                        iter.remove();
                        retVal  =  true;
                    }
                }
                return retVal;
            }

            /**
             * @throws NullPointerException
             */
            public boolean retainAll(HCollection c)
            {
                if(c==null)
                    throw new NullPointerException();
                HIterator iter = iterator();
                boolean retVal = false;
                while(iter.hasNext())
                {
                    if(!(c.contains(iter.next())))
                    {
                        iter.remove();
                        retVal = true;
                    }
                }
                return retVal;
            }
            public int size()
            {
                return MapAdapter.this.size();
            }
            public Object[] toArray()
            {
                HMap.Entry[] retVal = new HMap.Entry[size()];
                HIterator iter = iterator();
                int i =0;
                while(iter.hasNext())
                {
                    retVal[i]=(HMap.Entry)iter.next();
                    i++;
                }
                return retVal;
            }

            /**
             * @throws NullPointerException
             */
            public Object[] toArray(Object[]a)
            {
                if(a==null)
                    throw new NullPointerException();
                if(a.length<size())
                {
                    a = toArray();
                    return a;
                }
                HIterator iter = iterator();
                int i =0;
                while(iter.hasNext())
                {
                    a[i]=(HMap.Entry)iter.next();
                    i++;
                }
                while(i<a.length)
                {
                    a[i]=null;
                    i++;
                }
                return a;
            }

            /**
             * iteratore per il set di entry
             */
            protected class EntryIterator implements HIterator 
            {
                /**
                 * variabile usata per la memorizzaione delle chiavi
                 */
                protected Enumeration key;

                /**
                 * variabile usata per la memorizzazione delle entry
                 */
                protected HMap.Entry entry = null;

                /**
                 * variabile usata per la memorizzaione dei valori
                 */
                protected Enumeration value;

                public EntryIterator()
                {
                    key = htb.keys();
                    value = htb.elements();
                }
                
                public boolean hasNext()
                {
                    return key.hasMoreElements();
                }

                /**
                 * @throws IllegalStateException
                 */
                public void remove()
                {
                    if(entry==null)
                        throw new IllegalStateException();
                    htb.remove(entry.getKey());
                    entry = null;
                }

                /**
                 * @throws NoSuchElementException
                 */
                public Object next()
                {
                    if(hasNext())
                    {
                        entry = new MyEntry(key.nextElement(), value.nextElement());
                        return entry;
                    }
                    throw new NoSuchElementException();
                }
            }
        }
        /**
         * classe necessaria per l'implementazione del metodo keySet()
         */
        private class SetKey extends SetEntry
        {
            /**
             * @throws NullPointerException
             */
            public boolean contains(Object o)
            {
                if(o==null) 
                    throw new NullPointerException();
                return containsKey(o);
            }


            /**
             * @throws NullPointerException
             */
            public boolean containsAll(HCollection c)
            {
                if(c==null)
                    throw new NullPointerException();
                HIterator iter = c.iterator();
                while(iter.hasNext())
                {
                    if(!contains(iter.next()))
                        return false;
                }
                return true;
                
            }
            
            public int hashCode()
            {
                int sum =0;
                HIterator iter = iterator();
                while(iter.hasNext())
                {
                    sum = sum + iter.next().hashCode();
                }
                return sum;
            }
            
            public HIterator iterator()
            {
                return new SetKeyIterator();
            }

            /**
             * @throws NullPointerException
             */
            public boolean remove(Object o)
            {
                if(o==null)
                    throw new NullPointerException();
                if(contains(o))
                {
                    MapAdapter.this.remove(o);
                    return true;
                }
                return false;
            }

            /**
             * @throws NullPointerException
             */
            public boolean removeAll(HCollection c)
            {
                if(c==null)
                    throw new NullPointerException();
                boolean retVal = false;
                HIterator iter = iterator();
                while(iter.hasNext())
                {
                    if(c.contains(iter.next()))
                    {
                        iter.remove();
                        retVal=true;
                    }
                }
                return retVal;
            }

            
            public Object[] toArray()
            {
                Object[]retkey = new Object[size()];
                HIterator iter = iterator();
                int i =0;
                while(iter.hasNext())
                {
                    retkey[i]=iter.next();
                    i++;
                }
                return retkey;
            }

            /**
             * @throws NullPointerException
             */
            public Object[] toArray(Object[]a)
            {
                if(a==null)
                    throw new NullPointerException();
                if(a.length<size())
                    a = new Object[size()];
                HIterator iter = iterator();
                int i = 0;
                while(iter.hasNext())
                {
                    a[i]=iter.next();
                    i++;
                }
                return a;
            }
            
            /**
             * iteratore per il set di chiavi
             */
            private class  SetKeyIterator implements HIterator 
            {
                /**
                 * variabile che memorizza le chiavi visitate dall'iteratore
                 */
                private Enumeration key;
                
                /**
                 * variabile utilizzata per memorizzare l'oggetto che ispeziona e su cui agisce l'iteratore
                 */
                private Object otherKey=null;
                
                public SetKeyIterator()
                {
                    key = htb.keys();
                }
                public boolean hasNext()
                {
                    return key.hasMoreElements();
                }
                public Object next()
                {
                    otherKey = key.nextElement();
                    return otherKey;
                }

                /**
                 * @throws IllegalStateException
                 */
                public void remove()
                {
                    if(otherKey==null)
                        throw new IllegalStateException();
                    htb.remove(otherKey);
                    otherKey =null;
                }
            }      
        }

        /**
         * classe necessaria per l'implementazione del meteo values(), tale metodo ritorna una collection.
         * Sviluppo considerandolo come un set di valori
         */
        private class SetValue extends SetEntry
        {
            /**
             * @throws NullPointerException
             */
            public boolean contains(Object o)
            {
                if(o==null) 
                    throw new NullPointerException();
                return containsValue(o);
            }

            /**
             * @throws NullPointerException
             */
            public boolean containsAll(HCollection c)
            {
                if(c==null)
                    throw new NullPointerException();
                HIterator iter = c.iterator();
                while(iter.hasNext())
                {
                    if(!contains(iter.next()))
                        return false;
                }
                return true;
            }
            public boolean equals(Object o)
            {
                if(o==null)
                    return false;
                if(!(o instanceof HSet))
                    return false;
                HSet s = (HSet)o;
                if(s.size()!=size())
                    return false;
                if(s.containsAll(this) && containsAll(s))
                    return true;
                return false;
            }
            public int hashCode()
            {
                int sum =0;
                HIterator iter = iterator();
                while(iter.hasNext())
                {
                    sum = sum + iter.next().hashCode();
                }
                return sum;
            }
            public HIterator iterator()
            {
                return new SetValueIterator();
            }

            /**
             * @throws NullPointerException
             */
            public boolean remove(Object o)
            { 
                if(o==null)
                    throw new NullPointerException();
                boolean retVal = false;
                entrySet = MapAdapter.this.entrySet();
                HIterator iter = entrySet.iterator();
                if(MapAdapter.this.containsValue(o))
                {
                    while(iter.hasNext())
                    {
                        HMap.Entry elem = (HMap.Entry)iter.next();
                        Object val = elem.getValue();
                        if(val==o)
                        {
                            iter.remove();
                            retVal = true;
                            break;
                        }
                    }
                }
                return retVal;
            }
            
            /**
             * @throws NullPointerException
             */
            public boolean removeAll(HCollection c)
            {
                if(c==null)
                    throw new NullPointerException();
                boolean retVal = false;
                HIterator iter = iterator();
                while(iter.hasNext())
                {
                    if(c.contains(iter.next()))
                    {
                        iter.remove();
                        retVal=true;
                    }
                }
                return retVal;
            }

            /**
             * @throws NullPointerException
             */
            public boolean retainAll(HCollection c)
            {
                if(c==null)
                    throw new NullPointerException();
                HIterator iter = iterator();
                boolean retVal = false;
                while(iter.hasNext())
                {
                    if(!(c.contains(iter.next())))
                    {
                        iter.remove();
                        retVal = true;
                    }
                }
                return retVal;
            }
            public Object[] toArray()
            {
                Object[]value = new Object[size()];
                HIterator iter = iterator();
                int i =0;
                while(iter.hasNext())
                {
                    value[i]=iter.next();
                    i++;
                }
                return value;
            }

            /**
             * @throws NullPointerException
             */
            public Object[] toArray(Object[]a)
            {
                if(a==null)
                    throw new NullPointerException();
                if(a.length<size())
                    a = new Object[size()];
                HIterator iter = iterator();
                int i = 0;
                while(iter.hasNext())
                {
                    a[i]=iter.next();
                    i++;
                }
                return a;
            }

            private class SetValueIterator extends EntryIterator
            {
                public boolean hasNext()
                {
                    return key.hasMoreElements();
                }

                /**
                 * @throws NoSuchElementException
                 */
                public Object next()
                {
                    if(!hasNext())
                        throw new NoSuchElementException();
                        HMap.Entry entry = (HMap.Entry)super.next();
                    return entry.getValue();
                }
                
            }
        }
}


