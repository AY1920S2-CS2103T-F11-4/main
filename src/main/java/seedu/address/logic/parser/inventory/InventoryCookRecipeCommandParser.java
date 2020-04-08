package seedu.address.logic.parser.inventory;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.inventory.InventoryCommand;
import seedu.address.logic.commands.inventory.InventoryCookRecipeCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new InventoryCookRecipeCommand object
 */
public class InventoryCookRecipeCommandParser implements Parser<InventoryCookRecipeCommand> {

    private static final Pattern INVENTORY_COOK_RECIPE_COMMAND_ARGUMENT_FORMAT = Pattern
            .compile(" *(?<arguments>.*)");
    /**
     * Parses the given {@code String} of arguments in the context of the InventoryCookRecipeCommand
     * and returns an InventoryCookRecipeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InventoryCookRecipeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        final Matcher matcher = INVENTORY_COOK_RECIPE_COMMAND_ARGUMENT_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InventoryCookRecipeCommand.MESSAGE_USAGE));
        }

        final String arguments = matcher.group("arguments");

        try {
            Index index = ParserUtil.parseIndex(arguments);
            return new InventoryCookRecipeCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX,
                    InventoryCookRecipeCommand.MESSAGE_USAGE));
        }
    }
}
