package com.project.swordsmanhouse.utils;

import lombok.Data;

/**
 * 自定义返回结果类
 *
 * @author wy
 * @version 1.0
 */
@Data
public class GenericResult {
    private String code = "0";
    private String msg = "success";
    private String count;
    private Object data;

}
