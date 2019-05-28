package Chapter_11;

public class E03SmartPhone {
	private String company;
	private String os;
	
	public E03SmartPhone(String company, String os){
		this.company = company;
		this.os = os;
	}
	
	@Override
	public String toString(){
		return company + ", " + os;
	}
}
