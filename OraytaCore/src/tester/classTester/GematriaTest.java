package tester.classTester;

import hebrewStuff.GematriaTools;
import tester.ITest;

public class GematriaTest implements ITest
{

	public void Run() 
	{
		System.out.println(GematriaTools.gematriaLetters(2, true));
		
		System.out.println(GematriaTools.gematriaLetters(3, false));
		System.out.println(GematriaTools.gematriaLetters(4, true));
		
		System.out.println(GematriaTools.gematriaLetters(113, false));
		System.out.println(GematriaTools.gematriaLetters(214, true));
		
		System.out.println(GematriaTools.gematriaLetters(15, false));
		System.out.println(GematriaTools.gematriaLetters(15, true));
		
		System.out.println(GematriaTools.gematriaLetters(1116, false));
		System.out.println(GematriaTools.gematriaLetters(1116, true));
		
		System.out.println(GematriaTools.gematriaLetters(2422, false));
		System.out.println(GematriaTools.gematriaLetters(2422, true));
		
		System.out.println(GematriaTools.gmaraPageRepresentation(122));
		
		System.out.println(GematriaTools.gematriaValue("תתב"));
		
		//Round trip
		System.out.println(GematriaTools.gematriaValue(GematriaTools.gematriaLetters(11422, false)));
		System.out.println(GematriaTools.gematriaValue(GematriaTools.gematriaLetters(805, true)));
		System.out.println(GematriaTools.gematriaValue(GematriaTools.gematriaLetters(2, true)));
		System.out.println(GematriaTools.gematriaValue(GematriaTools.gematriaLetters(1, false)));
	}

}
