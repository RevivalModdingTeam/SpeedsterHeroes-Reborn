package com.speedstersreborn.proxy;

public interface IProxy {

    void preInit();

    void init();

    void postInit();

    void registerModelBakeryVariants();
}
