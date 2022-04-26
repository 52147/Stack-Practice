package queueandbfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 286. Walls and Gates
 * 
 * -1 : a wall
 * 0 : a gate
 * INF : infinity means an empty rooms. we use the value 2^31 - 1 = 2147483647 to represent INF
 * 
 * 
 * - Fill each empty room with distance to its nearest gate.
 * - If it is impossible to reach a gate, it should be filled with INF.
 * 
 * 
 * 
 * Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
 * Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 * 
 * 
 * Approach : BFS
 *
 */
public class WallsAndGates286 {
	
	private static final int EMPTY = Integer.MAX_VALUE;
	private static final int GATE = 0;
	private static final List<int[]> DIRECTIONS = Arrays.asList(
			new int[] {1, 0},
			new int[] {-1, 0},
			new int[] {0, 1},
			new int[] {0, -1}
			);
	
	
	public void wallsAndGates(int[][] rooms) {
		int m = rooms.length;
		if(m == 0) {return;}
		
		int n = rooms[0].length;
		
		Queue<int[]> q = new LinkedList<>();
		
		for(int row = 0; row < m; row++) {
			for(int col = 0; col < n; col++) {
				if(rooms[row][col] == GATE) { // initiate bfs from all gates (first level)
					q.add(new int[] {row, col}); 
				}
			}
		}
		
		
		while(!q.isEmpty()) {
			int[] point = q.poll();
			
			int row = point[0];
			int col = point[1];
			
			for(int[] direction : DIRECTIONS) {
				int r = row + direction[0];
				int c = col + direction[1];
				
				// if the row or col out of boundary or encounter a wall or gate process another route form the gate
				if(r < 0 || c < 0 || r>= m || c >= n || rooms[r][c] != EMPTY) {
					continue;
				}
				// otherwise set the room to the step from the closet gate
				rooms[r][c] = rooms[row][col] + 1;
				// add the route in it
				q.add(new int[] {r, c});
			}
		}
				
	}
	

}
/**
 * time: O(mn)
 * - start with only 1 gate
 * - the bfs search takes at most m x n steps to reach all rooms
 *   ,therefore the time complexity is O(mn)
 *   
 * - if we do bfs from k gates?
 * - Once we set a room's distance, we are marking it as visited, which means each room is visited at most once.
 * - therefore, the time does not depend on the number of gates and is O(mn)
 * 
 * 
 * space: O(mn)
 * - depends on the queue's size.
 * - we insert at most m * n points into the queue
 */





