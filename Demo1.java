package com.demanThread;

public class Demo1 {

	public static void main(String[] args) {
		Ticketsystem t1 = new Ticketsystem();
		Thread mythread = new Thread(() -> {
			int[] seats = { 1, 2, 4 };
			for (int ticket : seats) {
				t1.BookTicket(ticket);
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		mythread.start();
		
		Thread t2 = new Thread(()->{
			int []cancles= {1,2};
			for(int cancle:cancles) {
				t1.cancleTicket(cancle);
				try {
				Thread.sleep(1000);	
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t2.start();

	}

}

class Ticketsystem {
	private int availableTicet = 5;

	public synchronized void BookTicket(int numberOfSeates) {
		if (availableTicet < numberOfSeates) {
			System.out.println("ooPs tickets are the not available to much ");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
			availableTicet = this.availableTicet - numberOfSeates;
			System.out.println(
					"Booked " + numberOfSeates + "  " + "know the available ticket is the:= " + this.availableTicet);
			notify();

		}
	}

	public synchronized void cancleTicket(int cancleTicket) {
		availableTicet = this.availableTicet + cancleTicket;
		System.out.println(
				"cancleTicket:" + cancleTicket + "  " + "know the available Ticket is the :" + this.availableTicet);
		notify();

	}
}
