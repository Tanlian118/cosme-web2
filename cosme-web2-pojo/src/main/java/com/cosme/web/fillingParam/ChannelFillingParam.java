package com.cosme.web.fillingParam;

import com.cosme.web.queryParam.ProductQueryParam;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Tanlian
 * @create 2018-08-23 22:54
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChannelFillingParam {

    /**
     * 填充商品信息
     */
    ProductQueryParam productQueryParam;

    public boolean needProductDTO() {
        return productQueryParam != null;
    }
}
