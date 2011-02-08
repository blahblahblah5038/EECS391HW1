import java.util.HashSet;
import java.util.PriorityQueue;

//TODO change maxmoves

//I was able to just modify my code from A* search due to similarities between the two.
public class BeamSearch extends Search 
{
	public final int k = 10;
	public BeamSearch(Heuristic h, int maxmoves)
	{
		this.H=h;
		this.MaxMoves = maxmoves;
		this.States = new HashSet<String>();
	}
	public State GoGoGadgetSearch(State InputState) 
	{
		this.Q = new PriorityQueue<State>();
		InputState.HeuristicValue = this.H.RateMyState(InputState);
		this.Q.add(InputState);
		//not using the hashset for this
		//this.States.add(new String(InputState.State));
		State temp = InputState;
		boolean First = true;
		
		while(!temp.IsSolution())
		{
			temp = Q.peek();//peek at the best soln, maybe it is a winner, check that later
			PriorityQueue<State> newQ = new PriorityQueue<State>();
			this.TotalMoves++;
			for(int i = 0; i<k&&Q.size()>0; i++)
			{
				newQ.add(Q.poll());
			}
			Q.clear();
			
			for (int i = 0; i<k&&newQ.size()>0; i++)
			{
				//pop from newQ
				State temp3 = newQ.poll();
				
				//generate all possible states
				char[] moves = this.GetPossibleMoves(temp3);
				for(int j=0; j<moves.length; j++)
				{
					State temp2 = temp3.copy();
					temp2.NextMove = moves[j];
					temp2.SearchSteps++;
					if(this.TotalMoves>this.MaxMoves) 
					{
						System.out.println("No solution found.");
						System.exit(0);
					}
					System.out.print(new String(temp2.State) + "  "+temp2.NextMove+"  ");
					temp2.Move();
					temp2.HeuristicValue = this.H.RateMyState(temp2)+temp2.SearchSteps;
					System.out.println(temp2.State);
					Q.add(temp2);//put into Q
				}
				
			}
			
		}
		return temp;
	}
	private char[] GetPossibleMoves(State temp) 
	{
		String S = "";
		if(temp.CanMove('u'))
		{
			S+='u';
		}
		if(temp.CanMove('d'))
		{
			S+='d';
		}
		if(temp.CanMove('l'))
		{
			S+='l';
		}
		if(temp.CanMove('r'))
		{
			S+='r';
		}
		return S.toCharArray();
	}

}
