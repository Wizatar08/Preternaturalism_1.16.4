package com.Wizatar08.preternaturalism.init;

import com.Wizatar08.preternaturalism.Preternaturalism;
import com.Wizatar08.preternaturalism.recipes.*;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.util.registry.Registry;

public class RecipeSerializerInit {
    public static final IRecipeSerializer<ContainerRecipe> CONTAINER_RECIPE_SERIALIZER = new ContainerRecipeSerializer();
    public static final IRecipeType<IContainerRecipe> CONTAINER_RECIPE_TYPE = registerContainerType(IContainerRecipe.RECIPE_TYPE_ID);

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Preternaturalism.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<?>> CONTAINER_SERIALIZER = RECIPE_SERIALIZER.register("container", () -> CONTAINER_RECIPE_SERIALIZER);


    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T>{
        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }


    // Register Container Recipe
    private static IRecipeType<IContainerRecipe> registerContainerType(ResourceLocation recipeTypeId) {
        return Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
    }
}
