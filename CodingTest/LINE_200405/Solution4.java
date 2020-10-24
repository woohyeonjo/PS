package CodingTest.LINE_200405;
import java.util.*;

class Solution4 {
    
    static TreeMap<String, Integer> accounts = new TreeMap<>();
    
    public String[][] solution(String[][] snapshots, String[][] transactions) {        
    	init(snapshots, transactions);
    	repair(transactions);
        
    	int idx = 0;
        String[][] answer = new String[accounts.size()][2];
        for(String key : accounts.keySet()) {
        	answer[idx][0] = key;
        	answer[idx][1] = accounts.get(key) + "";
        	idx++;
        }
        
        Arrays.sort(answer, new Comparator<String[]>() {
        	@Override
        	public int compare(String[] s1, String[] s2) {
				int s1_len = s1.length;
                int s2_len = s2.length;
                int min = s1_len > s2_len ? s2_len : s1_len;
                
                for(int i = 0 ; i < min ; ++i){
                    if(s1[i].equals(s2[i])) continue;
                    return s1[i].compareTo(s2[i]);
                }
                
                return s1_len > s2_len ? 1 : -1;
        	}
		});
        
        return answer;
    }
    
    public void repair(String[][] transactions){
        int beforeId = -1;
        
        for(int i = 0 ; i < transactions.length ; ++i) {
        	String[] transaction = transactions[i];
        	
        	int id = stoi(transaction[0]);
        	String type = transaction[1];
        	String name = transaction[2];
        	int amount = stoi(transaction[3]);
        	
        	if(id == beforeId) continue;
            beforeId = id;
        	
        	if(accounts.containsKey(name)) {
        		int balance = accounts.get(name);
        		
        		if(type.equals("SAVE")) {
        			balance += amount;
        		} else {
        			balance -= amount;
        		}
        		
        		accounts.put(name, balance);
        	} else {
        		accounts.put(name, amount);
        	}
        }
    }
    
    public void init(String[][] snapshots, String[][] transactions) {
    	for(int i = 0 ; i < snapshots.length ; ++i){
            String name = snapshots[i][0];
            int balance = stoi(snapshots[i][1]);
            
            accounts.put(name, balance);
        }
        
        Arrays.sort(transactions, new Comparator<String[]>() {
        	@Override
        	public int compare(String[] s1, String[] s2) {
				int s1_id = stoi(s1[0]);
				int s2_id = stoi(s2[0]);
        		
				return s1_id - s2_id;
        	}
		});
    }
    
    public int stoi(String s) {
    	return Integer.parseInt(s);
    }

}