import java.util.Arrays;
import java.io.*;

public class State implements Comparable<State>
{
	//rows and columns are numbered 0 to 2, so 2 is a 3 by 3 array
	public static final int MaxRow = 2;
	public static final int MaxColumn = 2;
	public static final char [] Goal = "b12345678".toCharArray();
	public char[] State; //I want access to this as an array
	public String Moves; //this is nice because I can just add to it, it should hold the moves leading up to the state
	public char NextMove;
	public int HeuristicValue;
	public int SearchSteps = 0;
	public boolean IsSolution()
	{
		if(Arrays.equals(Goal,State)) return true;
		else return false;
	}
	public State(String S1, String S2, String S3)
	{
		Moves = "";
		SearchSteps=0;
		State = (S1+S2+S3).toCharArray();
	}
	//this should indicate whether or not a move can be made in a given direction.
	public boolean CanMove(char Move)
	{
		if (Move == 'u')
		{
			if (GetRow() == 0) return false;
			else return true;
		}
		else if (Move == 'd')
		{
			if (GetRow() == MaxRow) return false;
			else return true;
		}
		else if (Move == 'l')
		{
			if (GetColumn() == 0) return false;
			else return true;
		}
		else if (Move == 'r')
		{
			if(GetColumn() == MaxColumn) return false;
			else return true;
		}
		System.out.println("Invalid move of "+Move+".");
		return false; //if we have something other than the possible moves, then I error handle by printing a message and not making the move.
	}
	private int GetColumn() //this should return the column of the blank spot
	{
		for(int i = 0; i<State.length; i++)
		{
			if (State[i]=='b') return i%3;
		}
		System.out.println("No b found in array");
		return -1;
	}
	private int GetRow() //this should return the row of the blank spot
	{
		for(int i = 0; i<State.length; i++)
		{
			if (State[i]=='b') return i/3;
		}
		System.out.println("No b found in array");
		return -1;
	}
	public void Move(char Move, int Steps)
	{
		//this section makes the moves.
		//it only works if the move is valid, so we should run CanMove on the move before trying the move
		Moves += Move; //add the move to the list of moves.
		this.SearchSteps++;
		if (Move == 'u')
		{
			int y = GetIndex();
			Swap(y, y-3);
		}
		else if (Move == 'd')
		{
			int y = GetIndex();
			Swap(y, y+3);
		}
		else if (Move == 'l')
		{
			int x = GetIndex();
			Swap(x, x-1);
		}
		else if (Move == 'r')
		{
			int x = GetIndex();
			Swap(x,x+1);
		}
	}
	
	private int GetIndex() 
	{
		for(int i = 0; i<State.length; i++)
		{
			if(State[i]=='b') return i;
		}
		System.out.println("no b found!!!");
		return -1;
	}
	//Swaps two tiles at i and j
	private void Swap(int i, int j) 
	{
		char temp = State[i];
		State[i]=State[j];
		State[j]=temp;
	}
	public void Print()
	{
		System.out.print("Solution:");
		char[] MoveArray = Moves.toCharArray();
		for (int i = 0; i<MoveArray.length; i++)
		{
			if(MoveArray[i]=='u')System.out.print("Up ");
			else if(MoveArray[i]=='d')System.out.print("Down ");
			else if(MoveArray[i]=='l')System.out.print("Left ");
			else if(MoveArray[i]=='r')System.out.print("Right ");
		}
		System.out.println("\nSolution Steps:"+Moves.length());
		System.out.println("SearchSteps:"+SearchSteps );
	}
	public void Save(boolean Success, int TotalSteps)//write result to a csv file
	{
		try 
		{
			FileWriter F = new FileWriter ("results.csv");
			F.append(Success+","+this.SearchSteps+","+TotalSteps);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	public State copy() 
	{
		State S = new State(String.valueOf(this.State),"","");
		S.Moves = this.Moves;
		S.NextMove = this.NextMove;
		S.SearchSteps = this.SearchSteps;
		S.HeuristicValue = this.HeuristicValue;
		return S;
	}
	public int compareTo(State S) 
	{
		if(S.HeuristicValue>HeuristicValue)
		{
			return -1;
		}
		else if (S.HeuristicValue==this.HeuristicValue)
		{
			return 0;
		}
		else return 1;
	}
	public void Move() 
	{
		this.Move(this.NextMove, this.SearchSteps);
	}
}
