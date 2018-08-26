package com.cosme.controller;

import com.cosme.adapter.CarouselAdapter;
import com.cosme.common.PageModel;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.param.CarouselListParam;
import com.cosme.web.request.CarouselAddRequest;
import com.cosme.web.vo.CarouselVO;
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
 * @create 2018-08-22 17:01
 **/
@RestController
@RequestMapping("/cm/carousel")
public class CarouselController extends BaseController {

    @Autowired
    private CarouselAdapter carouselAdapter;

    /**
     * 添加或修改轮播图信息
     * @param carouselAddRequest
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> addOrUpdate(@RequestBody CarouselAddRequest carouselAddRequest) {
        return carouselAdapter.addOrUpdate(carouselAddRequest);
    }

    /**
     * 删除轮播图信息
     * @param cosmeImageIds
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDTO<Void> delete(@RequestBody Set<Integer> cosmeImageIds) {
        return carouselAdapter.deleteCarousel(cosmeImageIds);
    }

    /**
     * 查询轮播图信息
     * @param listParam
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public PageModel<CarouselVO> list(CarouselListParam listParam, HttpServletRequest request, HttpServletResponse response) {
        String onlineUserId = getOnlineUserId(request, response);
        listParam.setOperaterId(onlineUserId);
        return carouselAdapter.listCarousel(listParam);
    }

}
