package com.apps.base.utils;

public enum MonthShortEnum {
	/*
	 * 一月：January 简写Jan. 
	 * 二月：February 简写Feb. 
	 * 三月：March 简写Mar. 
	 * 四月：April 简写Apr.
	 * 五月：May 简写May. 
	 * 六月：June 简写Jun. 
	 * 七月：July 简写Jul. 
	 * 八月：August 简写Aug. 
	 * 九月：September 简写Sep. / Sept. 
	 * 十月：October 简写Oct. 
	 * 十一月：November 简写Nov. 
	 * 十二月：December 简写Dec.
	 */
	/*	Jan("January"),
	Feb("February"), 
	Mar("March"), 
	Apr("April"), 
	May("May"), 
	Jun("June"), 
	Jul("July"), 
	Aug("August"), 
	Sep("September"), 
	Oct("October"), 
	Nov("November"), 
	Dec("December");
	 */	
	Jan("1"),
	Feb("2"), 
	Mar("3"), 
	Apr("4"), 
	May("5"), 
	Jun("6"), 
	Jul("7"), 
	Aug("8"), 
	Sep("9"), 
	Oct("10"), 
	Nov("11"), 
	Dec("12");
	private final String month;
	private MonthShortEnum(String month){
		this.month = month;
	}
	public String getMonth(){
		return month;
	}
	/*public static void monthStr(int i){
		String monthStr = "";
		switch(i){
			case 1:  MonthEnum.Jan;break;
			case 2:  MonthEnum.Feb;break;
			case 3:  MonthEnum.Mar;break;
			case 4:  MonthEnum.Apr;break;
			case 5:  MonthEnum.May;break;
			case 6:  MonthEnum.Jun;break;
			case 7:  MonthEnum.Jul;break;
			case 8:  MonthEnum.Aug;break;
			case 9:  MonthEnum.Sep;break;
			case 10:  MonthEnum.Oct;break;
			case 11:  MonthEnum.Nov;break;
			case 12:  MonthEnum.Dec;break;
			default:
		}
	}*/
	
}
