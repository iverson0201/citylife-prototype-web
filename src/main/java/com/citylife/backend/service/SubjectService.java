package com.citylife.backend.service;

import com.citylife.backend.domain.subject.Subject;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月24日 下午1:31:38
 */
public interface SubjectService {

	void insertSubject(Subject subject);

	Subject findSubejct(String publishId);

	Subject updateSubject(String id, Subject subject);

	void deleteSubject(String subjectId);

}
