package demos.naturalnumbers.lists;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.lists.Permutation;
import naturalnumbers.lists.PermutationFixer;

public class PermutationDemo
{
	public static void main(String[] args) throws Exception
	{
		Permutation perm1 = new Permutation(5);

		IProblem p = new Conjunction(new IProblem[]
		{ new PermutationFixer(perm1, new long[]
				{ 1, 2, 0, 3, 4 }), });

		IProblemMessage s = p.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(perm1);
		}
		else
			System.out.println("No solution.");
	}
}
