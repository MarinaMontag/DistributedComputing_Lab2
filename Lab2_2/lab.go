package main

import "fmt"

var store=[]int{32,323,43,75,24,65675,5332,323,3536}

func Ivanow(ch chan<- int){
	for _,item:=range store{
		fmt.Printf("Ivanow took item %v from store and sent it to Petrow\n", item)
		ch<-item
	}
	close(ch)
}

func Petrow(ch <-chan int, truck chan<- int){
	for item:=range ch{
		fmt.Printf("Petrow got item %v from Ivanow and loaded it into truck\n", item)
		truck<-item
	}
	close(truck)
}

func Nechiporchuk(truck <-chan int, done chan<- bool)  {
	sum:=0
	for item:=range truck{
		fmt.Printf("Nechiporchuk counted price of item %v\n", item)
		sum+=item
	}
	fmt.Printf("The whole price of munition is %v\n",sum)
	done<-true
}

func main(){
	ch:=make(chan int)
	truck:=make(chan int)
	done:=make(chan bool)
	go Ivanow(ch)
	go Petrow(ch, truck)
	go Nechiporchuk(truck, done)
	<-done
}