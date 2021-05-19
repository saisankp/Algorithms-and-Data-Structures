// @author Prathamesh Sai
public class Edge {
	
	//An edge has three properties:
    protected int source;		//This is the starting node.
    protected int destination;	//This is the final node.
    protected double weight;	//This is the cost/effort to traverse through this edge.

    protected Edge(int source, int destination, double weight) {
      //Set all the values properly.
      this.source = source;
      this.destination = destination;
      this.weight = weight;
    }
  }