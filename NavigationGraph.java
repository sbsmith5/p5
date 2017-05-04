/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016
// PROJECT:          p4
// FILE:             Interval.java
//
// TEAM:    Team 6
// Authors:
// Author1: (Sidney Smith, sbsmith5@wisc.edu, sbsmith5, 001)
// Author2: (Jack Weissburg,,,)
// Author3: (Patrick Budyn, budyn@wisc.edu, budyn, 002)
// Author4: (Shikhar Mittal,,,)
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class NavigationGraph implements GraphADT<Location, Path> {


	private String[] edgeProperties;
	//SID - Will be representing the graph as an ArrayList<LinkedList<TYPE (TBD)>>
	private ArrayList< GraphNode<Location, Path>> navigationGraph;
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
		if(edgePropertyNames == null)
			throw new IllegalArgumentException();
		
		this.edgeProperties = edgePropertyNames;	
	}



	/**
	 * Adds a vertex to the Graph
	 * 
	 * @param vertex
	 *            vertex to be added
	 */
	public void addVertex(Location vertex){
		// TODO - Done (Sid)
		// Checked (PB & JW)

		//scan the list of current vertices in the graph to make sure that the vertex to be added
		//does not already exist
		if (vertex == null)
			throw new IllegalArgumentException();
		for (int i = 0; i<this.vertices.size(); i++)
			if (this.vertices.get(i).equals(vertex))
				return;

		GraphNode<Location, Path> toAdd = new GraphNode<Location, Path>(vertex, this.id);
		

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
		// do we need to add a check to see if there is path from src to dest.

		if(src == null || dest == null || edge == null)
			throw new IllegalArgumentException();
		GraphNode<Location, Path> g = getGraphNodebyLocation(src);
		
		if(g == null){
			throw new NullPointerException();
		}
		
		g.addOutEdge(edge);
		this.edges.add(edge); //adds edge
		this.navigationGraph.get(g.getId()).addOutEdge(edge);
		return;
	}

	private GraphNode<Location, Path> getGraphNodebyLocation(Location src){

		//TODO optimize this code. Do not create a graphnode every loop iteration
		for(int j=0; j<navigationGraph.size();j++){
			GraphNode<Location, Path> temp = (navigationGraph.get(j));
			if(temp.getVertexData().equals(src)){
				return temp;
			}
		}
		return null;
	}

	/**
	 * Getter method for the vertices
	 * 
	 * @return List of vertices of type Location
	 */
	public List<Location> getVertices(){
		//TODO - Done (SID)
		//		 Checked (PB & JW)
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
	public Path getEdgeIfExists(Location src, Location dest){
		//TODO - Done (SID)
		//		 Checked (PB & JW)
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
		//		 Checked (PB & JW)
		GraphNode<Location, Path> g = getGraphNodebyLocation(src);
		if (g!=null)
			return g.getOutEdges();
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
		
		if (vertex == null)
			throw new IllegalArgumentException();
		
		List<Location> neighbors = new ArrayList<Location>();
		List<Path> vertexPaths = new ArrayList<Path>();

		//check all nodes for neighbors or vertex
		for(int i = 0; i < vertexPaths.size(); i++){
			if(navigationGraph.get(i).equals(vertex)){
				//"out" neighbors
				List<Path> temp = navigationGraph.get(i).getOutEdges();
				for(int j = 0; j < temp.size(); j++){
					neighbors.add(temp.get(j).getSource());
				}
			}
			//"in" neighbors
			if(navigationGraph.get(i).getOutEdges().contains(vertex)){
				neighbors.add(navigationGraph.get(i).getVertexData());
			}
		}

		return neighbors;
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
	public List<Path> getShortestRoute(Location src, Location dest, String edgeProperty)
		throws IllegalArgumentException{
		//make static shell class with predecessor list equal to null, weight equal to infinity, and visited to false
		//in algorithm, have priority queue with vertex info of locations, put weight equal to 0
		//call get vertices //this.getvertices
		
		if (src == null || dest == null || edgePropertyName == null)
			throw new IllegalArgumentException();
		
		List<Path> shortestPath = new ArrayList<Path>();
		
		QueueEntry[] queue = new QueueEntry[navigationGraph.size()];
		int indexOfProperty = 0;
		GraphNode<Location, Path> node = null;
		
		//get index for weight property to be used for shortest route
		for (int i = 0; i<edgeProperties.length; i++){
			if(edgeProperties[i].equals(edgeProperty)){
				indexOfProperty = i;
				break;
			}
		}
		
		//create a queue entry for each vertex
		for (int i = 0; i<navigationGraph.size(); i++){
			
			//get all vertices in the graph, place them in the list of queue entries
			queue[i] = new QueueEntry(Double.MAX_VALUE, navigationGraph.get(i));
			//set each entry to unvisited
			queue[i].setVisited(false);
			//initialize each entry's predecessor to null
			queue[i].setPredecessor(null);
		}
		
		
		int startIndex = getIdByName(src);
		queue[startIndex].setWeight(0.0);
		PriorityQueue<QueueEntry> pq = new PriorityQueue<QueueEntry>();
		pq.add(queue[startIndex]);
		
		//current entry, 'C' as in Dijkstra's algorithm in class notes
		QueueEntry curr;
		while(!pq.isEmpty()){
			
			//remove min from queue
			curr = pq.poll();
			curr.setVisited(true);
			
			for (int j=0; j<curr.getVertex().getOutEdges().size(); j++){
				Path successor = curr.getVertex().getOutEdges().get(j);
				//get successor location in navgraph for the node that corresponds to the successor
				int index = getIdByName(successor.getDestination());
				
				if (!queue[index].getVisited()){
					if (curr.getWeight() + successor.getProperties().get(indexOfProperty) < queue[index].getWeight()){
						queue[index].setWeight(curr.getWeight() + successor.getProperties().get(indexOfProperty));
						queue[index].setPredecessor(curr);
						if (!pq.contains(queue[index])){
							pq.add(queue[index]);
						}
					}
				}
			}
		}
		
		//
		
		boolean visited = false; 
		double vectorWeight = Double.POSITIVE_INFINITY; 

		return;
	}

	/**
	 * Getter method for edge property names
	 * 
	 * @return array of String that denotes the Path property names
	 */
	public String[] getEdgePropertyNames(){
		return edgeProperties ;

	}



	/**
	 * Returns a Location object given its name
	 * 
	 * @param name
	 *            name of the location
	 * @return Location object
	 */
	public Location getLocationByName(String name) {
		
		if (name == null)
			throw new IllegalArgumentException();
		
		for (int k = 0; k < navigationGraph.size(); k++){
			if (vertices.get(k).getName().equals(name)){
				return vertices.get(k);
			}
		}
		return null;

	}
	
	public int getIdByName(Location name) 
		throws IllegalArgumentException{
		
		for (int j = 0; j<navigationGraph.size(); j++){
			if (navigationGraph.get(j).getVertexData().equals(name)){
				return navigationGraph.get(j).getId();
			}
		}
		throw new IllegalArgumentException();
	}

}