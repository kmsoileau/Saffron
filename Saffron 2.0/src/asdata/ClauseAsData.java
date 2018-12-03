package asdata;

import java.util.ArrayList;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Clause;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import exceptions.bits.BooleanLiteralException;

public class ClauseAsData implements IClauseAsData
{
	private static long cadCount;
	private static ArrayList<IBooleanVariable> VARIABLES;

	public static void declare(ArrayList<IBooleanVariable> variables) throws Exception
	{
		ClauseAsData.VARIABLES=variables;
	}

	public static int findIndex(IBooleanVariable bv) throws Exception
	{
		for(int i=0;i<ClauseAsData.VARIABLES.size();i++)
			if(ClauseAsData.VARIABLES.get(i).equals(bv))
				return i;
		return -1;
	}

	public static ArrayList<IBooleanVariable> getVARIABLES() 
	{
		return VARIABLES;
	}

	public static void main(String[] args) throws Exception
	{
		IBooleanVariable A=BooleanVariable.getBooleanVariable("A");
		IBooleanVariable B=BooleanVariable.getBooleanVariable("B");

		ArrayList<IBooleanVariable> ary = new ArrayList<IBooleanVariable>();
		ary.add(A);
		ary.add(B);
		ClauseAsData.declare(ary);

		IClauseAsData c1=new ClauseAsData();
		System.out.println("c1 = "+c1);

		IClauseAsData c2=new ClauseAsData("clause2");
		System.out.println(c2.getName()+" = "+c2);

		IClauseAsData c3=new ClauseAsData("clause2");
		c3.occurrenceIndicator(A).setValue(true);
		c3.barredIndicator(A).setValue(true);
		c3.occurrenceIndicator(B).setValue(true);
		System.out.println(c3.getName()+" = "+c3);
		IClause theClause=c3.toClause();
		System.out.println(c3.getName()+" = "+theClause);
		IProblem p = ((Clause)theClause).unsatisfiedClause();
		System.out.println(p);
	}

	public IBooleanVariable[] barred;
	private String name;
	/*
	 * The nth booleanvariable in occurrence indicates whether the nth variable appears in the clause.
	 * If so, the nth booleanvariable in barred indicates whether the nth variable is barred, else it 
	 * is ignored.
	 */
	public ArrayList<IBooleanVariable> occurrence;

	public ClauseAsData() throws Exception 
	{
		this("CAD-"+ ClauseAsData.cadCount++);
	}

	public ClauseAsData(String name) throws Exception 
	{
		if(ClauseAsData.VARIABLES==null)
			throw new UndeclaredVariablesException("ClauseAsData constructor was called before a call to ClauseAsData.declare method.");
		this.name=name;
		occurrence=new ArrayList<IBooleanVariable>();
		//occurrence.ensureCapacity(ClauseAsData.VARIABLES.size());
		for(int i=0;i<ClauseAsData.VARIABLES.size();i++)
			occurrence.add(i,BooleanVariable.getBooleanVariable(this.name
					+"_"+ClauseAsData.VARIABLES.get(i).getName()+"_occurrence_"+i));
		/*for(int i=0;i<occurrence.size();i++)
			occurrence.set(i,BooleanVariable.getBooleanVariable(this.name
					+"_"+ClauseAsData.VARIABLES.get(i).getName()+"_occurrence_"+i));*/
		barred=new IBooleanVariable[ClauseAsData.VARIABLES.size()];
		for(int i=0;i<barred.length;i++)
			barred[i]=BooleanVariable.getBooleanVariable(this.name+"_"
					+ClauseAsData.VARIABLES.get(i).getName()+"_barred_"+i);
	}

	public IBooleanVariable barredIndicator(IBooleanVariable bv) throws Exception
	{
		return this.barred[ClauseAsData.findIndex(bv)];
	}

	public String getName() 
	{
		return name;
	}
	
	public IBooleanVariable occurrenceIndicator(IBooleanVariable bv) throws Exception
	{
		return this.occurrence.get(ClauseAsData.findIndex(bv));
	}

	public void setName(String name) 
	{
		this.name=name;
	}

	public IClause toClause() throws Exception
	{
		IClause ret=Clause.newClause();
		for(IBooleanVariable bv : ClauseAsData.VARIABLES)
		{
			if(occurrenceIndicator(bv).getValue())
				if(barredIndicator(bv).getValue())
					ret.orNot(bv);
				else
					ret.or(bv);
		}
		return ret;
	}

	public String toString()
	{
		String res="{";
		for(int j=0;j<ClauseAsData.VARIABLES.size();j++)
		{
			if(!this.occurrence.get(j).getValue())
				continue;
			if(!this.barred[j].getValue())
			{
				IBooleanLiteral ib;
				try
				{
					ib = BooleanLiteral.getBooleanLiteral(
							ClauseAsData.VARIABLES.get(j), false);
					res+=ib;
				} 
				catch (BooleanLiteralException e)
				{
					e.printStackTrace();
				}

			}
			else
			{
				IBooleanLiteral ib;
				try
				{
					ib = BooleanLiteral.getBooleanLiteral(
							ClauseAsData.VARIABLES.get(j), true);
					res+=ib;
				} 
				catch (BooleanLiteralException e)
				{
					e.printStackTrace();
				}

			}
		}
		return res+"}";
}
}