
3주차 목

**백준 14889. 스타트와 링크**
---
[링크](https://www.acmicpc.net/problem/14889)  

N 최대 값 : 20, 20명을 두 팀으로 나누는 경우의 수 : 20C10 = 18만
제한시간 2초라 여유가 큼

중첩문 돌면서 같은 팀 끼리 합치고 차이 최소 구하기

**백준 11725. 트리의 부모 찾기**
---
[링크](https://www.acmicpc.net/problem/11725)  


**백준 2085. 나무 자르기**
---
[링크](https://www.acmicpc.net/problem/2805)  

binary search 기본 문제  
가져갈 수 있는 나무만 long하면 된다 


**백준 2252. 줄 세우기**
---
[링크](https://www.acmicpc.net/problem/2252)  

DAG 해당.  ex) 1번 > 2번, 2번 > 3번인데  3번>1번인 싸이클 X 일자로 서기 때문  
위상정렬 ,  indegree 0인것 뽑으면서 값에 저장 
indegree 높은 값이 줄에 앞에 선다.   
queue에서 뒤에나오는데 결과는 앞이기에 결과를 sb.insert하면서 저장 

**백준 1967. 트리의지름**
---
[링크](https://www.acmicpc.net/problem/1967)  

**백준 17472. 다리 만들기2**
---
[링크](https://www.acmicpc.net/problem/17472)  
못품
