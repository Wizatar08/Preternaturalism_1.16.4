package com.inf1n1T388.preternaturalism.client.entity.model;

import com.inf1n1T388.preternaturalism.entities.AshenCrawlerEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class AshenCrawlerEntityModel extends AnimatedEntityModel<AshenCrawlerEntity> {

    private final AnimatedModelRenderer Body;
	private final AnimatedModelRenderer Legs;
	private final AnimatedModelRenderer Right;
	private final AnimatedModelRenderer RightLeg6;
	private final AnimatedModelRenderer RightLeg5;
	private final AnimatedModelRenderer RightLeg4;
	private final AnimatedModelRenderer RightLeg3;
	private final AnimatedModelRenderer RightLeg2;
	private final AnimatedModelRenderer RightLeg1;
	private final AnimatedModelRenderer Left;
	private final AnimatedModelRenderer LeftLeg1;
	private final AnimatedModelRenderer LeftLeg2;
	private final AnimatedModelRenderer LeftLeg3;
	private final AnimatedModelRenderer LeftLeg4;
	private final AnimatedModelRenderer LeftLeg5;
	private final AnimatedModelRenderer LeftLeg6;
	private final AnimatedModelRenderer Head;

    public AshenCrawlerEntityModel()
    {
        textureWidth = 64;
    textureHeight = 64;
    Body = new AnimatedModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.setTextureOffset(0, 0).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 2.0F, 13.0F, 0.0F, false);
		Body.setModelRendererName("Body");
		this.registerModelRenderer(Body);

		Legs = new AnimatedModelRenderer(this);
		Legs.setRotationPoint(-8.0F, -8.0F, 8.0F);
		Body.addChild(Legs);
		
		Legs.setModelRendererName("Legs");
		this.registerModelRenderer(Legs);

		Right = new AnimatedModelRenderer(this);
		Right.setRotationPoint(0.0F, 0.0F, 0.0F);
		Legs.addChild(Right);
		
		Right.setModelRendererName("Right");
		this.registerModelRenderer(Right);

		RightLeg6 = new AnimatedModelRenderer(this);
		RightLeg6.setRotationPoint(6.5F, 7.0F, -2.5F);
		Right.addChild(RightLeg6);
		RightLeg6.setTextureOffset(0, 0).addBox(-3.5F, 0.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		RightLeg6.setModelRendererName("RightLeg6");
		this.registerModelRenderer(RightLeg6);

		RightLeg5 = new AnimatedModelRenderer(this);
		RightLeg5.setRotationPoint(6.5F, 7.0F, -4.5F);
		Right.addChild(RightLeg5);
		RightLeg5.setTextureOffset(0, 2).addBox(-3.5F, 0.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		RightLeg5.setModelRendererName("RightLeg5");
		this.registerModelRenderer(RightLeg5);

		RightLeg4 = new AnimatedModelRenderer(this);
		RightLeg4.setRotationPoint(6.5F, 7.0F, -6.5F);
		Right.addChild(RightLeg4);
		RightLeg4.setTextureOffset(0, 4).addBox(-3.5F, 0.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		RightLeg4.setModelRendererName("RightLeg4");
		this.registerModelRenderer(RightLeg4);

		RightLeg3 = new AnimatedModelRenderer(this);
		RightLeg3.setRotationPoint(6.5F, 7.0F, -8.5F);
		Right.addChild(RightLeg3);
		RightLeg3.setTextureOffset(0, 6).addBox(-3.5F, 0.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		RightLeg3.setModelRendererName("RightLeg3");
		this.registerModelRenderer(RightLeg3);

		RightLeg2 = new AnimatedModelRenderer(this);
		RightLeg2.setRotationPoint(6.5F, 7.0F, -10.5F);
		Right.addChild(RightLeg2);
		RightLeg2.setTextureOffset(0, 8).addBox(-3.5F, 0.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		RightLeg2.setModelRendererName("RightLeg2");
		this.registerModelRenderer(RightLeg2);

		RightLeg1 = new AnimatedModelRenderer(this);
		RightLeg1.setRotationPoint(6.5F, 7.0F, -12.5F);
		Right.addChild(RightLeg1);
		RightLeg1.setTextureOffset(0, 10).addBox(-3.5F, 0.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		RightLeg1.setModelRendererName("RightLeg1");
		this.registerModelRenderer(RightLeg1);

		Left = new AnimatedModelRenderer(this);
		Left.setRotationPoint(0.0F, 0.0F, 0.0F);
		Legs.addChild(Left);
		
		Left.setModelRendererName("Left");
		this.registerModelRenderer(Left);

		LeftLeg1 = new AnimatedModelRenderer(this);
		LeftLeg1.setRotationPoint(9.5F, 7.0F, -12.5F);
		Left.addChild(LeftLeg1);
		LeftLeg1.setTextureOffset(0, 21).addBox(-0.5F, 0.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		LeftLeg1.setModelRendererName("LeftLeg1");
		this.registerModelRenderer(LeftLeg1);

		LeftLeg2 = new AnimatedModelRenderer(this);
		LeftLeg2.setRotationPoint(9.5F, 7.0F, -10.5F);
		Left.addChild(LeftLeg2);
		LeftLeg2.setTextureOffset(0, 0).addBox(-0.5F, 0.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		LeftLeg2.setModelRendererName("LeftLeg2");
		this.registerModelRenderer(LeftLeg2);

		LeftLeg3 = new AnimatedModelRenderer(this);
		LeftLeg3.setRotationPoint(9.5F, 7.0F, -8.5F);
		Left.addChild(LeftLeg3);
		LeftLeg3.setTextureOffset(10, 19).addBox(-0.5F, 0.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		LeftLeg3.setModelRendererName("LeftLeg3");
		this.registerModelRenderer(LeftLeg3);

		LeftLeg4 = new AnimatedModelRenderer(this);
		LeftLeg4.setRotationPoint(9.5F, 7.0F, -6.5F);
		Left.addChild(LeftLeg4);
		LeftLeg4.setTextureOffset(0, 19).addBox(-0.5F, 0.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		LeftLeg4.setModelRendererName("LeftLeg4");
		this.registerModelRenderer(LeftLeg4);

		LeftLeg5 = new AnimatedModelRenderer(this);
		LeftLeg5.setRotationPoint(9.5F, 7.0F, -4.5F);
		Left.addChild(LeftLeg5);
		LeftLeg5.setTextureOffset(16, 17).addBox(-0.5F, 0.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		LeftLeg5.setModelRendererName("LeftLeg5");
		this.registerModelRenderer(LeftLeg5);

		LeftLeg6 = new AnimatedModelRenderer(this);
		LeftLeg6.setRotationPoint(9.5F, 7.0F, -2.5F);
		Left.addChild(LeftLeg6);
		LeftLeg6.setTextureOffset(14, 15).addBox(-0.5F, 0.0F, -0.5F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		LeftLeg6.setModelRendererName("LeftLeg6");
		this.registerModelRenderer(LeftLeg6);

		Head = new AnimatedModelRenderer(this);
		Head.setRotationPoint(0.0F, -2.0F, -6.0F);
		Body.addChild(Head);
		Head.setTextureOffset(0, 15).addBox(-3.0F, -1.0F, -2.0F, 6.0F, 2.0F, 2.0F, 0.0F, false);
		Head.setModelRendererName("Head");
		this.registerModelRenderer(Head);

    this.rootBones.add(Body);
  }


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation("preternaturalism", "animations/ashen_crawler_entity.json");
    }
}