package com.utility;

public class WaitUtils 
{
	public static void pause(int seconds) 
	{
	    try 
	    {
	        Thread.sleep(seconds * 1000L);
	    } 
	    catch (InterruptedException e) 
	    {
	        Thread.currentThread().interrupt();
	    }
	}
}
