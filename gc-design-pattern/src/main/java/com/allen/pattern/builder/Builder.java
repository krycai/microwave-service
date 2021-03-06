package com.allen.pattern.builder;

/**
 * @ClassName Builder
 * @Description 装机人员 组装命令：组装电脑的过程   抽象建造者。它声明为创建一个Product对象的各个部件指定的抽象接口/类。
 * @Author Xu
 * @Date 2019/3/20 15:22
 **/
public abstract class Builder {

    // CPU
    public abstract void buildCpu();

    // 主板
    public abstract void buildMainBoard();

    // 显示器
    public abstract void buildView();

    // 硬盘
    public abstract void buildHD();

    //获取电脑
    public abstract Computer getComputer();
}
