package com.cosme.adapter;

import com.cosme.common.BaseTransformer;
import com.cosme.common.PageModel;
import com.cosme.common.guava2.Lists2;
import com.cosme.service.CarouselService;
import com.cosme.transformers.CarouselTransformer;
import com.cosme.web.common.FixedPageSizeEnum;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.common.StateCode;
import com.cosme.web.dto.CarouselDTO;
import com.cosme.web.param.CarouselListParam;
import com.cosme.web.queryParam.CarouselQueryParam;
import com.cosme.web.request.CarouselAddRequest;
import com.cosme.web.vo.CarouselVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-22 17:03
 **/
@Service("carouselAdapter")
public class CarouselAdapter {

    @Autowired
    private CarouselService carouselService;

    public ResultDTO<Void> addOrUpdate(CarouselAddRequest carouselAddRequest) {

        if (carouselAddRequest == null) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入轮播图信息");
        }
        if (!StringUtils.hasText(carouselAddRequest.getCarouselImage())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请上传轮播图");
        }
        if (carouselAddRequest.getWeight() < 0 || carouselAddRequest.getWeight() > 99) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "权重需控制在0-99之间");
        }
        if (carouselAddRequest.isNeedLinkUrl()) {
            if (!StringUtils.hasText(carouselAddRequest.getLinkUrl())) {
                return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入网页链接");
            }
        }
        CarouselDTO carouselDTO = BaseTransformer.convert(carouselAddRequest, new CarouselDTO());
        carouselService.saveOrUpdate(carouselDTO);
        return ResultDTO.successfy();
    }

    public ResultDTO<Void> deleteCarousel(Set<Integer> cosmeImageIds) {
        if (CollectionUtils.isEmpty(cosmeImageIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请选择要删除的轮播图信息");
        }
        carouselService.deleteCarousel(cosmeImageIds);
        return ResultDTO.successfy();
    }

    public PageModel<CarouselVO> listCarousel(CarouselListParam listParam) {
        if (listParam == null) {
            return PageModel.emptyModel();
        }
        CarouselQueryParam carouselQueryParam = new CarouselQueryParam();
        carouselQueryParam.setNeedPagination(true);
        int pageSize = FixedPageSizeEnum.getByPageSize(listParam.getPageSize()).getPageSize();
        carouselQueryParam.setPageSize(pageSize);
        carouselQueryParam.setPage(listParam.getPage() * pageSize);
        PageModel<CarouselDTO> dtoPageModel = carouselService.queryCarousel(carouselQueryParam);
        List<CarouselDTO> carouselDTOs = dtoPageModel.getData();
        int totalCount = dtoPageModel.getTotalCount();
        List<CarouselVO> carouselVOs = Lists2.transform(carouselDTOs, CarouselTransformer.DTO_TO_VO);




        return PageModel.build(carouselVOs, totalCount,listParam.getPage(),  pageSize);
    }
}
