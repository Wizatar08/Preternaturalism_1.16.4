// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.Wizatar08.preternaturalism.client.entity.model;

import com.Wizatar08.preternaturalism.entities.MutatedSpiderEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class MutatedSpiderModel extends AnimatedEntityModel<MutatedSpiderEntity> {

    private final AnimatedModelRenderer Body;
	private final AnimatedModelRenderer BackCube;
	private final AnimatedModelRenderer MidBackCube;
	private final AnimatedModelRenderer MidFrontCube;
	private final AnimatedModelRenderer Head;
	private final AnimatedModelRenderer LeftLeg4;
	private final AnimatedModelRenderer LeftLeg3;
	private final AnimatedModelRenderer LeftLeg2;
	private final AnimatedModelRenderer LeftLeg1;
	private final AnimatedModelRenderer RightLeg3;
	private final AnimatedModelRenderer RightLeg4;
	private final AnimatedModelRenderer RightLeg2;
	private final AnimatedModelRenderer RightLeg1;

    public MutatedSpiderModel()
    {
        textureWidth = 128;
    textureHeight = 128;
    Body = new AnimatedModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		
		Body.setModelRendererName("Body");
		this.registerModelRenderer(Body);

		BackCube = new AnimatedModelRenderer(this);
		BackCube.setRotationPoint(0.0F, 0.0F, 15.0F);
		Body.addChild(BackCube);
		BackCube.setTextureOffset(0, 0).addBox(-8.0F, -21.0F, -11.0F, 16.0F, 14.0F, 16.0F, 0.0F, false);
		BackCube.setModelRendererName("BackCube");
		this.registerModelRenderer(BackCube);

		MidBackCube = new AnimatedModelRenderer(this);
		MidBackCube.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(MidBackCube);
		MidBackCube.setTextureOffset(48, 0).addBox(-7.0F, -19.0F, -1.0F, 14.0F, 11.0F, 5.0F, 0.0F, false);
		MidBackCube.setModelRendererName("MidBackCube");
		this.registerModelRenderer(MidBackCube);

		MidFrontCube = new AnimatedModelRenderer(this);
		MidFrontCube.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(MidFrontCube);
		MidFrontCube.setTextureOffset(49, 54).addBox(-4.0F, -18.0F, -6.0F, 8.0F, 10.0F, 5.0F, 0.0F, false);
		MidFrontCube.setModelRendererName("MidFrontCube");
		this.registerModelRenderer(MidFrontCube);

		Head = new AnimatedModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(Head);
		Head.setTextureOffset(0, 30).addBox(-7.0F, -21.0F, -19.0F, 14.0F, 16.0F, 13.0F, 0.0F, false);
		Head.setModelRendererName("Head");
		this.registerModelRenderer(Head);

		LeftLeg4 = new AnimatedModelRenderer(this);
		LeftLeg4.setRotationPoint(8.0F, -12.0F, 14.0F);
		Body.addChild(LeftLeg4);
		LeftLeg4.setTextureOffset(41, 36).addBox(0.0F, 0.0F, 0.0F, 15.0F, 1.0F, 1.0F, 0.0F, false);
		LeftLeg4.setTextureOffset(0, 59).addBox(14.0F, 1.0F, 0.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		LeftLeg4.setModelRendererName("LeftLeg4");
		this.registerModelRenderer(LeftLeg4);

		LeftLeg3 = new AnimatedModelRenderer(this);
		LeftLeg3.setRotationPoint(8.0F, -12.0F, 7.0F);
		Body.addChild(LeftLeg3);
		LeftLeg3.setTextureOffset(41, 38).addBox(0.0F, 0.0F, 0.0F, 15.0F, 1.0F, 1.0F, 0.0F, false);
		LeftLeg3.setTextureOffset(4, 59).addBox(14.0F, 1.0F, 0.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		LeftLeg3.setModelRendererName("LeftLeg3");
		this.registerModelRenderer(LeftLeg3);

		LeftLeg2 = new AnimatedModelRenderer(this);
		LeftLeg2.setRotationPoint(8.0F, -12.0F, 1.0F);
		Body.addChild(LeftLeg2);
		LeftLeg2.setTextureOffset(54, 43).addBox(-1.0F, 0.0F, 0.0F, 15.0F, 1.0F, 1.0F, 0.0F, false);
		LeftLeg2.setTextureOffset(8, 59).addBox(13.0F, 1.0F, 0.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		LeftLeg2.setModelRendererName("LeftLeg2");
		this.registerModelRenderer(LeftLeg2);

		LeftLeg1 = new AnimatedModelRenderer(this);
		LeftLeg1.setRotationPoint(4.0F, -12.0F, -5.0F);
		Body.addChild(LeftLeg1);
		LeftLeg1.setTextureOffset(54, 45).addBox(-9.0F, 0.0F, 0.0F, 24.0F, 1.0F, 1.0F, 0.0F, false);
		LeftLeg1.setTextureOffset(12, 59).addBox(14.0F, 1.0F, 0.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		LeftLeg1.setModelRendererName("LeftLeg1");
		this.registerModelRenderer(LeftLeg1);

		RightLeg3 = new AnimatedModelRenderer(this);
		RightLeg3.setRotationPoint(-8.0F, -12.0F, 7.0F);
		Body.addChild(RightLeg3);
		RightLeg3.setTextureOffset(41, 30).addBox(-15.0F, 0.0F, 0.0F, 15.0F, 1.0F, 1.0F, 0.0F, false);
		RightLeg3.setTextureOffset(4, 0).addBox(-15.0F, 1.0F, 0.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		RightLeg3.setModelRendererName("RightLeg3");
		this.registerModelRenderer(RightLeg3);

		RightLeg4 = new AnimatedModelRenderer(this);
		RightLeg4.setRotationPoint(-8.0F, -12.0F, 14.0F);
		Body.addChild(RightLeg4);
		RightLeg4.setTextureOffset(41, 41).addBox(-15.0F, 0.0F, 0.0F, 15.0F, 1.0F, 1.0F, 0.0F, false);
		RightLeg4.setTextureOffset(0, 0).addBox(-15.0F, 1.0F, 0.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		RightLeg4.setModelRendererName("RightLeg4");
		this.registerModelRenderer(RightLeg4);

		RightLeg2 = new AnimatedModelRenderer(this);
		RightLeg2.setRotationPoint(-7.0F, -12.0F, 1.0F);
		Body.addChild(RightLeg2);
		RightLeg2.setTextureOffset(41, 32).addBox(-15.0F, 0.0F, 0.0F, 15.0F, 1.0F, 1.0F, 0.0F, false);
		RightLeg2.setTextureOffset(8, 0).addBox(-15.0F, 1.0F, 0.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		RightLeg2.setModelRendererName("RightLeg2");
		this.registerModelRenderer(RightLeg2);

		RightLeg1 = new AnimatedModelRenderer(this);
		RightLeg1.setRotationPoint(-4.0F, -12.0F, -5.0F);
		Body.addChild(RightLeg1);
		RightLeg1.setTextureOffset(41, 34).addBox(-15.0F, 0.0F, 0.0F, 15.0F, 1.0F, 1.0F, 0.0F, false);
		RightLeg1.setTextureOffset(12, 0).addBox(-15.0F, 1.0F, 0.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		RightLeg1.setModelRendererName("RightLeg1");
		this.registerModelRenderer(RightLeg1);

    this.rootBones.add(Body);
  }


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation("preternaturalism", "animations/mutated_spider_entity.json");
    }
}