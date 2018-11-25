package asdata;

import java.util.ArrayList;

import bits.IProblem;
import bits.Problem;

public class ProblemAsData implements IProblemAsData
{
	private ArrayList<IClauseAsData> clauses;
	
	public ProblemAsData() 
	{
		this.clauses=new ArrayList<IClauseAsData>();
	}
	
	//If the Problem consists of a single Clause...
	public ProblemAsData(IClauseAsData clause) 
	{
		this.clauses=new ArrayList<IClauseAsData>();
		this.addClause(clause);
	}
	
	public ProblemAsData(IClauseAsData[] clauses) 
	{
		this.clauses=new ArrayList<IClauseAsData>();
		for(IClauseAsData clause : clauses)
			this.addClause(clause);
	}
	
	public boolean addClause(IClauseAsData c)
	{
		return this.clauses.add(c);
	}
	
	public ArrayList<IClauseAsData> getClauses() 
	{
		return clauses;
	}
	
	public IClauseAsData[] getClausesArray() 
	{
		return this.getClauses().toArray(new IClauseAsData[0]);
	}
	
	public boolean removeClause(IClauseAsData c)
	{
		return this.clauses.remove(c);
	}

	public IClauseAsData removeClause(int index)
	{
		return this.clauses.remove(index);
	}
	
	public IProblem toProblem() throws Exception
	{
		IProblem ret=Problem.newProblem();
		for(IClauseAsData cl : this.getClauses())
			ret.addClauseVoid(cl.toClause());
		return ret;
	}
}


