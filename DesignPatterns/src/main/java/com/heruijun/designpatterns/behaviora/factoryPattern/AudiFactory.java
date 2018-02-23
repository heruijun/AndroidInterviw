package com.heruijun.designpatterns.behaviora.factoryPattern;

/**
 * Created by heruijun on 2018/2/23.
 */

public abstract class AudiFactory {

    /**
     * 某车型的工作方法
     *
     * @param clz 具体的SUV型号类型
     * @return 具体型号的SUV车对象
     */
    public abstract <T extends AudiCar> T createAudiCar(Class<T> clz);

}
