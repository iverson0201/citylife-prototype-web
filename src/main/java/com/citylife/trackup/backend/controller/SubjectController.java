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
import com.citylife.trackup.backend.domain.subject.Subject;
import com.citylife.trackup.backend.dto.SubjectDto;
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
	@RequestMapping(value = "/{publishId}",method = RequestMethod.GET,produces = MediaTypes.JSON_UTF_8)
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
	@RequestMapping(value = "/{publishId}",method = RequestMethod.PUT,consumes = MediaTypes.JSON)
	public Result<SubjectDto> update(@PathVariable Subject subject){
		Subject subjectRet = subjectService.updateSubject(subject.getId(),subject);
		Result<SubjectDto> result = transformation(subjectRet);
	    return result;
	}
	/**
	 * 删除某个专题
	 * @param subjectId
	 * @return
	 */
	@RequestMapping(value = "/{publishId}",method = RequestMethod.DELETE,consumes = MediaTypes.JSON)
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
}
