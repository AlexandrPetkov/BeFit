package dao;

import java.util.List;

import bean.Pupil;
import dao.exception.DAOException;

public interface PupilDAO {
	Pupil singUpPupil(Pupil pupil) throws DAOException;

	List<Pupil> getAllPupils() throws DAOException;

	Pupil editProfile(Pupil pupil) throws DAOException;

	Pupil getPupil(int id) throws DAOException;

}
