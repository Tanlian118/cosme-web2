package com.cosme.adapter;

import com.cosme.common.BaseTransformer;
import com.cosme.common.PageModel;
import com.cosme.common.guava2.Lists2;
import com.cosme.service.ChannelService;
import com.cosme.transformers.ChannelTransformer;
import com.cosme.web.common.FixedPageSizeEnum;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.common.StateCode;
import com.cosme.web.dto.ChannelDTO;
import com.cosme.web.param.ChannelListParam;
import com.cosme.web.queryParam.ChannelQueryParam;
import com.cosme.web.request.ChannelAddRequest;
import com.cosme.web.vo.ChannelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * @author Tanlian
 * @create 2018-08-23 14:49
 **/
@Service("channelAdapter")
public class ChannelAdapter {

    @Autowired
    private ChannelService channelService;

    public ResultDTO<Void> addOrUpdate(ChannelAddRequest channelRequest) {
        ResultDTO<Void> resultDTO = checkParam(channelRequest);
        if (!resultDTO.isSuccess()) {
            return resultDTO;
        }
        ChannelDTO channelDTO = BaseTransformer.convert(channelRequest, new ChannelDTO());
        channelService.addOrUpdateChannel(channelDTO);
        return ResultDTO.successfy();
    }

    private ResultDTO<Void> checkParam(ChannelAddRequest channelRequest) {
        if (channelRequest == null) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入相关信息");
        }
        if (!StringUtils.hasText(channelRequest.getMainImage())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请上传频道主图");
        }
        if (!StringUtils.hasText(channelRequest.getChannelTitle())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入频道标题");
        }
        if (channelRequest.getChannelTitle().length() > 5) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "频道标题需控制在5个字之间！");
        }
        if (!StringUtils.hasText(channelRequest.getJapaneseTitle())) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入日文标题");
        }
        if (channelRequest.getJapaneseTitle().length() > 10) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "标题需控制在10个字之间！");
        }
        if (channelRequest.getWeight() < 0 || channelRequest.getWeight() > 99) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "权重需控制在0-99之间！");
        }
        if (channelRequest.isNeedLinkUrl()) {
            if (channelRequest.getLinkUrl() == null) {
                return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请输入网页链接");
            }
            channelRequest.setLinkUrl("");
        }
        return ResultDTO.successfy();
    }

    public ResultDTO<Void> delete(Set<Integer> channelIds) {
        if (CollectionUtils.isEmpty(channelIds)) {
            return ResultDTO.fail(StateCode.ILLEGAL_ARGS, "请选择要删除的频道信息");
        }
        channelService.deleteChannel(channelIds);
        return ResultDTO.successfy();
    }

    public PageModel<ChannelVO> list(ChannelListParam listParam) {
        if (listParam == null) {
            return PageModel.emptyModel();
        }
        ChannelQueryParam channelQueryParam = new ChannelQueryParam();
        channelQueryParam.setNeedPagination(true);
        int pageSize = FixedPageSizeEnum.getByPageSize(listParam.getPageSize()).getPageSize();
        channelQueryParam.setPageSize(pageSize);
        channelQueryParam.setPage(listParam.getPage() * pageSize);
        PageModel<ChannelDTO> pageModel = channelService.queryByParam(channelQueryParam);
        List<ChannelDTO> channelDTOs = pageModel.getData();
        List<ChannelVO> channelVOs = Lists2.transform(channelDTOs, ChannelTransformer.DTO_TO_VO);
        return PageModel.build(channelVOs, pageModel.getTotalCount(), listParam.getPage(), pageSize);
    }
}
