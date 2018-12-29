package com.zlp.dairy.base.util;

import org.apache.commons.io.FileUtils;
//import org.springframework.security.core.context.SecurityContextHolder;

import java.io.*;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class XaUtil {

    /**
     * 判断对象是否Empty(null或元素为0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object pObj) {
        if (pObj == null)
            return true;
        if ("".equals(pObj))
            return true;
        if (pObj instanceof String) {
            if (((String) pObj).length() == 0) {
                return true;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return true;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断对象是否为NotEmpty(!null或元素>0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Object pObj) {
        if (pObj == null)
            return false;
        if ("".equals(pObj))
            return false;
        if (pObj instanceof String) {
            if (((String) pObj).length() == 0) {
                return false;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return false;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 数据格式化.
     */
    public static String codeFormat(String pattern, Object value) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(value);
    }

    /**
     * 格式化时间.
     */
    public static String fomatDate(String date) {
        if (isNotEmpty(date)) {
            return date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
                    + date.substring(6, 8);
        }
        return null;
    }

    /**
     * 格式化时间.
     */
    public static String fomatLongDate(String date) {
        if (isNotEmpty(date)) {
            return date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
                    + date.substring(6, 8) + " " + date.substring(8, 10) + ":"
                    + date.substring(10, 12) + ":" + date.substring(12, 14);
        }
        return null;
    }

    /**
     * 格式化时间.
     */
    public static String fomatDateTime2String(String date) {
        if (isNotEmpty(date)) {
            return date.replace("-", "").replace("T", "").replace(":", "")
                    .replace(" ", "");
        }
        return null;
    }

    /**
     * 随机产生一个a到b之间的整数
     */
    public static int createRoundNum(int a, int b) {
        return Integer.valueOf(Math.round(Math.random() * (b - a) + a) + "");
    }

    /**
     * 将时间字符串格式化成一个日期(java.util.Date)
     */
    public static Date formatDateString2Date(String dateStr, String formatStr) {
        DateFormat dateFormat = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将时间字符串格式化成一个日期(java.util.Date)
     */
    public static String formatDate2String(Date date, String formatStr) {
        DateFormat dateFormat = new SimpleDateFormat(formatStr);
        String result = null;
        try {
            result = dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将一个毫秒数时间转换为相应的时间格式
     */
    public static String formateDateLongToString(long longSecond) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(longSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(gc.getTime());
    }

    /**
     * 格式化金额.
     */
    public static String formatCurrency2String(Long value) {
        if (value == null || "0".equals(String.valueOf(value))) {
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value / 100.00);
    }

    /**
     * 格式化金额.
     */
    public static Long formatCurrency2Long(String priceFormat) {
        BigDecimal bg = new BigDecimal(priceFormat);
        Long price = bg.multiply(new BigDecimal(100)).longValue();
        return price;
    }

    /**
     * 获取当前时间.
     */
    public static String getToDayStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间当作文件名称
     *
     * @return
     */
    public static String getToDayStrAsFileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

    public static Date getToDay() throws ParseException {
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Date date = sdf.parse(String.valueOf(System.currentTimeMillis()));
        return new Date();
    }

    /**
     * 获取下一天.
     *
     * @param currentDate the current date
     * @return the next date str
     * @throws ParseException the parse exception
     */
    public static String getNextDateStr(String currentDate)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(currentDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        String nextDate = sdf.format(calendar.getTime());
        return nextDate;
    }

    /**
     * 获取上一天.
     *
     * @param currentDate the current date
     * @return the next date str
     * @throws ParseException the parse exception
     */
    public static String getYesterdayStr(String currentDate)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(currentDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        String nextDate = sdf.format(calendar.getTime());
        return nextDate;
    }

    /**
     * 根据日期获取星期
     *
     * @param strdate
     * @return
     */
    public static String getWeekDayByDate(String strdate) {
        final String dayNames[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
                "星期六"};
        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        try {
            date = sdfInput.parse(strdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek < 0)
            dayOfWeek = 0;
        return dayNames[dayOfWeek];
    }

    /**
     * 生成固定长度的随机字符和数字
     *
     * @param len
     * @return
     */
    public static String generateRandomCharAndNumber(Integer len) {
        StringBuffer sb = new StringBuffer();
        for (Integer i = 0; i < len; i++) {
            int intRand = (int) (Math.random() * 52);
            int numValue = (int) (Math.random() * 10);
            char base = (intRand < 26) ? 'A' : 'a';
            char c = (char) (base + intRand % 26);
            if (numValue % 2 == 0) {
                sb.append(c);
            } else {
                sb.append(numValue);
            }
        }
        return sb.toString();
    }

    public static String readPropertiesFile(String key) {
        String fileName = "/filepath_win.properties";
        String filePath = "";
        InputStream inputStream = XaUtil.class.getResourceAsStream(fileName);
        Properties pros = new Properties();
        try {
            pros.load(inputStream);
            filePath = pros.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return filePath;
    }

    /**
     * 方法描述：将系统限定的路径转换为绝对正确的路径
     *
     * @param originalPath
     * @return
     */
    public static String getGeneralFilePath(String originalPath) {
        if ((null != originalPath) && !("".equals(originalPath))) {
            String strPath[] = originalPath.split("\\\\|/");
            originalPath = "";
            // 拼接jar路径
            for (int i = 0; i < strPath.length; i++) {
                if (!("".equals(strPath[i])) && !("".equals(strPath[i].trim()))) {
                    originalPath = originalPath + strPath[i].trim();
                    if (i < strPath.length - 1) {
                        originalPath = originalPath + File.separator;
                    }
                }
            }
        }
        return originalPath;
    }

    /**
     * 往文件写数据
     */
    public static void WriteFile(String content, String filePath,
                                 String fileName) throws IOException {

        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File f1 = new File(filePath + File.separator + fileName);
        FileUtils.writeStringToFile(f1, content, "utf-8");
//		try {
//
//			FileOutputStream fos = new FileOutputStream(f1);
//			fos.write(content.getBytes());
//			fos.close();
//
//		} catch (IOException e) {
//			System.out.println("Uh oh, got an IOException error!");
//			e.printStackTrace();
//		}
    }

    /**
     * 复制文件(以超快的速度复制文件)
     *
     * @return 实际复制的字节数，如果文件、目录不存在、文件为null或者发生IO异常，返回-1
     */
    @SuppressWarnings("resource")
    public static long copyFile(File srcFile, File destFile) throws Exception {
        long copySizes = 0;
        FileChannel fcin = new FileInputStream(srcFile).getChannel();
        FileChannel fcout = new FileOutputStream(destFile).getChannel();
        long size = fcin.size();
        fcin.transferTo(0, fcin.size(), fcout);
        fcin.close();
        fcout.close();
        copySizes = size;
        return copySizes;
    }

    /**
     * 删除单个文件
     *
     * @return 单个文件删除成功返回true, 否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
            System.out.println("删除单个文件" + fileName + "成功！");
            return true;
        } else {
            System.out.println("删除单个文件" + fileName + "失败！");
            return false;
        }
    }

    /**
     * @param count 位数，如果是1就产生1位的数字，如果是2就产生2位数字，依次类推
     * @return
     * @Title: getRandomNumber
     * @Description: 获取随机数
     */
    public static String getRandomNumber(int count) {
        String result = "";
        for (int i = 0; i < count; i++) {
            int rand = (int) (Math.random() * 10);
            result += rand;
        }
        return result;
    }

    public static void makeDir(File dir) {
        if (!dir.getParentFile().exists()) {
            makeDir(dir.getParentFile());
        }
        dir.mkdir();
    }

}