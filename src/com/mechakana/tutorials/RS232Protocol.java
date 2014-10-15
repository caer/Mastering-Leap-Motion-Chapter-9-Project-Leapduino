//File: RS232Protocol.java
//Project: Leapduino
//Date: October 8th, 2014
//
//Author: Brandon Sanders <brandon@mechakana.com>
//
///////////////////////////////////////////////////////////////////////////////
//Copyright (c) 2014 Brandon Sanders <brandon@mechakana.com>
/*
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
///////////////////////////////////////////////////////////////////////////////
//
package com.mechakana.tutorials;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

//Class: RS232Protocol/////////////////////////////////////////////////////////
/**
 * The RS232Protocol class simplifies reading from and writing to RS232 (Serial)
 * communication ports.
 */
public class RS232Protocol
{
//Private//////////////////////////////////////////////////////////////////////
	
	//Port
	private SerialPort port;
	
//Public///////////////////////////////////////////////////////////////////////
	
	//Class: RS232Listener/////////////////////////////////////////////////////
	public class RS232Listener implements SerialPortEventListener 
	{
	    public void serialEvent(SerialPortEvent event) 
	    {
	    	//Check if data is available.
	        if (event.isRXCHAR() && event.getEventValue() > 0) 
	        {
	        	try
	        	{
	        		int bytesCount = event.getEventValue();
	            	System.out.print(port.readString(bytesCount));
	        	}
	        	
	        	catch (SerialPortException e) { e.printStackTrace(); }
	        }
	    }
	}
	
	//Member Function: connect/////////////////////////////////////////////////
	/**
	 * Connects to the serial port specified by the passed address string.
	 * 
	 * @param newAddress Serial port to connect to.  Names are formatted as COM[X],
	 *                   where X is a port number.  Examples include COM3, COM4, etc.
	 */
	public void connect(String newAddress)
	{	
		try
		{
			//Set up connection.
			port = new SerialPort(newAddress);
			
			//Open port and set parameters.
            port.openPort();
            port.setParams(38400, 8, 1, 0);
            
            //Attach listener.
            port.addEventListener(new RS232Listener());
		}
		
		catch (SerialPortException e) { e.printStackTrace(); }
	}
	
	//Member Function: disconnect//////////////////////////////////////////////
	/**
	 * Disconnects from the currently connected serial port.
	 */
	public void disconnect()
	{
		try { port.closePort(); }
		
		catch (SerialPortException e) { e.printStackTrace(); }
	}
	
	//Member Function: write///////////////////////////////////////////////////
	/**
	 * Attempts to write the specified string to the currently connected serial
	 * port.
	 * 
	 * @param text String to write.
	 */
	public void write(String text)
	{
		try { port.writeBytes(text.getBytes()); }
		
		catch (SerialPortException e) { e.printStackTrace(); }
	}
}
