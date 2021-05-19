import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.HashMap;
import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.ArrayList;

// @author Prathamesh Sai

public class DirectedGraph {
	
	protected final double minusInfinity = Double.NEGATIVE_INFINITY;
	protected final double plusInfinity = Double.POSITIVE_INFINITY;
    protected int numberOfVertices;
    protected boolean validGraph;
	//Represent the graph using a hash-map
    protected static Map<Integer, List<Edge>> adjacencyListForGraph;
    //We will use distances for the floyd warshall algorithm.
    protected double[][] distances;


    //To convert an input file into a use-able graph for graph algorithms.
    protected DirectedGraph(String filename) throws IOException {
       if (filename == null) {
    	   this.validGraph = false;
    	   return;
       }
       
      //Declare a new Hash-map for the adjacencyListForGraph in the graph.
      adjacencyListForGraph = new HashMap<Integer, List<Edge>>();
      
      //Declare a BufferedReader (Reads text from a character-input stream, buffering characters for reading lines)
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      
      //The first number in the file represents the number of intersections.
      //This is equivalent to the number of vertices in the graph.
      this.numberOfVertices = Integer.valueOf(reader.readLine());
      
      //The second number in the file represents the number of streets. 
      //This is equivalent to the number of edges. 
      int numberOfEdges = Integer.valueOf(reader.readLine());
      
      //Any line from this point onwards will represent the structure of the graph
      //In other words, the lines from now on will connect the nodes together
      
      //Get the first line which connects the nodes together.
      String currentLine = reader.readLine();
      
      //We will use this variable to verify the graph is correct.
      int numberOfLinesConnectingNodes;
      
      //Iterate through the remaining part of the file, containing the lines which connect the nodes together.
      for(numberOfLinesConnectingNodes = 0; currentLine != null; numberOfLinesConnectingNodes++) {
    	  //We can use a scanner to parse the line.
    	  Scanner scanner = new Scanner(currentLine);
    	  
    	  //The first number on the line will be the first node in the edge.
          int previousNode = scanner.nextInt();
          
          //The second number on the line will be the second node in the edge.
          int connectingNode = scanner.nextInt();
          
          //The third number on the line will be the weight of the edge between the previous two nodes.
          double weight = scanner.nextDouble();
          
          //Try and get the list with the key as the previous node (the key is the previous node because dijkstra uses the first node as a parameter and we can use it there)
          List<Edge> newList = adjacencyListForGraph.get(previousNode);
          
          //If the returning list is null, we can make a new Array-list of edges instead.
          if (newList == null) {
        	  newList = new ArrayList<Edge>();
          }
          
          //Add new edge to list of edges.
          Edge newEdge = new Edge(previousNode, connectingNode, weight);
          
          newList.add(newEdge);
          
          //Put this list into the Hash-map, with the previousNode as the Key.
          adjacencyListForGraph.put(previousNode, newList);
        		  
          //Close the Scanner to avoid any warnings.
          scanner.close();
          
          //Move to the next line, and do the same thing - If it's null, then we're done.
          currentLine = reader.readLine();
      }
    	  
      	//Assume it's a valid graph at the start
      	this.validGraph = true;
      	
    	//If the number of lines that are connecting the nodes is not equivalent to the number of edges,
      	//Then the input file is wrong. If it's the same, then the graph is valid.
       if (numberOfLinesConnectingNodes != numberOfEdges) {
    	  this.validGraph = false;
      }
    }
  }