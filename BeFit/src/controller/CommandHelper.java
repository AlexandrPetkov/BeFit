package controller;

import java.util.HashMap;
import java.util.Map;

import command.Command;
import command.exception.CommandNotFoundException;
import command.impl.EditPupilData;
import command.impl.EditTrainerData;
import command.impl.EditUserPhoto;
import command.impl.FireTrainer;
import command.impl.GoToMyCard;
import command.impl.GoToSignIn;
import command.impl.GoToSignUpPupil;
import command.impl.GoToSignUpTrainer;
import command.impl.GoToUserCard;
import command.impl.HireTrainer;
import command.impl.MakeOffer;
import command.impl.ShowAllPupils;
import command.impl.ShowAllTrainers;
import command.impl.SignIn;
import command.impl.SignOut;
import command.impl.SignUpPupil;
import command.impl.SignUpTrainer;
import constant.Constants;

public class CommandHelper {
	private static CommandHelper instance;
	private Map<String, Command> commands = new HashMap<>();

	private CommandHelper() {
		commands.put(Constants.COMMAND_GO_TO_SIGN_IN, new GoToSignIn());
		commands.put(Constants.COMMAND_GO_TO_SIGN_UP_PUPIL, new GoToSignUpPupil());
		commands.put(Constants.COMMAND_GO_TO_SIGN_UP_TRAINER, new GoToSignUpTrainer());
		commands.put(Constants.COMMAND_GO_TO_USER_CARD, new GoToUserCard());
		commands.put(Constants.COMMAND_GO_TO_MY_CARD, new GoToMyCard());
		commands.put(Constants.COMMAND_SIGN_IN, new SignIn());
		commands.put(Constants.COMMAND_SIGN_OUT, new SignOut());
		commands.put(Constants.COMMAND_SIGN_UP_PUPIL, new SignUpPupil());
		commands.put(Constants.COMMAND_SIGN_UP_TRAINER, new SignUpTrainer());
		commands.put(Constants.COMMAND_SHOW_ALL_TRAINERS, new ShowAllTrainers());
		commands.put(Constants.COMMAND_SHOW_ALL_PUPILS, new ShowAllPupils());
		commands.put(Constants.COMMAND_HIRE_TRAINER, new HireTrainer());
		commands.put(Constants.COMMAND_FIRE_TRAINER, new FireTrainer());
		commands.put(Constants.COMMAND_MAKE_OFFER, new MakeOffer());
		commands.put(Constants.COMMAND_EDIT_PUPIL_DATA, new EditPupilData());
		commands.put(Constants.COMMAND_EDIT_TRAINER_DATA, new EditTrainerData());
		commands.put(Constants.COMMAND_CHANGE_PHOTO, new EditUserPhoto());

	}

	public static CommandHelper getInstatnce() {
		if (instance == null) {
			instance = new CommandHelper();
			return instance;
		}
		return instance;
	}

	public Command getCommand(String commandName) throws CommandNotFoundException {
		Command command = commands.get(commandName);
		if (command != null)
			return command;
		throw new CommandNotFoundException();
	}
}
