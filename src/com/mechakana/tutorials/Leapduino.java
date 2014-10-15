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

import com.leapmotion.leap.Controller;

//Class: Leapduino/////////////////////////////////////////////////////////////
/**
 * Main class for the Leapduino project, responsible for initializing
 * serial communications with an Arduino and starting the Leap Motion listener.
 */
public class Leapduino
{	
	//Main/////////////////////////////////////////////////////////////////////
	public static final void main(String args[])
	{		
		//Initialize Serial Communications.
		RS232Protocol serial = new RS232Protocol();
		serial.connect("COM4");
	
		//Initialize Leapduino Listener.
		LeapduinoListener leap = new LeapduinoListener(serial);
		Controller controller = new Controller();
		controller.addListener(leap);
	}
}
