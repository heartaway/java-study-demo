package com.java.demo.hbase;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @author: xinyuan.ymm
 * @create: 2017-02-27 下午4:43
 */
public class HbaseClient {

    public static void main(String[] args) throws Exception {
        HBaseHelper helper = HBaseHelper.getHelper(HBaseConfiguration.create());
        HTableInterface table = helper.getTable("tlog_biz");
        Put put = new Put(Bytes.toBytes("row1"));
        put.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        put.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"), Bytes.toBytes("val2"));
        table.put(put);
        table.close();
    }
}
