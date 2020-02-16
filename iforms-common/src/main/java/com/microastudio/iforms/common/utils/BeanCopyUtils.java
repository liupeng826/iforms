package com.microastudio.iforms.common.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

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
