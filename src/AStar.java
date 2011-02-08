import java.util.HashSet;
import java.util.PriorityQueue;


public class AStar extends Search
{
	public AStar(Heuristic h, int maxmoves)
	{
		this.H=h;
		this.MaxMoves = maxmoves;
		this.States = new HashSet<String>();
	}
	public State GoGoGadgetSearch(State InputState) 
	{
		this.Q = new PriorityQueue<State>();
		this.Q.add(InputState);
		this.States.add(new String(InputState.State));
		State temp = InputState;
		boolean First = true;
		while(!temp.IsSolution())
		{
			TotalMoves++;
			System.out.println("TotalMoves = "+this.TotalMoves);
			temp = Q.poll();
			char[]Moves = GetPossibleMoves(temp);
			
				for (int i = 0; i<Moves.length; i++)
				{
					State temp2 = temp.copy();
					temp2.NextMove = Moves[i];
					if(TotalMoves>this.MaxMoves) 
					{
						System.out.println("No solution found.");
						System.exit(0);
					}
					System.out.print(new String(temp2.State) + "  "+temp2.NextMove+"  ");
					temp2.Move();
					temp2.HeuristicValue = this.H.RateMyState(temp2)+temp2.SearchSteps;
					System.out.println(new String(temp2.State) + "   "+temp2.SearchSteps+"   " +(temp2.HeuristicValue)+"\n");
					if(!this.States.contains(new String(temp2.State)))
					{
					//	System.out.println(this.States.contains(new String(temp2.State)));
						this.States.add(new String(temp2.State));
					//	System.out.println(this.States.contains(new String(temp2.State)));
						Q.add(temp2);
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
