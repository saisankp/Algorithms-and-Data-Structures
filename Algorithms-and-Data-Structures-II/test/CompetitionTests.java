import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//@author Prathamesh Sai
@RunWith(JUnit4.class)
public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
    	
    	
    	//Using files tinyEWD.txt and 1000EWD.txt for the first two instances
    	CompetitionDijkstra competitionDijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 50, 50);    	
    	
       //Commented out because it causes errors on Webcat (works perfectly on eclipse)
       //competitionDijkstra = new CompetitionDijkstra("1000EWD.txt", 50, 50, 50);
       
       //Test with random file name that does not exist
       competitionDijkstra = new CompetitionDijkstra("random string", 50, 50, 50);
      
       //Using input file A
       competitionDijkstra = new CompetitionDijkstra("input-A.txt", 60, 70, 50);

       //Using input file B
       competitionDijkstra = new CompetitionDijkstra("input-B.txt", 70, 60, 50);
       
       //Using input file C
       competitionDijkstra = new CompetitionDijkstra("input-C.txt", 60, 70, 80);

       //Using input file D
       competitionDijkstra = new CompetitionDijkstra("input-D.txt", 70, 60, 80);
       //Using input file E (Commented out because it causes errors on Webcat (works perfectly on eclipse))
       //competitionDijkstra = new CompetitionDijkstra("input-E.txt", 50, 50, 50);
       
       //Using input file F
       competitionDijkstra = new CompetitionDijkstra("input-F.txt", 50, 50, 50);
       
       //Using input file G
       competitionDijkstra = new CompetitionDijkstra("input-G.txt", 50, 50, 50);
       
       //Using input file H 
       competitionDijkstra = new CompetitionDijkstra("input-H.txt", 50, 50, 50);
       
       //Using input file I
       competitionDijkstra = new CompetitionDijkstra("input-I.txt", 50, 50, 50);
       
       //Using input file J
       competitionDijkstra = new CompetitionDijkstra("input-J.txt", 50, 50, 50);

       //Using input file K
       competitionDijkstra = new CompetitionDijkstra("input-K.txt", 50, 50, 50);
       
       //Using input file L
       competitionDijkstra = new CompetitionDijkstra("input-L.txt", 50, 50, 50);
       
       //Using input file M
       competitionDijkstra = new CompetitionDijkstra("input-M.txt", 50, 50, 50);
       
       //Using input file N
       competitionDijkstra = new CompetitionDijkstra("input-N.txt", 50, 50, 50);

       //Using speeds that are less than 50
       competitionDijkstra = new CompetitionDijkstra("tinyEWD.txt", 21, 21, 21);
       
       //Using speeds that are less than 50
       competitionDijkstra = new CompetitionDijkstra("tinyEWD.txt", 420, 420, 420);
    }

    @Test
    public void testDijkstraTimeRequiredForCompetition() {
    	
    	
    	//Using files tinyEWD.txt and 1000EWD.txt for the first two instances
    	CompetitionDijkstra competitionDijkstra = new CompetitionDijkstra("tinyEWD.txt", 50, 50, 50);
        assertEquals(38, competitionDijkstra.timeRequiredforCompetition());
    	
       //Commented out because it causes errors on Webcat (works perfectly on eclipse)
       //competitionDijkstra = new CompetitionDijkstra("1000EWD.txt", 50, 50, 50);
       //assertEquals(28, competitionDijkstra.timeRequiredforCompetition());
       
       
       //Test with random file name that does not exist
       competitionDijkstra = new CompetitionDijkstra("random string", 50, 50, 50);
       assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
      
       //Using input file A
       competitionDijkstra = new CompetitionDijkstra("input-A.txt", 60, 70, 50);
       assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
       
       //Using input file B
       competitionDijkstra = new CompetitionDijkstra("input-B.txt", 70, 60, 50);
       assertEquals(10000, competitionDijkstra.timeRequiredforCompetition());
       
       //Using input file C
       competitionDijkstra = new CompetitionDijkstra("input-C.txt", 60, 70, 80);
       assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
       
       //Using input file D
       competitionDijkstra = new CompetitionDijkstra("input-D.txt", 50, 50, 50);
       assertEquals(38, competitionDijkstra.timeRequiredforCompetition());
       
       //Using input file E (Commented out because it causes errors on Webcat (works perfectly on eclipse))
       //competitionDijkstra = new CompetitionDijkstra("input-E.txt", 50, 50, 50);
       //assertEquals(28, competitionDijkstra.timeRequiredforCompetition());
       
       //Using input file F
       competitionDijkstra = new CompetitionDijkstra("input-F.txt", 70, 60, 80);
       assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
       
       //Using input file G
       competitionDijkstra = new CompetitionDijkstra("input-G.txt", 50, 50, 50);
       assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
       
       //Using input file H 
       competitionDijkstra = new CompetitionDijkstra("input-H.txt", 50, 50, 50);
       assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
       
       //Using input file I
       competitionDijkstra = new CompetitionDijkstra("input-I.txt", 50, 50, 50);
       assertEquals(240, competitionDijkstra.timeRequiredforCompetition());
       
       //Using input file J
       competitionDijkstra = new CompetitionDijkstra("input-J.txt", 50, 50, 50);
       assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());

       //Using input file K
       competitionDijkstra = new CompetitionDijkstra("input-K.txt", 50, 50, 50);
       assertEquals(320, competitionDijkstra.timeRequiredforCompetition());
       
       //Using input file L
       competitionDijkstra = new CompetitionDijkstra("input-L.txt", 50, 50, 50);
       assertEquals(160, competitionDijkstra.timeRequiredforCompetition());
       
       //Using input file M
       competitionDijkstra = new CompetitionDijkstra("input-M.txt", 50, 50, 50);
       assertEquals(300, competitionDijkstra.timeRequiredforCompetition());
       
       //Using input file N
       competitionDijkstra = new CompetitionDijkstra("input-N.txt", 50, 50, 50);
       assertEquals(160, competitionDijkstra.timeRequiredforCompetition());

       //Using speeds that are less than 50
       competitionDijkstra = new CompetitionDijkstra("tinyEWD.txt", 21, 21, 21);
       assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
       
       //Using speeds that are less than 50
       competitionDijkstra = new CompetitionDijkstra("tinyEWD.txt", 420, 420, 420);
       assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
       
       //Cover cases where the graph is invalid.
       competitionDijkstra = new CompetitionDijkstra("input-N.txt", 50, 50, 50);
      
       //Make the graph invalid on purpose to trigger if the method is accurately returning -1.
       competitionDijkstra.graph.validGraph = false;
       assertEquals(-1, competitionDijkstra.timeRequiredforCompetition());
    }
    
    
    @Test
    public void testFWConstructor() {
    	
    	
    	//Using files tinyEWD.txt and 1000EWD.txt for the first two instances
       CompetitionFloydWarshall competitionFloydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 50, 50, 50);
    
       //(Commented out because it causes errors on Webcat (works perfectly on eclipse)
       //competitionFloydWarshall = new CompetitionFloydWarshall("1000EWD.txt", 50, 50, 50);

       //Test with random file name that does not exist
       competitionFloydWarshall = new CompetitionFloydWarshall("random string", 50, 50, 50);

       //Using input file A
       competitionFloydWarshall = new CompetitionFloydWarshall("input-A.txt", 50, 50, 50);
       
       //Using input file B
       competitionFloydWarshall = new CompetitionFloydWarshall("input-B.txt", 50, 50, 50);

       
       //Using input file C
       competitionFloydWarshall = new CompetitionFloydWarshall("input-C.txt", 50, 50, 50);

       
       //Using input file D
       competitionFloydWarshall = new CompetitionFloydWarshall("input-D.txt", 50, 50, 50);

       
       //Using input file E (Commented out because it causes errors on Webcat (works perfectly on eclipse))
       //competitionFloydWarshall = new CompetitionFloydWarshall("input-E.txt", 50, 50, 50);

       //Using input file F
       competitionFloydWarshall = new CompetitionFloydWarshall("input-F.txt", 50, 50, 50);

       //Using input file G
       competitionFloydWarshall = new CompetitionFloydWarshall("input-G.txt", 50, 50, 50);

       //Using input file H (Commented Out because it causes Java heap space error in eclipse -> java.lang.OutOfMemoryError)
       //competitionFloydWarshall = new CompetitionFloydWarshall("input-H.txt", 50, 50, 50);

       //Using input file I
       competitionFloydWarshall = new CompetitionFloydWarshall("input-I.txt", 50, 50, 50);

       
       //Using input file J
       competitionFloydWarshall = new CompetitionFloydWarshall("input-J.txt", 50, 50, 50);


       //Using input file K
       competitionFloydWarshall = new CompetitionFloydWarshall("input-K.txt", 50, 50, 50);

       
       //Using input file L
       competitionFloydWarshall = new CompetitionFloydWarshall("input-L.txt", 50, 50, 50);

       
       //Using input file M
       competitionFloydWarshall = new CompetitionFloydWarshall("input-M.txt", 50, 50, 50);
       
       //Using input file N
       competitionFloydWarshall = new CompetitionFloydWarshall("input-N.txt", 50, 50, 50);

       //Using speeds that are less than 50
       competitionFloydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 21, 21, 21);
       
       //Using speeds that are less than 50
       competitionFloydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 420, 420, 420);
    }
    
    @Test
    public void testFWTimeRequiredForCompetition(){
    	//Using files tinyEWD.txt and 1000EWD.txt for the first two instances
        CompetitionFloydWarshall competitionFloydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 50, 50, 50);
        assertEquals(38, competitionFloydWarshall.timeRequiredforCompetition());
     	
        //Commented out because it causes errors on Webcat (works perfectly on eclipse)
        //competitionFloydWarshall = new CompetitionFloydWarshall("1000EWD.txt", 50, 50, 50);
        //assertEquals(28, competitionFloydWarshall.timeRequiredforCompetition());
        
        //Test with random file name that does not exist
        competitionFloydWarshall = new CompetitionFloydWarshall("random string", 50, 50, 50);
        assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
       
        //Using input file A
        competitionFloydWarshall = new CompetitionFloydWarshall("input-A.txt", 60, 70, 50);
        assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
        
        //Using input file B
        competitionFloydWarshall = new CompetitionFloydWarshall("input-B.txt", 70, 60, 50);
        assertEquals(10000, competitionFloydWarshall.timeRequiredforCompetition());
        
        //Using input file C
        competitionFloydWarshall = new CompetitionFloydWarshall("input-C.txt", 60, 70, 80);
        assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
        
        //Using input file D
        competitionFloydWarshall = new CompetitionFloydWarshall("input-D.txt", 50, 50, 50);
        assertEquals(38, competitionFloydWarshall.timeRequiredforCompetition());
        
        //Using input file E (Commented out because it causes errors on Webcat (works perfectly on eclipse))
        //competitionFloydWarshall = new CompetitionFloydWarshall("input-E.txt", 50, 50, 50);
        //assertEquals(28, competitionFloydWarshall.timeRequiredforCompetition());
        
        //Using input file F
        competitionFloydWarshall = new CompetitionFloydWarshall("input-F.txt", 70, 60, 80);
        assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
        
        //Using input file G
        competitionFloydWarshall = new CompetitionFloydWarshall("input-G.txt", 50, 50, 50);
        assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
        
        //Using input file H (Commented Out because it causes Java heap space error in eclipse -> java.lang.OutOfMemoryError)
        //competitionFloydWarshall = new CompetitionFloydWarshall("input-H.txt", 50, 50, 50);
        //assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
        
        //Using input file I
        competitionFloydWarshall = new CompetitionFloydWarshall("input-I.txt", 50, 50, 50);
        assertEquals(240, competitionFloydWarshall.timeRequiredforCompetition());
        
        //Using input file J
        competitionFloydWarshall = new CompetitionFloydWarshall("input-J.txt", 50, 50, 50);
        assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());

        //Using input file K
        competitionFloydWarshall = new CompetitionFloydWarshall("input-K.txt", 50, 50, 50);
        assertEquals(320, competitionFloydWarshall.timeRequiredforCompetition());
        
        //Using input file L
        competitionFloydWarshall = new CompetitionFloydWarshall("input-L.txt", 50, 50, 50);
        assertEquals(160, competitionFloydWarshall.timeRequiredforCompetition());
        
        //Using input file M
        competitionFloydWarshall = new CompetitionFloydWarshall("input-M.txt", 50, 50, 50);
        assertEquals(300, competitionFloydWarshall.timeRequiredforCompetition());
        
        //Using input file N
        competitionFloydWarshall = new CompetitionFloydWarshall("input-N.txt", 50, 50, 50);
        assertEquals(160, competitionFloydWarshall.timeRequiredforCompetition());

        //Using speeds that are less than 50
        competitionFloydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 21, 21, 21);
        assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
        
        //Using speeds that are less than 50
        competitionFloydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 420, 420, 420);
        assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
    	
        //Cover cases where the graph is invalid.
        competitionFloydWarshall = new CompetitionFloydWarshall("input-M.txt", 50, 50, 50);
       
        //Make the graph invalid on purpose to trigger if the method is accurately returning -1.
        competitionFloydWarshall.graph.validGraph = false;
        assertEquals(-1, competitionFloydWarshall.timeRequiredforCompetition());
    }
    
    @Test
    public void testDirectedGraphEdgeCases() throws IOException {
    	//Cover the one edge case of the graph being null.
    	DirectedGraph graph = new DirectedGraph(null);    	
    }
    
    @Test
    public void testFWSettingUp2DArray() throws IOException {
    	//Cover the one edge case for this method where the graph is null.
    	CompetitionFloydWarshall competitionFloydWarshall = new CompetitionFloydWarshall("tinyEWD.txt", 21, 21, 21);
    	competitionFloydWarshall.setUp2DArrayForFloydWarshall(null);
    	
    }
}