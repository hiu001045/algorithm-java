
public class Solution {
	int count=0;
	char[] voArr= {'A','E','I','O','U'};
	boolean found = false;
	public int solution(String word) {
		
		
		
		
		dfs(word,"");
		
		return count;
	}
	
	public void dfs(String word, String vowel) {
	
		if(word.equals(vowel)) {
			found = true;
			return;
		};
		if(vowel.length() == 5) return;
		
		for(int i=0;i< voArr.length;i++) {
				count++;
				dfs(word,vowel + voArr[i]);
			
				if(found) return;
		}
	}
}
