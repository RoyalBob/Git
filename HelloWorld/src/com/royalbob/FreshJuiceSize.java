package com.royalbob;

public class FreshJuiceSize {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		FreshJuice juice = new FreshJuice();
		juice.size = FreshJuice.FreshJuiceSize.MEDUIM ;
		System.out.println(juice.size);
	}

}

class FreshJuice {
   enum FreshJuiceSize{ SMALL, MEDUIM, LARGE }
   FreshJuiceSize size;
}