package com.code.challenge.Graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Code challenge graph class.
 *
 * @author  Prashant Kumar
 * @version 1.0
 * @since   2020-07-15
 */
public class Graph {

    private int V;
    private ArrayList<Integer> adj[];
    public Map<String, Integer> cities = new HashMap<>();


    public Graph(int v, Map<String, Integer> cities) {
        V = v;
        adj = new ArrayList[v];
        for(int i=0;i<v;i++) {
            adj[i] = new ArrayList<Integer>();
        }
        this.cities = cities;
    }

    /**
     * This is the addEdge method used to add edges between 2 vertices.
     *
     * @param u Source vertex
     * @param v Destination vertex
     */
    public void addEdge(int u, int v) {
        adj[u].add(v);
    }



    /**
     * This is the explore method which is used to implemented the dfs algorithm.
     *
     * @param adj vertex adjacency array list
     * @param v vertex
     * @param visited used to mark the current vertex visited
     */
    private void explore(ArrayList<Integer>[] adj, int v, boolean[] visited) {
        if(visited[v] == true) return;
        visited[v] = true;
        for(int i = 0; i < adj[v].size(); i++) {
            explore(adj, adj[v].get(i), visited);
        }
    }

    /**
     * This is the reach method used to check whether one vertex is reachable from another or not.
     *
     * @param x Source vertex
     * @param y Destination vertex
     * @return 1 is vertex are connected, otherwise 0
     */
    public int reach(int x, int y) {
        int result = 0;
        boolean[] visited = new boolean[adj.length];
        for(int i = 0; i < adj[x].size(); i++) {
            explore(adj, adj[x].get(i), visited);
        }
        if(visited[y] == true) {
            result = 1;
        }
        return result;
    }
}