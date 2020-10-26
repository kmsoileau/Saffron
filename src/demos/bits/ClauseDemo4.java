package demos.bits;

import java.util.Map;

import bits.BooleanVariable;
import bits.Clause;
import bits.IBooleanVariable;
import bits.IClause;

public class ClauseDemo4
{
	public static void main(String[] args) throws Exception
	{
		IClause[] clause = new IClause[3];
		IBooleanVariable[] bva = new IBooleanVariable[4];

		clause[0] = new Clause();
		clause[1] = new Clause();
		clause[2] = new Clause();

		bva[0] = BooleanVariable.getBooleanVariable("foo30");
		bva[1] = BooleanVariable.getBooleanVariable("foo11");
		bva[2] = BooleanVariable.getBooleanVariable("foo23");
		bva[3] = BooleanVariable.getBooleanVariable("foo83");

		clause[0].orNot(bva[0]);
		clause[0].or(bva[1]);
		clause[0].or(bva[2]);

		clause[1].or(bva[0]);
		clause[1].or(bva[3]);
		clause[1].orNot(bva[2]);

		clause[2].or(bva[1]);
		clause[2].or(bva[3]);
		clause[2].or(bva[2]);

		Map<IBooleanVariable, Integer> t1 = ((Clause) clause[0]).toMap();
		System.out.println(clause[0]);
		System.out.println(t1);

		Map<IBooleanVariable, Integer> t2 = ((Clause) clause[1]).toMap();
		System.out.println(clause[1]);
		System.out.println(t2);

		Map<IBooleanVariable, Integer> t3 = ((Clause) clause[2]).toMap();
		System.out.println(clause[2]);
		System.out.println(t3);

	}
}