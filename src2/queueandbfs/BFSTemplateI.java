package queueandbfs;

import java.util.Queue;

/**
 * 
 * = Queue and BFS =
 * 
 * One common application of BFS is to find the shortest path from the root node to the target node.
 * 
 * 
 * Insights:
 * 
 * 1. What is the processing order of the nodes?
 * 
 * - In the first round, we process the root node.
 * - In the second round, we process the nodes next to the root node;
 * - In the third round, we process the nodes which are two steps from the root node; so on and so forth.
 * 
 * - Similar to tree's level-order traversal,
 *   the nodes closer to the root node will be traversed earlier.
 * 
 * - If a node X is added to the queue in the kth round,
 *   the length of the shortest path between the root node and X is exactly k.
 * - That is to say, you are already in the shortest path the first time you find the targest node.
 * 
 * 
 * 2. What is the enqueue and dequeue order of the queue?
 * 
 * - As shown in the animation above, we first enqueue the root node.
 * - Then in each round, we process the nodes which are already in the queue one by one
 *   and add all their neighbors to the queue.
 *   
 * - It is worth noting that the newly-added nodes will not be traversed immediately but will be processed in the next round.
 * 
 * - The processing order of the nodes is the exact same order as how they were added to the queue, 
 *   which is First-in-First-out(FIFO).
 *   
 *   
 *  = BFS template =
 *  
 *  - Previously, we have already introduced 2 main scenarios of using BFS:
 *    1. do traversal
 *    2. find the shortest path.
 *  - Typically, it happens in a tree or a graph.
 *  
 *  - It will be important to do determine the nodes and the edges before doing BFS in a specific question.
 *  - Typically, the node will be an actual node or a status while the edge will be an actual edge or a possible transition.
 *  
 *  
 * 
 *
 */

// 1. As shown in the code, in each round, the nodes in the queue are the nodes which are waiting to be processed.
// 2. After each outer while loop, we are one step farther from the root node.
//    The variable step indicates the distance from the root node and the current node we are visiting.
public class BFSTemplateI {
	
	
	/**
	 * Return the length of the shortest path between root and target node.
	 */
	int BFS(Node root, Node target) {
		
		Queue<Node> queue; // store all node which are waiting to be processed
		
		int step = 0; // number of steps needed from root to current node
		
		add root to queue; // initialize
		
		
		// bfs
		while(queue is not empty) {
			
			// iterate the nodes which are already in the queue\
			int size = queue.size();
			
			for(int i = 0; i < size; i++) {
				
				Node cur = the first node in queue;
				
				return step if cur is target;
				
				for(Node next: the neighbors of cur) {
					
					add next to queue;
				}
				
				remove the first node form queue;
			}
			
			step = step + 1;
			
		}
		
		return -1; // there is no path from root to target
	}
	

}
