package com.heruijun.designpatterns.behaviora.factoryPattern;

/**
 * Created by heruijun on 2018/2/23.
 */

public class AudiQ7 extends AudiCar {

    @Override
    public void drive() {
        System.out.println("Q7 启动啦");
    }

    @Override
    public void selfNavigation() {
        System.out.println("Q7 开始自动巡航");
    }
}
