package com.inf1n1T388.preternaturalism.tileentity;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.container.ContainerHandlerContainer;
import com.inf1n1T388.preternaturalism.init.ItemInit;
import com.inf1n1T388.preternaturalism.init.ModTileEntityTypes;
import com.inf1n1T388.preternaturalism.init.RecipeSerializerInit;
import com.inf1n1T388.preternaturalism.objects.blockproperties.ContainerHandlerCurrentFluid;
import com.inf1n1T388.preternaturalism.objects.blocks.ContainerHandlerBlock;
import com.inf1n1T388.preternaturalism.recipes.ContainerRecipe;
import com.inf1n1T388.preternaturalism.util.recipehandlers.ContainerItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.world.PistonEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ContainerHandlerTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private ITextComponent customName;
    public int currentSmeltTime;
    public final int maxSmeltTime = 100;
    private ContainerItemHandler inventory;

    public ContainerHandlerTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        this.inventory = new ContainerItemHandler(5);
    }

    public ContainerHandlerTileEntity() {
        this(ModTileEntityTypes.CONTAINER_HANDLER.get());
    }

    @Override
    public Container createMenu(final int windowID, final PlayerInventory playerInv, final PlayerEntity playerIn) {
        return new ContainerHandlerContainer(windowID, playerInv, this);
    }

    @Override
    public void tick() {

        boolean dirty = false;
        if (this.world != null && !this.world.isRemote) {
            //Preternaturalism.LOGGER.info("Tick method: " + this.getBlockState().get(ContainerHandlerBlock.CURRENT_FLUID));
            if (this.world.isBlockPowered(this.getPos())) {
                // Ckeck if all ingredients in the recipe are true
                //Preternaturalism.LOGGER.info("Predicate: " + (!this.getRecipe(this.inventory.getStackInSlot(0)).getLavaPredicate()) + " or " + (this.getRecipe(this.inventory.getStackInSlot(0)).getLavaPredicate() && this.getBlockState().get(ContainerHandlerBlock.CURRENT_FLUID) == ContainerHandlerCurrentFluid.LAVA));
                // Check if fluid predicates are true
                if (this.getRecipe(this.inventory.getStackInSlot(0)) != null &&
                        (this.getRecipe(this.inventory.getStackInSlot(1)) != null || this.getRecipe(this.inventory.getStackInSlot(1)).getRecipeOutputIngredient().getItem() == Items.AIR) &&
                        getFluidPredicate(this.getRecipe(this.inventory.getStackInSlot(0)))) {
                    if (this.currentSmeltTime != this.maxSmeltTime) {
                        this.world.setBlockState(this.getPos(), this.getBlockState().with(ContainerHandlerBlock.LIT, true));
                        this.currentSmeltTime++;
                        dirty = true;
                    } else {
                        this.world.setBlockState(this.getPos(),
                                this.getBlockState().with(ContainerHandlerBlock.LIT, false));
                        this.currentSmeltTime = 0;
                        ItemStack output_container = this.getRecipe(this.inventory.getStackInSlot(0)).getRecipeOutputContainer();
                        this.inventory.insertItem(2, output_container.copy(), false);
                        ItemStack output_ingredient = this.getRecipe(this.inventory.getStackInSlot(1)).getRecipeOutputIngredient();
                        this.inventory.insertItem(3, output_ingredient.copy(), false);
                        this.inventory.decrStackSize(0, 1);
                        this.inventory.decrStackSize(1, 1);
                        dirty = true;
                    }
                }


            }
        }

        if (dirty) {
            this.markDirty();
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(),
                    Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    private Boolean getFluidPredicate(ContainerRecipe recipe){
        //Preternaturalism.LOGGER.info("Fluid predicate: " + this.getCurrentFluid());
        if(recipe.getLavaPredicate()){
            return this.getCurrentFluid() == ContainerHandlerCurrentFluid.LAVA;
        } else if(recipe.getContaminatedWaterPredicate()){
            return this.getCurrentFluid() == ContainerHandlerCurrentFluid.CONTAMINATED_WATER;
        } else if(recipe.getWaterPredicate()){
            return this.getCurrentFluid() == ContainerHandlerCurrentFluid.WATER;
        } else if(recipe.getSulfurGasPredicate()){
            return this.getCurrentFluid() == ContainerHandlerCurrentFluid.SULFUR_GAS;
        } else if(recipe.getSulfurLiquidPredicate()){
            return this.getCurrentFluid() == ContainerHandlerCurrentFluid.SULFUR_LIQUID;
        } else {
            return true;
        }

    }

    public ContainerHandlerCurrentFluid getCurrentFluid(){
        return getThisBlock(this.getBlockState().getBlock()).getCurrentFluid(this.world, this.pos);
    }

    private ContainerHandlerBlock getThisBlock(Block block){
        return (ContainerHandlerBlock) block;
    }

    public void setCustomName(ITextComponent name) {
        this.customName = name;
    }

    public ITextComponent getName() {
        return this.customName != null ? this.customName : this.getDefaultName();
    }

    private ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + Preternaturalism.MOD_ID + ".container_handler");
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.getName();
    }

    @Nullable
    public ITextComponent getCustomName() {
        return this.customName;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            this.customName = ITextComponent.Serializer.getComponentFromJson(compound.getString("CustomName"));
        }

        NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(this.inventory.getSlots(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, inv);
        this.inventory.setNonNullList(inv);

        this.currentSmeltTime = compound.getInt("CurrentSmeltTime");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }

        ItemStackHelper.saveAllItems(compound, this.inventory.toNonNullList());
        compound.putInt("CurrentSmeltTime", this.currentSmeltTime);

        return compound;
    }

    @Nullable
    private ContainerRecipe getRecipe(ItemStack stack) {
        if (stack == null) {
            return null;
        }

        Set<IRecipe<?>> recipes = findRecipesByType(RecipeSerializerInit.CONTAINER_RECIPE_TYPE, this.world);
        for (IRecipe<?> iRecipe : recipes) {
            ContainerRecipe recipe = (ContainerRecipe) iRecipe;
            if (recipe.matches(new RecipeWrapper(this.inventory), this.world)) {
                return recipe;
            }
        }

        return null;
    }

    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn, World world) {
        return world != null ? world.getRecipeManager().getRecipes().stream()
                .filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
    }

    @SuppressWarnings("resource")
    @OnlyIn(Dist.CLIENT)
    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn) {
        ClientWorld world = Minecraft.getInstance().world;
        return world != null ? world.getRecipeManager().getRecipes().stream()
                .filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
    }

    public static Set<ItemStack> getAllRecipeInputs(IRecipeType<?> typeIn, World worldIn) {
        Set<ItemStack> inputs = new HashSet<ItemStack>();
        Set<IRecipe<?>> recipes = findRecipesByType(typeIn, worldIn);
        for (IRecipe<?> recipe : recipes) {
            NonNullList<Ingredient> ingredients = recipe.getIngredients();
            ingredients.forEach(ingredient -> {
                for (ItemStack stack : ingredient.getMatchingStacks()) {
                    inputs.add(stack);
                }
            });
        }
        return inputs;
    }

    public final IItemHandlerModifiable getInventory() {
        return this.inventory;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);
        return new SUpdateTileEntityPacket(this.pos, 0, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(this.getBlockState(), pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);
        return nbt;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT nbt) {
        this.read(state, nbt);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> this.inventory));
    }
}