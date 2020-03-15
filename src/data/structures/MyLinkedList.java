package data.structures;

import java.util.*;
public class MyLinkedList
{  
   private Node first;
   public MyLinkedList()
   {       first = null;   }
   public Object getFirst()
   {  
      if (first == null) { throw new NoSuchElementException(); }
      return first.data;
   }
   public Object removeFirst()
   {  
      if (first == null) { throw new NoSuchElementException(); }
      Object element = first.data;  first = first.next;  return element;   }
   public void addFirst(Object element)
   {  
      Node newNode = new Node();      newNode.data = element;
      newNode.next = first;      first = newNode;
   }
   public Iterator iterator()
   {      return new MyIterator();   }
  
   class Node  { public Object data;  public Node next; }
   class MyIterator implements Iterator
   {  private Node position;
      private Node previous;
      private boolean isAfterNext;
      public MyIterator()
      {  position = null; previous = null; isAfterNext = false;   }
      public Object next()
      {  
         if (!hasNext()) { throw new NoSuchElementException(); }
         previous = position; // Remember for remove
         isAfterNext = true;

         if (position == null)
         {  position = first;   }
         else
         {   position = position.next;  }
         return position.data;
      }
      public boolean hasNext()
      {  
         if (position == null)
         {    return first != null; }
         else
         {    return position.next != null;
         }
      }

      public void remove()
      {  
         if (!isAfterNext) { throw new IllegalStateException(); }

         if (position == first)
         {
            removeFirst();
         }
         else 
         {  
            previous.next = position.next;
         }
         position = previous;
         isAfterNext = false;
      }
   }
   public static void main(String[] args){
   MyLinkedList x = new MyLinkedList();
   x.addFirst("A");x.addFirst("B");x.addFirst("C");x.addFirst("D");x.addFirst("E");x.addFirst("M");
   Iterator i = x.iterator();
   while (i.hasNext()){
      System.out.println(i.next());
      //i.remove();
     // i.next();
   }
    Iterator ii = x.iterator();
   while (ii.hasNext()){
      System.out.println(ii.next());
        }
   }
}
