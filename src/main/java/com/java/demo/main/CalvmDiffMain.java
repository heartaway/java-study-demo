package com.java.demo.main;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据源：
 * 上海非云ET2&EU13,2.0,adp,adphost,91
 * 深圳云SU18,2.4,adp,adphost,82
 *
 * 张北云NA61,3.2,adp,adphost,110
 * EuSU18,4.0,adp,adphost,137
 * 上海云ET2&EU13,4.4,adp,adphost,151
 * <p>
 * <p>
 * <p>
 * Created by xinyuan on 16/10/10.
 */
public class CalvmDiffMain {


    public static void main(String[] args) throws Exception {
        File file = new File("/Users/xinyuan/Desktop/111.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buf = new byte[1024];
        StringBuffer sb = new StringBuffer();
        while ((fileInputStream.read(buf)) != -1) {
            sb.append(new String(buf));
            buf = new byte[1024];
        }

        String[] lineData = sb.toString().split("\n");
        System.out.println(lineData);
        Map<String/*应用分组*/, List<String[]>> map = new HashMap<String, List<String[]>>();
        for (String line : lineData) {
            String[] lineArray = line.split(",");
            String[] newLineArray = new String[6];
            newLineArray[0] = lineArray[0];
            newLineArray[1] = lineArray[1];
            newLineArray[2] = lineArray[2];
            newLineArray[3] = lineArray[3];
            newLineArray[4] = lineArray[4];


            String groupName = lineArray[3];
            String idc = lineArray[0];
            if (idc.contains("非云")) {
                if (map.containsKey(groupName + "_非云")) {
                    map.get(groupName + "_非云").add(newLineArray);
                } else {
                    List<String[]> tmp = new ArrayList<String[]>();
                    tmp.add(newLineArray);
                    map.put(groupName + "_非云", tmp);
                }
            } else {
                if (map.containsKey(groupName + "_云")) {
                    map.get(groupName + "_云").add(newLineArray);
                } else {
                    List<String[]> tmp = new ArrayList<String[]>();
                    tmp.add(newLineArray);
                    map.put(groupName + "_云", tmp);
                }
            }
        }


        for (Map.Entry<String, List<String[]>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String[]> value = entry.getValue();
            //云分组处理
            if (key.contains("_云")) {

                double basicScale = 0;
                double basicScaleCount = 0;
                int index = 0;
                //找出最大QPS下标 ,配比、机器数量
//                for (int i = 0; i < value.size(); i++) {
//                    String[] item = value.get(i);
//                    double currentScale = Double.valueOf(item[1]);
//                    int currentScaleCount = Integer.valueOf(item[4]);
//                    double currentQps = currentScale * 10000 / currentScaleCount;
//                    if (currentQps > basicQps) {
//                        basicQps = currentQps;
//                        basicScale = currentScale;
//                        basicScaleCount = currentScaleCount;
//
//                        index = i;
//                    }
//                }

                //张北云为基准
                for (int i = 0; i < value.size(); i++) {
                    String[] item = value.get(i);
                    if (item[0].contains("张北云")) {
                        basicScale = Double.valueOf(item[1]);
                        basicScaleCount = Integer.valueOf(item[4]);
                        index = i;
                        break;
                    }
                }

                if (basicScale == 0 || basicScaleCount == 0) {
                    continue;
                }

                for (int i = 0; i < value.size(); i++) {
                    if (i == index) {
                        value.get(i)[5] = "0";
                    } else {
                        double currentScale = Double.valueOf(value.get(i)[1]);
                        int currentScaleCount = Integer.valueOf(value.get(i)[4]);
                        int diff = Double.valueOf(basicScaleCount * currentScale / basicScale).intValue() - currentScaleCount;
                        if (diff > 0) {
                            value.get(i)[5] = " + " + diff;
                        } else {
                            value.get(i)[5] = String.valueOf(diff);
                        }
                    }
                }
            }

            //非云分组处理
            if (key.contains("_非云")) {
                //找出深圳非云scale下标 ,配比、机器数量
                double basicScale = 10000000;
                int basicScaleCount = 0;
                int index = 0;
                for (int i = 0; i < value.size(); i++) {
                    String[] item = value.get(i);
                    if (item[0].contains("深圳非云")) {
                        basicScale = Double.valueOf(item[1]);
                        basicScaleCount = Integer.valueOf(item[4]);
                        index = i;
                        break;
                    }
                }

                if (basicScale == 10000000 || basicScaleCount == 0) {
                    continue;
                }

                for (int i = 0; i < value.size(); i++) {
                    if (i == index) {
                        value.get(i)[5] = "0";
                    } else {
                        double currentScale = Double.valueOf(value.get(i)[1]);
                        int currentScaleCount = Integer.valueOf(value.get(i)[4]);
                        int diff = Double.valueOf(basicScaleCount * currentScale / basicScale).intValue() - currentScaleCount;
                        if (diff > 0) {
                            value.get(i)[5] = " + " + diff;
                        } else {
                            value.get(i)[5] = String.valueOf(diff);
                        }
                    }
                }
            }
        }


        Map<String, List<String[]>> resultMap = new HashMap<String, List<String[]>>();
        for (Map.Entry<String, List<String[]>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String[]> value = entry.getValue();
            String groupName = key.replace("_非云", "");
            groupName = groupName.replace("_云", "");
            if (resultMap.containsKey(groupName)) {
                resultMap.get(groupName).addAll(value);
            } else {
                resultMap.put(groupName, value);
            }
        }

        //输出格式：应用名称，应用分组，深圳云SU18(实际)，深圳云SU18(差值) ,张北云NA61(实际), 张北云NA61(差值), 上海云ET2&EU13(4.4) , 深圳非云SU18 (4), 上海非云ET2&EU13(2)
        StringBuilder resultBuilder = new StringBuilder();
        for (Map.Entry<String, List<String[]>> entry : resultMap.entrySet()) {
            String key = entry.getKey();//应用分组
            List<String[]> value = entry.getValue();//应用的5个数据

            if (value.size() > 0) {
                resultBuilder.append(value.get(0)[2]);
                resultBuilder.append(",");
                resultBuilder.append(value.get(0)[3]);
            }

            String szC = "";
            String zbC = "";
            String shC = "";
            String szNC = "";
            String shNC = "";

            for (String[] item : value) {
                if (item[0].contains("深圳云")) {
                    szC = "," + item[4] + "," + item[5];
                }

                if (item[0].contains("张北云")) {
                    zbC = "," + item[4] + "," + item[5];
                }

                if (item[0].contains("上海云")) {
                    shC = "," + item[4] + "," + item[5];
                }

                if (item[0].contains("深圳非云")) {
                    szNC = "," + item[4] + "," + item[5];
                }

                if (item[0].contains("上海非云")) {
                    shNC = "," + item[4] + "," + item[5];
                }
            }

            if (StringUtils.isEmpty(szC)) {
                resultBuilder.append(",,");
            } else {
                resultBuilder.append(szC);
            }

            if (StringUtils.isEmpty(zbC)) {
                resultBuilder.append(",,");
            } else {
                resultBuilder.append(zbC);
            }

            if (StringUtils.isEmpty(shC)) {
                resultBuilder.append(",,");
            } else {
                resultBuilder.append(shC);
            }

            if (StringUtils.isEmpty(szNC)) {
                resultBuilder.append(",,");
            } else {
                resultBuilder.append(szNC);
            }

            if (StringUtils.isEmpty(szC)) {
                resultBuilder.append(",,");
            } else {
                resultBuilder.append(shNC);
            }

            resultBuilder.append("\r\n");
        }


        System.out.println(resultBuilder.toString());
    }
}
