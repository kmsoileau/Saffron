package demos.bits;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.ConditionalDisjunction;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

/**
 * <pre>
 *  DisjunctionDemo1
 *  Copyright (c) 2005 Positronic Software
 * </pre>
 * 
 * @author Kerry Michael Soileau
 * @version 2.0
 */

public class DisjunctionDemo1
{
	public static void main(String[] args) throws Exception
	{
		IProblemMessage s;

		IBooleanVariable X = BooleanVariable.getBooleanVariable("X");
		IBooleanVariable Y = BooleanVariable.getBooleanVariable("Y");
		IBooleanVariable Z = BooleanVariable.getBooleanVariable("Z");

		INaturalNumber A = new NaturalNumber("A");
		INaturalNumber B = new NaturalNumber("B");
		INaturalNumber C = new NaturalNumber("C");
		INaturalNumber D = new NaturalNumber("D");

		IProblem p1 = new NaturalNumberFixer(A, 3);
		IProblem p2 = new NaturalNumberFixer(B, 1);
		IProblem p3 = new NaturalNumberFixer(C, 11);
		IProblem p4 = new NaturalNumberFixer(D, 5);

		IProblem problem;

		// IProblem-0
		problem = new Disjunction(p1);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");
		// IProblem-1
		problem = new Disjunction(p1, p2);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");
		// IProblem-2
		problem = new Disjunction(Y, p2, p3);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");
		// IProblem-3
		problem = new Disjunction(p1, p3, p4);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");
		// IProblem-4
		problem = new Disjunction(p1, p2, p3, p4);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");
		// IProblem-5
		problem = new Disjunction(new IProblem[]
		{ p1, p2, p3, p4 });
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");
		// IProblem-6
		problem = new Disjunction(new IBooleanVariable[]
		{ X, Y, Z }, new IProblem[]
		{ p1, p2, p3, p4 });
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");
		// IProblem-7
		problem = new Conjunction(new Disjunction(new IBooleanVariable[]
		{ X, Y, Z }, new IProblem[]
		{ p1, p2, p3, p4 }), new BitFixer(X, false), new BitFixer(Y, false),
				new BitFixer(Z, false));
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");
		// IProblem-8
		problem = new ConditionalDisjunction(new IProblem[]
		{ p2, p3, p4 }, new IBooleanVariable[]
		{ X, Y, Z });
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
			BooleanLiteral.reset(s.getLiterals());
		}
		else
			System.out.println("No solution.");
	}
}