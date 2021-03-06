package com.Wizatar08.preternaturalism.client.entity.model;

import com.Wizatar08.preternaturalism.entities.BlazingWaveEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class BlazingWaveModel extends AnimatedEntityModel<BlazingWaveEntity> {
	private final AnimatedModelRenderer bone;
	private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer front;
	private final AnimatedModelRenderer middle;
	private final AnimatedModelRenderer end;
	private final AnimatedModelRenderer front_middle;
	private final AnimatedModelRenderer middle_end;
	private final AnimatedModelRenderer wings;
	private final AnimatedModelRenderer left;
	private final AnimatedModelRenderer left_bottom_middle_wing;
	private final AnimatedModelRenderer left_top_middle_wing;
	private final AnimatedModelRenderer left_front_wing;
	private final AnimatedModelRenderer right;
	private final AnimatedModelRenderer right_bottom_middle_wing;
	private final AnimatedModelRenderer right_top_middle_wing;
	private final AnimatedModelRenderer right_front_wing;
	private final AnimatedModelRenderer tail;

	public BlazingWaveModel() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new AnimatedModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.setModelRendererName("bone");
		this.registerModelRenderer(bone);
		

		body = new AnimatedModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(body);
		body.setModelRendererName("body");
		this.registerModelRenderer(body);
		

		front = new AnimatedModelRenderer(this);
		front.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(front);
		front.setTextureOffset(0, 16).addBox(-3.0F, -7.0F, -8.0F, 6.0F, 4.0F, 4.0F, 0.0F, false);
		front.setModelRendererName("front");
		this.registerModelRenderer(front);

		middle = new AnimatedModelRenderer(this);
		middle.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(middle);
		middle.setTextureOffset(0, 8).addBox(-3.0F, -7.0F, -1.0F, 6.0F, 4.0F, 4.0F, 0.0F, false);
		middle.setModelRendererName("middle");
		this.registerModelRenderer(middle);

		end = new AnimatedModelRenderer(this);
		end.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(end);
		end.setTextureOffset(0, 0).addBox(-3.0F, -7.0F, 6.0F, 6.0F, 4.0F, 4.0F, 0.0F, false);
		end.setModelRendererName("end");
		this.registerModelRenderer(end);

		front_middle = new AnimatedModelRenderer(this);
		front_middle.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(front_middle);
		front_middle.setTextureOffset(28, 28).addBox(-2.0F, -6.0F, -4.0F, 4.0F, 2.0F, 5.0F, 0.0F, false);
		front_middle.setModelRendererName("front_middle");
		this.registerModelRenderer(front_middle);

		middle_end = new AnimatedModelRenderer(this);
		middle_end.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(middle_end);
		middle_end.setTextureOffset(15, 25).addBox(-2.0F, -6.0F, 2.0F, 4.0F, 2.0F, 5.0F, 0.0F, false);
		middle_end.setModelRendererName("middle_end");
		this.registerModelRenderer(middle_end);

		wings = new AnimatedModelRenderer(this);
		wings.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(wings);
		wings.setModelRendererName("wings");
		this.registerModelRenderer(wings);
		

		left = new AnimatedModelRenderer(this);
		left.setRotationPoint(0.0F, 0.0F, 0.0F);
		wings.addChild(left);
		left.setModelRendererName("left");
		this.registerModelRenderer(left);
		

		left_bottom_middle_wing = new AnimatedModelRenderer(this);
		left_bottom_middle_wing.setRotationPoint(3.0F, -4.0F, 0.0F);
		left.addChild(left_bottom_middle_wing);
		left_bottom_middle_wing.setTextureOffset(0, 24).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);
		left_bottom_middle_wing.setTextureOffset(17, 17).addBox(5.0F, 1.0F, -1.0F, 2.0F, 0.0F, 3.0F, 0.0F, false);
		left_bottom_middle_wing.setTextureOffset(0, 3).addBox(7.0F, 1.0F, -1.0F, 1.0F, 0.0F, 1.0F, 0.0F, false);
		left_bottom_middle_wing.setModelRendererName("left_bottom_middle_wing");
		this.registerModelRenderer(left_bottom_middle_wing);

		left_top_middle_wing = new AnimatedModelRenderer(this);
		left_top_middle_wing.setRotationPoint(3.0F, -6.0F, 0.0F);
		left.addChild(left_top_middle_wing);
		left_top_middle_wing.setTextureOffset(16, 12).addBox(-1.0F, 0.0F, -1.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);
		left_top_middle_wing.setTextureOffset(13, 9).addBox(5.0F, 1.0F, -1.0F, 2.0F, 0.0F, 3.0F, 0.0F, false);
		left_top_middle_wing.setTextureOffset(0, 1).addBox(7.0F, 1.0F, -1.0F, 1.0F, 0.0F, 1.0F, 0.0F, false);
		left_top_middle_wing.setModelRendererName("left_top_middle_wing");
		this.registerModelRenderer(left_top_middle_wing);

		left_front_wing = new AnimatedModelRenderer(this);
		left_front_wing.setRotationPoint(-3.0F, -5.0F, 8.0F);
		left.addChild(left_front_wing);
		left_front_wing.setTextureOffset(22, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
		left_front_wing.setTextureOffset(19, 11).addBox(-4.0F, 0.0F, -1.0F, 2.0F, 0.0F, 1.0F, 0.0F, false);
		left_front_wing.setModelRendererName("left_front_wing");
		this.registerModelRenderer(left_front_wing);

		right = new AnimatedModelRenderer(this);
		right.setRotationPoint(0.0F, 0.0F, 0.0F);
		wings.addChild(right);
		right.setModelRendererName("right");
		this.registerModelRenderer(right);
		

		right_bottom_middle_wing = new AnimatedModelRenderer(this);
		right_bottom_middle_wing.setRotationPoint(-3.0F, -4.0F, 0.0F);
		right.addChild(right_bottom_middle_wing);
		right_bottom_middle_wing.setTextureOffset(16, 20).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);
		right_bottom_middle_wing.setTextureOffset(13, 17).addBox(-7.0F, 1.0F, -1.0F, 2.0F, 0.0F, 3.0F, 0.0F, false);
		right_bottom_middle_wing.setTextureOffset(0, 2).addBox(-8.0F, 1.0F, -1.0F, 1.0F, 0.0F, 1.0F, 0.0F, false);
		right_bottom_middle_wing.setModelRendererName("right_bottom_middle_wing");
		this.registerModelRenderer(right_bottom_middle_wing);

		right_top_middle_wing = new AnimatedModelRenderer(this);
		right_top_middle_wing.setRotationPoint(-3.0F, -6.0F, 0.0F);
		right.addChild(right_top_middle_wing);
		right_top_middle_wing.setTextureOffset(16, 4).addBox(-5.0F, 0.0F, -1.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);
		right_top_middle_wing.setTextureOffset(13, 0).addBox(-7.0F, 1.0F, -1.0F, 2.0F, 0.0F, 3.0F, 0.0F, false);
		right_top_middle_wing.setTextureOffset(0, 0).addBox(-8.0F, 1.0F, -1.0F, 1.0F, 0.0F, 1.0F, 0.0F, false);
		right_top_middle_wing.setModelRendererName("right_top_middle_wing");
		this.registerModelRenderer(right_top_middle_wing);

		right_front_wing = new AnimatedModelRenderer(this);
		right_front_wing.setRotationPoint(3.0F, -5.0F, 8.0F);
		right.addChild(right_front_wing);
		right_front_wing.setTextureOffset(18, 9).addBox(0.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
		right_front_wing.setTextureOffset(19, 0).addBox(2.0F, 0.0F, -1.0F, 2.0F, 0.0F, 1.0F, 0.0F, false);
		right_front_wing.setModelRendererName("right_front_wing");
		this.registerModelRenderer(right_front_wing);

		tail = new AnimatedModelRenderer(this);
		tail.setRotationPoint(0.0F, -5.0F, 10.0F);
		bone.addChild(tail);
		tail.setTextureOffset(0, 29).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
		tail.setTextureOffset(18, 1).addBox(-1.0F, 0.0F, 3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		tail.setModelRendererName("tail");
		this.registerModelRenderer(tail);

		this.rootBones.add(bone);
	}

	@Override
	public ResourceLocation getAnimationFileLocation() {
		return new ResourceLocation("preternaturalism", "animations/blazing_wave_entity.json");
	}
}