package com.citylife.backend.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.backend.common.Constant;
import com.citylife.backend.common.Utils;
import com.citylife.backend.common.mapper.BeanMapper;
import com.citylife.backend.common.web.MediaTypes;
import com.citylife.backend.domain.person.Backer;
import com.citylife.backend.domain.person.Praise;
import com.citylife.backend.domain.result.Result;
import com.citylife.backend.domain.result.Results;
import com.citylife.backend.domain.share.Share;
import com.citylife.backend.domain.share.ShareReply;
import com.citylife.backend.dto.ShareDto;
import com.citylife.backend.dto.ShareReplyDto;
import com.citylife.backend.exception.RestException;
import com.citylife.backend.service.ShareReplyService;
import com.citylife.backend.service.ShareService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年10月11日 下午4:46:44
 * 分享
 */
@RestController
@RequestMapping("/api/v1/share")
public class ShareController {

	@Autowired
	private ShareService shareService;
	@Autowired
	private ShareReplyService shareReplyService;
	/**
	 * 发布分享
	 * @param share
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public Result<ShareDto> create(@RequestBody @Valid Share share,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new IllegalArgumentException(Utils.parseErrors(bindingResult.getFieldErrors()));
		}
		Date date = new Date();
		share.setCreatedAt(date);
		share.setUpdatedAt(date);
		shareService.insertShare(share);
		return extracted(share);
	}
	/**
	 *  获取某个分享详情
	 * @param shareId
	 * @return
	 */
	@RequestMapping(value = "/{shareId}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public Result<ShareDto> detail(@PathVariable String shareId){
		Share share = shareService.getShare(shareId);
		if(share == null){
			throw new RestException(Constant.SHARE_NOT_FOUND);
		}
		return extracted(share);
	}
	
	/**
	 * 更新分享信息
	 * @param share
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT,produces = MediaTypes.JSON_UTF_8)
	public Result<ShareDto> update(@RequestBody Share share){
		Share shareRet = shareService.updateShare(share.getId(),share);
		return extracted(shareRet);
	}
	/**
	 * 删除分享
	 * @param shareId
	 * @return
	 */
	@RequestMapping(value ="/{shareId}",method = RequestMethod.DELETE,produces = MediaTypes.JSON_UTF_8)
	public String delete(@PathVariable String shareId){
		shareService.deleteShare(shareId);
		return "{\"code\" : 1}";
	}
	private Result<ShareDto> extracted(Share share) {
		ShareDto shareDto = BeanMapper.map(share, ShareDto.class);
		Result<ShareDto> result = new Result<ShareDto>();
		result.setObj(shareDto);
		return result;
	}
	/**
	 * 回复分享
	 * @param shareReply
	 * @return
	 */
	@RequestMapping(value = "/reply/{shareId}",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public Result<ShareReplyDto> reply(@RequestBody ShareReply shareReply,@PathVariable String shareId,BindingResult results){
		if (results.hasErrors()) {
            throw new IllegalArgumentException(Utils.parseErrors(results.getFieldErrors()));
        }
		Date date = new Date();
		shareReply.setCreatedAt(date);
		shareReply.setUpdatedAt(date);
		shareReply.setShareId(shareId);
		shareReplyService.insert(shareReply);
		Share share = shareService.getShare(shareId);
		share.setShareReplyCount();
		shareService.updateShare(shareId, share);
		return extractedReply(shareReply);
	}
	/**
	 * 删除回复 
	 * @param replyId
	 * @return
	 */
	@RequestMapping(value = "/reply/{replyId}",method = RequestMethod.DELETE,produces = MediaTypes.JSON_UTF_8)
	public String deleteReply(@PathVariable String replyId){
		shareReplyService.deleteReply(replyId);
		return "{\"code\" : 1}";
	}
	/**
	 *  某个用户的分享列表分页
	 * @param size
	 * @param page
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/{userId}/users",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public Results<ShareDto> getSharesByUserId(@PathVariable String userId,
	@RequestParam(value = "size",required = false,defaultValue = "10") Integer size,
	@RequestParam(value = "page",required = false,defaultValue = "1") int page,
	@RequestParam(value = "sort",required = false,defaultValue = "updateAt") String sort, 
	@RequestParam(value = "order",required = false,defaultValue = "DESC") String order ){
		Assert.isTrue(page > 0, "Page index must be greater than 0");
        Assert.isTrue(size > 0, "Size must be greater than 0");
        Assert.isTrue("DESC".intern() == order.intern() || "ASC".intern() == order.intern(), "The value of order must be 'DESC' or 'ASC'");
        List<Share> shares = shareService.getSharesByUserId(userId,size,page,sort,order);
		List<ShareDto> dtos = BeanMapper.mapList(shares, ShareDto.class);
		Results<ShareDto> results = new Results<ShareDto>();
		results.setList(dtos);
        return results;
	}
	
	@RequestMapping(value = "/{poiId}/pois",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public Results<ShareDto> getSharesByPoiId(@PathVariable String poiId,
	@RequestParam(value = "size",required = false,defaultValue = "10") Integer size,
	@RequestParam(value = "page",required = false,defaultValue = "1") int page,
	@RequestParam(value = "sort",required = false,defaultValue = "updateAt") String sort, 
	@RequestParam(value = "order",required = false,defaultValue = "DESC") String order ){
		Assert.isTrue(page > 0, "Page index must be greater than 0");
        Assert.isTrue(size > 0, "Size must be greater than 0");
        Assert.isTrue("DESC".intern() == order.intern() || "ASC".intern() == order.intern(), "The value of order must be 'DESC' or 'ASC'");
        List<Share> shares = shareService.getSharesByPoiId(poiId,size,page,sort,order);
		List<ShareDto> dtos = BeanMapper.mapList(shares, ShareDto.class);
		Results<ShareDto> results = new Results<ShareDto>();
		results.setList(dtos);
        return results;
	}
	
	/**
	 * 跟帖
	 * @param shareReply
	 * 
	 * @return
	 */
	@RequestMapping(value = "/follow/{shareId}",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public Result<ShareReplyDto> replyFollow(@RequestBody ShareReply shareReply,@PathVariable String shareId,
			@RequestParam String userId,@RequestParam String userName,@RequestParam String headImage,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new IllegalArgumentException(Utils.parseErrors(bindingResult.getFieldErrors()));
		}
		Date date = new Date();
		shareReply.setCreatedAt(date);
		shareReply.setUpdatedAt(date);
		shareReply.setShareId(shareId);
		Backer backer = new Backer(userId,userName,headImage);
		shareReply.setBacker(backer);
		shareReplyService.insert(shareReply);
		Share share = shareService.getShare(shareId);
		share.setShareReplyCount();
		shareService.updateShare(shareId, share);
		return extractedReply(shareReply);
	}
	
	private Result<ShareReplyDto> extractedReply(ShareReply shareReply) {
		ShareReplyDto shareReplyDto = BeanMapper.map(shareReply, ShareReplyDto.class);
		Result<ShareReplyDto> result = new Result<ShareReplyDto>();
		result.setObj(shareReplyDto);
		return result;
	}
	
	/**
	 * 分享点赞 | 取消点赞
	 * @param shareId
	 * @param praise
	 * @param results
	 * @return
	 */
	@RequestMapping(value = "/praise/{shareId}",method = RequestMethod.PUT,consumes = MediaTypes.JSON)
	public Result<ShareDto> test(@PathVariable String shareId, @RequestBody Praise praise,BindingResult results){
		if (results.hasErrors()) {
            throw new IllegalArgumentException(Utils.parseErrors(results.getFieldErrors()));
        }
		Share share = shareService.getShare(shareId);
		boolean flag = false;
		int i = 0;
		List<Praise> praises = share.getPraises();
		if(praises == null || praises.size() == 0){
			flag = true;
		}
		for(; i <= praises.size() - 1 && !flag; i++){
			if(praises.get(i).getUserId().intern() == praise.getUserId().intern()){
				praises.remove(i);
				i = -1;
				break;
			}
		}
		if(i > 0){
			flag = true;
		}
		if(flag){
			praises.add(praise);
		}
		share.setPraises(praises);
		Share shareRet = shareService.updateShare(shareId, share);
		return extracted(shareRet);
	}
	
	/**
	 * 回复列表
	 * @param shareId
	 * @param size
	 * @param page
	 * @return
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "/reply/{shareId}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public Results<ShareReply> replyList(@PathVariable String shareId,
			@RequestParam(value = "size",required = false,defaultValue = "5") int size,
			@RequestParam(value = "page",required = false,defaultValue = "1") int page){
		List<ShareReply> shareReplies = shareReplyService.getShareReplys(shareId,size,page);
		if(shareReplies == null && shareReplies.size() == 0){
			throw new RestException(Constant.NOT_REPALY);
		}
		Results<ShareReply> results = new Results<ShareReply>();
		results.setList(shareReplies);
		return results;
	}

	@RequestMapping(value = "/test",method = RequestMethod.GET,consumes = MediaTypes.JSON_UTF_8)
	public String test(@RequestHeader(value = "nihao") String etag){
		return etag;
	}
}
