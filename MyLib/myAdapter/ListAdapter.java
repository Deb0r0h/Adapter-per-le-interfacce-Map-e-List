package myAdapter;


import java.util.NoSuchElementException;
import java.util.Vector;

//import org.junit.Test;

/**
 * la classe crea un contenitore lista basandosi su un Vector per il salvataggio degli elementi
 * e dei suoi metodi, è accettato ogni tipo di elemento ad eccezione di null
 */
public class ListAdapter implements HList 
{
    private Vector lst;

    public ListAdapter()
    {
        this(new Vector());
    }
    public ListAdapter(Vector v)
    {
        lst = v;
    }
    
    /**
     * @throws NullPointerException
     * @throws IndexOutOfBoundsException
     */
    public void add(int index, Object element) 
    {
        if(element==null)
            throw new NullPointerException();
        if(index<0 || index>lst.size())
            throw new IndexOutOfBoundsException();
        
        lst.insertElementAt(element,index);
    }

    
    public boolean add(Object o) 
    {
        add(lst.size(),o);
        return true;
    }

    
    public boolean addAll(HCollection c) 
    {
        return addAll(size(), c);
    }

    /**
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */
    public boolean addAll(int index, HCollection c) 
    {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException();
        if (c == null)
            throw new NullPointerException();
        int size= size();
        ListAdapter cc = (ListAdapter)c;
        HIterator iter = cc.iterator();
        while (iter.hasNext())
            add(index++, iter.next());
        return size != size();
    }

    
    public void clear() 
    {
        lst.removeAllElements();
    }

    /**
     * @throws NullPointerException
     */
    public boolean contains(Object o) 
    {
        if(o==null)
            throw new NullPointerException();
        if(lst.contains(o))
            return true;
        return false;
    }

    /**
     * @throws NullPointerException 
     */
    public boolean containsAll(HCollection c) 
    {
        if (c == null)
            throw new NullPointerException();
        ListAdapter cc = (ListAdapter)c;
        HIterator iter = cc.iterator();
        while (iter.hasNext())
            if (!lst.contains(iter.next()))
                return false;
        return true;
    }

   public boolean equals(Object o)
   {
        if (o == null)
            return false;
        HCollection collection;
        if (o instanceof HCollection)
            collection = (HCollection) o;
        else 
            return false;
        HIterator iter = collection.iterator();
        if (collection.size() != size())
            return false;
        for (int i = 0; i < size(); i++)
            if (!iter.next().equals(get(i)))
                return false;
        return true;
   }

   /**
    * @throws IndexOutOfBoundsException
    */
    public Object get(int index) 
    {
        if(index < 0 || index >= lst.size())
            throw new IndexOutOfBoundsException();
        return lst.elementAt(index);
    }

    public int hashCode()
    {
        int hashCode = 1;
        HIterator i = iterator();
        while (i.hasNext()) {
            Object obj = i.next();
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    /**
     * @throws NullPointerException
     */
    public int indexOf(Object o) 
    {
        if(o==null)
            throw new NullPointerException();
        return lst.indexOf(o);
    }

   
    public boolean isEmpty() 
    {
        return lst.isEmpty();
    }

    public HIterator iterator() 
    {
        return new Iterator(lst);
    }

    /**
     * @throws NullPointerException
     */
    public int lastIndexOf(Object o) 
    {
        if(o==null)
            throw new NullPointerException();
        return lst.lastIndexOf(o);
    }

    public HListIterator listIterator()
    {
        return new ListIterator(lst);
    }

    /**
     *@throws IndexOutOfBoundsException
     */
    public HListIterator listIterator(int index)
    {
        if (index < 0 || index > lst.size())
            throw new IndexOutOfBoundsException();
        return new ListIterator(lst, index);
    }

    /**
     *@throws IndexOutOfBoundsException
     */
    public Object remove(int index)
    {   
        if(index < 0 || index >= lst.size())
            throw new IndexOutOfBoundsException();
        Object valore = lst.elementAt(index);
        lst.removeElementAt(index);
        return valore;
        
    }

    /**
     *@throws IndexOutOfBoundsException
     */
    public HList subList(int fromIndex, int toIndex) 
    {
        if (fromIndex < 0 || toIndex > lst.size() || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();
        return new SubList(fromIndex,toIndex);
    }

    
    /**
     *@throws NullPointerException
     */
    public boolean remove(Object o) 
    {
        if(o==null)
            throw new NullPointerException();
        return lst.removeElement(o);
    }

    /**
     *@throws NullPointerException
     */
    public boolean removeAll(HCollection c)
    {
        
        if (c == null)
            throw new NullPointerException();
       ListAdapter cc = (ListAdapter)c;
       HIterator iter = cc.iterator();
       int size = size();
       while (iter.hasNext())
            remove(iter.next());
       if(size!=size())
            return true;
        return false;
    }

    /**
     *@throws NullPointerException
     */
    public boolean retainAll(HCollection c)
    {
        if (c == null)
            throw new NullPointerException();
        int size = size();
        HIterator iter = iterator();
        while(iter.hasNext())
            if(!c.contains(iter.next()))
                iter.remove();
        if(size!=size())
            return true;
        return false;
    }

    public int size() 
    {
        return lst.size();
    }

    /**
     *@throws NullPointerException
     *@throws IndexOutOfBoundsException
     */
    public Object set(int index, Object element) {
        if(index < 0 || index >= lst.size())
            throw new IndexOutOfBoundsException();
        if(element==null)
            throw new NullPointerException();
        Object valore = lst.elementAt(index);
        lst.setElementAt(element, index);
        return valore;
    }

    public Object[] toArray() 
    {
       Object[] element = new Object[lst.size()];
       lst.copyInto(element);
       return element;
    }

    /**
     *@throws NullPointerException
     */
    public Object[] toArray(Object[] a) 
    {
        if(a==null)
            throw new NullPointerException();
        if(a.length<size())
            return toArray();
        lst.copyInto(a);
        return a;
    }
    
    /**
     * iteratore che verrà utilizzato per scansionare la lista. Anch'esso si basa su un Vector
     */
    private class Iterator implements HIterator
    {
        /**
         * vettore di elementi da iterare
         */
        protected Vector it;
        /**
         * variabile che memorizza la posizione dell'iteratore
         */
        protected int index;
        /**
         * variabile usata per la scansione
         */
        protected boolean itNext;

        public Iterator(Vector l)
        {
            this(l,0);
        }

        public Iterator(Vector l, int index)
        {
            it = l;
            this.index=index;
            itNext = false;
        }

        public boolean hasNext()
        {
            return index < it.size();
        }
        
        /**
        *@throws NoSuchElementException
        */
        public Object next()
        {
            if(index>=it.size())
                throw new NoSuchElementException();
            itNext= true;
            return it.elementAt(index++);
        }

        /**
         *@throws IllegalStateException
         */
        public void remove()
        {
            if(!itNext)
                throw new IllegalStateException();
            itNext=false;
            it.removeElementAt(--index);
        }
    }

    /**
     * classe che aumenta i metodi diponibili per l'iteratore, valida solo per una lista
     */
    private class ListIterator extends Iterator implements HListIterator
    {
        /**
         * varibile usata per la scansione in senso inverso
         */
        protected boolean prev;
        public ListIterator(Vector l)
        {
            super(l);
            prev=false;
        }
        public ListIterator(Vector l, int index) 
        {
            super(l, index);
            prev = false;
        }

        /**
         * @throws IllegalArgumentException
         */
        public void add(Object o)
        {
            if (o == null)
                throw new IllegalArgumentException();
            it.insertElementAt(o, index++);
            itNext=prev=false;
        }

        public boolean hasNext()
        {
            return super.hasNext();
        }

        public boolean hasPrevious()
        {
            return index>0;
        }

        /**
         * @throws NoSuchElementException
         */
        public Object next()
        {
            if(!hasNext())
                throw new NoSuchElementException();
            itNext=true;
            prev=false;
            return super.next();
        }

        public int nextIndex()
        {
                return index;
        }

         /**
         * @throws NoSuchElementException
         */
        public Object previous()
        {
            if(!hasPrevious())
                throw new NoSuchElementException();
                itNext = false;
                prev = true;
                return it.elementAt(--index);
        }

        public int previousIndex()
        {
                return index-1;
        }

         /**
         * @throws IllegalStateException
         */
        public void remove()
        {
            if(!itNext&&!prev)
                throw new IllegalStateException();
            else if(prev)
            {
                it.removeElementAt(index);
                prev=itNext=false;
                return;
            }
            else
                super.remove();
        }

        /**
         * @throws IllegalArgumentException
         * @throws IllegalStateException
         */
        public void set(Object o)
        {
            if (o == null)
                throw new IllegalArgumentException();
            if(it.isEmpty())
            {
                add(o);
                return;
            }
            if(!itNext&&!prev)
                throw new IllegalStateException();
            else if (itNext)
                it.setElementAt(o, index - 1);
            else if (prev)
                it.setElementAt(o, index);
        }
    }
    
    /**
     * classe che crea una sottolista, ogni azione sulla sottolista si riflette sulla lista e viceversa
     * si basa anch'essa di conseguenza su Vector
     */
    private class SubList extends ListAdapter 
    {
        /**
         * variabile relativa all'indice iniziale incluso
         */
        private int from;
        /**
         * variabile relativa all'indice finale escluso
         */
        private int to;

        public SubList(int from, int to)
        {
            this.from=from;
            this.to=to;
        }

        /**
         * @throws NullPointerException
         * @throws IndexOutOfBoundsException
         */
       public  void add(int index, Object element)
       {
            if(element==null)
                throw new NullPointerException();
            if (index < 0 || index > size())
                throw new IndexOutOfBoundsException();
            ListAdapter.this.add(index+from,element);
            to++;
       }
       /**
         * @throws NullPointerException
         */
       public boolean add(Object o)
       {
            if(o==null)
                throw new NullPointerException();
            ListAdapter.this.add(to++,o);
            return true;
       }

       /**
         * @throws NullPointerException
         */
       public boolean addAll(HCollection c)
       {
            if(c==null)
                throw new NullPointerException();
            int size= size();
            ListAdapter.this.addAll(to, c);
            to = to + c.size();
            if(size!=size())
                return true;
            return false;
       }

       /**
         * @throws NullPointerException
         * @throws IndexOutOfBoundsException
         */
       public boolean addAll(int index, HCollection c)
       {
            if (c == null)
                throw new NullPointerException();
            if (index < 0 || index > size())
                throw new IndexOutOfBoundsException();
            int size = size();
            ListAdapter.this.addAll(from+index, c);
            to = to +c.size();
            if(size!=size())
                return true;
            return false;
       }
       public  void clear()
        {
            while(to>from)
                remove(to-from-1);
        }

        /**
         * @throws NullPointerException
         */
       public boolean contains(Object o)
       {
            if(o==null)
                throw new NullPointerException();
            if(isEmpty())
                return false;
            int count = 0;
            while(count<size())
            {
                if(ListAdapter.this.get(from+count).equals(o))
                    return true;
                count++;
            }
            return false;
            
       }
       
       /**
         * @throws NullPointerException
         * @throws ClassCastException
         */
       public boolean containsAll(HCollection c)
       {
            if (c == null)
                throw new NullPointerException();
            if (!(c instanceof ListAdapter))
                throw new ClassCastException();
            ListAdapter collection = (ListAdapter) c;
            HIterator iter = collection.iterator();
            boolean val = true;
            while (iter.hasNext())
                val= val & contains(iter.next());
            return val;
       }
       public boolean equals(Object o)
       {
            if(o==null)
                return false;
            HCollection h;
            if (o instanceof HCollection)
                h = (HCollection) o;
            else 
                return false;
            if(h.size() != size())
                return false;
            HIterator iter = h.iterator();
            for(int i =0;i<size();i++)
                if(!ListAdapter.this.get(from+i).equals(iter.next()))
                    return false;
            return true;

       }

        /**
         * @throws IndexOutOfBoundsException
         */
       public Object get(int index)
       {
            if (index < 0 || index >= size())
                throw new IndexOutOfBoundsException();
            return ListAdapter.this.get(from+index);
       }
       public int hashCode()
       {
        int hashCode = 1;
        HListIterator i = ListAdapter.SubList.this.listIterator();
        while (i.hasNext()) {
            Object obj = i.next();
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
        }
        return hashCode;
       }

       /**
         * @throws NullPointerException
         */
       public int indexOf(Object o)
       {
           if(o==null)
                throw new NullPointerException();
            for(int i =from;i<to;i++)
            {
                if(ListAdapter.this.get(i).equals(o))
                    return i-from;   
            }
            return -1;
       }
       public boolean isEmpty()
       {
             return size()==0;
       }
       public HIterator iterator()
       {
            return new SubListIterator(ListAdapter.this.lst, from, from, to);
       }

       /**
         * @throws NullPointerException
         */
       public int lastIndexOf(Object o)
       {
           if(o==null)
                throw new NullPointerException();
            HIterator iter = iterator();
            int count=0;
            int result = -1;
            while(iter.hasNext())
            {
                if(iter.next().equals(o))
                    result = count;
                count++;
            }
            return result;
       }
       public HListIterator listIterator()
       {
            return new SubListIterator(ListAdapter.this.lst,from,from,to);
       }

       /**
         * @throws IndexOutOfBoundsException
         */
       public HListIterator listIterator(int index)
       {
            if (index < 0 || index > size())
                throw new IndexOutOfBoundsException();
            return new SubListIterator(ListAdapter.this.lst, from + index, from, to);
       }

       /**
         * @throws IndexOutOfBoundsException
         */
       public Object remove(int index)
       {
            if (index < 0 || index >= size())
                throw new IndexOutOfBoundsException();
            to--;
            return ListAdapter.this.remove(from+index);
       }


       public HList subList(int fromIndex, int toIndex)
       {
            if (fromIndex < 0 || toIndex > to || fromIndex > toIndex)
                throw new IndexOutOfBoundsException();
           HList sub = new SubList(fromIndex, toIndex);
           return sub;
       }

       /**
         * @throws NullPointerException
         */
       public boolean remove(Object o)
       {    
           if(o==null)
                throw new NullPointerException();
            int i = indexOf(o);
            if(i>=0)
            {
                remove(i);
                return true;
            }
            return false;
       }

       /**
         * @throws NullPointerException
         * @throws ClassCastException
         */
       public boolean removeAll(HCollection c)
       {
            if(c==null)
                throw new NullPointerException();
            if (!(c instanceof ListAdapter))
                throw new ClassCastException();
            ListAdapter collection = (ListAdapter) c;
            HIterator iter = collection.iterator();
            int size = size();
            Object elem;
            while (iter.hasNext())
            {
                elem = iter.next();
                if (contains(elem))
                    remove(elem);
            }
            if(size!=size())
                return true;
            return false;
       }

       /**
         * @throws NullPointerException
         * @throws ClassCastException
         */
       public boolean retainAll(HCollection c)
       {
            if (c == null)
                throw new NullPointerException();
            if (!(c instanceof ListAdapter))
                throw new ClassCastException();
            HIterator it = iterator();
            int size = size();
            while(it.hasNext())
                if(!c.contains(it.next()))
                    it.remove();
            if(size!=size())
                return true;
            return false;
       }
       public int size()
       {
           return to-from;
       }

       /**
         * @throws NullPointerException
         * @throws IndexOutOfBoundsException
         */
       public Object set(int index, Object element)
       {
            if (index < 0 || index >= size())
                throw new IndexOutOfBoundsException();
            if (element == null)
                throw new NullPointerException();
            Object elem = ListAdapter.this.get(index + from);
            ListAdapter.this.set(index + from, element);
            return elem;
       }
       public Object[] toArray()
       {
           Object[] elements = new Object[size()];
           for(int i =from;i<to;i++)
                elements[i-from]=ListAdapter.this.get(i);
           return elements;
       }

       /**
         * @throws NullPointerException
         */
       public Object[] toArray(Object[] a)
       {
           if(a==null)
                throw new NullPointerException();
            if(a.length<size())
                return toArray();
            for(int i=0;i<size();i++)
                a[i]=get(i);
            return a;
       }
       /**
        * classe che implementa un iteratore per la subList
        */
        private class SubListIterator extends ListIterator
        {
            /**
             * variabile usata per impostare un limite superiore per la scansione
             */
            private int to;

            /**
             * variabile usata per impostare un limite inferiore per la scansione
             */
            private int from;

            public SubListIterator(Vector v, int ind, int from, int to) 
            {
                super(v, ind);
                this.to = to;
                this.from = from;
            }

            public void add(Object o)
            {
                to++;
                ListAdapter.SubList.this.to=to;
                super.add(o);
            }
            public boolean hasNext()
            {
                return index<to;
            }
            public boolean hasPrevious()
            {
                return index>from;
            }

            /**
            * @throws NoSuchElementException
            */
            public Object next()
            {
                if(!hasNext())
                    throw new NoSuchElementException();
                return super.next();
            }
            public int nextIndex()
            {
                return index-from;
            }

            /**
            * @throws NoSuchElementException
            */
            public Object previous()
            {
                if(!hasPrevious())
                    throw new NoSuchElementException();
                return super.previous();
            }
            public int previousIndex()
            {
                return index-from-1;
            }
            
            /**
            * @throws IllegalStateException
            * @throws  IllegalArgumentException
            */
            public void set(Object o)
            {
                if(o==null)
                    throw new IllegalArgumentException();
                if(from==to)
                {
                    add(o);
                    return;
                }
                if(!itNext&&!prev)
                    throw new IllegalStateException();
                else if (itNext)
                    it.setElementAt(o, index - 1);
                else if (prev)
                    it.setElementAt(o, index);
            }


            /**
            * @throws IllegalStateException
            */
            public void remove()
            {
                if(!itNext&&!prev)
                    throw new IllegalStateException();
                else if (prev) 
                {
                    it.removeElementAt(index);
                    prev = itNext = false;
                }
                else 
                {
                    itNext = false;
                    it.removeElementAt(--index);
                }
                to--;
                ListAdapter.SubList.this.to = to;
            }
        }
    }
}
