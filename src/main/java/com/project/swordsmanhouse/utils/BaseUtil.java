package com.project.swordsmanhouse.utils;

import java.util.Collection;
import java.util.Map;

/**
 * *************************************************
 * 基础操作工具类
 * <p>
 * *************************************************
 */
public final class BaseUtil {

    /**
     * 私有构造
     */
    private BaseUtil() {
        super();
    }

    /**
     * 判断集合  不为空
     *
     * @param con Collection<?> 目标集合
     * @return boolean true 表示集合不为空  false 表示集合为空
     * @since 1.0.0
     */
    public static boolean listNotNull(Collection<?> con) {
        return con != null && !con.isEmpty();
    }

    /**
     * 判断集合  为空
     *
     * @param con Collection<?> 目标集合
     * @return boolean true 表示集合为空  false 表示集合不为空
     * @since 1.0.0
     */
    public static boolean listNull(Collection<?> con) {
        return !listNotNull(con);
    }

    /**
     * 判断数组  不为空
     *
     * @param array Object[] 目标数组
     * @return boolean true 表示数组不为空  false 表示数组为空
     * @since 1.0.0
     */
    public static boolean arrayNotNull(Object[] array) {
        if (array != null) {
            return array.length != 0;
        }
        return false;
    }

    /**
     * 判断数组  为空
     *
     * @param array Object[] 目标数组
     * @return boolean true 表示数组为空  false 表示数组不为空
     * @since 1.0.0
     */
    public static boolean arrayNull(Object[] array) {
        return !arrayNotNull(array);
    }

    /**
     * 判断对象  不为空
     *
     * @param obj Object 目标对象
     * @return boolean true 表示对象不为空  false 表示对象为空
     * @since 1.0.0
     */
    public static boolean objectNotNull(Object obj) {
        return obj != null;
    }

    /**
     * 判断对象  为空
     *
     * @param obj Object 目标对象
     * @return boolean true 表示对象为空  false 表示对象不为空
     * @since 1.0.0
     */
    public static boolean objectNull(Object obj) {
        return !objectNotNull(obj);
    }

    /**
     * 判断map 不为空
     *
     * @param map Map<?, ?> 目标map
     * @return boolean true　表示map不为空　false　表示map为空
     * @since 1.0.0
     */
    public static boolean mapNotNull(Map<?, ?> map) {
        return null != map && !map.isEmpty();
    }

    /**
     * 判断map 为空
     *
     * @param map Map<?, ?> 目标map
     * @return boolean true　表示map为空　false　表示map不为空
     * @since 1.0.0
     */
    public static boolean mapNull(Map<?, ?> map) {
        return !mapNotNull(map);
    }

    /**
     * 判断字符串　不为空
     *
     * @param str String 目标字符串
     * @return　boolean　true 表示字符串不为空　false　表示字符串为空
     * @since 1.0.0
     */
    public static boolean stringNotNull(String str) {
        return null != str && !"".equals(str);
    }

    /**
     * 判断字符串　为空
     *
     * @param str String 目标字符串
     * @return　boolean　true 表示字符串为空　false　表示字符串不为空
     * @since 1.0.0
     */
    public static boolean stringNull(String str) {
        return !stringNotNull(str);
    }
}
