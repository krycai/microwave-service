package com.allen.algorithm.link;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xuguocai on 2020/12/29 10:38  环形链表测试类
 */
public class LinkLoopTest {
    /**
     * 无环
     * @throws Exception
     */
    @Test
    public void isLoop() throws Exception {
        LinkLoop.Node node3 = new LinkLoop.Node("3");
        LinkLoop.Node node2 = new LinkLoop.Node("2") ;
        LinkLoop.Node node1 = new LinkLoop.Node("1") ;

        node1.next = node2 ;
        node2.next = node3 ;

        LinkLoop linkLoop = new LinkLoop() ;
        boolean loop = linkLoop.isLoop(node1);
        System.out.println("状态："+loop);
        Assert.assertEquals(loop,false);
    }

    /**
     * 有环
     * @throws Exception
     */
    @Test
    public void isLoop2() throws Exception {
        LinkLoop.Node node3 = new LinkLoop.Node("3");
        LinkLoop.Node node2 = new LinkLoop.Node("2") ;
        LinkLoop.Node node1 = new LinkLoop.Node("1") ;

        node1.next = node2 ;
        node2.next = node3 ;
        node3.next = node1 ;

        LinkLoop linkLoop = new LinkLoop() ;
        boolean loop = linkLoop.isLoop(node1);
        System.out.println("状态："+loop);
        Assert.assertEquals(loop,true);
    }

    /**
     * 无环
     * @throws Exception
     */
    @Test
    public void isLoop3() throws Exception {
        LinkLoop.Node node2 = new LinkLoop.Node("2") ;
        LinkLoop.Node node1 = new LinkLoop.Node("1") ;

        node1.next = node2 ;


        LinkLoop linkLoop = new LinkLoop() ;
        boolean loop = linkLoop.isLoop(node1);
        System.out.println("状态："+loop);
        Assert.assertEquals(loop,false);
    }
}
