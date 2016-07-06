package com.java.demo.classcreate;

/**
 * <p>对于List来说不同的实现类 适合不同不同的遍历方法</p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 15/5/10
 * Time: 下午4:35
 */
public class ListForOrForeach {

    /**
     * JDK中推荐的是对List集合尽量要实现RandomAccess接口
     * 如果集合类是RandomAccess的实现，则尽量用for(int i = 0; i < size; i++) 来遍历而不要用Iterator迭代器来遍历，在效率上要差一些。
     * 反过来，如果List是Sequence List，则最好用迭代器来进行迭代。
     */

}
