package com.cosme.transformers;

import com.cosme.common.BaseTransformer;
import com.cosme.common.SafeFunction;
import com.cosme.web.dto.ChannelDTO;
import com.cosme.web.dto.ProductDTO;
import com.cosme.web.entity.ChannelEntity;
import com.cosme.web.vo.ChannelVO;
import com.cosme.web.vo.ProductVO;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Tanlian
 * @create 2018-08-22 21:45
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChannelTransformer extends BaseTransformer {

    public static final SafeFunction<ChannelDTO, ChannelEntity> DTO_TO_ENTITY = input -> convert(input, new ChannelEntity());

    public static final SafeFunction<ChannelEntity, ChannelDTO> ENTITY_TO_DTO = input -> convert(input, new ChannelDTO());

    public static final SafeFunction<ChannelDTO, ChannelVO> DTO_TO_VO = input -> {
        ChannelVO channelVO = convert(input, new ChannelVO());
        List<ProductDTO> productDTOs = input.getProductDTOs();
        if (!CollectionUtils.isEmpty(productDTOs)) {
            List<ProductVO> productVOs = Lists.transform(productDTOs, ProductTransformer.DTO_TO_VO);
            channelVO.setProductVOs(productVOs);
        }
        return channelVO;
    };


    public static final SafeFunction<ChannelDTO, ChannelVO> DTO_TO_SHORT_VO = input -> {
        ChannelVO channelVO = new ChannelVO();
        channelVO.setChannelTitle(input.getChannelTitle());
        channelVO.setJapaneseTitle(input.getJapaneseTitle());
        return convert(input, new ChannelVO());
    };
}
