//File: Leapduino.ino
//Project: Leapduino
//Date: October 8th, 201
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
#define Leapduino_Version "1.0"

//Most Arduino boards have an LED pre-wired to pin 13.
int led = 13;

//Current LED state.  LOW is off and HIGH is on.
int ledState = LOW;

//Blink rate in milliseconds.  Default is one second.
long blinkRate = 500;

//Last time the LED was updated.
long previousTime = 0;

//Function: setup//////////////////////////////////////////
//
//This function is the first function run by the Arduino.
//It is used for initializing values and setting up
//connections, etc.
//
void setup() 
{
  //Initialize the built-in LED (assuming the Arduino board has one)
  pinMode(led, OUTPUT);  
  
  //Start a serial connection at a baud rate of 38,400.
  Serial.begin(38400);
}

//Function: loop///////////////////////////////////////////
//
//This function is run by the arduino repeatedly after the
//setup function is executed.
//
void loop() 
{ 
  //Get the current system time in milliseconds.
  unsigned long currentTime = millis();
  
  //Check if it's time to toggle the LED on or off.
  if (currentTime - previousTime >= blinkRate)
  {
    previousTime = currentTime;
   
    if (ledState == LOW) ledState = HIGH;
    else ledState = LOW;
   
    digitalWrite(led, ledState);
  }
  
  //Check if there is serial data available.
  if (Serial.available())
  {
    //Wait for all data to arrive.
    delay(20);
    
    //Our data.
    String data = "";
    
    //Iterate over all of the available data and compound it into a string.
    while (Serial.available())
      data += (char) (Serial.read());
    
    //Set the blink rate based on our newly-read data.
    blinkRate = 1000 - (abs(data.toInt()) * 10);
    
    //A blink rate lower than 30 milliseconds won't really be perceptable by a human.
    if (blinkRate < 30) blinkRate = 30;
    
    //Echo the data.
    Serial.println("Leapduino Client Received:");
    Serial.println("Raw Leap Data: " + data + " | Blink Rate (MS): " + blinkRate);
  }
}

