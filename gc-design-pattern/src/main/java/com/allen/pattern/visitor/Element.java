package com.allen.pattern.visitor;

/**
 * @ClassName Element
 * @Description 抽象元素类 它定义了一个接受访问者（accept）的方法，其意义是指，每一个元素都要可以被访问者访问。
 *
 * 定义：封装某些作用于某种数据结构中各元素的操作，它可以在不改变数据结构的前提下定义作用于这些元素的新的操作。
 *
 *
 * 接口或者抽象类，声明接受哪一类访问者访问，程序上是通过accept方法中的参数来定义的。抽象元素一般有两类方法，一部分是本身的业务逻辑，另外就是允许接收哪类访问者来访问。
 *
 * 优点：
 * 符合单一职责原则：凡是适用访问者模式的场景中，元素类中需要封装在访问者中的操作必定是与元素类本身关系不大且是易变的操作，使用访问者模式一方面符合单一职责原则，
 * 另一方面，因为被封装的操作通常来说都是易变的，所以当发生变化时，就可以在不改变元素类本身的前提下，实现对变化部分的扩展。
 * 扩展性良好：元素类可以通过接受不同的访问者来实现对不同操作的扩展。
 *
 * 缺点：
 * 增加新的元素类比较困难，在元素类数目不确定的情况下，应该慎用访问者模式。访问者模式比较适用于对已有功能的重构
 *1、具体元素对访问者公布细节，违反了迪米特原则。
 * 2、具体元素变更比较困难。
 *3、违反了依赖倒置原则，依赖了具体类，没有依赖抽象。
 *
 * @Author allen小哥
 * @Date 2019/4/1 14:56   抽象元素。定义一个Accept操作，它以一个访问者为参数。
 **/
public abstract class Element {

    //声明接受哪一类访问者访问
    public abstract void accept(IVisitor visitor);

    public abstract void doSomething();

}
