package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Pupil;
import constant.Constants;
import dao.PupilDAO;
import dao.connection.CloseDAO;
import dao.connection.ConnectionPool;
import dao.exception.DAOException;

public class PupilDAOImpl implements PupilDAO {

	@Override
	public Pupil singUpPupil(Pupil pupil) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement st = null;

		try {
			con = connectionPool.take();
			con.setAutoCommit(false);
			ps = con.prepareStatement(Constants.INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, pupil.getLogin().toLowerCase());
			ps.setString(2, pupil.getPassword());
			ps.setString(3, pupil.getName());
			ps.setString(4, pupil.getSecondName());
			ps.setDate(5, pupil.getBirthday());
			ps.setString(6, pupil.getPhoto());
			ps.setBoolean(7, pupil.isTrainer());
			ps.setBoolean(8, pupil.isMale());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();

			while (rs.next()) {
				pupil.setId(rs.getInt(1));
			}

			st = con.createStatement();
			st.execute(String.format(Constants.INSERT_NEW_PUPIL, pupil.getId(), pupil.getHeight_sm(), pupil.getWeight()));
			con.commit();

			connectionPool.free(con);
		} catch (InterruptedException e) {
			// logger
			throw new DAOException(e);
		} catch (SQLException e) {
			// logger
			try {
				con.rollback();
			} catch (SQLException e1) {
				// logger
				throw new DAOException(e1);
			}
			throw new DAOException(e);
		} finally {
			CloseDAO.closeResultSet(rs);
			CloseDAO.closePreparedStatement(ps);
			CloseDAO.closeStatement(st);
		}

		return pupil;
	}

	@Override
	public List<Pupil> getPupilsByIdTrainers(int idTrainers) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet rs = null;
		Pupil pupil = null;
		List<Pupil> pupils = new ArrayList<>();

		try {
			con = connectionPool.take();
			statement = con.createStatement();
			rs = statement.executeQuery(String.format(Constants.SELECT_ALL_PUPILS_BY_ID_TRAINER, idTrainers));

			while (rs.next()) {

				pupil = fillPupil(rs);

				pupils.add(pupil);
			}

			connectionPool.free(con);

		} catch (InterruptedException e) {
			// logger
			throw new DAOException(e);
		} catch (SQLException e) {
			// logger
			throw new DAOException(e);
		} finally {
			CloseDAO.closeStatement(statement);
			CloseDAO.closeResultSet(rs);
		}

		return pupils;
	}

	@Override
	public void editProfile(Pupil pupil) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection con = null;
		Statement st = null;

		try {
			con = connectionPool.take();
			con.setAutoCommit(false);
			st = con.createStatement();
			st.executeUpdate(String.format(Constants.UPDATE_USER, pupil.getName(), pupil.getSecondName(), pupil.getBirthday(), pupil.getId()));

			st.execute(String.format(Constants.UPDATE_PUPIL, pupil.getHeight_sm(), pupil.getWeight(), pupil.getGoal(), pupil.getId()));
			con.commit();

			connectionPool.free(con);
		} catch (InterruptedException e) {
			// logger
			throw new DAOException(e);
		} catch (SQLException e) {
			// logger
			try {
				con.rollback();
			} catch (SQLException e1) {
				// logger
				throw new DAOException(e1);
			}
			throw new DAOException(e);
		} finally {
			CloseDAO.closeStatement(st);
		}
	}

	@Override
	public Pupil getPupil(int id) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Statement statement = null;
		ResultSet rs = null;

		Connection con = null;
		Pupil pupil = null;
		try {
			con = connectionPool.take();
			statement = con.createStatement();
			rs = statement.executeQuery(Constants.SELECT_PUPIL_BY_ID + id);
			pupil = new Pupil();

			while (rs.next()) {

				pupil = fillPupil(rs);
			}

			connectionPool.free(con);

		} catch (InterruptedException e) {
			// logger
			throw new DAOException(e);
		} catch (SQLException e) {
			// logger
			throw new DAOException(e);
		} finally {
			CloseDAO.closeStatement(statement);
			CloseDAO.closeResultSet(rs);
		}

		return pupil;
	}

	private Pupil fillPupil(ResultSet rs) throws SQLException {
		Pupil pupil = null;
		Date date = new Date();

		pupil = new Pupil();
		pupil.setId(rs.getInt(1));
		pupil.setHeight_sm(rs.getString(2));
		pupil.setWeight(rs.getString(3));
		pupil.setIdTrainers(rs.getInt(4));
		pupil.setGoal(rs.getString(5));
		pupil.setLogin(rs.getString(7));

		pupil.setName(rs.getString(9));
		pupil.setSecondName(rs.getString(10));
		pupil.setBirthday(rs.getDate(11));
		pupil.setPhoto(rs.getString(12));
		pupil.setTrainer(rs.getBoolean(13));
		pupil.setMale(rs.getBoolean(14));

		date.setTime(date.getTime() - pupil.getBirthday().getTime());
		pupil.setAge(date.getYear() - 70);

		return pupil;
	}
}
