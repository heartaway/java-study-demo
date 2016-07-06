package com.java.demo.threadsafe;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *     SimpleDateFormat 非线程安全，所以定义工具类的时候需要自定义线程安全的工具类
 * </p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 16/3/10
 * Time: 下午9:00
 */
public class SimpleDateFormatTest {

    private Map<String,ThreadLocal<SimpleDateFormat>> map =new HashMap<String, ThreadLocal<SimpleDateFormat>>();


}
