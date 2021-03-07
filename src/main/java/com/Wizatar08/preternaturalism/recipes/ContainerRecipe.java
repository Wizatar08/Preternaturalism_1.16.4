package com.Wizatar08.preternaturalism.recipes;

import com.Wizatar08.preternaturalism.init.RecipeSerializerInit;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class ContainerRecipe implements IContainerRecipe {

    private final ResourceLocation id;
    private Ingredient input_container;
    private Ingredient input_ingredient;
    private final ItemStack output_container;
    private final ItemStack output_ingredient;

    private final Boolean lava_predicate;
    private final Boolean contaminated_water_predicate;
    private final Boolean water_predicate;
    private final Boolean sulfur_gas_predicate;
    private final Boolean sulfur_liquid_predicate;

    public ContainerRecipe(ResourceLocation id, Ingredient input_container, Ingredient input_ingredient, ItemStack output_container, ItemStack output_ingredient,
                           Boolean lava_predicate, Boolean contaminated_water_predicate, Boolean water_predicate, Boolean sulfur_gas_predicate, Boolean sulfur_liquid_predicate) {
        this.id = id;
        this.output_ingredient = output_ingredient;
        this.output_container = output_container;
        this.input_container = input_container;
        this.input_ingredient = input_ingredient;
        this.lava_predicate = lava_predicate;
        this.contaminated_water_predicate = contaminated_water_predicate;
        this.water_predicate = water_predicate;
        this.sulfur_gas_predicate = sulfur_gas_predicate;
        this.sulfur_liquid_predicate = sulfur_liquid_predicate;
    }

    @Override
    public boolean matches(RecipeWrapper inv, World worldIn) {
        if (this.input_container.test(inv.getStackInSlot(0)) && this.input_ingredient.test(inv.getStackInSlot(1))) {
            return true;
        }
        return false;
    }

    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
        return null;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }


    public ItemStack getCraftingResultContainer(RecipeWrapper inv) {
        return this.output_container;
    }
    public ItemStack getCraftingResultIngredient(RecipeWrapper inv) {
        return this.output_ingredient;
    }

    public ItemStack getRecipeOutputContainer() {
        return this.output_container;
    }
    public ItemStack getRecipeOutputIngredient() {
        return this.output_ingredient;
    }


    public Boolean getLavaPredicate() {
        return this.lava_predicate;
    }
    public Boolean getContaminatedWaterPredicate() { return this.contaminated_water_predicate; }
    public Boolean getWaterPredicate() { return this.water_predicate; }
    public Boolean getSulfurGasPredicate() { return this.sulfur_gas_predicate; }
    public Boolean getSulfurLiquidPredicate() { return this.sulfur_liquid_predicate; }


    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeSerializerInit.CONTAINER_SERIALIZER.get();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.from(null, this.input_container);
    }

    public Ingredient getInputContainer() {
        return this.input_container;
    }
    public Ingredient getInputIngredient() {
        return this.input_ingredient;
    }

    @Override
    public Ingredient getInput() {
        return null;
    }
}