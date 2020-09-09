package demos.bits;

import java.util.ArrayList;
import java.util.List;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Clause;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IClause;

public class ClauseDemo1
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

		System.out.println("clause[0].equals(clause[0]) is " + clause[0].equals(clause[0]));
		System.out.println("clause[0].equals(clause[1]) is " + clause[0].equals(clause[1]));
		System.out.println("clause[0].equals(clause[2]) is " + clause[0].equals(clause[2]));

		System.out.println("clause[1].equals(clause[0]) is " + clause[1].equals(clause[0]));
		System.out.println("clause[1].equals(clause[1]) is " + clause[1].equals(clause[1]));
		System.out.println("clause[1].equals(clause[2]) is " + clause[1].equals(clause[2]));

		System.out.println("clause[2].equals(clause[0]) is " + clause[2].equals(clause[0]));
		System.out.println("clause[2].equals(clause[1]) is " + clause[2].equals(clause[1]));
		System.out.println("clause[2].equals(clause[2]) is " + clause[2].equals(clause[2]));

		System.out.println(clause[0]);
		System.out.println(clause[1]);
		System.out.println(clause[2]);

		System.out.println("-----------------------------------");
		System.out.println(clause[0].resolve(bva[1], false));
		System.out.println(clause[1].resolve(bva[1], false));
		System.out.println(clause[2].resolve(bva[1], false));

		System.out.println("-----------------------------------");
		System.out.println(clause[0]);
		IBooleanVariable iv = BooleanVariable.getBooleanVariable("foo23");
		IBooleanLiteral il = BooleanLiteral.getBooleanLiteral(iv, true);
		System.out.println(il);
		IClause newc = clause[0].resolve(iv, false);
		System.out.println(newc);
		IClause newc2 = clause[0].resolve(il);
		System.out.println(newc2);

		Object[] res = clause[2].getBooleanVariables();
		for (int i = 0; i < res.length; i++)
			System.out.println(((IBooleanVariable) res[i]).getName());
		clause[2].or(bva[3]);
		for (int i = 0; i < res.length; i++)
			System.out.println(((IBooleanVariable) res[i]).getName());

		List<IBooleanVariable> reslist = new ArrayList<IBooleanVariable>();
		clause[2].getBooleanVariables(reslist);
		for (int i = 0; i < res.length; i++)
			System.out.println((reslist.get(i)).getName());
	}
}