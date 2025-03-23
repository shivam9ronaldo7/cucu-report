package com.cucu.report.annotations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Component
public class LogInjector implements BeanPostProcessor {

    /**
     * Return the bean itself.
     *
     * @param bean the new bean instance
     * @param beanName the name of the bean
     * @return
     * @throws BeansException
     */
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        return bean;
    }

    /**
     * For all beans before initialization, inject the logger using log4j.
     *
     * @param bean
     * @param beanName
     * @return returns same bean by injecting logger.
     */
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        ReflectionUtils.doWithFields(bean.getClass(), new ReflectionUtils.FieldCallback() {
            public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
                ReflectionUtils.makeAccessible(field);
                if (field.getAnnotation(Log.class) != null) {
                    if (field.get(bean) == null) {
                        final Logger logger = LogManager.getLogger(bean.getClass());
                        field.set(bean, logger);
                    }
                }
            }
        });
        return bean;
    }

}
