class Solution {
    public int find(int[] parent, int u) {
        if(u == parent[u])
            return u;

        parent[u] = @@@;
        return parent[u];
    }

    public boolean merge(int[] parent, int u, int v) {
        u = find(parent, u);
        v = find(parent, v);

        if(u == v)
            return true;

        @@@;
        return false;
    }

    public int solution(int n, int[][] connections) {
        int answer = 0;

        int[] parent = new int[n+1];
        for(int i = 1; i <= n; i++)
            @@@;

        for(int i = 0; i < connections.length; i++)
            if(merge(parent, connections[i][0], connections[i][1])) {
                answer = i + 1;
                break;
            }

        return answer;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 3;
        int[][] connections = {{1, 2}, {1, 3}, {2, 3}};
        int ret = sol.solution(n, connections);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret + " 입니다.");
    }
}