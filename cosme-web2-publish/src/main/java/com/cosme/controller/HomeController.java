package com.cosme.controller;

import com.cosme.adapter.HomeAdapter;
import com.cosme.web.param.NewsListParam;
import com.cosme.web.param.ProductListParam;
import com.cosme.web.vo.CarouselVO;
import com.cosme.web.vo.ChannelVO;
import com.cosme.web.vo.NewsDetailVO;
import com.cosme.web.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-24 15:26
 **/
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeAdapter homeAdapter;

    /**
     * 读取轮播图列表
     *
     * @return
     */
    @RequestMapping(value = "readCarousel", method = RequestMethod.GET)
    public List<CarouselVO> readCarousel() {
        return homeAdapter.readCarousel();
    }

    /**
     * 读取频道信息
     * @return
     */
    @RequestMapping(value = "readChannel", method = RequestMethod.GET)
    public List<ChannelVO> readChannel() {
        return homeAdapter.readChannel();
    }

    /**
     * 查询权重最高的商品
     *
     * @param channelIds
     * @return
     */
    @RequestMapping(value = "product", method = RequestMethod.GET)
    public List<ChannelVO> readProduct(@RequestParam Set<Integer> channelIds) {
        return homeAdapter.readProduct(channelIds);
    }

    /**
     * 读取频道对应商品信息
     * @return
     */
    @RequestMapping(value = "channelProduct", method = RequestMethod.GET)
    public List<ProductVO> readProduct(ProductListParam listParam) {
        return homeAdapter.channelProduct(listParam);
    }

    /**
     * 读取新闻列表
     *
     * @return
     */
    @RequestMapping(value = "readNews", method = RequestMethod.GET)
    public List<NewsDetailVO> readNews(NewsListParam newsListParam) {
        return homeAdapter.readNews(newsListParam);
    }

    /**
     * 读取新闻详情页
     *
     * @return
     */
    @RequestMapping(value = "newsDetails", method = RequestMethod.GET)
    public List<NewsDetailVO> readNewsDetails() {
        return homeAdapter.newsDetails();
    }

}
