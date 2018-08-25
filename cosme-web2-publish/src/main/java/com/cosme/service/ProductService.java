package com.cosme.service;

import com.cosme.common.PageModel;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.dto.ProductDTO;
import com.cosme.web.queryParam.ProductQueryParam;

import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-23 16:25
 **/
public interface ProductService {

    /**
     * 添加或修改商品信息
     * @param productDTO
     */
    ResultDTO<Void> addOrUpdateProduct(ProductDTO productDTO);

    /**
     * 删除商品信息
     * @param productIds
     */
    ResultDTO<Void> deleteProduct(Set<Integer> productIds);

    /**
     * 查询商品信息
     * @param productQueryParam
     * @return
     */
    PageModel<ProductDTO> queryByParam(ProductQueryParam productQueryParam);
}
