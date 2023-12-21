Write a ARM embedded C program configure Timer 0 create a delay of 1secs between an 
LED on and off. Assume LED's are connected in all the PINS of GPIO0 and clock frequency 
is 3MHz. 
Hint1: Use the formula in Lecture Section-9 Slide 36 to calculate the Match register value. 
Hint2: Use Logic analyzer to visualize the time delay
#include <LPC214X.h>
void delay(){
T0MR0 = 3000000; //Load Calculated Count for desirted delay
T0MCR = 0x00000004; //Stop on MR0
T0TCR = 1; // Enable Timer 0
while(T0TC != T0MR0); //wait untill TC reaches the desired 
delay
T0TC=0; // Reset Timer
}
main(){
IODIR0 = 0xFFFFFFFF; //Configure POrt 0 as output port
while(1){
IOSET0 = 0xFFFFFFFF; // Make all poins of port 0 High
delay();
IOCLR0 = 0xFFFFFFFF; // Make all poins of port 0 Low
delay();
}
}





2. Write a ARM embedded C program count the external events occurred using Counter 
module. 
Assignment Problem 
1. Alter the program in Ex.1 to create a blinking LED pattern with ON time as 5 secs and off 
time as 2 secs. 
#include <LPC21xx.H> /* LPC21xx 
definitions */
#define T0CTCR (*((volatile unsigned long *) 
0xE0004070))
#define T1CTCR (*((volatile unsigned long *) 
0xE0008070))
int count;
int main(void)
{
PINSEL0 = 0x00200020; //Setting for Capture in port P0.2 
and P0.10
T0TCR = 0x00000001; //Enable Timercounter register for 
counting
T1TCR = 0x00000001; //Enable Timercounter register for 
counting
T0CTCR = 0x00000001; // enabling counter for counting on 
rising edge od signal in CAP0 Timer0 pin
T1CTCR = 0x00000001; // enabling counter for counting on 
rising edge od signal in CAP0 Timer1 pin
while(1)
count = T0TC;