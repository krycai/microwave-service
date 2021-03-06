package com.allen.pattern.interpreter;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: pattern
 * @description: AndExpression 接口的实体类  和表达式。实现与文法中的终结符相关的解释操作。实现抽象表达式中所要求的方法。文法中每一个终结符都有一个具体的终结表达式与之相对应。
 * @author: allen小哥
 * @Date: 2019-03-31 18:01
 **/
@Slf4j
public class AndExpression implements Expression {

    private Expression expr1 = null;
    private Expression expr2 = null;

    public AndExpression(Expression expr1,Expression expr2){
        this.expr1 =expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        log.info("执行 and 操作");
        return expr1.interpret(context) && expr2.interpret(context);
    }
}
