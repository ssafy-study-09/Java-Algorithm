import sys

class Graph:
    def __init__(self,N):
        self.N = N
        self.con = [
            []
            for _ in range(N+1)
        ]
        
    def add_edge(self,x,y,bidirect=True):
        pass
    
    def is_x_y_con(self,x,y):
        pass
    
    def get_con_by_x(self,x):
        pass
    
    
class adjList(Graph):
    def __init__(self,N):
        super().__init__(N)
    
    def add_edge(self, x, y, bidirect=True):
        self.con[x].append(y)
        if bidirect:
            self.con[y].append(x)
            
    def is_x_y_con(self, x, y):
        return y in self.con[x]
    
    def get_con_by_x(self, x):
        return self.con[x]


def findCircle():
    global visited
    visited = [0]*(N+1)
    dfs(1,1)
    # for i in range(1,N+1):
    
def dfs(start,current):
    visited[current]=1
    if current in adj.get_con_by_x(start):
        print(visited)
        return
    for e in adj.get_con_by_x(current):
        if not visited[e]:
            dfs(start,e)
si = sys.stdin.readline

N = int(si().rstrip())

adj = adjList(N)

visited = [0]*(N+1)
for _ in range(N):
    x,y = map(int,si().rstrip().split())
    adj.add_edge(x,y)

findCircle()