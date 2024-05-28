package com.ptt.pojo.vo;

import lombok.Data;

/**
 * ClassName: PortalVo
 * Package: com.ptt.pojo.vo
 * Description:
 *
 * @Author ptt
 * @Create 2024/3/26 15:06
 * @Version 1.0
 */
@Data
public class PortalVo {
    private String keyWords;
    private Integer type=0;
    private Integer pageNum=1;
    private Integer pageSize=10;

}
