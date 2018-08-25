package com.cosme.service.impl;

import com.cosme.common.PageModel;
import com.cosme.common.guava2.Lists2;
import com.cosme.common.guava2.Maps2;
import com.cosme.dao.ChannelDAO;
import com.cosme.service.ChannelService;
import com.cosme.service.ProductService;
import com.cosme.transformers.ChannelTransformer;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.common.StateCode;
import com.cosme.web.dto.ChannelDTO;
import com.cosme.web.dto.ProductDTO;
import com.cosme.web.entity.ChannelEntity;
import com.cosme.web.fillingParam.ChannelFillingParam;
import com.cosme.web.queryParam.ChannelQueryParam;
import com.cosme.web.queryParam.ProductQueryParam;
import com.google.common.collect.ArrayListMultimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Tanlian
 * @create 2018-08-23 15:00
 **/
@Service("channelService")
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelDAO channelDAO;
    @Autowired
    private ProductService productService;


    @Override
    public ResultDTO<Void> addOrUpdateChannel(ChannelDTO channelDTO) {
        ChannelEntity channelEntity = ChannelTransformer.DTO_TO_ENTITY.apply(channelDTO);
        if (channelDTO.getChannelId() == null) {
            channelDAO.save(channelEntity);
            return ResultDTO.successfy();
        }
        channelDAO.update(channelEntity);
        return ResultDTO.successfy();
    }

    @Override
    public ResultDTO<Void> deleteChannel(Set<Integer> channelIds) {
        if (CollectionUtils.isEmpty(channelIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "channelIds为空");
        }
        channelDAO.delete(channelIds);
        return ResultDTO.successfy();
    }

    @Override
    public PageModel<ChannelDTO> queryByParam(ChannelQueryParam channelQueryParam) {
        if (channelQueryParam == null) {
            return PageModel.emptyModel();
        }
        List<ChannelEntity> channelEntities = channelDAO.queryByParam(channelQueryParam);
        List<ChannelDTO> channelDTOs = Lists2.transform(channelEntities, ChannelTransformer.ENTITY_TO_DTO);
        ChannelFillingParam fillingParam = channelQueryParam.getFillingParam();
        fillChannelDTODetails(channelDTOs, fillingParam);
        int count = channelDAO.count(channelQueryParam);
        return PageModel.build(channelDTOs, count);
    }

    private void fillChannelDTODetails(List<ChannelDTO> channelDTOs, ChannelFillingParam fillingParam) {
        if (CollectionUtils.isEmpty(channelDTOs) || fillingParam == null) {
            return ;
        }
        if (fillingParam.needProductDTO()) {
            ProductQueryParam productQueryParam = fillingParam.getProductQueryParam();
            getProductInfo(channelDTOs, productQueryParam);
        }

    }

    private void getProductInfo(List<ChannelDTO> channelDTOs, ProductQueryParam productQueryParam) {
        Set<Integer> channelIds = channelDTOs.stream().map(ChannelDTO::getChannelId).collect(Collectors.toSet());
        productQueryParam.setChannelIds(channelIds);
        List<ProductDTO> productDTOs = productService.queryByParam(productQueryParam).getData();
        ArrayListMultimap<Integer, ProductDTO> productDTOAndIdMap = Maps2.newMultimapWithValue(productDTOs, ProductDTO::getChannelId);
        channelDTOs.stream().forEach(v->v.setProductDTOs(productDTOAndIdMap.get(v.getChannelId())));
    }
}
