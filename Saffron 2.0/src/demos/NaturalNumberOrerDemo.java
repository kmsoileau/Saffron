package demos;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberNoter;
import naturalnumbers.NaturalNumberOrer;

public class NaturalNumberOrerDemo
{
	public static void main(String[] args) throws Exception
	{
		INaturalNumber X = new NaturalNumber("X");
		INaturalNumber Y = new NaturalNumber("Y");
		INaturalNumber Z = new NaturalNumber("Z");

		NaturalNumberOrer bnnfx = new NaturalNumberOrer(X, Y, Z);
		NaturalNumberNoter NaturalNumberNoter1 = new NaturalNumberNoter(X, Y);

		IProblem p = new Conjunction(bnnfx, NaturalNumberNoter1);
		System.out.println(p);
		List<IBooleanLiteral> s = p.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X);
			System.out.println("Y= " + Y);
			System.out.println("Z= " + Z);
		}
		else
			System.out.println("No solution.");
	}
}