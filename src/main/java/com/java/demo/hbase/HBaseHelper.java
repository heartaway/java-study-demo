package com.java.demo.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: xinyuan.ymm
 * @create: 2017-02-27 下午4:47
 */
public class HBaseHelper {

    private HTablePool tablePool = null;

    protected HBaseHelper(Configuration conf) throws IOException {
        conf.set("hbase.zookeeper.quorum", "hbasetestmaster1.et2sqa.tbsite.net");
        conf.set("zookeeper.znode.parent", "/hbase-et2sqa-perf");
        // The recommanded number of pool size is 30~50. But also depends on
        // your
        // business requirement, if your client has 200 threads, modify this
        // value to 200.
        this.tablePool = new HTablePool(conf, 40);
    }

    public static HBaseHelper getHelper(Configuration conf) throws IOException {
        return new HBaseHelper(conf);
    }

    /**
     * Get table from pool.
     *
     * @param table
     * @return
     * @throws IOException
     */
    public HTable getTable(String table) throws IOException {
        return (HTable) tablePool.getTable(table);
    }

    /**
     * Generate random test record into table.
     *
     * @param table
     * @param startRow
     * @param endRow
     * @param numCols
     * @param colfams
     * @throws IOException
     */
    public void fillTable(String table, int startRow, int endRow, int numCols, String... colfams) throws IOException {
        fillTable(table, startRow, endRow, numCols, -1, false, colfams);
    }

    /**
     * Generate random test record into table.
     *
     * @param table
     * @param startRow
     * @param endRow
     * @param numCols
     * @param setTimestamp
     * @param colfams
     * @throws IOException
     */
    public void fillTable(String table, int startRow, int endRow, int numCols, boolean setTimestamp, String... colfams)
        throws IOException {
        fillTable(table, startRow, endRow, numCols, -1, setTimestamp, colfams);
    }

    /**
     * Generate random test record into table.
     *
     * @param table
     * @param startRow
     * @param endRow
     * @param numCols
     * @param pad
     * @param setTimestamp
     * @param colfams
     * @throws IOException
     */
    public void fillTable(String table, int startRow, int endRow, int numCols, int pad, boolean setTimestamp,
        String... colfams) throws IOException {
        fillTable(table, startRow, endRow, numCols, pad, setTimestamp, false, colfams);
    }

    /**
     * Generate random test record into table.
     *
     * @param table
     * @param startRow
     * @param endRow
     * @param numCols
     * @param pad
     * @param setTimestamp
     * @param random
     * @param colfams
     * @throws IOException
     */
    public void fillTable(String table, int startRow, int endRow, int numCols, int pad, boolean setTimestamp,
        boolean random, String... colfams) throws IOException {
        HTableInterface tbl = getTable(table);
        Random rnd = new Random();
        for (int row = startRow; row <= endRow; row++) {
            for (int col = 0; col < numCols; col++) {
                Put put = new Put(Bytes.toBytes("row-" + padNum(row, pad)));
                for (String cf : colfams) {
                    String colName = "col-" + padNum(col, pad);
                    String val = "val-" + (random
                        ? Integer.toString(rnd.nextInt(numCols))
                        : padNum(row, pad) + "." + padNum(col, pad));
                    if (setTimestamp) {
                        put.add(Bytes.toBytes(cf), Bytes.toBytes(colName), col, Bytes.toBytes(val));
                    } else {
                        put.add(Bytes.toBytes(cf), Bytes.toBytes(colName), Bytes.toBytes(val));
                    }
                }
                tbl.put(put);
            }
        }
        tbl.close();
    }

    public String padNum(int num, int pad) {
        String res = Integer.toString(num);
        if (pad > 0) {
            while (res.length() < pad) {
                res = "0" + res;
            }
        }
        return res;
    }

    /**
     * Dump table data to IDE console.
     *
     * @param table
     * @param rows
     * @param fams
     * @param quals
     * @throws IOException
     */
    public void dump(String table, String[] rows, String[] fams, String[] quals) throws IOException {
        HTableInterface tbl = getTable(table);
        List<Get> gets = new ArrayList<Get>();
        for (String row : rows) {
            Get get = new Get(Bytes.toBytes(row));
            get.setMaxVersions();
            if (fams != null) {
                for (String fam : fams) {
                    for (String qual : quals) {
                        get.addColumn(Bytes.toBytes(fam), Bytes.toBytes(qual));
                    }
                }
            }
            gets.add(get);
        }
        Result[] results = tbl.get(gets);
        for (Result result : results) {
            for (KeyValue kv : result.raw()) {
                System.out.println("KV: " + kv + ", Value: " + Bytes.toString(kv.getValue()));
            }
        }
        tbl.close();
    }
}
