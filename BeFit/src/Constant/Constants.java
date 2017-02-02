package Constant;

public class Constants {

	// session and request parameters
	public static final String PARAM_ENCODING = "UTF-8";
	public static final String PARAM_URL = "url";
	public static final String PARAM_COMMAND = "command";
	public static final String PARAM_LOGIN = "login";
	public static final String PARAM_PASSWORD = "password";
	public static final String PARAM_PASSWORD_CONFIRMATION = "confPassword";
	public static final String PARAM_NAME = "name";
	public static final String PARAM_SECOND_NAME = "secondName";
	public static final String PARAM_AGE = "age";
	public static final String PARAM_IS_MALE = "isMale";
	public static final String PARAM_HEIGHT = "height_sm";
	public static final String PARAM_WEIGHT = "weight";
	public static final String PARAM_GOAL = "goal";
	public static final String PARAM_PHOTO = "no photo";
	public static final String PARAM_EXPERIENCE = "experience_years";
	public static final String PARAM_SPECIALIZATION = "specialization";
	public static final String PARAM_PRICE = "price";
	public static final String PARAM_ABOUT = "about";
	public static final String PARAM_IS_LOGGED = "isLogged";
	public static final String PARAM_ERROR_TEXT = "errorText";
	public static final String PARAM_USER = "user";
	public static final String PARAM_USERS = "users";
	public static final String PARAM_ID = "id";
	public static final String PARAM_IS_TRAINER = "isTrainer";
	public static final String PARAM_ID_TRAINER = "idTrainers";
	public static final String PARAM_ID_PUPIL = "idPupils";

	// commands
	public static final String COMMAND_GO_TO_SIGN_IN = "GoToSignIn";
	public static final String COMMAND_GO_TO_SIGN_UP_PUPIL = "GoToSignUpPupil";
	public static final String COMMAND_GO_TO_SIGN_UP_TRAINER = "GoToSignUpTrainer";
	public static final String COMMAND_GO_TO_USER_CARD = "goToUserCard";
	public static final String COMMAND_SIGN_IN = "SignIn";
	public static final String COMMAND_SIGN_OUT = "SignOut";
	public static final String COMMAND_SIGN_UP_PUPIL = "SignUpPupil";
	public static final String COMMAND_SIGN_UP_TRAINER = "SignUpTrainer";
	public static final String COMMAND_SHOW_ALL_TRAINERS = "ShowAllTrainers";
	public static final String COMMAND_SHOW_ALL_PUPILS = "ShowAllPupils";
	public static final String COMMAND_HIRE_TRAINER = "HireTrainer";
	public static final String COMMAND_FIRE_TRAINER = "FireTrainer";
	public static final String COMMAND_MAKE_OFFER = "MakeOffer";

	// pages
	public static final String PAGE_INDEX = "/index";
	public static final String PAGE_ERROR = "/error";
	public static final String PAGE_SIGN_IN = "/signin";
	public static final String PAGE_SIGN_UP_PUPIL = "/signUpPupil";
	public static final String PAGE_SIGN_UP_TRAINER = "/signUpTrainer";
	public static final String PAGE_All_TRAINERS = "/allTrainers";
	public static final String PAGE_All_PUPILS = "/allPupils";
	public static final String PAGE_PUPILS_CARD = "/pupilsCard";
	public static final String PAGE_TRAINERS_CARD = "/trainersCard";

	// DB parameters
	public static final String DB_BUNDLE = "resources/DB";
	public static final String DB_USER = "user";
	public static final String DB_URL = "url";
	public static final String DB_DRIVER = "driver";
	public static final String DB_PASSWORD = "password";
	public static final String DB_POOLSIZE = "poolsize";

	// DB queries (select)
	public static final String SELECT_USER_BY_LOGIN_AND_PASWORD = "SELECT * FROM Users WHERE Account = ? AND Password = ?";
	public static final String SELECT_TRAINER_BY_ID = "SELECT * FROM Trainers, Users WHERE Users.idUsers = Trainers.idUsers AND Users.idUsers = ";
	public static final String SELECT_PUPIL_BY_ID = "SELECT * FROM Pupils, Users WHERE Users.idUsers = Pupils.idUsers AND Users.idUsers = ";
	public static final String SELECT_ALL_PUPILS_WITHOUT_TRAINER = "SELECT * FROM Pupils, Users Where Pupils.idTrainers = 0 AND Pupils.idUsers = Users.idUsers";
	public static final String SELECT_ALL_TRAINERS = "SELECT * FROM Trainers, Users Where Trainers.idUsers = Users.idUsers";

	// DB queries (insert & update)
	public static final String INSERT_NEW_USER = "INSERT INTO Users (Account, Password, Name, Second_name, Age, Photo, isTrainer, isMale) VALUES (?,?,?,?,?,?,?,?)";
	public static final String INSERT_NEW_PUPIL = "INSERT INTO Pupils (idUsers, Heigth_sm, Weight) VALUES (%d, '%s', '%s')";
	public static final String INSERT_NEW_TRAINER = "INSERT INTO Trainers (idUsers, Experience_years, Specialization, Price, About) VALUES (%d, '%s', '%s', '%s', '%s')";
	public static final String UPDATE_PUPIL_WITH_ZERO_TRAINER = "UPDATE Pupils SET idTrainers=0 WHERE idUsers=%d";
	public static final String UPDATE_PUPIL_WITH_TRAINER = "UPDATE Pupils SET idTrainers=%d WHERE idUsers=%d";

	// Exception messages
	public static final String CONNECTION_POOL_IS_NULL = "Connection is null";
	public static final String NO_SUCH_USER = "incorrect email or password. Please, try again.";
	public static final String NEED_TO_LOGIN = "Sorry, but you need to authorize first. Please, sign in.";
	public static final String INCORRECT_REGISTRATION_DATA = "не получилось вас зарегистрировать. Что-то вы ввели неправильно, посмотрите, чтобы в полях были указаны корректные данные. Например в поле возраст было только число от 10 до 99 и т.д.";
	public static final String EMPTY_LOGIN_OR_PASSWORD = "login or password is was empty. Please, try again";
	public static final String SQL_EXCEPTION_MESSAGE = "Something wrong with DB: ";
	public static final String INTERRUPTED_EXCEPTION_MESSAGE = "Something wrong with internet: ";
	public static final String CANT_FIRE_TRAINER = "Sorry, but something got wrong when we try to fire this trainer. Please, try again.";
	public static final String CANT_HIRE_TRAINER = "Sorry, but something got wrong when we try to hire this trainer. Please, try again.";
	public static final String CANT_MAKE_OFFER = "Sorry, but something got wrong when we try to make offer to this pupil. Please, try again.";
	public static final String NOT_A_PUPIL = "Sorry, but we can't do this, cause you are not a pupil";
	public static final String NOT_A_TRAINER = "Sorry, but we can't do this, cause you are not a trainer";
	public static final String CANT_GO_TO_CABINET = "Sorry, didn't get to personal page((. Something wrong with user's id";
}
