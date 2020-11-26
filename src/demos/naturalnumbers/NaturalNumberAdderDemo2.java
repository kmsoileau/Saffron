package demos.naturalnumbers;

import java.util.ArrayList;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberAdder;
import naturalnumbers.NaturalNumberFixer;

public class NaturalNumberAdderDemo2
{
	public static void main(String[] args) throws Exception
	{
		/**
		 * Set Java variables:
		 */

		/**
		 * Set globals:
		 */

		NaturalNumber.setLargestNaturalNumber(9999);

		/**
		 * Create Saffron objects and arrays:
		 */

		
		ArrayList<INaturalNumber> addend=new ArrayList<INaturalNumber>();
		INaturalNumber Z=new NaturalNumber("Z");
		INaturalNumber a1=new NaturalNumber("a1");
		INaturalNumber a2=new NaturalNumber("a2");
		INaturalNumber a3=new NaturalNumber("a3");
		addend.add(a1);
		addend.add(a2);
		addend.add(a3);
		
		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */
		
		IProblem p1=new NaturalNumberFixer(a1, 35);
		IProblem p2=new NaturalNumberFixer(a2, 1);
		IProblem p3=new NaturalNumberFixer(a3, 571);
		IProblem px=new NaturalNumberAdder(addend, Z);
		
		
		
		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */
		
		IProblem prob=new Conjunction(p1,p2,p3,px);
		
		//System.out.println(prob);
		
		/**
		 * Solve the IProblem:
		 */
		
		IProblemMessage s = prob.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println("a1 = " + a1);
			System.out.println("a2 = " + a2);
			System.out.println("a3 = " + a3);
			System.out.println("Z = " + Z);
		}
		else
			System.out.println("No solution.");

	}

}
