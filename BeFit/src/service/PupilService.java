package service;

import java.util.List;

import bean.Pupil;
import service.exception.ServiceException;

public interface PupilService {
	Pupil singUpPupil(Pupil pupil, String confPassword) throws ServiceException;

	Pupil getPupil(int id) throws ServiceException;

	List<Pupil> getAllPupils() throws ServiceException;

	void editProfile(Pupil pupil) throws ServiceException;

}