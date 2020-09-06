package integers.lists;

/**
 * <p>Title: IIntegerList</p>
 * <p>Description: The interface for classes implementing the integer list.</p>
 * <p>Copyright (c) 2019</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */

import integers.IInteger;

public interface IIntegerList
{
	IInteger getInteger(int i) throws Exception;

	IInteger[] getIntegerArray();

	String getName();
}