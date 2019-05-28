package Chapter_11;

public class E01Member {
	public String id;
	
	public E01Member(String id){
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof E01Member){
			E01Member member = (E01Member) obj;
			if(id.equals(member.id)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return id.hashCode();
	}
}
