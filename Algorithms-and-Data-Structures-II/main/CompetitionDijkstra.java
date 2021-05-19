import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

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
 * This class implements the competition using Dijkstra's algorithm
 * 
 * @author Prathamesh Sai
 */

public class CompetitionDijkstra {
	
	  protected DirectedGraph graph;
	  protected int sA;
	  protected int sB;
	  protected int sC;	  
	  
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */
    CompetitionDijkstra (String filename, int sA, int sB, int sC){
	    
    	//Set the integers representing the average walking speed of each of the contestants.
    	this.sA = sA;
	    this.sB = sB;
	    this.sC = sC;
	    
	    //Use the graph class to populate a graph in which to hold the city road network,
    	try {
    	      this.graph = new DirectedGraph(filename);
    	    } 
        catch (IOException e) {
    	      this.graph = null;
    	    }
    }
    
    /** @return double[]: The Dijkstra single source algorithm's output of an array of distances from a source node to every other node*/
    private double[] Dijkstra(int sourceNode) {
    
      //Get the adjacency list from the graph and store it.
      Map<Integer, List<Edge>> adjacencyList = DirectedGraph.adjacencyListForGraph;
      
      
      //Store the distances to nodes in this array, which will be the output
      double[] distTo = new double[graph.numberOfVertices];
      
      Queue<Integer> PQforDijkstra = new PriorityQueue<>(Comparator.comparing(node -> distTo[node]));
      
      //Dijkstra's Algorithm sets all distances to +infinity at the start
      //However, the distance of a vertex to itself is 0!
      for(int i = 0; i < distTo.length; i++) {
    	  if(i == sourceNode) {
    		  distTo[i] = 0;
    	  }
    	  else {
    		  //Start off by setting all distances to +infinity to indicate no paths between them.
    		  distTo[i] = this.graph.plusInfinity;
    	  }
      }
      
      //Add source node to the priority queue.
      PQforDijkstra.add(sourceNode);
      
      //Make an array of booleans to let us know which nodes we have been to
      boolean[] beenToNodeAlready = new boolean[graph.numberOfVertices];
      
      while (PQforDijkstra.isEmpty() == false) {
    	  
    	//remove node from PQ
        int nodefromPQ = PQforDijkstra.remove();
          
        List<Edge> whatToLoopThrough = (adjacencyList.get(nodefromPQ) == null) ? new ArrayList<Edge>() : adjacencyList.get(nodefromPQ);
        
        //Loop through the list of edges. 
        for (Edge Edge : whatToLoopThrough) {
        	
          //Get the destination of the edge
          int connectingNode = Edge.destination;
          
          //If we haven't met this vertex before
          if (beenToNodeAlready[connectingNode] == false) {
        	//Add the current distance to the node and the weight of the edge.
            double changedDist = distTo[nodefromPQ] + Edge.weight;
            
            //If the new distance we encountered is less than the distance to the vertex we know already
            //We update the old distance to the vertex.
            if (changedDist < distTo[connectingNode]) {
            	distTo[connectingNode] = changedDist;
            }
            
            //We remove the vertex from the priority queue and add it again to regain ordering.
            PQforDijkstra.remove(connectingNode);
            PQforDijkstra.add(connectingNode);
          }
        }
        
        //Set that vertex/node as seen
        beenToNodeAlready[nodefromPQ] = true;
      }
      return distTo;
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
      int numberOfVertices = graph.numberOfVertices;
            
      //Iterate through the vertices
      for (int vertex1 = 0; vertex1 < numberOfVertices; vertex1++) {
    	//Find the shortest path from vertex1 to every other vertex, and store it in an array.
        double[] shortestPaths = Dijkstra(vertex1);
        for (int vertex2 = 0; vertex2 < numberOfVertices; vertex2++) {
          //We don't need to find the distance of a vertex to itself.
          if (vertex2 == vertex1) { 
        	  continue; //Skip this iteration of the loop.
          }
          else {
        	  //Get the distance between the two vertices
              double distanceFromVertex1toVertex2 = shortestPaths[vertex2];
              
              //If there are 2 random locations in a city between which no path exists, we return -1
              //No path between nodes exist when the distance between them is +infinity.
              if (distanceFromVertex1toVertex2 == this.graph.plusInfinity) {
            	  return -1;
              }
              else {
            	//If this distance from vertex1 to vertex2 is greater than the current maximum distance, we update it.
            	  maximumDistance = (maximumDistance < distanceFromVertex1toVertex2) ? distanceFromVertex1toVertex2 : maximumDistance;
              }
          }
        }
      }
      
      //At this point, we found the maximum distance and we can return it.
      return maximumDistance;
    }   
}