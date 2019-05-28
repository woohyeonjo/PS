package Chapter_08;

public class E14Example {
	public static void main(String args[]){
		E14ImplementationC impl = new E14ImplementationC();
		
		E14InterfaceA ia = impl;
		ia.methodA();
		System.out.println();
		
		E14InterfaceB ib = impl;
		ib.methodB();
		System.out.println();
		
		E14InterfaceC ic = impl;
		ic.methodA();
		ic.methodB();
		ic.methodC();
		System.out.println();
	}
}
