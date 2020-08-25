package com.per.lkc.lucene;

import com.hankcs.hanlp.HanLP;
import org.junit.Test;

/**
 * 描述用途
 * <p>
 * </p>
 * DATE 2020/8/25.
 *
 * @author zhangjunbo.
 */
public class HanlpTest {
    @Test
    public void test() {
        System.out.println(HanLP.segment("你好，欢迎使用hanlp"));
    }
}
