package tester.classTester;

import java.util.ArrayList;

import search.ISortedNumberList;
import search.LesserGreaterSearchableArrayList;
import tester.ITest;

public class BinarySearchTest implements ITest {

	public void Run() {

		ArrayList<Integer> numList = new ArrayList<Integer>();
		
		numList.add(1); 
		numList.add(12); 
		numList.add(13); 
		numList.add(14); 
		numList.add(21); 
		numList.add(23); 
		numList.add(25); 
		numList.add(31); 
		numList.add(36); 
		numList.add(39); 
		numList.add(41); 
		
		ISortedNumberList s = new LesserGreaterSearchableArrayList(numList);
		
		System.out.println(s.lastValueEqualOrLesserThan(-5)); //-1
		System.out.println(s.lastValueEqualOrLesserThan(5)); //0
		System.out.println(s.lastValueEqualOrLesserThan(13)); //2
		System.out.println(s.lastValueEqualOrLesserThan(14)); //3
		System.out.println(s.lastValueEqualOrLesserThan(16)); //3
		System.out.println(s.lastValueEqualOrLesserThan(41)); //10
		System.out.println(s.lastValueEqualOrLesserThan(42)); //10
		System.out.println(s.lastValueEqualOrLesserThan(45)); //10
		
		
		System.out.println(s.firstValueGreaterThan(-5)); //0
		System.out.println(s.firstValueGreaterThan(0)); //0
		System.out.println(s.firstValueGreaterThan(1)); //1
		System.out.println(s.firstValueGreaterThan(13)); //3
		System.out.println(s.firstValueGreaterThan(20)); //4
		System.out.println(s.firstValueGreaterThan(41)); //-1
		System.out.println(s.firstValueGreaterThan(42)); //-1
		
	}

}
