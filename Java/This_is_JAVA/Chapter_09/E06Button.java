package Chapter_09;

public class E06Button {
	OnClickListener listener;
	
	void setOnClickListener(OnClickListener listener){
		this.listener = listener;
	}
	
	void touch(){
		listener.onClick();
	}
	
	interface OnClickListener {
		void onClick();
	}
}
