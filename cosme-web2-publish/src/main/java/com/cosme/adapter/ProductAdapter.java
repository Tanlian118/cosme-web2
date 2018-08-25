package com.cosme.adapter;

import com.cosme.common.BaseTransformer;
import com.cosme.common.PageModel;
import com.cosme.common.guava2.Lists2;
import com.cosme.service.ProductService;
import com.cosme.transformers.ProductTransformer;
import com.cosme.web.common.FixedPageSizeEnum;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.common.StateCode;
import com.cosme.web.dto.ProductDTO;
import com.cosme.web.fillingParam.ProductFillingParam;
import com.cosme.web.param.ProductListParam;
import com.cosme.web.queryParam.ChannelQueryParam;
import com.cosme.web.queryParam.ProductQueryParam;
import com.cosme.web.request.ProductAddRequest;
import com.cosme.web.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-23 16:21
 **/
@Service("productAdapter")
public class ProductAdapter {

    @Autowired
    private ProductService productService;

   public  ResultDTO<Void> addOrUpdate(ProductAddRequest productRequest) {
       ResultDTO<Void> resultDTO = checkParam(productRequest);
       if (resultDTO.isSuccess())  {
           return resultDTO;
       }

        ProductDTO productDTO = BaseTransformer.convert(productRequest, new ProductDTO());
        productService.addOrUpdateProduct(productDTO);
        return ResultDTO.successfy();
    }

    private ResultDTO<Void> checkParam(ProductAddRequest productRequest) {
        if (productRequest == null) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入相关信息");
        }
        if (!StringUtils.hasText(productRequest.getMainImage())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请上传商品图片");
        }
        if (!StringUtils.hasText(productRequest.getProductTitle())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入商品标题");
        }
        if (productRequest.getProductTitle().length() > 13) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "商品标题需在13个字以内");
        }
        if (!StringUtils.hasText(productRequest.getJapaneseTitle())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入日文标题");
        }
        if (productRequest.getJapaneseTitle().length() > 20) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "日文标题需在20个字以内");
        }
        Integer channelId = productRequest.getChannelId();
        if (channelId == null || channelId <= 0) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请选择商品频道");
        }
        if (!StringUtils.hasText(productRequest.getEffect())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入产品功效");
        }
        if (productRequest.getEffect().length() > 13) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "产品功效需在13个字以内");
        }
        if (!StringUtils.hasText(productRequest.getBarCode())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入条形码");
        }
        return ResultDTO.successfy();
    }

    public  ResultDTO<Void> delete(Set<Integer> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请选择要删除的商品信息");
        }
        productService.deleteProduct(productIds);
        return ResultDTO.successfy();
    }

   public PageModel<ProductVO> list(ProductListParam listParam){
        if (listParam == null) {
            return PageModel.emptyModel();
        }
       ProductQueryParam productQueryParam = getProductQueryParam(listParam);
       PageModel<ProductDTO> pageModel = productService.queryByParam(productQueryParam);
        List<ProductDTO> productDTOs = pageModel.getData();
       List<ProductVO> productVOs = Lists2.transform(productDTOs, ProductTransformer.DTO_TO_VO);
        return PageModel.build(productVOs, pageModel.getTotalCount(), pageModel.getPage(), pageModel.getPageSize());
    }

    private ProductQueryParam getProductQueryParam(ProductListParam listParam) {
        ProductQueryParam productQueryParam = new ProductQueryParam();
        productQueryParam.setChannelIds(listParam.getChannelIds());
        productQueryParam.setProductTitle(listParam.getProductTitle());
        productQueryParam.setNeedPagination(true);
        int pageSize = FixedPageSizeEnum.getByPageSize(listParam.getPageSize()).getPageSize();
        productQueryParam.setPageSize(pageSize);
        productQueryParam.setPage(listParam.getPage() * pageSize);
        ProductFillingParam fillingParam = new ProductFillingParam();
        fillingParam.setChannelQueryParam(new ChannelQueryParam());
        productQueryParam.setFillingParam(fillingParam);
        return productQueryParam;
    }
}
