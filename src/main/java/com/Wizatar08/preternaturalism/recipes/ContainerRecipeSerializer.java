package com.Wizatar08.preternaturalism.recipes;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ContainerRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
        implements IRecipeSerializer<ContainerRecipe> {

    @Override
    public ContainerRecipe read(ResourceLocation recipeId, JsonObject json) {
        ItemStack output_container = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output_container"), true);
        ItemStack output_ingredient = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output_ingredient"), true);
        Ingredient input_container = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input_container"));
        Ingredient input_ingredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input_ingredient"));
        Boolean lava_predicate = JSONUtils.getBoolean(json, "lava_predicate");
        Boolean contaminated_water_predicate = JSONUtils.getBoolean(json, "contaminated_water_predicate");
        Boolean water_predicate = JSONUtils.getBoolean(json, "water_predicate");
        Boolean sulfur_gas_predicate = JSONUtils.getBoolean(json, "sulfur_gas_predicate");
        Boolean sulfur_liquid_predicate = JSONUtils.getBoolean(json, "sulfur_liquid_predicate");

        return new ContainerRecipe(recipeId, input_container, input_ingredient, output_container, output_ingredient,
                lava_predicate, contaminated_water_predicate, water_predicate, sulfur_gas_predicate, sulfur_liquid_predicate);
    }

    @Override
    public ContainerRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        ItemStack output_container = buffer.readItemStack();
        ItemStack output_ingredient = buffer.readItemStack();
        Ingredient input_container = Ingredient.read(buffer);
        Ingredient input_ingredient = Ingredient.read(buffer);
        Boolean lava_predicate = buffer.readBoolean();
        Boolean contaminated_water_predicate = buffer.readBoolean();
        Boolean water_predicate = buffer.readBoolean();
        Boolean sulfur_gas_predicate = buffer.readBoolean();
        Boolean sulfur_liquid_predicate = buffer.readBoolean();

        return new ContainerRecipe(recipeId, input_container, input_ingredient, output_container, output_ingredient,
                lava_predicate, contaminated_water_predicate, water_predicate, sulfur_gas_predicate, sulfur_liquid_predicate);
    }

    @Override
    public void write(PacketBuffer buffer, ContainerRecipe recipe) {
        Ingredient input_container = recipe.getIngredients().get(0);
        input_container.write(buffer);
        Ingredient input_ingredient = recipe.getIngredients().get(1);
        input_ingredient.write(buffer);

        buffer.writeItemStack(recipe.getRecipeOutput(), false);
    }
}