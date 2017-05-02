/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016
// PROJECT:          p4
// FILE:             Interval.java
//
// TEAM:    Team 6
// Authors:
// Author1: (Sidney Smith, sbsmith5@wisc.edu, sbsmith5, 001)
// Author2: ()
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;
import java.util.List;

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
	
	private String[] edgeProperties;
	//SID - Will be representing the graph as an ArrayList<LinkedList<TYPE (TBD)>>
	private ArrayList<GraphNode> navigationGraph;
	
	/**
	 * Creates a directed NavigationGraph object whose paths have edge properties 
	 * 
	 * @param edgePropertyNames
	 *            properties that edges within this NavigationGraph consist of
	 * @param destination
	 *            destination location for the edge
	 * @param pathProperties
	 *            list of property values associated with the edge (Note: length
	 *            of this list must agree with the propertynames in the graph)
	 */
	public NavigationGraph(String[] edgePropertyNames) {
		this.edgeProperties = new String[edgePropertyNames.length];
		return;
	}
	//JACK'S ATTEMPT BELOW
//	//TODO: THIS NEEDS TO BE CHANGED TO GRAPHNODES
//	public NavigationGraph(String[] edgePropertyNames) {
//		Boolean visited[] = new Boolean [edgePropertyNames.length];
//		int totalWeight[] = new int [edgePropertyNames.length];
//		//array of paths?
//		for (int i = 0; i < edgePropertyNames.length - 1; i++){
//			visited[i] = false;
//			totalWeight[i] = Integer.MAX_VALUE;
//			GraphNode node = new GraphNode(edgePropertyNames[i], i);
//			
//			//set paths to null
//		}
//		totalWeight[0] = 0; // set start vertex weight to zero
//		PriorityQueue<String> queue = 
//	            new PriorityQueue<String>(edgePropertyNames.length,
//	            		getShortestRoute(V src, V dest, String edgePropertyName)); //unsure??
//		//insert start vertex total weight, start vertex to queue
//		// step 4 (last loop) on pg 4 of week 13 notes
//			
//		
//	}
	///END JACK'S ATTEMPT
	
	/**
	 * Adds a vertex to the Graph
	 * 
	 * @param vertex
	 *            vertex to be added
	 */
	public void addVertex(Location vertex){
		//TODO
		//May need to check if the graphnode already exists.
		GraphNode toAdd = new GraphNode(vertex, );
		this.navigationGraph.add()
		return;
	}
	
	/**
	 * Creates a directed edge from src to dest
	 * 
	 * @param src
	 *            source vertex from where the edge is outgoing
	 * @param dest
	 *            destination vertex where the edge is incoming
	 * @param edge
	 *            edge between src and dest
	 */
	public void addEdge(Location src, Location dest, Path edge){
		//TODO
		return;
	}
	
	/**
	 * Getter method for the vertices
	 * 
	 * @return List of vertices of type Location
	 */
	public List<Location> getVertices(){
		return;
	}
	
	/**
	 * Returns edge if there is one from src to dest vertex else null
	 * 
	 * @param src
	 *            Source vertex
	 * @param dest
	 *            Destination vertex
	 * @return Edge of type Path from src to dest
	 */
	public Path getEdgeIfExists(Location src, Path dest){
		return;
	}
	
	/**
	 * Returns the outgoing edges from a vertex
	 * 
	 * @param src
	 *            Source vertex for which the outgoing edges need to be obtained
	 * @return List of edges of type Path
	 */
	public List<Path> getOutEdges(Location src){
		return;
	}
	
	/**
	 * Returns neighbors of a vertex
	 * 
	 * @param vertex
	 *            vertex for which the neighbors are required
	 * @return List of vertices(neighbors) of type Location
	 */
	public List<Location> getNeighbors(Location vertex){
		return;
	}
	
	/**
	 * Calculate the shortest route from src to dest vertex using
	 * edgePropertyName
	 * 
	 * @param src
	 *            Source vertex from which the shortest route is desired
	 * @param dest
	 *            Destination vertex to which the shortest route is desired
	 * @param edgePropertyName
	 *            edge property by which shortest route has to be calculated
	 * @return List of paths that denote the shortest route by edgePropertyName
	 */
	public List<Path> getShortestRoute(Location src, Location dest, String edgePropertyName){
		return;
	}
	
	/**
	 * Getter method for edge property names
	 * 
	 * @return array of String that denotes the Path property names
	 */
	public String[] getEdgePropertyNames(){
		return;
	}	
	
	/**
	 * Returns a Location object given its name
	 * 
	 * @param name
	 *            name of the location
	 * @return Location object
	 */
	public Location getLocationByName(String name) {
		Location loc = new Location(name);
		return loc; 
	}

}
