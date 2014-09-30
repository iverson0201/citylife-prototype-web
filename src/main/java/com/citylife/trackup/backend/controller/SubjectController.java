package com.citylife.trackup.backend.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citylife.trackup.backend.common.Utils;
import com.citylife.trackup.backend.common.mapper.BeanMapper;
import com.citylife.trackup.backend.common.web.MediaTypes;
import com.citylife.trackup.backend.domain.result.Result;
import com.citylife.trackup.backend.domain.subject.SpecialReply;
import com.citylife.trackup.backend.domain.subject.Subject;
import com.citylife.trackup.backend.dto.SubjectDto;
import com.citylife.trackup.backend.exception.RestException;
import com.citylife.trackup.backend.service.SpecialReplyService;
import com.citylife.trackup.backend.service.SubjectService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月24日 上午11:50:04
 * 
 * 专题api
 */
@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private SpecialReplyService specialReplyService;

	/**
	 * 发布专题
	 * @param subject
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/publish",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public Result<SubjectDto> create(@RequestBody @Valid Subject subject,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			throw new IllegalArgumentException(Utils.parseErrors(bindingResult.getFieldErrors()));
		}
		Date date = new Date();
		subject.setCreatedAt(date);
		subject.setUpdatedAt(date);
		subjectService.insertSubject(subject);
		Result<SubjectDto> result = transformation(subject);
	    return result;
	}
	/**
	 * 获取某个专题详情
	 * @param publishId
	 * @return
	 */
	@RequestMapping(value = "/{subjectId}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
	public Result<SubjectDto> get(@PathVariable String publishId){
		Subject subjectRet = subjectService.findSubejct(publishId);
		Result<SubjectDto> result = transformation(subjectRet);
	    return result;
	}
	/**
	 * 更新专题信息
	 * @param subject
	 * @return
	 */
	@RequestMapping(value = "/{subjectId}",method = RequestMethod.PUT,consumes = MediaTypes.JSON)
	public Result<SubjectDto> update(@RequestBody Subject subject){
		Subject subjectRet = subjectService.updateSubject(subject.getId(),subject);
		Result<SubjectDto> result = transformation(subjectRet);
	    return result;
	}
	/**
	 * 删除某个专题
	 * @param subjectId
	 * @return
	 */
	@RequestMapping(value = "/{subjectId}",method = RequestMethod.DELETE,consumes = MediaTypes.JSON)
	public String delete(@PathVariable String subjectId){
		subjectService.deleteSubject(subjectId);
		return "{\"code\" : 1}";
	}

	/**
	 * 封装特定的dto对象
	 * @param subject
	 * @return
	 */
	private Result<SubjectDto> transformation(Subject subject) {
		SubjectDto subjectDto = BeanMapper.map(subject, SubjectDto.class);
		Result<SubjectDto> result = new Result<SubjectDto>();
		result.setObj(subjectDto);
		return result;
	}
//	/**
//	 * 回复专题
//	 * @param specialReply
//	 * @return
//	 */
//	@RequestMapping(value = "/{subjectId}/comment",method = RequestMethod.PUT,consumes = MediaTypes.JSON)
//	public Result<SubjectDto> replySubject(@RequestBody SpecialReplyDto specialReplyDto){
//		Subject subject = subjectService.findSubejct(specialReplyDto.getSubjectId());
//		SpecialReply specialReply = specialReplyDto.getSpecialReply();
//		specialReply.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//		specialReply.setCreatedAt(new Date());
//		subject.getSpecialReplies().add(specialReply);
//		subject.setUpdatedAt(new Date());
//		Subject subjectRet = subjectService.updateSubject(specialReplyDto.getSubjectId(), subject);
//		Result<SubjectDto> result = transformation(subjectRet);
//	    return result;
//	}
//	/**
//	 * 删除回复
//	 * @param subjectId
//	 * @param specialReplyId
//	 * @return
//	 */
//	@RequestMapping(value = "/{subjectId}/{specialReplyId}",method = RequestMethod.DELETE,consumes = MediaTypes.JSON)
//	public Result<SubjectDto> deleteReply(@PathVariable String subjectId,@PathVariable String specialReplyId){
//		Subject subjectRet = subjectService.findSubejct(subjectId);
//		List<SpecialReply> specialReplies = subjectRet.getSpecialReplies();
//		if(specialReplies == null || specialReplies.size() == 0){
//			throw new RestException("您没有回复专题：" + subjectRet.getTitle() + "，不能删除。");
//		}
//		boolean flag = false;
//		for (int i = 0; i < specialReplies.size() && !flag; i++) {
//			if(specialReplyId.intern() == specialReplies.get(i).getId().intern()){
//				specialReplies.remove(i);
//				flag = true;
//			}
//		}
//		if(flag){
//			subjectRet.setSpecialReplies(specialReplies);
//			subjectRet.setUpdatedAt(new Date());
//			subjectService.updateSubject(subjectId, subjectRet);
//		}
//		Result<SubjectDto> result = transformation(subjectRet);
//	    return result;
//	}
	/**
	 * 回复专题
	 * @param specialReply
	 * @return
	 */
	@RequestMapping(value = "/{subjectId}/comment",method = RequestMethod.POST,consumes = MediaTypes.JSON)
	public SpecialReply reply(@RequestBody SpecialReply specialReply,@PathVariable String subjectId){
		Date date = new Date();
		specialReply.setCreatedAt(date);
		specialReply.setUpdatedAt(date);
		specialReply.setSubjectId(subjectId);
		specialReplyService.save(specialReply);
	    return specialReply;
	}
	/**
	 * 删除回复
	 * @param specialReplyId
	 * @return
	 */
	@RequestMapping(value = "/delete/replay/{specialReplyId}")
	public String removeReply(@PathVariable String specialReplyId){
		specialReplyService.deleteReply(specialReplyId);
		boolean flag = specialReplyService.findReply(specialReplyId);
		if(flag){
			throw new RestException(specialReplyId + " 不存在.");
		}
		return "{\"code\" : 1}";
	}
}
