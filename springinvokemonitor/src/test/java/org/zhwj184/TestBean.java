package org.zhwj184;

public class TestBean {

	public float add(int a, float b){
		return a + b;
	}
	
	public A add(A a, A b){
		A c = new A();
		c.setC(a.getC() + b.getC());
		c.setD(a.getD() + b.getD());
		return c;
	}
	
}

class A{
	int c ;
	double d;
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	public double getD() {
		return d;
	}
	public void setD(double d) {
		this.d = d;
	}
	
}
