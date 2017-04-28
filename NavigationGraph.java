public class NavigationGraph implements GraphADT<Location, Path> {

	//TODO: Implement all methods of GraphADT
	
//	The implementation is left up to you, though we strongly recommend using the GraphNode
//	class to implement an Adjacency List representation of the graph. You may add whatever 
//	private methods you see fit to in addition to implementing all of the required public
//	methods even if your final solution does not use a particular operation.
//
//	This class must implement GraphADT and create a graph of Locations connected by Paths. 
//	i.e., it must not be generic.  
//	The constructor must accept an array of String property names (eg. Time, Cost) that 
//	correspond to the edge properties.	
	
	public NavigationGraph(String[] edgePropertyNames) {
		Boolean visited[] = new Boolean [edgePropertyNames.length];
		int totalWeight[] = new int [edgePropertyNames.length];
		//array of paths?
		for (int i = 0; i < edgePropertyNames.length - 1; i++){
			visited[i] = false;
			totalWeight[i] = Integer.MAX_VALUE;
			GraphNode node = new GraphNode(edgePropertyNames[i], i);
			//set paths to null
		}
		totalWeight[0] = 0; // set start vertex weight to zero
		PriorityQueue<String> queue = 
	            new PriorityQueue<String>(edgePropertyNames.length,
	            		getShortestRoute(V src, V dest, String edgePropertyName)); //unsure??
		//insert start vertex total weight, start vertex to queue
		// step 4 (last loop) on pg 4 of week 13 notes
			
		
	}
	
	
	/**
	 * Returns a Location object given its name
	 * 
	 * @param name
	 *            name of the location
	 * @return Location object
	 */
	public Location getLocationByName(String name) {
		
		return null; //TODO: implement correctly. 
	}

}
