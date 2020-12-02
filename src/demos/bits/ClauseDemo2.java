package demos.bits;

import bits.BooleanVariable;
import bits.Clause;
import bits.IBooleanVariable;
import bits.IClause;

public class ClauseDemo2
{
	public static void main(String[] args) throws Exception
	{
		IClause[] clause = new IClause[2];
		IBooleanVariable[] bva = new IBooleanVariable[4];

		clause[0] = new Clause();
		clause[1] = new Clause();

		bva[0] = BooleanVariable.getBooleanVariable("A");
		bva[1] = BooleanVariable.getBooleanVariable("B");
		bva[2] = BooleanVariable.getBooleanVariable("C");

		clause[0].or(bva[0]);
		clause[0].or(bva[1]);
		clause[0].or(bva[2]);

		clause[1].or(bva[0]);
		clause[1].orNot(bva[1]);
		clause[1].or(bva[2]);

		System.out.println(clause[0]);
		System.out.println(clause[1]);
	}
}