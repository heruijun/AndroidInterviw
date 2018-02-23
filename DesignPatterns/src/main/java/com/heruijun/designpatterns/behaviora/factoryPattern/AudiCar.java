package com.heruijun.designpatterns.behaviora.factoryPattern;

/**
 * Created by heruijun on 2018/2/23.
 */

public abstract class AudiCar {

    /**
     * 汽车的抽象产品类
     * 定义汽车的一个行为方法，车可以启动开走
     */
    public abstract void drive();

    /**
     * 汽车的抽象产品类
     * 定义汽车的一个行为方法，车可以自动巡航
     */
    public abstract void selfNavigation();

}
