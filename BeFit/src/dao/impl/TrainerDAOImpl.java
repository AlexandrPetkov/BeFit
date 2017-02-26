package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Trainer;
import constant.Constants;
import dao.TrainerDAO;
import dao.connection.CloseDAO;
import dao.connection.ConnectionPool;
import dao.exception.DAOException;

public class TrainerDAOImpl implements TrainerDAO {

	@Override
	public Trainer singUpTrainer(Trainer trainer) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = connectionPool.take();
			con.setAutoCommit(false);
			ps = con.prepareStatement(Constants.INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, trainer.getLogin().toLowerCase());
			ps.setString(2, trainer.getPassword());
			ps.setString(3, trainer.getName());
			ps.setString(4, trainer.getSecondName());
			ps.setDate(5, trainer.getBirthday());
			ps.setString(6, trainer.getPhoto());
			ps.setBoolean(7, trainer.isTrainer());
			ps.setBoolean(8, trainer.isMale());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {
				trainer.setId(rs.getInt(1));
			}

			Statement st = con.createStatement();
			st.executeUpdate(String.format(Constants.INSERT_NEW_TRAINER, trainer.getId(), trainer.getExperience_years(), trainer.getSpecialization(), trainer.getPrice(), trainer.getAbout()));

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
			CloseDAO.closePreparedStatement(ps);
		}
		return trainer;
	}

	@Override
	public void editProfile(Trainer trainer) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection con = null;
		Statement st = null;

		try {
			con = connectionPool.take();
			con.setAutoCommit(false);
			st = con.createStatement();
			st.executeUpdate(String.format(Constants.UPDATE_USER, trainer.getName(), trainer.getSecondName(), trainer.getBirthday(), trainer.getId()));

			st.execute(String.format(Constants.UPDATE_TRAINER, trainer.getExperience_years(), trainer.getSpecialization(), trainer.getPrice(), trainer.getAbout(), trainer.getId()));
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
	public List<Trainer> getAllTrainers() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection con = null;
		Trainer trainer = null;
		List<Trainer> trainers = new ArrayList<>();
		Statement statement = null;
		ResultSet rs = null;

		try {
			con = connectionPool.take();
			statement = con.createStatement();
			rs = statement.executeQuery(Constants.SELECT_ALL_TRAINERS);

			while (rs.next()) {
				trainer = fillTrainer(rs);
				trainers.add(trainer);
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

		return trainers;
	}

	@Override
	public void hireTrainer(int idUsers, int idTrainers) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection con = null;
		Statement statement = null;

		try {
			con = connectionPool.take();
			statement = con.createStatement();
			statement.execute(String.format(Constants.UPDATE_PUPIL_WITH_TRAINER, idTrainers, idUsers));

		} catch (InterruptedException e) {
			// logger
			throw new DAOException(e);
		} catch (SQLException e) {
			// logger
			throw new DAOException(e);
		} finally {
			CloseDAO.closeStatement(statement);
		}
	}

	@Override
	public void fireTrainer(int idUsers) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection con = null;
		Statement statement = null;

		try {
			con = connectionPool.take();
			statement = con.createStatement();
			statement.execute(String.format(Constants.UPDATE_PUPIL_WITH_ZERO_TRAINER, idUsers));

			connectionPool.free(con);

		} catch (InterruptedException e) {
			// logger
			throw new DAOException(e);
		} catch (SQLException e) {
			// logger
			throw new DAOException(e);
		} finally {
			CloseDAO.closeStatement(statement);
		}
	}

	@Override
	public Trainer getTrainer(int id) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection con = null;
		Statement statement = null;
		ResultSet rs = null;
		Trainer trainer = null;

		try {
			con = connectionPool.take();
			statement = con.createStatement();
			rs = statement.executeQuery(Constants.SELECT_TRAINER_BY_ID + id);

			while (rs.next()) {
				trainer = fillTrainer(rs);
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

		return trainer;
	}

	private Trainer fillTrainer(ResultSet rs) throws SQLException {
		Trainer trainer = new Trainer();

		Date date = new Date();
		trainer.setId(rs.getInt(1));
		trainer.setExperience_years(rs.getString(2));
		trainer.setSpecialization(rs.getString(3));
		trainer.setPrice(rs.getString(5));
		trainer.setAbout(rs.getString(6));
		trainer.setLogin(rs.getString(8));
		trainer.setName(rs.getString(10));
		trainer.setSecondName(rs.getString(11));
		trainer.setBirthday(rs.getDate(12));
		trainer.setPhoto(rs.getString(13));
		trainer.setTrainer(rs.getBoolean(14));
		trainer.setMale(rs.getBoolean(15));

		date.setTime(date.getTime() - trainer.getBirthday().getTime());
		trainer.setAge(date.getYear() - 70);

		return trainer;
	}

}
