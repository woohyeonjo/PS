package Chapter_09;

public class E09Button {
	OnClickListener listener;
	
	void setOnClickListener(OnClickListener listener){
		this.listener = listener;
	}
	
	void touch(){
		listener.onClick();
	}
	
	interface OnClickListener{
		void onClick();
	}
}
