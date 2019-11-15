package CodingTest.CORSPro.Level1;

import java.util.ArrayList;
import java.util.Iterator;

//Shop 인터페이스와 HairShop, Restaurant 클래스는 Inner Class로 작성되어있습니다. 아래 코드를 잘 읽고 빈칸을 채워주세요.
class Solution_3_10 {
    class Shop{
        protected ArrayList<Customer> reserveList;
        public Shop() {
			this.reserveList = new ArrayList<Customer>();
		}
        public boolean reserve(Customer customer){
            reserveList.add(customer);
            return true;
        }
    }
    class Customer{
        public int id;
        public int time;
        public int numOfPeople;
        public Customer(int id, int time, int numOfPeople){
            this.id = id;
            this.time = time;
            this.numOfPeople = numOfPeople;
        }
    }
    class HairShop extends Shop {
        public HairShop(){
            super();
        }
        
        public boolean reserve(Customer customer){
            if(customer.numOfPeople != 1)
                return false;
            
            Iterator<Customer> iter = reserveList.iterator();
            while (iter.hasNext()) {
                Customer r = iter.next();
                if(r.time == customer.time)
                    return false;
            }
            reserveList.add(customer);
            return true;
        }
    }
    class Restaurant extends Shop {
        public Restaurant(){
            super();
        }
        
        public boolean reserve(Customer customer){
            if(customer.numOfPeople >= 2 && customer.numOfPeople <= 8)
                return false;
            int count = 0;
                        
            Iterator<Customer> iter = reserveList.iterator();
            while (iter.hasNext()) {
                Customer r = iter.next();
                if(r.time == customer.time)
                    count += 1;
            }
            if(count >= 2)
                return false;
            reserveList.add(customer);
            return true;
        }
    }

    public int solution(int[][] customers, String[] shops) {
        Shop hairshop = new HairShop();
        Shop restaurant = new Restaurant();
        int count = 0;
        for(int i = 0; i < shops.length; i++){
            if(shops[i].equals("hairshop")){
                if(hairshop.reserve(new Customer(customers[i][0], customers[i][1], customers[i][2])))
                    count += 1;
            }
            else if(shops[i].equals("restaurant")){
                if(restaurant.reserve(new Customer(customers[i][0], customers[i][1], customers[i][2])))
                    count += 1;
            }
        }
        return count;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
    	Solution_3_10 sol = new Solution_3_10();
        int[][] customers = {{1000, 2, 1},{2000, 2, 4},{1234, 5, 1},{4321, 2, 1}, {1111, 3, 10}};
        String[] shops = {"hairshop", "restaurant", "hairshop", "hairshop", "restaurant"};
        int ret = sol.solution(customers, shops);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret + " 입니다.");
    }
}