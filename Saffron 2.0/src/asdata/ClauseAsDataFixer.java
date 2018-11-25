package asdata;

import java.util.ArrayList;
import java.util.List;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Clause;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import bits.Problem;

public class ClauseAsDataFixer extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable A=BooleanVariable.getBooleanVariable("A");
		IBooleanVariable B=BooleanVariable.getBooleanVariable("B");
		IBooleanVariable C=BooleanVariable.getBooleanVariable("C");
		IBooleanVariable D=BooleanVariable.getBooleanVariable("D");
		IBooleanVariable E=BooleanVariable.getBooleanVariable("E");
		IBooleanVariable F=BooleanVariable.getBooleanVariable("F");
		
		ArrayList<IBooleanVariable> ary=new ArrayList<IBooleanVariable>();
		ary.add(B);
		ary.add(A);
		ary.add(F);
		ary.add(E);
		ary.add(C);
		ary.add(D);
		ClauseAsData.declare(ary);
		
		IClauseAsData c1=new ClauseAsData();
		IClause clause1=Clause.newClause().nor(A).or(B);
		IClauseAsData c2=new ClauseAsData();
		IClause clause2=Clause.randomClause(ClauseAsData.getVARIABLES()
				.toArray(new IBooleanVariable[0]));
		
		IProblem prob1=new ClauseAsDataFixer(c1,clause1);
		IProblem prob2=new ClauseAsDataFixer(c2,clause2);
		IProblem prob=new Conjunction(prob1,prob2);
		System.out.println(prob);
				
		List<IBooleanLiteral> s=(List<IBooleanLiteral>) prob.findModel(Problem.defaultSolver());
		System.out.println("Reporting...");
		if(s!=null && s.size()>0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(clause1);
			System.out.println(c1);
			System.out.println(clause2);
			System.out.println(c2);
		}
		else
			System.out.println("No solution.");
		
		System.out.println(">\t"+c1.toClause());
		System.out.println(">\t"+c2.toClause());
	}
	
	public ClauseAsDataFixer(IClauseAsData c, IClause clause) throws Exception
	{
		IProblem prob=null;
		
		label: 
		for(IBooleanVariable bv : ClauseAsData.getVARIABLES())
		{
			IBooleanVariable occurs = c.occurrenceIndicator(bv);
			IBooleanVariable barred = c.barredIndicator(bv);
			
			for(int lit=0;lit<clause.size();lit++)
			{
				IBooleanLiteral v = clause.getLiteralAt(lit);
				if(v.getBooleanVariable().equals(bv))
				{
					prob = new Conjunction(prob,
							new BitFixer(occurs,true),
							new BitFixer(barred,v.isBarred()));
					continue label;
				}
			}
			prob = new Conjunction(prob,
					new BitFixer(occurs,false),
					new BitFixer(barred,false));
		}
			
		this.setClauses(prob.getClauses());
	}
}
