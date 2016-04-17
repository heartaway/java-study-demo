package com.java.demo.classcreate;

/**
 * <p>私有构造器，强制类不可实例化</p>
 * User: 心远
 * Date: 14/12/16
 * Time: 上午1:35
 * 使用场景：定义一些工具类的时候，不期望这样的类被实例化，为了防止用户调用隐藏的共有无参构造器，显示声明私有构造器。
 * 编程习惯：对于这样的场景，最好在私有构造器上添加类似于这样的备注信息。
 */
public class XxUtil {

    //Suppress default constructor for noninstanctiable
    private void XxUtils(){}


}
