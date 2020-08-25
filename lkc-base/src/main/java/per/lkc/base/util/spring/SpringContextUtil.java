/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package per.lkc.base.util.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
        log.info("set appliction context success:{}", applicationContext);

    }

    /**
     * 通过name获取bean对象
     *
     * @param name bean名称
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * 通过类类型来获取bean对象
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return applicationContext.getBean(clazz);
    }


    /**
     * 获取resource地址
     *
     * @param location
     * @return
     */
    public static Resource getResource(String location) {
        return applicationContext.getResource(location);
    }

}
