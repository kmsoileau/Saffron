package demos.naturalnumbers.rectangles;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import naturalnumbers.NaturalNumber;
import naturalnumbers.rectangles.INaturalNumberRectangle;
import naturalnumbers.rectangles.NaturalNumberRectangle;
import naturalnumbers.rectangles.NaturalNumberRectangleCongruenter;
import naturalnumbers.rectangles.NaturalNumberRectangleContainer;
import naturalnumbers.rectangles.NaturalNumberRectangleDisjointer;
import naturalnumbers.rectangles.NaturalNumberRectangleFixer;

public class NaturalNumberRectangleDisjointerDemo2
{
	private static boolean decide(INaturalNumberRectangle rect, int row, int col)
	{
		return rect.getAltitude().getLower().getValue() <= row
				&& row <= rect.getAltitude().getUpper().getValue()
				&& rect.getBase().getLower().getValue() <= col
				&& col <= rect.getBase().getUpper().getValue();
	}

	public static void main(String[] args) throws Exception
	{

		/**
		 * Set Java variables:
		 */

		/**
		 * Set globals:
		 */

		/**
		 * Create Saffron objects and arrays:
		 */

		INaturalNumberRectangle enclosure = new NaturalNumberRectangle("encl");

		INaturalNumberRectangle A = new NaturalNumberRectangle("A");
		INaturalNumberRectangle B = new NaturalNumberRectangle("B");
		INaturalNumberRectangle C = new NaturalNumberRectangle("C");
		INaturalNumberRectangle D = new NaturalNumberRectangle("D");
		INaturalNumberRectangle E = new NaturalNumberRectangle("E");
		INaturalNumberRectangle F = new NaturalNumberRectangle("F");
		INaturalNumberRectangle G = new NaturalNumberRectangle("G");
		INaturalNumberRectangle H = new NaturalNumberRectangle("H");

		INaturalNumber Ax = new NaturalNumber();
		INaturalNumber Ay = new NaturalNumber();
		INaturalNumber Bx = new NaturalNumber();
		INaturalNumber By = new NaturalNumber();
		INaturalNumber Cx = new NaturalNumber();
		INaturalNumber Cy = new NaturalNumber();
		INaturalNumber Dx = new NaturalNumber();
		INaturalNumber Dy = new NaturalNumber();
		INaturalNumber Ex = new NaturalNumber();
		INaturalNumber Ey = new NaturalNumber();
		INaturalNumber Fx = new NaturalNumber();
		INaturalNumber Fy = new NaturalNumber();
		INaturalNumber Gx = new NaturalNumber();
		INaturalNumber Gy = new NaturalNumber();
		INaturalNumber Hx = new NaturalNumber();
		INaturalNumber Hy = new NaturalNumber();

		INaturalNumberRectangle AA = new NaturalNumberRectangle("AA");
		INaturalNumberRectangle BB = new NaturalNumberRectangle("BB");
		INaturalNumberRectangle CC = new NaturalNumberRectangle("CC");
		INaturalNumberRectangle DD = new NaturalNumberRectangle("DD");
		INaturalNumberRectangle EE = new NaturalNumberRectangle("EE");
		INaturalNumberRectangle FF = new NaturalNumberRectangle("FF");
		INaturalNumberRectangle GG = new NaturalNumberRectangle("GG");
		INaturalNumberRectangle HH = new NaturalNumberRectangle("HH");

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem[] rectangleFixers = new IProblem[]
		{ new NaturalNumberRectangleFixer(A, 3, 3),
				new NaturalNumberRectangleFixer(B, 2, 4),
				new NaturalNumberRectangleFixer(C, 3, 3),
				new NaturalNumberRectangleFixer(D, 3, 4),
				new NaturalNumberRectangleFixer(E, 2, 2),
				new NaturalNumberRectangleFixer(F, 2, 2),
				new NaturalNumberRectangleFixer(G, 4, 2),
				new NaturalNumberRectangleFixer(H, 2, 2) };

		IProblem[] rectangleCongruenters = new IProblem[]
		{ new NaturalNumberRectangleCongruenter(AA, A, Ax, Ay),
				new NaturalNumberRectangleCongruenter(BB, B, Bx, By),
				new NaturalNumberRectangleCongruenter(CC, C, Cx, Cy),
				new NaturalNumberRectangleCongruenter(DD, D, Dx, Dy),
				new NaturalNumberRectangleCongruenter(EE, E, Ex, Ey),
				new NaturalNumberRectangleCongruenter(FF, F, Fx, Fy),
				new NaturalNumberRectangleCongruenter(GG, G, Gx, Gy),
				new NaturalNumberRectangleCongruenter(HH, H, Hx, Hy) };

		IProblem rectangleDisjointer = new NaturalNumberRectangleDisjointer(
				new INaturalNumberRectangle[]
				{ AA, BB, CC, DD, EE, FF, GG, HH });

		IProblem[] rectangleContainers = new IProblem[]
		{ new NaturalNumberRectangleFixer(enclosure, 0, 0, 8, 8),
				new NaturalNumberRectangleContainer(AA, enclosure),
				new NaturalNumberRectangleContainer(BB, enclosure),
				new NaturalNumberRectangleContainer(CC, enclosure),
				new NaturalNumberRectangleContainer(DD, enclosure),
				new NaturalNumberRectangleContainer(EE, enclosure),
				new NaturalNumberRectangleContainer(FF, enclosure),
				new NaturalNumberRectangleContainer(GG, enclosure),
				new NaturalNumberRectangleContainer(HH, enclosure) };

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new Conjunction(new Conjunction(rectangleFixers),
				new Conjunction(rectangleCongruenters), rectangleDisjointer,
				new Conjunction(rectangleContainers));

		/**
		 * Solve the IProblem:
		 */

		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.print("\n");
			System.out.println("A = " + A);
			System.out.println("B = " + B);
			System.out.println("C = " + C);
			System.out.println("D = " + D);
			System.out.println("E = " + E);
			System.out.println("F = " + F);
			System.out.println("G = " + G);
			System.out.println("H = " + H);

			// System.out.println("(Ax,Ay) = (" + Ax + "," + Ay + ")");
			// System.out.println("(Bx,By) = (" + Bx + "," + By + ")");
			// System.out.println("(Cx,Cy) = (" + Cx + "," + Cy + ")");
			// System.out.println("(Dx,Dy) = (" + Dx + "," + Dy + ")");
			// System.out.println("(Ex,Ey) = (" + Ex + "," + Ey + ")");
			// System.out.println("(Fx,Fy) = (" + Fx + "," + Fy + ")");
			// System.out.println("(Gx,Gy) = (" + Gx + "," + Gy + ")");
			// System.out.println("(Hx,Hy) = (" + Hx + "," + Hy + ")");

			System.out.print("\n");
			System.out.println("AA = " + AA);
			System.out.println("BB = " + BB);
			System.out.println("CC = " + CC);
			System.out.println("DD = " + DD);
			System.out.println("EE = " + EE);
			System.out.println("FF = " + FF);
			System.out.println("GG = " + GG);
			System.out.println("HH = " + HH);

			System.out.print("\n");
			for (int row = 7; row >= 0; row--)
			{
				String ret = "";
				for (int col = 0; col <= 7; col++)
				{
					if (decide(AA, row, col))
					{
						ret += " A";
						continue;
					}
					if (decide(BB, row, col))
					{
						ret += " B";
						continue;
					}
					if (decide(CC, row, col))
					{
						ret += " C";
						continue;
					}
					if (decide(DD, row, col))
					{
						ret += " D";
						continue;
					}
					if (decide(EE, row, col))
					{
						ret += " E";
						continue;
					}
					if (decide(FF, row, col))
					{
						ret += " F";
						continue;
					}
					if (decide(GG, row, col))
					{
						ret += " G";
						continue;
					}
					if (decide(HH, row, col))
					{
						ret += " H";
						continue;
					}
					ret += " +";
				}
				System.out.println(ret);
			}
		}
		else
			System.out.println("No solution.");
	}
}
