import java.util.*;
abstract public class Search 
{
	public int MaxMoves;
	public Heuristic H;
	public PriorityQueue<State> Q;
	public HashSet<String> States;
	public int TotalMoves = 0;
	abstract State GoGoGadgetSearch(State InputState);//runs the search on my stuff and outputs a result
}
