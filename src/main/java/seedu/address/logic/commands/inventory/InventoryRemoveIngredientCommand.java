package seedu.address.logic.commands.inventory;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT_QUANTITY;

import java.util.Optional;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.ingredient.IngredientQuantity;
import seedu.address.model.ingredient.exceptions.NonPositiveIngredientQuantityException;

/**
 * Creates an InventoryRemoveIngredientCommand to remove an ingredient with the specified {@code IngredientName} and
 * {@code IngredientQuantity} (if any) from the inventory
 */
public class InventoryRemoveIngredientCommand extends InventoryCommand {

    public static final String COMMAND_WORD = "remove ingredient";
    public static final String MESSAGE_SUCCESS = "%1$s removed from inventory";
    public static final String MESSAGE_INGREDIENT_QUANTITY_TOO_HIGH = "The quantity specified is too large";
    public static final String MESSAGE_USAGE = COMMAND_CATEGORY + " " + COMMAND_WORD
            + ": This commands allows you to remove ingredients to your inventory.\n"
            + "Parameters for removing an ingredient into your inventory is as follows: \n"
            + PREFIX_INGREDIENT_NAME + "INGREDIENT "
            + "[" + PREFIX_INGREDIENT_QUANTITY + "QUANTITY]\n"
            + "Examples:\n"
            + COMMAND_CATEGORY + " " + COMMAND_WORD + " "
            + PREFIX_INGREDIENT_NAME + "Eggs\n"
            + COMMAND_CATEGORY + " " + COMMAND_WORD + " "
            + PREFIX_INGREDIENT_NAME + "Eggs " + PREFIX_INGREDIENT_QUANTITY + "10";

    private final IngredientName ingredientName;
    private final Optional<IngredientQuantity> ingredientQuantity;

    /**
     * Creates a InventoryAddIngredientCommand to add the specified {@code Ingredient} to the inventory
     */
    public InventoryRemoveIngredientCommand(IngredientName ingredientName,
            Optional<IngredientQuantity> ingredientQuantity) {
        requireAllNonNull(ingredientName, ingredientQuantity);
        this.ingredientName = ingredientName;
        this.ingredientQuantity = ingredientQuantity;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            ingredientQuantity.map(x -> new Ingredient(ingredientName, x))
                    .ifPresentOrElse(model::removeInventoryIngredient, () ->
                        model.removeInventoryIngredient(ingredientName));

            String ingredientRemoved = ingredientQuantity.map(x -> new Ingredient(ingredientName, x).toString())
                    .orElseGet(() -> "All " + ingredientName);

            return new CommandResult(String.format(MESSAGE_SUCCESS, ingredientRemoved));
        } catch (NonPositiveIngredientQuantityException e) {
            throw new CommandException(String.format(MESSAGE_INGREDIENT_QUANTITY_TOO_HIGH));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InventoryRemoveIngredientCommand // instanceof handles nulls
                && ingredientName.equals(((InventoryRemoveIngredientCommand) other).ingredientName)
                && ingredientQuantity.equals(((InventoryRemoveIngredientCommand) other).ingredientQuantity));
    }
}
