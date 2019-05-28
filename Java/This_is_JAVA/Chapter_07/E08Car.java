package Chapter_07;

public class E08Car {

	E07Tire[] tires = {
			new E07Tire("앞 왼쪽", 6),
			new E07Tire("앞 오른쪽", 2),
			new E07Tire("뒷 왼쪽", 3),
			new E07Tire("뒷 오른쪽", 4)
	};
	
	int run(){
		System.out.println("[자동차가 달립니다.]");
		
		for(int i = 0 ; i < tires.length ; i++){
			if(tires[i].roll() == false){
				stop();
				return (i+1);
			}
		}
		return 0;
	}
	
	void stop(){
		System.out.println("[자동차가 멈춥니다.]");
	}
}
