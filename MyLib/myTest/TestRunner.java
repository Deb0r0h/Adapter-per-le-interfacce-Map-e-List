package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * esecuzione e verifica di tutti i test con verifica se i test sono andati a buon fine(true)
 * e stampa del numero dei test effettuati per ogni singolo tester
 */
public class TestRunner
{
    public static void main(String[] args) 
    {

        System.out.println("JUnit version: 4.13");

        System.out.println("ListAdapterTest:");
        Result result1 = JUnitCore.runClasses(ListAdapterTest.class);
        for (Failure failure : result1.getFailures()) 
        {
            System.out.println(failure.toString());
        }
        System.out.println(result1.wasSuccessful());
        System.out.println("Test totali: "+result1.getRunCount());
        System.out.println();

        
        System.out.println("ListAdapterSubListTest:");
        Result result2 = JUnitCore.runClasses(ListAdapterSubListTest.class);
        for (Failure failure : result2.getFailures()) 
        {
            System.out.println(failure.toString());
        }
        System.out.println(result2.wasSuccessful());
        System.out.println("Test totali: "+result2.getRunCount());
        System.out.println();
        
        System.out.println("MapAdapterTest:");
        Result result3 = JUnitCore.runClasses(MapAdaptertTest.class);
        for (Failure failure : result3.getFailures()) 
        {
            System.out.println(failure.toString());
        }
        System.out.println(result3.wasSuccessful());
        System.out.println("Test totali: "+result3.getRunCount());
        System.out.println();

        System.out.println("EntryAdapterTest:");
        Result result4 = JUnitCore.runClasses(EntryAdapterTest.class);
        for (Failure failure : result4.getFailures()) 
        {
            System.out.println(failure.toString());
        }
        System.out.println(result4.wasSuccessful());
        System.out.println("Test totali: "+result4.getRunCount());
        System.out.println();

        System.out.println("SetAdapterTest:");
        Result result5 = JUnitCore.runClasses(SetAdapterTest.class);
        for (Failure failure : result5.getFailures()) 
        {
            System.out.println(failure.toString());
        }
        System.out.println(result5.wasSuccessful());
        System.out.println("Test totali: "+result5.getRunCount());
        System.out.println();
        
    }



}
