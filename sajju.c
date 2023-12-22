#include <LPC21xx.H>

void delay() {
    int count;
    for (count = 0; count <= 10000; count++);
}

int main() {
    // Initialize Port 1 pins 16 to 23 as output
    IODIR1 |= 0xFF0000; // 0xFF0000 sets P1.16 to P1.23 as output

    while (1) {
        // Check if switch no 21 (P0.21) is on
        if (IOPIN0 & (1 << 21)) {
            // Glow odd LEDs: 17, 19, 21, 23
            IOSET1 = 0xAA0000; // Set P1.17, P1.19, P1.21, P1.23
            delay();
            IOCLR1 = 0xAA0000; // Clear P1.17, P1.19, P1.21, P1.23
        }
        // Check if switch no 28 (P0.28) is on
        else if (IOPIN0 & (1 << 28)) {
            // Glow even LEDs: 16, 18, 20, 22
            IOSET1 = 0x550000; // Set P1.16, P1.18, P1.20, P1.22
            delay();
            IOCLR1 = 0x550000; // Clear P1.16, P1.18, P1.20, P1.22
        }

        delay(); // Additional delay for switch debouncing
    }

    return 0;
}
