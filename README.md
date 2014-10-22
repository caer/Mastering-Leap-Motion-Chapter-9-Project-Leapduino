Leapduino
========

A simple Java application bundled with a basic Arduino sketch that uses the Leap Motion Controller to make the built-in LED on an Arduino blink.

How do I use it?
=
This project was created using Eclipse and the Arduino IDE, since there are two separate programs that work together to make it work.

Assuming you're using Eclipse already, all you have to do is download the project and import it into the Eclipse IDE in order to get the Java half to work.  

You will then need to open Leapduino.ino (located in Leapduino/Leapduino.ino in the project) and open it from within the Arduino IDE.  Once Leapduino.ino is open, simply upload it to a connected Arduino.  From there, assuming the Arduino is on COM4, you can start the Leapduino Java application and begin playing around!

Everything else is pre-configured to "just work", with the exception of LeapJava.jar, Leap.dll and LeapJava.dll.  The ones included with the project are for a 64-bit Windows installation, but you can find the files for your specific platform within the Leap Motion SDK at https://developer.leapmotion.com/.  

The proper location for the DLLs is in the root directory of the project, while the .JAR can go anywhere you like so long as you remember to add it to your Java library path.  Just be sure to use the correct ones for your platform!

Other questions?
=
If you have any questions, don't hesitate; send them to brandon@mechakana.com!
