/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016
// PROJECT:          p4
// FILE:             Interval.java
//
// TEAM:    Team 6
// Authors:
// Author1: (Sidney Smith, sbsmith5@wisc.edu, sbsmith5, 001)
// Author2: (Jack Weisburg, weissburg2@wisc.edu, weissburg2, 002)
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Driver class that reads/parses the input file and creates NavigationGraph
 * object.
 * 
 * @author CS367
 *
 */
public class MapApp {

	private NavigationGraph graphObject;

	/**
	 * Constructs a MapApp object
	 * 
	 * @param graph
	 *            NaviagtionGraph object
	 */
	public MapApp(NavigationGraph graph) {
		this.graphObject = graph;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java MapApp <pathToGraphFile>");
			System.exit(1);
		}

		// read the filename from command line argument
		String locationFileName = args[0];
		try {
			NavigationGraph graph = createNavigationGraphFromMapFile(locationFileName);
			MapApp appInstance = new MapApp(graph);
			appInstance.startService();

		} catch (FileNotFoundException e) {
			System.out.println("GRAPH FILE: " + locationFileName + " was not found.");
			System.exit(1);
		} catch (InvalidFileException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

	}

	/**
	 * Displays options to user about the various operations on the loaded graph
	 */
	public void startService() {

		System.out.println("Navigation App");
		Scanner sc = new Scanner(System.in);

		int choice = 0;
		do {
			System.out.println();
			System.out.println("1. List all locations");
			System.out.println("2. Display Graph");
			System.out.println("3. Display Outgoing Edges");
			System.out.println("4. Display Shortest Route");
			System.out.println("5. Quit");
			System.out.print("Enter your choice: ");

			while (!sc.hasNextInt()) {
				sc.next();
				System.out.println("Please select a valid option: ");
			}
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println(graphObject.getVertices());
				break;
			case 2:
				System.out.println(graphObject.toString());
				break;
			case 3: {
				System.out.println("Enter source location name: ");
				String srcName = sc.next();
				Location src = graphObject.getLocationByName(srcName);

				if (src == null) {
					System.out.println(srcName + " is not a valid Location");
					break;
				}

				List<Path> outEdges = graphObject.getOutEdges(src);
				System.out.println("Outgoing edges for " + src + ": ");
				for (Path path : outEdges) {
					System.out.println(path);
				}
			}
				break;

			case 4:
				System.out.println("Enter source location name: ");
				String srcName = sc.next();
				Location src = graphObject.getLocationByName(srcName);

				System.out.println("Enter destination location name: ");
				String destName = sc.next();
				Location dest = graphObject.getLocationByName(destName);

				if (src == null || dest == null) {
					System.out.println(srcName + " and/or " + destName + " are not valid Locations in the graph");
					break;
				}

				if (src == dest) {
					System.out.println(srcName + " and " + destName + " correspond to the same Location");
					break;
				}

				System.out.println("Edge properties: ");
				// List Edge Property Names
				String[] propertyNames = graphObject.getEdgePropertyNames();
				for (int i = 0; i < propertyNames.length; i++) {
					System.out.println("\t" + (i + 1) + ": " + propertyNames[i]);
				}
				System.out.println("Select property to compute shortest route on: ");
				int selectedPropertyIndex = sc.nextInt() - 1;

				if (selectedPropertyIndex >= propertyNames.length) {
					System.out.println("Invalid option chosen: " + (selectedPropertyIndex + 1));
					break;
				}

				String selectedPropertyName = propertyNames[selectedPropertyIndex];
				List<Path> shortestRoute = graphObject.getShortestRoute(src, dest, selectedPropertyName);
				for(Path path : shortestRoute) {
					System.out.print(path.displayPathWithProperty(selectedPropertyIndex)+", ");
				}
				if(shortestRoute.size()==0) {
					System.out.print("No route exists");
				}
				System.out.println();

				break;

			case 5:
				break;

			default:
				System.out.println("Please select a valid option: ");
				break;

			}
		} while (choice != 5);
		sc.close();
	}

	/**
	 * Reads and parses the input file passed as argument create a
	 * NavigationGraph object. The edge property names required for
	 * the constructor can be got from the first line of the file
	 * by ignoring the first 2 columns - source, destination. 
	 * Use the graph object to add vertices and edges as
	 * you read the input file.
	 * 
	 * @param graphFilepath
	 *            path to the input file
	 * @return NavigationGraph object
	 * @throws FileNotFoundException
	 *             if graphFilepath is not found
	 * @throws InvalidFileException
	 *             if header line in the file has < 3 columns or 
	 *             if any line that describes an edge has different number of properties 
	 *             	than as described in the header or 
	 *             if any property value is not numeric 
	 */

	public static NavigationGraph createNavigationGraphFromMapFile(String graphFilepath) throws FileNotFoundException, InvalidFileException{
			// TODO: read/parse the input file graphFilepath and create
			// NavigationGraph with vertices and edges
					//String[] edgePropertyNames = new String[10]; //TODO: arrayList
		if(graphFilepath == null)
			throw new FileNotFoundException();
		
		//array list of the edge names
		List<String> edgeNamesList = new ArrayList<String>();
		
		
		//Scanner for string
		Scanner scnr = new Scanner(graphFilepath); 
		String headers = scnr.nextLine(); 
		//Scanner for header labels (first line)
		Scanner firstLine = new Scanner(headers); 
		firstLine.next(); //skips "source"
		firstLine.next(); //skips "destination"
		int propertyCounter = 0;
		while (firstLine.hasNext()){
			//adds property names from file
			edgeNamesList.add(firstLine.next()); 
			propertyCounter++;
		}
		//if header line is too short
		if (propertyCounter < 2){
			throw new InvalidFileException("header line in the file has < 3 columns");
		}
		
		//converts ArrayList of property names to Array
		String[] edgePropertyNames = new String [edgeNamesList.size()];
		for(int k = 0; k < edgeNamesList.size(); k++){
				edgePropertyNames[k] = edgeNamesList.get(k);
		}
			
		//navGraph that will add vertices and edges
		NavigationGraph navGraph = new NavigationGraph(edgePropertyNames);
		
		//arrayList of the location
		List<Location> locationList = new ArrayList<Location>();

		int i = 0; //i is used for the GraphNode IDs
		while (scnr.hasNext()){
			//list for edge values
			List<Double> edges = new ArrayList<Double>(edgeNamesList.size()); 
			String source;
			String destination;
			Boolean sourceDup = false;
			Boolean destDup = false;
			source = scnr.next();
			destination = scnr.next();
			Location sourceLoc = new Location(source);
			Location destinationLoc = new Location(destination);
			
			for(int j = 0; j < locationList.size(); j++){
				if (source.equals(locationList.get(j))){
					sourceDup = true;
				}
				if (destination.equals(locationList.get(i))){
					destDup = true;
				}
				if (source.equals(destination)){
					destDup = true;
					sourceDup = true;
				}
			}
			if(!sourceDup){
				//creates new vertex if it isn't a duplicate
				navGraph.addVertex(sourceLoc);
				i++;
				//adds location to the list
				locationList.add(sourceLoc);
			}
			if(!destDup){
				navGraph.addVertex(destinationLoc);
				i++;
				locationList.add(destinationLoc);
			}
			
			//create edge
			Path newPath = new Path(sourceLoc, destinationLoc, edges);
			navGraph.addEdge(sourceLoc, destinationLoc, newPath);
			
			//adds edge weight if it is numeric
			int intTemp = 0; //used if weight is an int
			//keep track of how many properties are on each line
			int numOfProperties = 0;
			while(scnr.hasNextLine()){
				if (scnr.hasNextDouble()){
					edges.add(scnr.nextDouble());
					numOfProperties++;
				}
				else if (scnr.hasNextInt()) {
					intTemp = scnr.nextInt();
					edges.add((double) intTemp);
					numOfProperties++;
				}
				else {
					throw new InvalidFileException("property value is not numeric");
				}	
			}
			//if the number of properties is inconsistent, throw error
			if (numOfProperties != propertyCounter)
				throw new InvalidFileException("line that describes an edge has"
						+ " different number of properties than as described in the header");
			
			scnr.nextLine(); //skip to next line
		}
		
		
		//returns the navigation graph
		return navGraph;

	}

}
