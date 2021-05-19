import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 * 
 * @author Prathamesh Sai
 */

public class CompetitionFloydWarshall {

	  protected DirectedGraph graph;
	  protected int sA;
	  protected int sB;
	  protected int sC;	
	  
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){
    	//Set the integers representing the average walking speed of each of the contestants.
    	this.sA = sA;
	    this.sB = sB;
	    this.sC = sC;
	    
	    //Use the graph class to populate a graph in which to hold the city road network,
    	try {
    	      this.graph = new DirectedGraph(filename);
    	      setUp2DArrayForFloydWarshall(filename);
    	    } 
        catch (IOException e) {
    	      this.graph = null;
    	    }
    }
    
    protected void setUp2DArrayForFloydWarshall(String filename) throws IOException{
    	if (filename == null) {
      	   this.graph.validGraph = false;
      	   return;
         }
       //Declare a BufferedReader (Reads text from a character-input stream, buffering characters for reading lines)
       BufferedReader reader = new BufferedReader(new FileReader(filename));
       
       //The first number in the file represents the number of intersections.
       //This is equivalent to the number of vertices in the graph.
       this.graph.numberOfVertices = Integer.parseInt(reader.readLine());
       
       
       //Set up 2D array of distances for the Floyd Warshall Algorithm to work on.
       this.graph.distances = new double[this.graph.numberOfVertices][this.graph.numberOfVertices];
       
       
       //The second number in the file represents the number of streets. 
       //This is equivalent to the number of edges. 
       int numberOfEdges = Integer.parseInt(reader.readLine());
       
       //Set all distances to infinity at the start.
       for(int i =0; i<this.graph.distances.length;i++) {
     	  for(int j = 0; j < this.graph.distances[i].length; j++) {
     		 this.graph.distances[i][j] = this.graph.plusInfinity;
     	  }
       }
       
       //Any line from this point onwards will represent the structure of the graph
       //In other words, the lines from now on will connect the nodes together
       
       //Get the first line which connects the nodes together.
       String currentLine = reader.readLine();
       
       
       //Iterate through the remaining part of the file, containing the lines which connect the nodes together.
       for(int numberOfLinesConnectingNodes = 0; currentLine != null; numberOfLinesConnectingNodes++) {
     	  //We can use a scanner to parse the line.
     	  Scanner scanner = new Scanner(currentLine);
     	  
     	  //The first number on the line will be the first node in the edge.
           int previousNode = scanner.nextInt();
           
           //The second number on the line will be the second node in the edge.
           int connectingNode = scanner.nextInt();
           
           //The third number on the line will be the weight of the edge between the previous two nodes.
           double weight = scanner.nextDouble();
           
           this.graph.distances[previousNode][connectingNode] = weight;
           this.graph.distances[previousNode][previousNode] = 0;
          	  
           //Close the Scanner to avoid any warnings.
           scanner.close();
           
           //Move to the next line, and do the same thing - If it's null, then we're done.
           currentLine = reader.readLine();
       }
    }
    
    /** @return void: The Floyd-Warshall Algorithm that is used to find the shortest paths in a directed weighted graph */
    private void FloydWarshall() {
      for (int x = 0; x < this.graph.numberOfVertices; x++)
        for (int y = 0; y < this.graph.numberOfVertices; y++)
          for (int z = 0; z < this.graph.numberOfVertices; z++)
            if (this.graph.distances[y][x] + this.graph.distances[x][z] < this.graph.distances[y][z])
            	this.graph.distances[y][z] = this.graph.distances[y][x] + this.graph.distances[x][z];
    }
    
    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
    	//We cannot find the time for a invalid or empty graph.
    	if(graph == null || graph.validGraph == false) {
    		return -1;
    	}
    	
       	//To implement this function, we need to know that:
    	//Time = Distance / Speed
    	//Furthermore, we need to use the maximum distance and the minimum speed in this formula.
    	//This will let us find the minimum time that a live TV broadcast should last to cover 
    	//the contestant's journeys regardless of the contestant's initial positions and the 
    	//intersection at which they finally meet.
    
    	//The speed of any of the contestants cannot be less than 50 and greater than 100.
    	int[] holdSpeeds = new int[3];
    	holdSpeeds[0] = sA;
    	holdSpeeds[1] = sB;
    	holdSpeeds[2] = sC;
    	
    	for(int i = 0; i < holdSpeeds.length; i++) {
    		if(holdSpeeds[i] < 50) {
    			return -1;
    		}
    		else if(holdSpeeds[i] > 100){
    			return -1;
    		}
    	}
    	
    	//Then, we need to find the minimum speed.
    	int minimumSpeedinMetersPerMinute = (sA < sB) ? sA : sB;
    	minimumSpeedinMetersPerMinute = (minimumSpeedinMetersPerMinute < sC) ? minimumSpeedinMetersPerMinute : sC;
    	
    	//Run Floyd-Warshall's algorithm on the graph's distances
        FloydWarshall();
        
    	//Then, we need to find the maximum distance between any two nodes.
        double maximumDistanceinKilometers = maximumDistanceInGraph();
        
        //The maximum distance between any two nodes cannot be less than or equal to 0.
        if (maximumDistanceinKilometers <= 0) {
        	return -1;
        }
        else {
        	double maximumDistanceinMeters = (maximumDistanceinKilometers * 1000);
        	double roundUp = (int) maximumDistanceinMeters / minimumSpeedinMetersPerMinute;
        	double timeRequired = (roundUp == (maximumDistanceinMeters / minimumSpeedinMetersPerMinute)) ? roundUp : roundUp+1;
        	return (int) timeRequired;
        }
    }
    
    
    /** @return double: maximum distance of two nodes in the graph */
    private double maximumDistanceInGraph() {
      //Declare the maximum distance as minus infinity at the start
      double maximumDistance = this.graph.minusInfinity;
      //Get the number of vertices from the graph (it must be valid at this point)
      int numberOfVertices = this.graph.numberOfVertices;
 
      //Iterate through the vertices
      for (int vertex1 = 0; vertex1 < numberOfVertices; vertex1++) {
        for (int vertex2 = 0; vertex2 < numberOfVertices; vertex2++) {
          //We don't care about the distance of a vertex to itself.
          if (vertex2 == vertex1) {
        	  continue; //Skip this iteration of the loop.
          }
          else {
              //Get the distance between the two vertices
              double distanceFromVertex1toVertex2 = this.graph.distances[vertex1][vertex2];
              
              //If there are 2 random locations in a city between which no path exists, we return -1
              //No path between nodes exist when the distance between them is +infinity.
              if (distanceFromVertex1toVertex2 == this.graph.plusInfinity) {
            	  return -1;
              }
              maximumDistance = (maximumDistance < distanceFromVertex1toVertex2) ? distanceFromVertex1toVertex2 : maximumDistance;
          }
        }
      }
      return maximumDistance;
    }
}