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

/**
 * Wrapper class for the vertices of the graph. 
 * Containing data need to implement "getShortestPath"
 * Each entry will be used in the priority queue in "getShortestPath" 
 *
 */
class QueueEntry implements Comparable<QueueEntry>{
	
	// Weight of the path
	private Double weight;
	// Graphnode corresponding to current location		
	private GraphNode<Location, Path> vertex;
	// Visited? Just a marker 
	private boolean visited; 
	// Used in tracing back up the shortest route
	private QueueEntry predecessor; 

	/**
	 * Constructor for the priority queue entry 
	 * @param weight
	 * @param startVertex
	 */
	QueueEntry(Double weight, GraphNode<Location, Path> startVertex){
		this.weight = weight;
		this.vertex = startVertex;
		visited = false; 
		predecessor = null; 
	}
	
	/**
	 * Setter method for the predecessor for this entry
	 * 
	 */
	public void setPredecessor(QueueEntry pred){
		predecessor = pred; 
	}
	
	/**
	 * Getter method for the predecessor
	 * 
	 * @return the predecessor for this entry 
	 */
	public QueueEntry getPredecessor(){
		return predecessor; 
	}
	
	/**
	 * Setter method for the visited field for this entry
	 * 
	 */
	public void setVisited(boolean vis){
		visited = vis; 
	}
	
	/**
	 * Getter method for the predecessor
	 * 
	 * @return the true or false boolean statement for this entry 
	 */
	public boolean getVisited(){
		return visited; 
	}
	
	/**
	 * Getter method for the predecessor
	 * 
	 * @return the weight for this entry
	 */
	public Double getWeight() {
		return this.weight;
	}
	
	/**
	 * Getter method for the predecessor
	 * 
	 * @return the vertex 
	 */
	public GraphNode<Location, Path> getVertex() {
		return this.vertex;
	}
	
	/**
	 * Setter method for the weight for this entry
	 */
	void setWeight(Double wght) {
		this.weight = wght;
	}
	
	/**
	 * Implements the comparable class. 
	 */
	public int compareTo(QueueEntry o) {
		if(this.weight < o.getWeight()) {
			return -1;
		}
		else if(this.weight == o.getWeight()) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
}
