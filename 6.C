//Write a ARM embedded C program to turn on an LED connected to P0.10 using Keil 
//Simulator
#include <LPC21xx.H> 
int main()
{
IODIR0 |= (1<<10); //Setting P0.10 as output port
while(1) {
IOSET0 |= (1<<10) ; //Sending HIGH to P0.10
}
return(0);
}




//Write a ARM embedded C program to blink an LED connected to P1.17 using Keil 
//Simulator

#include <LPC21xx.H> 
delay() //Delay function
{
int count;
for(count = 0;count<=10000;count++);
}
int main()
{
IODIR1 |= (1<<17); //setting P1.17 as output 
port
while(1){
IOSET1 |= (1<<17) ; //Sending HIGH to P1.17
delay();
IOCLR1 |= (1<<17) ; // Sending LOW to p1.17
delay();
}
return(0);
}





//Assignment Problem 
//1. Write an ARM embedded C program to form a chasing LED pattern with eight LEDs using 
//Keil simulato

#include <LPC21xx.H> 
delay(int count) //Delay function
{
for(count = 0;count<=10000;count++);

}

int main()
{
int i=0;
 //setting P1.17 as output
 IODIR1 |= (1 << 0) | (1 << 1) | (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);
while(1){

  for (i = 0; i < 8; i++) {
            IOSET1 = (1 << i); // Turn on the current LED
            delay(100);     // Delay for a short period
            IOCLR1 = (1 << i); // Turn off the current LED
        }
} 

return(0);
}