package com.java.demo.regularex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p></p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/8/9
 * Time: 上午10:43
 */
public class UrlDateAnalysis {


    /**
     * 根据Url提取 年月日时间戳， 输出格式类似于 20160810
     * 规则的匹配从上到下，优先级逐级递减
     *
     * @param url
     * @return
     */
    public static String getDateArrayFromUrl(String url) {
        List<String> dateStrings = new ArrayList<String>();
        //url中包含 /20160302/ 信息的模式
        Pattern pattern = Pattern.compile("/201[56](0?[1-9]|1[0-2])((0?[1-9])|((1|2)[0-9])|30|31)/");
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            String date = matcher.group().replaceAll("/", "");
            dateStrings.add(date);
        }

        //url中包含 /2016-03-02/ 信息的模式
        pattern = Pattern.compile("/201[56][-](0?[1-9]|1[0-2])[-]((0?[1-9])|((1|2)[0-9])|30|31)/");
        matcher = pattern.matcher(url);
        while (matcher.find()) {
            String date = matcher.group().replaceAll("/", "");
            date = date.replaceAll("-", "");
            dateStrings.add(date);
        }

        //url中包含 /2016/03/02/ 信息的模式
        pattern = Pattern.compile("/201[56][//](0?[1-9]|1[0-2])[//]((0?[1-9])|((1|2)[0-9])|30|31)/");
        matcher = pattern.matcher(url);
        while (matcher.find()) {
            String date = matcher.group().replaceAll("/", "");
            dateStrings.add(date);
        }

        //url中包含 /160302/ 信息的模式
        pattern = Pattern.compile("/1[56](0?[1-9]|1[0-2])((0?[1-9])|((1|2)[0-9])|30|31)/");
        matcher = pattern.matcher(url);
        while (matcher.find()) {
            String date = matcher.group().replaceAll("/", "");
            dateStrings.add(date);
        }

        //url中包含 _20160302_ 信息的模式
        pattern = Pattern.compile("_201[56](0?[1-9]|1[0-2])((0?[1-9])|((1|2)[0-9])|30|31)_");
        matcher = pattern.matcher(url);
        while (matcher.find()) {
            String date = matcher.group().replaceAll("_", "");
            dateStrings.add(date);
        }

        //url中包含 2016080924238402180xxx.jpg 信息（日期后必须至少为9个数字）的模式
        pattern = Pattern.compile("201[56](0?[1-9]|1[0-2])((0?[1-9])|((1|2)[0-9])|30|31)[0-9]{9,}?");
        matcher = pattern.matcher(url);
        while (matcher.find()) {
            String date = matcher.group().substring(0, 8);
            dateStrings.add(date);
        }

        //处理 http请求中ptime结构
        if (dateStrings.size() == 0 && url.contains("ptime")) {
            Pattern.compile("ptime=[0-9]*$");
            matcher = pattern.matcher(url);
            while (matcher.find()) {
                System.out.println(matcher.group());
                //TODO
            }
        }

        System.out.println(dateStrings);
        if (dateStrings.size() >= 1) {
            return dateStrings.get(0);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        String url = "ftp://userf:userf@172.18.128.220:21//172.17.128.88/_20160309_/20160101/2016-03-03/2016/03/03/11//0_05_2016031311000187654321.jpg";
        getDateArrayFromUrl(url);
    }
}
