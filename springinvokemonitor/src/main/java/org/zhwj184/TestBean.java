package org.zhwj184;

import com.alibaba.fastjson.JSON;

public class TestBean {

	public float add(int a, float b){
		return a + b;
	}
	
	public A addBean(A a, A b){
		A c = new A();
		c.setC(a.getC() + b.getC());
		c.setD(a.getD() + b.getD());
		return c;
	}
	public static void main(String[] args) {
		A c = new A();
		c.setC(23);
		c.setD(323.34);
		System.out.println(JSON.toJSON(c));
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
