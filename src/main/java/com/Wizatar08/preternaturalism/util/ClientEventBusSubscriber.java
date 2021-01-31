package com.inf1n1T388.preternaturalism.util;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.client.ContainerHandlerScreen;
import com.inf1n1T388.preternaturalism.client.IronContainerScreen;
import com.inf1n1T388.preternaturalism.client.ItemChamberScreen;
import com.inf1n1T388.preternaturalism.client.entity.EntityRendering;
import com.inf1n1T388.preternaturalism.client.entity.render.AshenCrawlerEntityRender;
import com.inf1n1T388.preternaturalism.client.entity.render.GlowingHoverdustEntityRender;
import com.inf1n1T388.preternaturalism.client.entity.render.MutatedSpiderEntityRender;
import com.inf1n1T388.preternaturalism.client.tileentityrenderer.ItemChamberRenderer;
import com.inf1n1T388.preternaturalism.entities.ExplosiveAbniteOrbEntity;
import com.inf1n1T388.preternaturalism.init.BlockInit;
import com.inf1n1T388.preternaturalism.init.ModContainerTypes;
import com.inf1n1T388.preternaturalism.init.ModEntityTypes;
import com.inf1n1T388.preternaturalism.init.ModTileEntityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.network.play.IClientPlayNetHandler;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.server.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Preternaturalism.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber implements IClientPlayNetHandler {
    private Minecraft client;
    private ClientWorld world;

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        ScreenManager.registerFactory(ModContainerTypes.IRON_CONTAINER.get(), IronContainerScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.CONTAINER_HANDLER.get(), ContainerHandlerScreen::new);
        ScreenManager.registerFactory(ModContainerTypes.ITEM_CHAMBER.get(), ItemChamberScreen::new);

        EntityRendering.bindEntities();

        RenderTypeLookup.setRenderLayer(BlockInit.CRYSTALLINE_BERRY_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.ASHEN_DOOR.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.SHRIVELED_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.DECAYED_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.RED_CRYSTAL_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(BlockInit.YELLOW_CRYSTAL_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(BlockInit.GREEN_CRYSTAL_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(BlockInit.BLUE_CRYSTAL_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(BlockInit.PURPLE_CRYSTAL_BLOCK.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(BlockInit.CONTAMINATED_DOOR.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.LUMINESCENT_DOOR.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.GLOWMINEOUS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.TALL_GLOWMINEOUS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.ITEM_CHAMBER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.SICKLY_SPROUTS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.INTERTWINED_CONTAMINATED_SHRUB.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.INTERTWINED_CONTAMINATED_BUSH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BlockInit.INTERTWINED_CONTAMINATED_ROOTS.get(), RenderType.getCutout());

        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ASHEN_CRAWLER.get(), AshenCrawlerEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.GLOWING_HOVERDUST.get(), GlowingHoverdustEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MUTATED_SPIDER.get(), MutatedSpiderEntityRender::new);

        ClientRegistry.bindTileEntityRenderer(ModTileEntityTypes.ITEM_CHAMBER.get(), ItemChamberRenderer::new);
    }

    @Override
    public void handleSpawnObject(SSpawnObjectPacket packetIn) {
        PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, this.client);
        double d0 = packetIn.getX();
        double d1 = packetIn.getY();
        double d2 = packetIn.getZ();
        EntityType<?> entitytype = packetIn.getType();
        Entity entity;
        if (entitytype == ModEntityTypes.EXPLOSIVE_ABNITE_ORB.get()) {
            entity = new ExplosiveAbniteOrbEntity(world, d0, d1, d2);
        } else {
            entity = null;
        }
        if (entity != null) {
            int i = packetIn.getEntityID();
            entity.setPacketCoordinates(d0, d1, d2);
            entity.rotationPitch = (float)(packetIn.getPitch() * 360) / 256.0F;
            entity.rotationYaw = (float)(packetIn.getYaw() * 360) / 256.0F;
            entity.setEntityId(i);
            entity.setUniqueId(packetIn.getUniqueId());
            this.world.addEntity(i, entity);
        }
    }

    @Override
    public void handleSpawnExperienceOrb(SSpawnExperienceOrbPacket packetIn) {

    }

    @Override
    public void handleSpawnMob(SSpawnMobPacket packetIn) {
    }

    @Override
    public void handleScoreboardObjective(SScoreboardObjectivePacket packetIn) {

    }

    @Override
    public void handleSpawnPainting(SSpawnPaintingPacket packetIn) {

    }

    @Override
    public void handleSpawnPlayer(SSpawnPlayerPacket packetIn) {

    }

    @Override
    public void handleAnimation(SAnimateHandPacket packetIn) {

    }

    @Override
    public void handleStatistics(SStatisticsPacket packetIn) {

    }

    @Override
    public void handleRecipeBook(SRecipeBookPacket packetIn) {

    }

    @Override
    public void handleBlockBreakAnim(SAnimateBlockBreakPacket packetIn) {

    }

    @Override
    public void handleSignEditorOpen(SOpenSignMenuPacket packetIn) {

    }

    @Override
    public void handleUpdateTileEntity(SUpdateTileEntityPacket packetIn) {

    }

    @Override
    public void handleBlockAction(SBlockActionPacket packetIn) {

    }

    @Override
    public void handleBlockChange(SChangeBlockPacket packetIn) {

    }

    @Override
    public void handleChat(SChatPacket packetIn) {

    }

    @Override
    public void handleMultiBlockChange(SMultiBlockChangePacket packetIn) {

    }

    @Override
    public void handleMaps(SMapDataPacket packetIn) {

    }

    @Override
    public void handleConfirmTransaction(SConfirmTransactionPacket packetIn) {

    }

    @Override
    public void handleCloseWindow(SCloseWindowPacket packetIn) {

    }

    @Override
    public void handleWindowItems(SWindowItemsPacket packetIn) {

    }

    @Override
    public void handleOpenHorseWindow(SOpenHorseWindowPacket packetIn) {

    }

    @Override
    public void handleWindowProperty(SWindowPropertyPacket packetIn) {

    }

    @Override
    public void handleSetSlot(SSetSlotPacket packetIn) {

    }

    @Override
    public void handleCustomPayload(SCustomPayloadPlayPacket packetIn) {

    }

    @Override
    public void handleDisconnect(SDisconnectPacket packetIn) {

    }

    @Override
    public void handleEntityStatus(SEntityStatusPacket packetIn) {

    }

    @Override
    public void handleEntityAttach(SMountEntityPacket packetIn) {

    }

    @Override
    public void handleSetPassengers(SSetPassengersPacket packetIn) {

    }

    @Override
    public void handleExplosion(SExplosionPacket packetIn) {

    }

    @Override
    public void handleChangeGameState(SChangeGameStatePacket packetIn) {

    }

    @Override
    public void handleKeepAlive(SKeepAlivePacket packetIn) {

    }

    @Override
    public void handleChunkData(SChunkDataPacket packetIn) {

    }

    @Override
    public void processChunkUnload(SUnloadChunkPacket packetIn) {

    }

    @Override
    public void handleEffect(SPlaySoundEventPacket packetIn) {

    }

    @Override
    public void handleJoinGame(SJoinGamePacket packetIn) {

    }

    @Override
    public void handleEntityMovement(SEntityPacket packetIn) {

    }

    @Override
    public void handlePlayerPosLook(SPlayerPositionLookPacket packetIn) {

    }

    @Override
    public void handleParticles(SSpawnParticlePacket packetIn) {

    }

    @Override
    public void handlePlayerAbilities(SPlayerAbilitiesPacket packetIn) {

    }

    @Override
    public void handlePlayerListItem(SPlayerListItemPacket packetIn) {

    }

    @Override
    public void handleDestroyEntities(SDestroyEntitiesPacket packetIn) {

    }

    @Override
    public void handleRemoveEntityEffect(SRemoveEntityEffectPacket packetIn) {

    }

    @Override
    public void handleRespawn(SRespawnPacket packetIn) {

    }

    @Override
    public void handleEntityHeadLook(SEntityHeadLookPacket packetIn) {

    }

    @Override
    public void handleHeldItemChange(SHeldItemChangePacket packetIn) {

    }

    @Override
    public void handleDisplayObjective(SDisplayObjectivePacket packetIn) {

    }

    @Override
    public void handleEntityMetadata(SEntityMetadataPacket packetIn) {

    }

    @Override
    public void handleEntityVelocity(SEntityVelocityPacket packetIn) {

    }

    @Override
    public void handleEntityEquipment(SEntityEquipmentPacket packetIn) {

    }

    @Override
    public void handleSetExperience(SSetExperiencePacket packetIn) {

    }

    @Override
    public void handleUpdateHealth(SUpdateHealthPacket packetIn) {

    }

    @Override
    public void handleTeams(STeamsPacket packetIn) {

    }

    @Override
    public void handleUpdateScore(SUpdateScorePacket packetIn) {

    }

    @Override
    public void func_230488_a_(SWorldSpawnChangedPacket p_230488_1_) {

    }

    @Override
    public void handleTimeUpdate(SUpdateTimePacket packetIn) {

    }

    @Override
    public void handleSoundEffect(SPlaySoundEffectPacket packetIn) {

    }

    @Override
    public void handleSpawnMovingSoundEffect(SSpawnMovingSoundEffectPacket packetIn) {

    }

    @Override
    public void handleCustomSound(SPlaySoundPacket packetIn) {

    }

    @Override
    public void handleCollectItem(SCollectItemPacket packetIn) {

    }

    @Override
    public void handleEntityTeleport(SEntityTeleportPacket packetIn) {

    }

    @Override
    public void handleEntityProperties(SEntityPropertiesPacket packetIn) {

    }

    @Override
    public void handleEntityEffect(SPlayEntityEffectPacket packetIn) {

    }

    @Override
    public void handleTags(STagsListPacket packetIn) {

    }

    @Override
    public void handleCombatEvent(SCombatPacket packetIn) {

    }

    @Override
    public void handleServerDifficulty(SServerDifficultyPacket packetIn) {

    }

    @Override
    public void handleCamera(SCameraPacket packetIn) {

    }

    @Override
    public void handleWorldBorder(SWorldBorderPacket packetIn) {

    }

    @Override
    public void handleTitle(STitlePacket packetIn) {

    }

    @Override
    public void handlePlayerListHeaderFooter(SPlayerListHeaderFooterPacket packetIn) {

    }

    @Override
    public void handleResourcePack(SSendResourcePackPacket packetIn) {

    }

    @Override
    public void handleUpdateBossInfo(SUpdateBossInfoPacket packetIn) {

    }

    @Override
    public void handleCooldown(SCooldownPacket packetIn) {

    }

    @Override
    public void handleMoveVehicle(SMoveVehiclePacket packetIn) {

    }

    @Override
    public void handleAdvancementInfo(SAdvancementInfoPacket packetIn) {

    }

    @Override
    public void handleSelectAdvancementsTab(SSelectAdvancementsTabPacket packetIn) {

    }

    @Override
    public void handlePlaceGhostRecipe(SPlaceGhostRecipePacket packetIn) {

    }

    @Override
    public void handleCommandList(SCommandListPacket packetIn) {

    }

    @Override
    public void handleStopSound(SStopSoundPacket packetIn) {

    }

    @Override
    public void handleTabComplete(STabCompletePacket packetIn) {

    }

    @Override
    public void handleUpdateRecipes(SUpdateRecipesPacket packetIn) {

    }

    @Override
    public void handlePlayerLook(SPlayerLookPacket packetIn) {

    }

    @Override
    public void handleNBTQueryResponse(SQueryNBTResponsePacket packetIn) {

    }

    @Override
    public void handleUpdateLight(SUpdateLightPacket packetIn) {

    }

    @Override
    public void handleOpenBookPacket(SOpenBookWindowPacket packetIn) {

    }

    @Override
    public void handleOpenWindowPacket(SOpenWindowPacket packetIn) {

    }

    @Override
    public void handleMerchantOffers(SMerchantOffersPacket packetIn) {

    }

    @Override
    public void handleUpdateViewDistancePacket(SUpdateViewDistancePacket packetIn) {

    }

    @Override
    public void handleChunkPositionPacket(SUpdateChunkPositionPacket packetIn) {

    }

    @Override
    public void handleAcknowledgePlayerDigging(SPlayerDiggingPacket packetIn) {

    }

    @Override
    public void onDisconnect(ITextComponent reason) {

    }

    @Override
    public NetworkManager getNetworkManager() {
        return null;
    }
}
