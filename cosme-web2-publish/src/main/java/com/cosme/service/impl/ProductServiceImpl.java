package com.cosme.service.impl;

import com.cosme.common.BaseTransformer;
import com.cosme.common.PageModel;
import com.cosme.common.guava2.Lists2;
import com.cosme.dao.ProductDAO;
import com.cosme.service.ChannelService;
import com.cosme.service.ProductService;
import com.cosme.transformers.ProductTransformer;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.common.StateCode;
import com.cosme.web.dto.ChannelDTO;
import com.cosme.web.dto.ProductDTO;
import com.cosme.web.entity.ProductEntity;
import com.cosme.web.fillingParam.ProductFillingParam;
import com.cosme.web.queryParam.ChannelQueryParam;
import com.cosme.web.queryParam.ProductQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Tanlian
 * @create 2018-08-23 16:25
 **/
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productsDAO;
    @Autowired
    private ChannelService channelService;

    @Override
    public ResultDTO<Void> addOrUpdateProduct(ProductDTO productDTO) {
        ProductEntity productEntity = BaseTransformer.convert(productDTO, new ProductEntity());
        if (productEntity.getProductId() == null) {
            productsDAO.save(productEntity);
            return ResultDTO.successfy();
        }
        productsDAO.update(productEntity);
        return ResultDTO.successfy();


    }

    @Override
    public ResultDTO<Void> deleteProduct(Set<Integer> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "productIds is empty");
        }
        productsDAO.delete(productIds);
        return ResultDTO.successfy();
    }

    @Override
    public PageModel<ProductDTO> queryByParam(ProductQueryParam queryParam) {
        if (queryParam == null) {
            return PageModel.emptyModel();
        }
        List<ProductEntity> productEntities = productsDAO.queryByParam(queryParam);
        List<ProductDTO> productDTOs = Lists2.transform(productEntities, ProductTransformer.ENTITY_TO_DTO);
        int count = productsDAO.count(queryParam);
        ProductFillingParam fillingParam = queryParam.getFillingParam();
        fillProductDetails(productDTOs, fillingParam);
        return PageModel.build(productDTOs, count, queryParam.getPage(), queryParam.getPageSize());

    }

    private void fillProductDetails(List<ProductDTO> productDTOs, ProductFillingParam fillingParam) {
        if (CollectionUtils.isEmpty(productDTOs) || fillingParam == null) {
            return;
        }
        if (fillingParam.needChannelDTO()) {
            ChannelQueryParam channelQueryParam = fillingParam.getChannelQueryParam();
            fillingChannelInfo(productDTOs, channelQueryParam);
        }
    }

    private void fillingChannelInfo(List<ProductDTO> productDTOs, ChannelQueryParam queryParam) {
        Set<Integer> channelIds = productDTOs.stream()
                .map(ProductDTO::getChannelId).collect(Collectors.toSet());
        queryParam.setChannelIds(channelIds);
        List<ChannelDTO> channelDTOs = channelService.queryByParam(queryParam).getData();
        Map<Integer, String> channelIdAndTitleMap = channelDTOs.stream()
                .collect(Collectors.toMap(ChannelDTO::getChannelId, ChannelDTO::getChannelTitle));
        productDTOs.stream()
                .forEach(v -> v.setChannelTitle(channelIdAndTitleMap.get(v.getChannelId())));
    }
}
