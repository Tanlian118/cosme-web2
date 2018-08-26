package com.cosme.controller;

import com.cosme.adapter.ProductAdapter;
import com.cosme.common.PageModel;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.param.ProductListParam;
import com.cosme.web.request.ProductAddRequest;
import com.cosme.web.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-12 16:20
 **/
@RestController
@RequestMapping("cm/product")
public class ProductController extends BaseController{

    @Autowired
    private ProductAdapter productAdapter;

    /**
     * 添加或修改商品信息
     */
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> save(@RequestBody ProductAddRequest productRequest) {
        return productAdapter.addOrUpdate(productRequest);
    }

    /**
     * 删除商品信息
     * @param productIds
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> delete(@RequestBody Set<Integer> productIds) {
        return productAdapter.delete(productIds);
    }

    /**
     * 查询商品信息
     * @param productListParam
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public PageModel<ProductVO> list(ProductListParam productListParam, HttpServletRequest request, HttpServletResponse response) {
        String onlineUserId = getOnlineUserId(request, response);
        productListParam.setOperaterId(onlineUserId);
        return productAdapter.list(productListParam);
    }
}
