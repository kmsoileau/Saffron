package demos.naturalnumberlists;

import naturalnumberlists.INaturalNumberList;
import naturalnumberlists.NaturalNumberList;

/**
 * <p>
 * Title: NaturalNumberListDemo
 * </p>
 * <p>
 * Description: TBS
 * </p>
 * <p>
 * Copyright (c) 2005
 * </p>
 * <p>
 * Company: Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */

public class NaturalNumberListDemo
{
	public static void main(String[] args) throws Exception
	{
		long[] valueArray = new long[]
		{ 4, 26, 11 };

		INaturalNumberList n = new NaturalNumberList(valueArray);
		System.out.println(n);
	}
}