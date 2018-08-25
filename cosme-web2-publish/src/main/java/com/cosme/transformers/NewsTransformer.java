package com.cosme.transformers;

import com.cosme.common.BaseTransformer;
import com.cosme.common.SafeFunction;
import com.cosme.web.dto.NewsDTO;
import com.cosme.web.entity.NewsEntity;
import com.cosme.web.vo.NewsDetailVO;
import com.cosme.web.vo.NewsVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Tanlian
 * @create 2018-08-22 21:45
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NewsTransformer extends BaseTransformer {

    public static final SafeFunction<NewsDTO, NewsEntity> DTO_TO_ENTITY = input -> convert(input, new NewsEntity());

    public static final SafeFunction<NewsEntity, NewsDTO> ENTITY_TO_DTO = input -> convert(input, new NewsDTO());

    public static final SafeFunction<NewsDTO, NewsVO> DTO_TO_VO = input -> convert(input, new NewsVO());

    public static final SafeFunction<NewsDTO, NewsDetailVO> DTO_TO_DETAILS_VO = input -> convert(input, new NewsDetailVO());

}
