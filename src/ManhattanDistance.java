
public class ManhattanDistance implements Heuristic
{
	public  int RateMyState(State S) 
	{
		int Value = 0;
		for (int i = 0; i<S.State.length; i++)
		{
			Value+=Math.abs(GetColumn(S.State[i], S.State)-GetColumn(S.State[i],State.Goal));
			Value+=Math.abs(GetRow(S.State[i], S.State)-GetRow(S.State[i],State.Goal));
		}
		return Value;
	}
	private int GetColumn(char square, char[]State) //this should return the column of the blank spot
	{
		for(int i = 0; i<State.length; i++)
		{
			if (State[i]==square) return i%3;
		}
		return -1;
	}
	private int GetRow(char square, char[]State) //this should return the row of the blank spot
	{
		for(int i = 0; i<State.length; i++)
		{
			if (State[i]==square) return i/3;
		}
		return -1;
	}

}
