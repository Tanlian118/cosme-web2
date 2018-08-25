package com.cosme.transformers;

import com.cosme.common.BaseTransformer;
import com.cosme.common.SafeFunction;
import com.cosme.web.dto.ProductDTO;
import com.cosme.web.entity.ProductEntity;
import com.cosme.web.vo.ProductVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Tanlian
 * @create 2018-08-22 21:45
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductTransformer extends BaseTransformer {

    public static final SafeFunction<ProductDTO, ProductEntity> DTO_TO_ENTITY = input -> convert(input, new ProductEntity());

    public static final SafeFunction<ProductEntity, ProductDTO> ENTITY_TO_DTO = input -> convert(input, new ProductDTO());

    public static final SafeFunction<ProductDTO, ProductVO> DTO_TO_VO = input -> convert(input, new ProductVO());
}
