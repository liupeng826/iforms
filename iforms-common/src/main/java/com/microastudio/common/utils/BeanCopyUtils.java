package com.microastudio.common.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author peng
 */
public class BeanCopyUtils extends BeanUtils {

    public static void copyProperties(Object dest, Object orig) {
        if(orig == null) {
            return;
        }
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }

}
