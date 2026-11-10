import java.util.*;

class Solution {
    
    //남은 시간을 최대한 균등하게 남겨야 야근 피로도 최소
    //배열 크기, 합 x이고, 일할 수 있는 시간 -> 최소화 할 값 찾기?
    //제일 큰 값에서 빼기 , 제일 큰 값 ?
    //우선순위 큐 ? n번반복하면서 빼기
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        
        for(int i = 0; i < works.length;i++){
            pq.offer(works[i]);
        }
        
        for(int i = 0 ; i < n ; i++){
            int now = pq.poll();
            if(now == 0){
                pq.offer(0);
                break;
            }
            else{
                pq.offer(now-1);
            }
        }
        int count = pq.size();
        for(int i = 0 ; i < count;i++){
           
            int now = pq.poll();
            answer += (now * now);
        }
        
        
        return answer;
    }
}