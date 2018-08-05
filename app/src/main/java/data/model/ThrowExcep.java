package data.model;

/**
 * Created by RDX on 11-07-2018.
 */

class ThrowExcep
{
    static void fun()
    {
        try
        {
            throw new NullPointerException("demo");
        }
        catch(NullPointerException e)
        {
            System.out.println("Caught inside fun().");
            throw e; // rethrowing the exception
        }
    }

    public static void main(String args[])
    {
        try
        {
            fun();
        }
        catch(Throwable e)
        {
            System.out.println("Caught in main.");
        }
    }
}

