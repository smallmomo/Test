package com.mynote.service;

import java.util.ArrayList;

import com.mynote.vo.Financial;

public class Main {
	public static void main(String[] args){
		FinancialService f=new FinancialService(); 
		ArrayList<Financial> fList = f.getFinancials(1, 10);
		for (int i = 0; i < fList.size(); i++) {
			System.out.print(fList.get(i));
		}
		
	}
}
