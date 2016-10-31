package com.java.demo.main;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源：
 * address-server-bufferhost,ET2,1
 * address-server-bufferhost,SU18,1
 * address-server_prehost,ET2,3
 * address-serverhost,ET2,9
 * address-serverhost,EU13,9
 * address-serverhost,SU18,8
 * <p>
 * <p>
 * <p>
 * Created by xinyuan on 16/10/10.
 */
public class DataMergeMain {


    public static void main(String[] args) throws Exception {
        //解析原始文件
        File targetFile = new File("/Users/xinyuan/Desktop/123.csv");
        FileInputStream targetFileInputStream = new FileInputStream(targetFile);
        byte[] bufTarget = new byte[1024];
        StringBuffer sbTarget = new StringBuffer();
        while ((targetFileInputStream.read(bufTarget)) != -1) {
            sbTarget.append(new String(bufTarget));
            bufTarget = new byte[1024];
        }

        String[] targetLineData = sbTarget.toString().split("\r");
        Map<String/*应用分组*/, String[]> targetMap = new HashMap<String, String[]>();
        for (String line : targetLineData) {
            String[] lineArray = line.split(";");
            if(lineArray == null || lineArray.length == 0){
                continue;
            }
            String[] newLineArray = new String[3];
            String groupName = lineArray[0].toLowerCase();
            if (targetMap.containsKey(groupName)) {
                continue;
            }
            targetMap.put(groupName, newLineArray);
        }


        //解析补充数据
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
        Map<String/*应用分组*/, Map<String, String>> map = new HashMap<String, Map<String, String>>();
        for (String line : lineData) {
            String[] lineArray = line.split(",");
            String groupName = lineArray[0].toLowerCase();
            String idcName = lineArray[1].toUpperCase();
            String count = lineArray[2].toUpperCase();
            if (map.containsKey(groupName)) {
                if (map.get(groupName).containsKey(idcName)) {
                    throw new IllegalArgumentException("data repeat," + groupName + "," + idcName);
                }
                map.get(groupName).put(idcName, count);
            } else {
                Map<String, String> item = new HashMap<String, String>();
                item.put(idcName, count);
                map.put(groupName, item);
            }
        }


        //填充数据
        for (Map.Entry<String, String[]> entry : targetMap.entrySet()) {
            String groupName = entry.getKey();
            if (map.containsKey(groupName)) {
                Map<String, String> datas = map.get(groupName);
                for (Map.Entry<String, String> item : datas.entrySet()) {
                    String idcName = item.getKey();
                    String count = item.getValue();
                    if ("ET2".equalsIgnoreCase(idcName)) {
                        entry.getValue()[0] = count;
                    }

                    if ("EU13".equalsIgnoreCase(idcName)) {
                        entry.getValue()[1] = count;
                    }

                    if ("SU18".equalsIgnoreCase(idcName)) {
                        entry.getValue()[2] = count;
                    }
                }
            }
        }

        //回写数据
        StringBuilder resultBuilder = new StringBuilder();
        int i = 0;
        for (String line : targetLineData) {
            i++;
            if(i == 1222){
                System.out.println("------");
            }

            String[] lineArray = line.split(";");
            if(lineArray == null || lineArray.length == 0){
                continue;
            }
            String groupName = lineArray[0].toLowerCase();

            resultBuilder.append(groupName).append(";");
            resultBuilder.append(lineArray[1]).append(";");
            resultBuilder.append(lineArray[2]).append(";");
            resultBuilder.append(lineArray[3]).append(";");
            resultBuilder.append(lineArray[4]).append(";");
            resultBuilder.append(lineArray[5]).append(";");
            resultBuilder.append(lineArray[6]).append(";");
            resultBuilder.append(lineArray[7]).append(";");
            resultBuilder.append(lineArray[8]).append(";");
            resultBuilder.append(lineArray[9]).append(";");
            resultBuilder.append(lineArray[10]).append(";");

            if (targetMap.containsKey(groupName)) {
                String[] otherData = targetMap.get(groupName);
                if(StringUtils.isEmpty(otherData[0])){
                    otherData[0] = "";
                }
                if(StringUtils.isEmpty(otherData[1])){
                    otherData[1] = "";
                }
                if(StringUtils.isEmpty(otherData[2])){
                    otherData[2] = "";
                }
                resultBuilder.append(otherData[0]).append(";");
                resultBuilder.append(otherData[1]).append(";");
                resultBuilder.append(otherData[2]).append("\r\n");
            }else{
                resultBuilder.append("").append(";");
                resultBuilder.append("").append(";");
                resultBuilder.append("").append("\r\n");
            }
        }


        System.out.println(resultBuilder.toString());
    }
}
