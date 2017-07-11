package com.groovy.test

class Money {
	double amount
	
	public Money(double amount) {
		this.amount = amount
	}
	
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Money && o.amount == amount
	}
	
	Money plus(Money o){
		return new Money(amount + o.amount)
	}
	

	Money plus(o){
		return new Money(amount + o)
	}
	
	boolean say(String o){
		println 'Money : ' + o
		return true;
	}
	boolean say(int o){
		println 'Money : ' + o
		return true;
	}
	
	boolean isCase(Money m){
		return m.amount >= 0;
	}
}
