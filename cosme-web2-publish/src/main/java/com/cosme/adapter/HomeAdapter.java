package com.cosme.adapter;

import com.cosme.common.guava2.Lists2;
import com.cosme.service.CarouselService;
import com.cosme.service.ChannelService;
import com.cosme.service.NewsService;
import com.cosme.service.ProductService;
import com.cosme.transformers.CarouselTransformer;
import com.cosme.transformers.ChannelTransformer;
import com.cosme.transformers.NewsTransformer;
import com.cosme.transformers.ProductTransformer;
import com.cosme.web.common.FixedPageSizeEnum;
import com.cosme.web.dto.CarouselDTO;
import com.cosme.web.dto.ChannelDTO;
import com.cosme.web.dto.NewsDTO;
import com.cosme.web.dto.ProductDTO;
import com.cosme.web.fillingParam.ChannelFillingParam;
import com.cosme.web.param.NewsListParam;
import com.cosme.web.param.ProductListParam;
import com.cosme.web.queryParam.CarouselQueryParam;
import com.cosme.web.queryParam.ChannelQueryParam;
import com.cosme.web.queryParam.NewsQueryParam;
import com.cosme.web.queryParam.ProductQueryParam;
import com.cosme.web.vo.CarouselVO;
import com.cosme.web.vo.ChannelVO;
import com.cosme.web.vo.NewsDetailVO;
import com.cosme.web.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-24 15:27
 **/
@Service("homeAdapter")
public class HomeAdapter {

    @Autowired
    private ChannelService channelService;
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private ProductService productService;

    public List<CarouselVO> readCarousel() {
        CarouselQueryParam carouselQueryParam = new CarouselQueryParam();
        List<CarouselDTO> carouselDTOs = carouselService.queryCarousel(carouselQueryParam).getData();
        List<CarouselVO> carouselVOs = Lists2.transform(carouselDTOs, CarouselTransformer.DTO_TO_VO);
        return carouselVOs.subList(0, 5);
    }

    public List<ChannelVO> readChannel() {
        ChannelQueryParam channelQueryParam = new ChannelQueryParam();
        List<ChannelDTO> channelDTOs = channelService.queryByParam(channelQueryParam).getData();
        List<ChannelVO> channelVOs = Lists2.transform(channelDTOs, ChannelTransformer.DTO_TO_SHORT_VO);

        return channelVOs;
    }

    public List<ChannelVO> readProduct(Set<Integer> channelIds) {
        ChannelQueryParam queryParam = getChannelQueryParam(channelIds);
        queryParam.setChannelIds(channelIds);
        List<ChannelDTO> channelDTOs = channelService.queryByParam(queryParam).getData();
        List<ChannelVO> channelVOs = Lists2.transform(channelDTOs, ChannelTransformer.DTO_TO_VO);
        for (ChannelVO channelVO : channelVOs) {
            List<ProductVO> productVOs = channelVO.getProductVOs();
            if (!CollectionUtils.isEmpty(productVOs)) {
                if (productVOs.size() > 2) {
                    List<ProductVO> productVOList = productVOs.subList(0, 2);
                    channelVO.setProductVOs(productVOList);
                }
            }
        }
        return channelVOs;
    }

    private ChannelQueryParam getChannelQueryParam(Set<Integer> channelIds) {
        ChannelQueryParam channelQueryParam = new ChannelQueryParam();
        channelQueryParam.setChannelIds(channelIds);
        ChannelFillingParam channelFillingParam = new ChannelFillingParam();
        channelFillingParam.setProductQueryParam(new ProductQueryParam());
        channelQueryParam.setFillingParam(channelFillingParam);
        return channelQueryParam;
    }

    public List<ProductVO> channelProduct(ProductListParam listParam) {
        if (listParam == null) {
            return Collections.emptyList();
        }
        int pageSize = FixedPageSizeEnum.getByPageSize(listParam.getPageSize()).getPageSize();
        if (pageSize == 10) {
            pageSize = 30;
        }
        ProductQueryParam productQueryParam = new ProductQueryParam();
        productQueryParam.setChannelIds(listParam.getChannelIds());
        productQueryParam.setPage(listParam.getPage());
        productQueryParam.setPage(listParam.getPage() * pageSize);
        List<ProductDTO> productDTOs = productService.queryByParam(productQueryParam).getData();
        return Lists2.transform(productDTOs, ProductTransformer.DTO_TO_VO);
    }

    public List<NewsDetailVO> readNews(NewsListParam newsListParam) {
        NewsQueryParam newsQueryParam = new NewsQueryParam();
        newsQueryParam.setNeedPagination(true);
        int pageSize = FixedPageSizeEnum.getByPageSize(newsListParam.getPage()).getPageSize();
        newsQueryParam.setPage(newsListParam.getPage() * pageSize);
        newsQueryParam.setPageSize(pageSize);
        List<NewsDTO> newsDTOs = newsService.queryByParam(newsQueryParam).getData();
        return Lists2.transform(newsDTOs, NewsTransformer.DTO_TO_DETAILS_VO);
    }

    public List<NewsDetailVO> newsDetails() {
        NewsQueryParam newsQueryParam = new NewsQueryParam();
        List<NewsDTO> newsDTOs = newsService.queryByParam(newsQueryParam).getData();
        return Lists2.transform(newsDTOs, NewsTransformer.DTO_TO_DETAILS_VO);
    }
}
