/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016
// PROJECT:          p4
// FILE:             Interval.java
//
// TEAM:    Team 6
// Authors:
// Author1: (Sidney Smith, sbsmith5@wisc.edu, sbsmith5, 001)
// Author2: (Jack Weissburg,,,)
// Author3: (Patrick,,,)
// Author4: (Shikhar Mittal,,,)
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
	//this represents the list of locations found in this NavigationGraph instance
	private ArrayList<Location> vertices;
	//this represents the list of edges found in this NavigationGraph instance.
	//created to make getEdgeIfExists() easier to implement.
	private ArrayList<Path> edges;
	//this represents the current id to be assigned to new GraphNodes
	private int id = 0;
	
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
		//TODO - Done (Sid)
		this.edgeProperties = new String[edgePropertyNames.length];
		return;
	}
	
	/**
	 * Adds a vertex to the Graph
	 * 
	 * @param vertex
	 *            vertex to be added
	 */
	public void addVertex(Location vertex){
		//TODO - Done (Sid)
		//		 Checked ()
		//May need to check if the graphnode already exists.
		GraphNode<Location, Integer> toAdd = new GraphNode<Location, Integer>(vertex, this.id);
		//May need to check if the vertex already exists.
		vertices.add(vertex);
		this.navigationGraph.add(toAdd);
		this.id++;
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
		//TODO - DONE (SID)
		//	  	 Checked ()
		boolean srcExists = false;
		boolean destExists = false; 
		int srcId = 0;
		int destId = 0;
		//Check to see if the src and dest locations already exist in this.navigationGraph.
		//If they do, add the edge between the existing GraphNodes
		for (int j = 0; j<this.navigationGraph.size(); j++){
			if (this.navigationGraph.get(j).getVertexData().equals(src)){
				srcExists = true;
				srcId = this.navigationGraph.get(j).getId();
			}
			if (this.navigationGraph.get(j).getVertexData().equals(dest)){
				destExists = true;
				destId = this.navigationGraph.get(j).getId();
			}
		}
		//If a graphnode doesn't exist for the src Location, create a new one and add the edge to
		//its list of outEdges.
		if (!srcExists){
			addVertex(src);
			this.navigationGraph.get(srcId).addOutEdge(edge);
			this.edges.add(edge);
		}
		//If a graphnode exists, add the edge to its list of outEdges.
		else {
			this.navigationGraph.get(srcId).addOutEdge(edge);
			this.edges.add(edge);
		}
		return;
	}
	
	/**
	 * Getter method for the vertices
	 * 
	 * @return List of vertices of type Location
	 */
	public List<Location> getVertices(){
		//TODO - Done (SID)
		//		 Checked ()
		return this.vertices;
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
		//TODO - Done (SID)
		//		 Checked ()
		for (int j = 0; j<edges.size(); j++){
			//if the source is the same, check the destination
			if (edges.get(j).getSource().equals(src)){
				//if the destination is the same as well, return the existing edge
				if (edges.get(j).getDestination().equals(dest)){
					return edges.get(j);
				}
			}
		}
		//else, an edge does not exist
		return null;
	}
	
	/**
	 * Returns the outgoing edges from a vertex
	 * 
	 * @param src
	 *            Source vertex for which the outgoing edges need to be obtained
	 * @return List of edges of type Path
	 */
	public List<Path> getOutEdges(Location src) 
			throws IllegalArgumentException{
		//TODO - Done (SID)
		//		 Checked ()
		for (int j=0; j<vertices.size(); j++){
			if (navigationGraph.get(j).getVertexData().equals(src)){
				return navigationGraph.get(j).getOutEdges();
			}
		}
		//if the vertex does not exist, throw illegalArgumentException
		throw new IllegalArgumentException("That location does not exist!");
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
