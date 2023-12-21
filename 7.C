//Write a ARM embedded C program to turn on/off an LED connected to P0.10 using a on/off 
//switch connected in P0.1 in Keil Simulator

#include <LPC21xx.H> 
int main()
{
IODIR0 |= (1<<10); //Settin P0.10 as output port
IODIR0 |= (0<<1); // Setting P0.1 as input port
while(1) {
if (IOPIN0 & (1<<1))
IOSET0 |= (1<<10) ; //Sending HIGH to P0.10
else
IOCLR0 |= (1<<10) ; //Sending LOW to P0.10
}
return(0);
}




//Assignment Problem 
//1. Write an ARM embedded C program to control the chasing LED pattern of eight LEDs using 
//on/off switch Keil simulator. If the switch is on then the chasing in forward direction else 
//backward direction.

#include <LPC21xx.H>

delay() {
    int count;
    for (count = 0; count <= 10000; count++);
}

int main() {
    int i = 0;
    int forwardDirection = 1; // 1 for forward, 0 for backward

    IODIR0 |= (1 << 1) | (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7) | (1 << 8);
    IODIR0 |= (0 << 0); // Set P0.0 as an input for the switch

    while (1) {
        // Check the state of the switch on P0.0
        if (IOPIN0 & (1 << 0)) {
            forwardDirection = 1; // Switch is on, set forward direction
        } else {
            forwardDirection = 0; // Switch is off, set backward direction
        }

        // Chasing LED pattern based on direction
        if (forwardDirection) {
            for (i = 0; i <= 8; i++) {
                IOSET0 = (1 << i); // Turn on the current LED
                delay();           // Delay for a short period
                IOCLR0 = (1 << i); // Turn off the current LED
            }
        } else {
            for (i = 8; i > 0; i--) {
                IOSET0 = (1 << i); // Turn on the current LED
                delay();           // Delay for a short period
                IOCLR0 = (1 << i); // Turn off the current LED
            }
        }
    }

    return 0;
}

