package com.citylife.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylife.backend.dao.SubjectDao;
import com.citylife.backend.domain.subject.Subject;
import com.citylife.backend.service.SubjectService;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月24日 下午1:32:06
 */
@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectDao subjectDao;
	@Override
	public void insertSubject(Subject subject) {
		subjectDao.insert2Mongo(subject);
	}
	@Override
	public Subject findSubejct(String publishId) {
		return subjectDao.findByIdFromMongo(publishId);
	}
	@Override
	public Subject updateSubject(String id, Subject subject) {
		return subjectDao.update2Mongo(id, subject);
	}
	@Override
	public void deleteSubject(String subjectId) {
		subjectDao.delete2Mongo(subjectId);
	}

}
