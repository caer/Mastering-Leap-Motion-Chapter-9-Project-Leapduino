//File: LeapduinoListener.java
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

import com.leapmotion.leap.*;

//Class: LeapduinoListener/////////////////////////////////////////////////////
public class LeapduinoListener extends Listener
{    
//Private//////////////////////////////////////////////////////////////////////
	
	//Serial port that we'll be using to communicate with the Arduino.
	private RS232Protocol serial;
	
//Public///////////////////////////////////////////////////////////////////////
	
	//Constructor//////////////////////////////////////////////////////////////
	public LeapduinoListener(RS232Protocol serial) { this.serial = serial; }
	
	//Member Function: onInit//////////////////////////////////////////////////
    //
	//Called when the LeapduinoListener class is first initialized.
	//
	public void onInit(Controller controller) { System.out.println("Initialized"); }

	//Member Function: onConnect///////////////////////////////////////////////
	//
	//Called when the LeapduinoListener class is connected to a Leap Motion Controller.
	//
    public void onConnect(Controller controller) { System.out.println("Connected"); }

    //Member Function: onDisconnect////////////////////////////////////////////
    //
    //Called when the LeapduinoListener class is disconnected from a Leap Motion Controller.
    //
    public void onDisconnect(Controller controller) { System.out.println("Disconnected"); }

    //Member Function: onExit//////////////////////////////////////////////////
    //
    //Called when the LeapduinoListener class is destroyed.
    //
    public void onExit(Controller controller) { System.out.println("Exited"); }

    //Member Function: onFrame/////////////////////////////////////////////////
    //
    //Called when a new frame is received from the Leap Motion Controller.
    //
    public void onFrame(Controller controller) 
    {
        //Get the most recent frame.
        Frame frame = controller.frame();
        
        //Verify a hand is in view.
		if (frame.hands().count() > 0)
		{
			//Get some hand tracking data.
			int hand = (int) (frame.hands().frontmost().palmPosition().getY());
			
			//Send the hand pitch to the Arduino.
			serial.write(String.valueOf(hand));
			
			//Give the Arduino some time to process our data.
			try { Thread.sleep(30); }
			catch (InterruptedException e) { e.printStackTrace(); }
		}
    }
}