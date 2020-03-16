package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.cookbook.CookbookCommand;
import seedu.address.logic.commands.inventory.InventoryCommand;
import seedu.address.logic.commands.recipe.RecipeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class CookingPapaParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern
            .compile("(?<commandCategory>\\S+)(?<index>\\S+)?(?<commandWord>\\S+)?(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandCategory = matcher.group("commandCategory");
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandCategory) {

        case CookbookCommand.COMMAND_CATEGORY:
            return null;

        case RecipeCommand.COMMAND_CATEGORY:
            return null;

        case InventoryCommand.COMMAND_CATEGORY:
            return null;

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}