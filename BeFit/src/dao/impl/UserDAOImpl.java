package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Pupil;
import bean.Trainer;
import bean.User;
import constant.Constants;
import dao.UserDAO;
import dao.connection.CloseDAO;
import dao.connection.ConnectionPool;
import dao.exception.DAOException;

public class UserDAOImpl implements UserDAO {

	@Override
	public User singInUser(String login, String password) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement ps = null;
		Statement st = null;
		ResultSet rs = null;

		Pupil pupil = null;
		Trainer trainer = null;
		User user = null;

		try {
			con = connectionPool.take();
			con.setAutoCommit(false);
			ps = con.prepareStatement(Constants.SELECT_USER_BY_LOGIN_AND_PASWORD);

			ps.setString(1, login.toLowerCase());
			ps.setString(2, password);

			rs = ps.executeQuery();

			while (rs.next()) {

				user = fillUser(rs);
				st = con.createStatement();

				if (user.getisTrainer()) {
					trainer = new Trainer(user);

					rs = st.executeQuery(Constants.SELECT_TRAINER_BY_ID + trainer.getId());

					while (rs.next()) {
						trainer.setExperience_years(rs.getString(2));
						trainer.setSpecialization(rs.getString(3));
						trainer.setPrice(rs.getString(5));
						trainer.setAbout(rs.getString(6));
					}
					user = trainer;

				} else {
					pupil = new Pupil(user);

					rs = st.executeQuery(Constants.SELECT_PUPIL_BY_ID + pupil.getId());

					while (rs.next()) {
						pupil.setHeight_sm(rs.getString(2));
						pupil.setWeight(rs.getString(3));
						pupil.setIdTrainers(rs.getInt(4));
						pupil.setGoal(rs.getString(5));
					}
					user = pupil;
				}
			}

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
			CloseDAO.closeStatement(st);
			CloseDAO.closeResultSet(rs);
		}

		return user;
	}

	private User fillUser(ResultSet rs) throws SQLException {
		User user = new User();

		user.setId(rs.getInt(1));
		user.setTrainer(rs.getBoolean(8));
		user.setLogin(rs.getString(2));
		user.setPassword(rs.getString(3));
		user.setName(rs.getString(4));
		user.setSecondName(rs.getString(5));
		user.setBirthday(rs.getDate(6));
		user.setPhoto(rs.getString(7));
		user.setMale(rs.getBoolean(9));

		return user;
	}
}
