package Chapter_11;

public class E02Key {
	public int number;
	
	public E02Key(int number){
		this.number = number;
	}


	@Override
	public boolean equals(Object obj){
		if(obj instanceof E02Key){
			E02Key compareKey = (E02Key) obj;
			if(this.number == compareKey.number){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return number;
	}
}
