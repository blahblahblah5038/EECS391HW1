import java.util.HashSet;


public class EightPuzzle {
	public static void main(String[] args) 
	{
		//I am going to assume that the arguments that you give me are correct
		
		//The three strings that define the state
		String Str1 = "724";//args[0];
		String Str2 = "5b6";//args[1];
		String Str3 = "831";//args[2];
		State InitialState = new State(Str1, Str2, Str3);
		
		//the integer represtenting the search method
		int SearchMethod = 2;//new Integer(args[3]);
		
		//the integer representing the search depth
		//TODO fix this
		int NumSteps = 10000;//new Integer(args[4]);
		
		State Solution = null;

		if (SearchMethod == 0)
		{	
			Heuristic H = new ManhattanDistance();
			Search A = new AStar(H,NumSteps);
			Solution = A.GoGoGadgetSearch(InitialState);
		}
		else if (SearchMethod == 1)
		{
			Heuristic H = new EuclideanDistance();
			Search A = new AStar(H,NumSteps);
			Solution = A.GoGoGadgetSearch(InitialState);
		}
		else if (SearchMethod==2)
		{
			Heuristic H = new ManhattanDistance();
			Search S = new BeamSearch(H, NumSteps);
			Solution = S.GoGoGadgetSearch(InitialState);
		}
		
		Solution.Print();
		
	}
}
