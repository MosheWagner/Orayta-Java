package tester.classTester;

import html.OBK_ParseHelper;
import tester.ITest;

public class DecryptTester implements ITest
{

	public void Run() 
	{
		String d = "BDMHCGNGKDADADADBDADHDEDNCDCEPIPHPACAOACNCACOOJPAPEOACAOMH";
		System.out.println(OBK_ParseHelper.Decrypt(d));
		System.out.println(OBK_ParseHelper.Decrypt(OBK_ParseHelper.Encrypt(OBK_ParseHelper.Decrypt(d))));
		
		String d2 = "פרק א - משנה א";
		System.out.println(OBK_ParseHelper.Encrypt(d));
		System.out.println(OBK_ParseHelper.Decrypt(OBK_ParseHelper.Encrypt(d2)));

		String d3 = "אוו - מבאצינו";
		System.out.println(OBK_ParseHelper.Encrypt(d3));
		System.out.println(OBK_ParseHelper.Decrypt(OBK_ParseHelper.Encrypt(d3)));
		
		String e = "<!--excJDMHCGNGKDADADADADHDGDEDNCDCDOBOIPJONOACEPIPHPNCFONCLHGONHMHMH-->{דברים ו-ז}";
		System.out.println(OBK_ParseHelper.decodeExternalLink(e));
	}

}
