package com.cosme.transformers;

import com.cosme.common.BaseTransformer;
import com.cosme.common.SafeFunction;
import com.cosme.web.dto.CarouselDTO;
import com.cosme.web.entity.CarouselEntity;
import com.cosme.web.vo.CarouselVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Tanlian
 * @create 2018-08-22 21:45
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CarouselTransformer extends BaseTransformer {

    public static  final SafeFunction<CarouselDTO, CarouselEntity> DTO_TO_ENTITY = input -> convert(input, new CarouselEntity());

    public static  final SafeFunction<CarouselEntity, CarouselDTO> ENTITY_TO_DTO = input -> convert(input, new CarouselDTO());

    public static  final SafeFunction<CarouselDTO, CarouselVO> DTO_TO_VO = input -> convert(input, new CarouselVO());
}
