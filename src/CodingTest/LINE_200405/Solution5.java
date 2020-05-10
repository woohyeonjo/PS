package CodingTest.LINE_200405;
import java.util.*;

class Solution5 {
    
	class Document implements Comparable<Document> {
		int involve;
		String name;
		HashMap<String, String> tags;
		
		Document(String name){
			this.name = name;
			this.tags = new HashMap<>();
			this.involve = 0;
		}

		@Override
		public int compareTo(Document o) {
			if(this.involve == o.involve) {
				return this.name.compareTo(o.name);
			} else {
				return -(this.involve - o.involve);
			}
		}
	}
	
    public String[] solution(String[][] dataSource, String[] tags) {
    	
    	ArrayList<Document> docList = new ArrayList<>();
    	ArrayList<String> answer = new ArrayList<>();
    	
    	for(int i = 0 ; i < dataSource.length ; ++i) {
    		String name = dataSource[i][0];
    		Document doc = new Document(name);
    		
    		for(int j = 1 ; j < dataSource[i].length ; ++j) {
    			String tag = dataSource[i][j];
    			doc.tags.put(tag, tag);
    		}
    		
    		docList.add(doc);
    	}
    	
		for(Document doc : docList) {
			for(int i = 0 ; i < tags.length ; ++i) {
				if(doc.tags.containsKey(tags[i])) {
					doc.involve++;
				}
			}
		}
    	
		Collections.sort(docList);
		
    	for(Document doc : docList) {
    		if(doc.involve > 0) {
    			answer.add(doc.name);
    		}
    	}
    	
    	String[] ans = new String[answer.size()];
    	
    	for(int i = 0 ; i < ans.length ; ++i) {
    		ans[i] = answer.get(i);
    	}
    	
    	return ans;
    }
}