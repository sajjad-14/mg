//to control speed of The chasing LED blinking, 8 LEDs - p0.3 p0.10 
// switches - 3, switch 1 max speed p1.22, switch 2 medium speed p1.23, switch 3 slow speed p1.24
#include <LPC21xx.H>

max_speed(){
 int count;
 for(count=0;count<100;count++);
}
medium_speed(){
 int count1;
 for(count1=0;count1<100000;count1++);
}
slow_speed(){
 int count2;
 for(count2=0;count2<10000000;count2++);
}

int main(){
 int i = 0;
 IODIR0 |= (1<<3)|(1<<4)|(1<<5)|(1<<6)|(1<<7)|(1<<8)|(1<<9)|(1<<10);
 IODIR1 |= (0<<22);
 IODIR1 |= (0<<23);
 IODIR1 |= (0<<24);
while(1){
if(IOPIN1 & (1<<22)){
 	for(i=3;i<=10;i++){
	 IOSET0 |= (1<<i);
	 max_speed();
	 IOCLR0 |= (1<<i);
	 max_speed();
}
}else if(IOPIN1 & (1<<23)){
	 for(i=3;i<=10;i++){
	  IOSET0 |= (1<<i);
	   medium_speed();
	   IOCLR0 |= (1<<i);
	   medium_speed();
}
}else{
 	for(i=3;i<=10;i++){
	 	IOSET0 |= (1<<i);
	 	slow_speed();
	 	IOCLR0 |= (1<<i);
	 	slow_speed();
}
	 }
	 return(0);
}
}