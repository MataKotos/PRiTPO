package lr5pt2;
import java.util.*;
import java.io.*;

public class ProblemSelection {
    static class Edge {
        int to, rev;
        int flow, capacity;
        
        Edge(int to, int rev, int capacity) {
            this.to = to;
            this.rev = rev;
            this.capacity = capacity;
        }
    }
    
    static class Dinic {
        int[] level;
        List<Edge>[] graph;
        
        Dinic(int nodes) {
            level = new int[nodes];
            graph = new ArrayList[nodes];
            for (int i = 0; i < nodes; i++)
                graph[i] = new ArrayList<>();
        }
        
        void addEdge(int u, int v, int capacity) {
            Edge forward = new Edge(v, graph[v].size(), capacity);
            Edge backward = new Edge(u, graph[u].size(), 0);
            graph[u].add(forward);
            graph[v].add(backward);
        }
        
        boolean bfs(int source, int sink) {
            Arrays.fill(level, -1);
            level[source] = 0;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(source);
            
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (Edge e : graph[u]) {
                    if (level[e.to] < 0 && e.flow < e.capacity) {
                        level[e.to] = level[u] + 1;
                        queue.add(e.to);
                    }
                }
            }
            return level[sink] >= 0;
        }
        
        int dfs(int u, int sink, int flow) {
            if (u == sink) return flow;
            
            for (Edge e : graph[u]) {
                if (level[e.to] == level[u] + 1 && e.flow < e.capacity) {
                    int minFlow = Math.min(flow, e.capacity - e.flow);
                    int pushedFlow = dfs(e.to, sink, minFlow);
                    
                    if (pushedFlow > 0) {
                        e.flow += pushedFlow;
                        graph[e.to].get(e.rev).flow -= pushedFlow;
                        return pushedFlow;
                    }
                }
            }
            return 0;
        }
        
        int maxFlow(int source, int sink) {
            int totalFlow = 0;
            while (bfs(source, sink)) {
                int flow;
                while ((flow = dfs(source, sink, Integer.MAX_VALUE)) > 0) {
                    totalFlow += flow;
                }
            }
            return totalFlow;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nk = Integer.parseInt(st.nextToken());
            int np = Integer.parseInt(st.nextToken());
            
            if (nk == 0 && np == 0) break;
            
            int[] categories = new int[nk];
            st = new StringTokenizer(br.readLine());
            int totalRequired = 0;
            for (int i = 0; i < nk; i++) {
                categories[i] = Integer.parseInt(st.nextToken());
                totalRequired += categories[i];
            }
            
            List<List<Integer>> problemCategories = new ArrayList<>();
            for (int i = 0; i < np; i++) {
                st = new StringTokenizer(br.readLine());
                int cnt = Integer.parseInt(st.nextToken());
                List<Integer> cats = new ArrayList<>();
                for (int j = 0; j < cnt; j++) {
                    cats.add(Integer.parseInt(st.nextToken()) - 1);
                }
                problemCategories.add(cats);
            }
            
            int source = 0;
            int sink = 1 + nk + np;
            Dinic dinic = new Dinic(sink + 1);
            
            for (int i = 0; i < nk; i++) {
                dinic.addEdge(source, 1 + i, categories[i]);
            }
            
            for (int i = 0; i < np; i++) {
                for (int cat : problemCategories.get(i)) {
                    dinic.addEdge(1 + cat, 1 + nk + i, 1);
                }
            }
            
            for (int i = 0; i < np; i++) {
                dinic.addEdge(1 + nk + i, sink, 1);
            }
            
            int maxFlow = dinic.maxFlow(source, sink);
            
            if (maxFlow != totalRequired) {
                pw.println(0);
            } else {
                pw.println(1);
                
                // Reconstruct the solution
                List<List<Integer>> selected = new ArrayList<>();
                for (int i = 0; i < nk; i++) {
                    selected.add(new ArrayList<>());
                }
                
                for (int i = 0; i < np; i++) {
                    int problemNode = 1 + nk + i;
                    for (Edge e : dinic.graph[problemNode]) {
                        if (e.to >= 1 && e.to <= nk && e.flow == -1) {
                            selected.get(e.to - 1).add(i + 1);
                        }
                    }
                }
                
                for (int i = 0; i < nk; i++) {
                    Collections.sort(selected.get(i));
                    StringBuilder sb = new StringBuilder();
                    for (int prob : selected.get(i)) {
                        sb.append(prob).append(" ");
                    }
                    if (sb.length() > 0) {
                        sb.setLength(sb.length() - 1);
                    }
                    pw.println(sb.toString());
                }
            }
        }
        
        pw.flush();
        pw.close();
        br.close();
    }
}