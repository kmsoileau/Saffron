# Saffron-2.0
Saffron is a preprocessor which serves as a front end for Sat4j. Saffron is to Sat4j as a higher level language is to an assembler. The user expresses his/her problem by writing a Java application using the Saffron API. When the application is run, Saffron creates a corresponding SAT problem, then applies the Sat4j API to find a solution. The Sat4j solution is analyzed by Saffron, and the bit assignments provided by Sat4j are used to construct the solution values of the original problem.

For more information, see the blog at http://www.kerrysoileau.com/saffronpreprocessor/

## Getting Started

1.	Download Saffron source files.
2.	Download the this (or a later) version of the following Sat4j library: org.sat4j.core_2.3.5.v201308161310.jar
3.	To familiarize yourself with the use of Saffron, run the numerous demo applications in the package named (of all things): demos. Extensive javadoc files can be found in the folder called: doc

## Running From The Windows Terminal

For instance, here's how the NaturalNumberXorerDemo app runs in my particular Java configuration:

cd C:\Users\Kerry\\.AndroidStudio2.2\system\restart\jre\bin\

java.exe -classpath "C:\Users\Kerry\git\Saffron-2.0\Saffron 2.0\bin;C:\Users\Kerry\Development\eclipse\plugins\org.sat4j.core_2.3.5.v201308161310.jar" demos.NaturalNumberXorerDemo

## Built With

* [Eclipse](https://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/photonr) - Java IDE
* [Sat4j](http://www.sat4j.org/) - Java SAT solver

## Author

* [**Kerry Michael Soileau**](http://www.kerrysoileau.com/index.html)

## License
This project is licensed under the GPLv3 License - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgments

*	[Daniel Le Berre](https://github.com/danielleberre) provided invaluable guidance in connecting the Saffron code to the Sat4j API.

## Introduction to Saffron Programming

Saffron applications all share the same basic structure:

1. Set Java variables
2. Set globals
3. Create Saffron objects and arrays
4. Create problems which constrain the values of these Saffron objects
5. Create the IProblem of satisfying all of these constraining problems
6. Solve the IProblem
		
For instance,see the following complete Saffron application, which finds a subset of integers that sums to <code>desiredSum</code>, which in this example is 171.

```
package showcase.subsetsum;

import naturalnumbers.ConditionalAdder;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;
import bitstrings.BitString;

public class SubsetSumDemo
{
	public static void main(String[] args) throws Exception
	{
		/**
		 * Set Java constants:
		 */
		 
		int desiredSum = 171;

		Integer[] data = new Integer[]
		{ 99, 92, 93, 85, 35, 27, 9, 2, 88, 90, 90, 1, 83, 45, 63, 83, 33, 21 };

		int maxSum = 0;
		for (int i = 0; i < data.length; i++)
			maxSum += data[i];

		/**
		 * Set globals:
		 */
		 
		NaturalNumber.setLargestNaturalNumber(maxSum);

		/**
		 * Create Saffron objects and arrays:
		 */
		 
		INaturalNumber[] dataNNarry = new INaturalNumber[data.length];
		IBitString membership = new BitString(data.length);
		IProblem[] r = new IProblem[data.length];
		INaturalNumber W = new NaturalNumber();
		for (int i = 0; i < data.length; i++)
		{
			dataNNarry[i] = new NaturalNumber();
		}

		/**
		 * Create problems which constrain the values of Saffron objects:
		 */
		 
		for (int i = 0; i < data.length; i++)
		{
			r[i] = new NaturalNumberFixer(dataNNarry[i], data[i]);
		}
		
		IProblem rArray = new Conjunction(r);
		IProblem fixW = new NaturalNumberFixer(W, desiredSum);
		IProblem cAdd = new ConditionalAdder(dataNNarry, membership, W);

		/**
		 * Create the Conjunction of all of these constraining problems:
		 */
		 
		IProblem problem = new Conjunction(rArray, fixW, cAdd);

		/**
		 * Solve the Conjunction:
		 */
		 
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE
				&& s.getLiterals().size() > 0)
		{
			BooleanLiteral.interpret(s.getLiterals());
			for (int i = 0; i < membership.size(); i++)
				if (membership.getBooleanVariable(i).getValue())
					System.out.print(data[i] + " ");
		}
		else
			System.out.println("No solution.");
	}
}
```
