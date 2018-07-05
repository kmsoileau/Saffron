/*
 * IProblem.java
 *
 * Copyright 2004-2005 Positronic Software.
 *
 */

package bits;

import java.util.ArrayList;
import java.util.List;

import org.sat4j.specs.ISolver;

/**
 * The <code>IProblem</code> interface must be implemented by any class
 * definition of <tt>Problem</tt> contempleted as an alternative to the
 * <tt>Problem</tt> class provided by this package.
 *
 * @author Kerry Michael Soileau
 * @version 1.4, 06/04/10
 */

public interface IProblem extends Iterable<IClause>
{
	boolean addClause(IClause c) throws Exception;

	void addClauseVoid(IClause c) throws Exception;

	IProblem and(IProblem p) throws Exception;

	IProblem combineSinglyMatchingClauses() throws Exception;

	boolean contains(IClause c) throws Exception;

	List<IBooleanLiteral> findModel() throws Exception;

	List<IBooleanLiteral> findModel(ISolver defaultSolver) throws Exception;

	List<IBooleanLiteral> findModelList(ISolver defaultSolver) throws Exception;

	ArrayList<?>[] findTwoModels(IBooleanVariable booleanVariable)
			throws Exception;

	ArrayList<IBooleanVariable> getBooleanVariables() throws Exception;

	IClause getClause(int i);

	IClause[] getClauses();

	int numberOfClauses();

	void removeClause(int pos);

	IProblem resolve(List<IBooleanLiteral> v1) throws Exception;

	void setClause(int i, IClause newc);

	void setClauses(IClause[] c) throws Exception;

	int size();

	boolean solve(ISolver solver) throws Exception;

	void sort() throws Exception;

	String toXML();
}