package com.cosme.service.impl;

import com.cosme.common.BaseTransformer;
import com.cosme.common.PageModel;
import com.cosme.common.guava2.Lists2;
import com.cosme.dao.CarouselDAO;
import com.cosme.service.CarouselService;
import com.cosme.transformers.CarouselTransformer;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.common.StateCode;
import com.cosme.web.dto.CarouselDTO;
import com.cosme.web.entity.CarouselEntity;
import com.cosme.web.queryParam.CarouselQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-22 17:12
 **/
@Service("carouselService")
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselDAO carouselDAO;

    @Override
    public ResultDTO<Void> saveOrUpdate(CarouselDTO carouselDTO) {
        CarouselEntity carouselEntity = BaseTransformer.convert(carouselDTO, new CarouselEntity());
        if (carouselEntity.getCosmeImageId() == null) {
            carouselDAO.save(carouselEntity);
            return ResultDTO.successfy();
        }
        carouselDAO.update(carouselEntity);
        return ResultDTO.successfy();
    }

    @Override
    public ResultDTO<Void> deleteCarousel(Set<Integer> cosmeImageIds) {
        if (CollectionUtils.isEmpty(cosmeImageIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "carouselIds为空");
        }
        carouselDAO.delete(cosmeImageIds);
        return ResultDTO.successfy();
    }

    @Override
    public PageModel<CarouselDTO> queryCarousel(CarouselQueryParam queryParam) {
        if (queryParam == null) {
            return PageModel.emptyModel();
        }
        int count = carouselDAO.count(queryParam);
        List<CarouselEntity> carouselEntities = carouselDAO.queryCarousel(queryParam);
        List<CarouselDTO> carouselDTOs = Lists2.transform(carouselEntities, CarouselTransformer.ENTITY_TO_DTO);
        return PageModel.build(carouselDTOs, count);
        }
    }
