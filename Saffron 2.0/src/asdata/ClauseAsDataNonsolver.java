package asdata;

import java.util.ArrayList;
import java.util.List;

import bits.BitEqualizer;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Clause;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import bits.Problem;

public class ClauseAsDataNonsolver extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable A=BooleanVariable.getBooleanVariable("A");
		IBooleanVariable B=BooleanVariable.getBooleanVariable("B");
		
		ArrayList<IBooleanVariable> ary=new ArrayList<IBooleanVariable>();
		ary.add(A);
		ary.add(B);
		ClauseAsData.declare(ary);
		
		IClause clause=Clause.newClause().nor(B);
		
		IClauseAsData dataclause=new ClauseAsData();
		
		IProblem prob=new Conjunction(new ClauseAsDataFixer(dataclause,clause),
				new ClauseAsDataNonsolver(dataclause));
		
		System.out.println(prob);
				
		List<IBooleanLiteral> s=(List<IBooleanLiteral>) prob.findModel(Problem.defaultSolver());
		System.out.println("Reporting...");
		if(s!=null && s.size()>0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(A);
			System.out.println(B);
		}
		else
			System.out.println("No solution.");
	}
	
	public ClauseAsDataNonsolver(IClauseAsData dataclause) throws Exception
	{
		IProblem prob=null;
		for(IBooleanVariable bv : ClauseAsData.getVARIABLES())
			prob=new Conjunction(prob,new Disjunction(
					new BitFixer(dataclause.occurrenceIndicator(bv),false),
					new BitEqualizer(bv,dataclause.barredIndicator(bv))));
		this.setClauses(prob.getClauses());
	}
}
