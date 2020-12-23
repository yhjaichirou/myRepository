package com.fgw.project.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

public class MDateUtil {
	public static final String format="yyyy-MM-dd HH:mm:ss";
	public static final String formatDate="yyyy-MM-dd";
	public static final String formatTime="HH:mm:ss";
	
	/**
	 * 获取当前季度
	 * @return
	 */
	public static Integer getCurrQuarter(Integer _month) {
		DateTime dateTime = new DateTime(DateUtil.today(), "yyyy-MM-dd");
		Integer month = _month==null?(dateTime.month() + 1):_month;// 当前月
		Integer currQuarter = 1;
		if(month.equals(1) || month.equals(2) ||month.equals(3) ) {
			currQuarter = 1;
		}else if(month.equals(4) || month.equals(5) ||month.equals(6)) {
			currQuarter = 2;
		}else if(month.equals(7) || month.equals(8) ||month.equals(9)) {
			currQuarter = 3;
		}else if(month.equals(10) || month.equals(11) ||month.equals(12)) {
			currQuarter = 4;
		}
		return currQuarter;
	}
	
	/**
	 * 获取当前月份 所在季度 的月份值
	 * @return
	 */
	public static Integer[] getCurrMonthInQuarter(Integer _month) {
		DateTime dateTime = new DateTime(DateUtil.today(), "yyyy-MM-dd");
		Integer month = _month==null?(dateTime.month() + 1):_month;// 当前月
		Integer[] months = null;
		if(month.equals(1) || month.equals(2) ||month.equals(3) ) {
			months = new Integer[] {1,2,3};
		}else if(month.equals(4) || month.equals(5) ||month.equals(6)) {
			months = new Integer[] {4,5,6};
		}else if(month.equals(7) || month.equals(8) ||month.equals(9)) {
			months = new Integer[] {7,8,9};
		}else if(month.equals(10) || month.equals(11) ||month.equals(12)) {
			months = new Integer[] {10,11,12};
		}
		return months;
	}
	
	/**
	 * 
	 * @param date
	 * @param parv  yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String dateToString(Date date ,String parv) {
		parv = parv==null?format:parv;
		SimpleDateFormat sf = new SimpleDateFormat(parv);
		return sf.format(date);
	}
	
	public static Date stringToDate(String date ,String parv) {
		parv = parv==null?format:parv;
		SimpleDateFormat sf = new SimpleDateFormat(parv);
		try {
			return sf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取今天开始的时间戳
	 * @return
	 */
	public static long getTodayStartTime() {
		long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
		return zero;
	}
	/**
	 * 获取今天结束的时间戳
	 * @return
	 */
	public static long getTodayFinishTime() {
		long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
		return twelve;
	}
	
	/**
	 * 日期 单数字 格式转变  
	 * @param date
	 * @return
	 */
	public static String coverSimpleDate(String date) {
		if(StrKit.isBlank(date)) {
			return null;
		}
		String[] s = date.split("-");
		String year = s[0],month = s[1],day = s[2];
		if(month.length()<=1) {
			month = "0"+month;
		}
		if(day.length()<=1) {
			day = "0"+day;
		}
		return year +"-"+ month +"-"+ day;
	}
	
	public static String coverSimpleTime(String date) {
		if(StrKit.isBlank(date)) {
			return null;
		}
		String[] s = date.split(":");
		String hour = s[0],minute = s[1],second = s[2];
		if(hour.length()<=1) {
			hour = "0"+hour;
		}
		if(minute.length()<=1) {
			minute = "0"+minute;
		}
		if(second.length()<=1) {
			second = "0"+second;
		}
		return hour +":"+ minute +":"+ second;
	}
	
	/**
	 * 获取 差值  ： 格式  xxx天  12:22:22
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static String getDiff(Date startDate,Date endDate) {
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();
		//时间差的毫秒数
		long dateDiff = endTime - startTime;
		//计算出相差天数
		long day=dateDiff/(24*60*60*1000);
		long hour=(dateDiff/(60*60*1000)-day*24);
		long min=((dateDiff/(60*1000))-day*24*60-hour*60);
		long second=(dateDiff/1000-day*24*60*60-hour*60*60-min*60);
		return day<=0? "-"+ hour + ":"+min+":"+second:day+"天-"  + hour + ":"+min+":"+second ; 
	}
	
	/**
	 * 获取 差值  ： 格式  xxx天  
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Integer getDiffDay(Date startDate,Date endDate) {
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();
		//时间差的毫秒数
		long dateDiff = endTime - startTime;
		//计算出相差天数
		Long day=dateDiff/(24*60*60*1000);
		return day.intValue();
	}
	
	/**
	 * 获取 差值  ： 格式  xxx分  
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Integer getDiffMinute(Date startDate,Date endDate) {
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();
		//时间差的毫秒数
		long dateDiff = endTime - startTime;
		//计算出相差天数
		Long minute=dateDiff/(60*1000);
		return minute.intValue();
	}
	
	/**
	 * 获取 指定年份 到现在的年份数组
	 * @return
	 */
	public static List<Integer> getYears(Integer year) {
		Integer nowYear = DateUtil.year(DateUtil.date());
		List<Integer> years = new ArrayList<>();
		while (true) {
			years.add(year);
			if(nowYear.equals(year)) {
				break;
			}
			year += 1;
		}
		return years;
	}
	
	
	/**
	 * 获取 差值  ： 格式  xxx 年
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Integer getDiffYear(Date startDate,Date endDate) {
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();
		//时间差的毫秒数
		long dateDiff = endTime - startTime;
		//计算出相差天数
		Long day=dateDiff/(24*60*60*1000*365);
		return day.intValue();
	}
	
	/**
	 * 获取指定某年某月的 当月天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysByYearMonth(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}
	
	/**
	 * 获取当月第一天日期
	 * @return
	 */
	public static DateTime getFirstDayDateOfDate(Date date) {
		return DateUtil.beginOfMonth(date);
	}
	
	/**
	 * 获得指定日期是这个日期所在月份的第几天
	 * @return
	 */
	public static int getFirstDayOfMonth(Date date) {
		return DateUtil.beginOfMonth(date).dayOfMonth();
	}
	
	/**
	 * 获取当天所在的星期 ，type: 1：一个星期的日期 , 2：获取收尾日期
	 * @return
	 */
	public static LinkedList<String> getCurrWeekDate(Integer type){
		Date w1=new Date(), w2=new Date(),w3=new Date(),w4=new Date(),w5=new Date(),w6=new Date(),w7=new Date();
		DateTime date = DateUtil.date();
		Integer wekDay = date.dayOfWeek();
		switch(wekDay) {
		case 1://日
			w1.setDate(date.getDate() - 6);
			w2.setDate(date.getDate() - 5);
			w3.setDate(date.getDate() - 4);
			w4.setDate(date.getDate() - 3);
			w5.setDate(date.getDate() - 2);
			w6.setDate(date.getDate() - 1);
			break;
		case 2://一
			w2.setDate(date.getDate() + 1);
			w3.setDate(date.getDate() + 2);
			w4.setDate(date.getDate() + 3);
			w5.setDate(date.getDate() + 4);
			w6.setDate(date.getDate() + 5);
			w7.setDate(date.getDate() + 6);
			break;
		case 3://二
			w1.setDate(date.getDate() - 1);
			w3.setDate(date.getDate() + 1);
			w4.setDate(date.getDate() + 2);
			w5.setDate(date.getDate() + 3);
			w6.setDate(date.getDate() + 4);
			w7.setDate(date.getDate() + 5);
			break;
		case 4://三
			w1.setDate(date.getDate() - 2);
			w2.setDate(date.getDate() - 1);
			w4.setDate(date.getDate() + 1);
			w5.setDate(date.getDate() + 2);
			w6.setDate(date.getDate() + 3);
			w7.setDate(date.getDate() + 4);
			break;
		case 5://四
			w1.setDate(date.getDate() - 3);
			w2.setDate(date.getDate() - 2);
			w3.setDate(date.getDate() - 1);
			w5.setDate(date.getDate() + 1);
			w6.setDate(date.getDate() + 2);
			w7.setDate(date.getDate() + 3);
			break;
		case 6://五
			w1.setDate(date.getDate() - 4);
			w2.setDate(date.getDate() - 3);
			w3.setDate(date.getDate() - 2);
			w4.setDate(date.getDate() - 1);
			w6.setDate(date.getDate() + 1);
			w7.setDate(date.getDate() + 2);
			break;
		case 7://六
			w1.setDate(date.getDate() - 5);
			w2.setDate(date.getDate() - 4);
			w3.setDate(date.getDate() - 3);
			w4.setDate(date.getDate() - 2);
			w5.setDate(date.getDate() - 1);
			w7.setDate(date.getDate() + 1);
			break;
		}
		LinkedList<String> weeks = new LinkedList<>();
		//List<String> weeks = new ArrayList<>();
		
		System.out.println(w1.toString()+"--"+w2.toString()+"--"+w3.toString()+"--"+w4.toString()+"--"+w5.toString()+"--"+w6.toString()+"--"+w7.toString());
		if(type==1) {//获取一个星期日期
			weeks.add(dateToString(w1,formatDate));
			weeks.add(dateToString(w2,formatDate));
			weeks.add(dateToString(w3,formatDate));
			weeks.add(dateToString(w4,formatDate));
			weeks.add(dateToString(w5,formatDate));
			weeks.add(dateToString(w6,formatDate));
			weeks.add(dateToString(w7,formatDate));
		}else if(type==2) {//获取收尾日期
			weeks.add(dateToString(w1,formatDate));
			weeks.add(dateToString(w7,formatDate));
		}
		return weeks;
	}
	
	//---------------------计算农历-------------------
    public static class Lunar {
        private int year;
        private int month;
        private int day;
        private boolean leap;
        final String chineseNumber[] = { "一", "二", "三", "四", "五", "六", "七",
                "八", "九", "十", "十一", "十二" };
        SimpleDateFormat chineseDateFormat = new SimpleDateFormat(
                "yyyy年MM月dd日");
        final  long[] lunarInfo = new long[] { 0x04bd8, 0x04ae0, 0x0a570,
                0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2,
                0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0,
                0x0ada2, 0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50,
                0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566,
                0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0,
                0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4,
                0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550,
                0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950,
                0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260,
                0x0f263, 0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0,
                0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6,
                0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40,
                0x0af46, 0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3,
                0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960,
                0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0,
                0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9,
                0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0,
                0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65,
                0x0d530, 0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0,
                0x1d0b6, 0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2,
                0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0 };
     
        // ====== 传回农历 y年的总天数
        final private  int yearDays(int y) {
            int i, sum = 348;
            for (i = 0x8000; i > 0x8; i >>= 1) {
                if ((lunarInfo[y - 1900] & i) != 0)
                    sum += 1;
            }
            return (sum + leapDays(y));
        }
     
        // ====== 传回农历 y年闰月的天数
        final private int leapDays(int y) {
            if (leapMonth(y) != 0) {
                if ((lunarInfo[y - 1900] & 0x10000) != 0)
                    return 30;
                else
                    return 29;
            } else
                return 0;
        }
     
        // ====== 传回农历 y年闰哪个月 1-12 , 没闰传回 0
        final private int leapMonth(int y) {
            return (int) (lunarInfo[y - 1900] & 0xf);
        }
     
        // ====== 传回农历 y年m月的总天数
        final private int monthDays(int y, int m) {
            if ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0)
                return 29;
            else
                return 30;
        }
     
        // ====== 传回农历 y年的生肖
        final public String animalsYear() {
            final String[] Animals = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇",
                    "马", "羊", "猴", "鸡", "狗", "猪" };
            return Animals[(year - 4) % 12];
        }
     
        // ====== 传入 月日的offset 传回干支, 0=甲子
        final private String cyclicalm(int num) {
            final String[] Gan = new String[] { "甲", "乙", "丙", "丁", "戊", "己", "庚",
                    "辛", "壬", "癸" };
            final String[] Zhi = new String[] { "子", "丑", "寅", "卯", "辰", "巳", "午",
                    "未", "申", "酉", "戌", "亥" };
            return (Gan[num % 10] + Zhi[num % 12]);
        }
     
        // ====== 传入 offset 传回干支, 0=甲子
        final public String cyclical() {
            int num = year - 1900 + 36;
            return (cyclicalm(num));
        }
     
        /** */
        /**
         * 　　* 传出y年m月d日对应的农历. 　　* yearCyl3:农历年与1864的相差数 ? 　　*
         * monCyl4:从1900年1月31日以来,闰月数 　　* dayCyl5:与1900年1月31日相差的天数,再加40 ? 　　* @param
         * cal 　　* @return 　　
         */
        public Lunar(Calendar cal) {
            @SuppressWarnings("unused")
            int yearCyl, monCyl, dayCyl;
            int leapMonth = 0;
            Date baseDate = null;
            try {
                baseDate = chineseDateFormat.parse("1900年1月31日");
            } catch (ParseException e) {
                e.printStackTrace(); // To change body of catch statement use
                                        // Options | File Templates.
            }
            // 求出和1900年1月31日相差的天数
            int offset = (int) ((cal.getTime().getTime() - baseDate.getTime()) / 86400000L);
            dayCyl = offset + 40;
            monCyl = 14;
            // 用offset减去每农历年的天数
            // 计算当天是农历第几天
            // i最终结果是农历的年份
            // offset是当年的第几天
            int iYear, daysOfYear = 0;
            for (iYear = 1900; iYear < 2050 && offset > 0; iYear++) {
                daysOfYear = yearDays(iYear);
                offset -= daysOfYear;
                monCyl += 12;
            }
            if (offset < 0) {
                offset += daysOfYear;
                iYear--;
                monCyl -= 12;
            }
            // 农历年份
            year = iYear;
            yearCyl = iYear - 1864;
            leapMonth = leapMonth(iYear); // 闰哪个月,1-12
            leap = false;
            // 用当年的天数offset,逐个减去每月（农历）的天数，求出当天是本月的第几天
            int iMonth, daysOfMonth = 0;
            for (iMonth = 1; iMonth < 13 && offset > 0; iMonth++) {
                // 闰月
                if (leapMonth > 0 && iMonth == (leapMonth + 1) && !leap) {
                    --iMonth;
                    leap = true;
                    daysOfMonth = leapDays(year);
                } else
                    daysOfMonth = monthDays(year, iMonth);
                offset -= daysOfMonth;
                // 解除闰月
                if (leap && iMonth == (leapMonth + 1))
                    leap = false;
                if (!leap)
                    monCyl++;
            }
            // offset为0时，并且刚才计算的月份是闰月，要校正
            if (offset == 0 && leapMonth > 0 && iMonth == leapMonth + 1) {
                if (leap) {
                    leap = false;
                } else {
                    leap = true;
                    --iMonth;
                    --monCyl;
                }
            }
            // offset小于0时，也要校正
            if (offset < 0) {
                offset += daysOfMonth;
                --iMonth;
                --monCyl;
            }
            month = iMonth;
            day = offset + 1;
        }
     
        public String getChinaDayString(int day) {
            String chineseTen[] = { "初", "十", "廿", "卅" };
            int n = day % 10 == 0 ? 9 : day % 10 - 1;
            if (day > 30)
                return "";
            if (day == 10)
                return "初十";
            else
                return chineseTen[day / 10] + chineseNumber[n];
        }
     
        public String toString() {
            return year + "年" + (leap ? "闰" : "") + chineseNumber[month - 1] + "月"
                    + getChinaDayString(day);
        }
        public String toSingleLunner() {
            return getChinaDayString(day);
        }
    }
    
    
    
}
