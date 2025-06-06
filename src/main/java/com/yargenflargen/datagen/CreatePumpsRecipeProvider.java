package com.yargenflargen.datagen;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.yargenflargen.CreateBetterPumps;
import com.yargenflargen.CreatePumpsBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class CreatePumpsRecipeProvider extends RecipeProvider implements IConditionBuilder {


    public CreatePumpsRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(pOutput, registries);
    }



    @Override
    protected void buildRecipes(RecipeOutput pWriter) {


        /*ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAPPHIRE_BLOCK.get())
                .pattern("sss")
                .pattern("sss")
                .pattern("sss")
                .define('s', ModItems.SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
                .save(pWriter);*/


        /*ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 9)
                .requires(ModBlocks.SAPPHIRE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SAPPHIRE_BLOCK.get()), has(ModBlocks.SAPPHIRE_BLOCK.get()))
                .save(pWriter);*/

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CreatePumpsBlocks.WOOD_PUMP.get())
                .pattern(" w ")
                .pattern("wcw")
                .pattern(" w ")
                .define('w', Ingredient.of(ItemTags.LOGS))
                .define('c', AllBlocks.COGWHEEL)
                .unlockedBy(getHasName(CreatePumpsBlocks.IRON_PUMP.get()), has(CreatePumpsBlocks.IRON_PUMP.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CreatePumpsBlocks.LARGE_COG_WOOD_PUMP_BLOCK.get())
                .pattern(" w ")
                .pattern("wLw")
                .pattern(" w ")
                .define('w', Ingredient.of(ItemTags.LOGS))
                .define('L', AllBlocks.LARGE_COGWHEEL)
                .unlockedBy(getHasName(CreatePumpsBlocks.LARGE_COG_WOOD_PUMP_BLOCK.get()), has(CreatePumpsBlocks.LARGE_COG_WOOD_PUMP_BLOCK.get()))
                .save(pWriter);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CreatePumpsBlocks.IRON_PUMP.get())
                .pattern(" i ")
                .pattern("ipi")
                .pattern(" i ")
                .define('i', Ingredient.of(Items.IRON_INGOT))
                .define('p', AllBlocks.MECHANICAL_PUMP.get())
                .unlockedBy(getHasName(CreatePumpsBlocks.IRON_PUMP.get()), has(CreatePumpsBlocks.IRON_PUMP.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CreatePumpsBlocks.LARGE_COG_IRON_PUMP_BLOCK.get())
                .pattern(" i ")
                .pattern("ipi")
                .pattern(" iL")
                .define('i', Ingredient.of(Items.IRON_INGOT))
                .define('p', AllBlocks.MECHANICAL_PUMP.get())
                .define('L', AllBlocks.LARGE_COGWHEEL.get())
                .unlockedBy(getHasName(CreatePumpsBlocks.LARGE_COG_IRON_PUMP_BLOCK.get()), has(CreatePumpsBlocks.LARGE_COG_IRON_PUMP_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CreatePumpsBlocks.BRASS_PUMP.get())
                .pattern("ddd")
                .pattern("dpd")
                .pattern("ddd")
                .define('d', Ingredient.of(AllItems.BRASS_INGOT))
                .define('p', CreatePumpsBlocks.IRON_PUMP.get())
                .unlockedBy(getHasName(CreatePumpsBlocks.BRASS_PUMP.get()), has(CreatePumpsBlocks.BRASS_PUMP.get()))
                .save(pWriter);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CreatePumpsBlocks.LARGE_COG_BRASS_PUMP_BLOCK.get())
                .pattern("bbb")
                .pattern("bpb")
                .pattern("bbb")
                .define('b', Ingredient.of(AllItems.BRASS_INGOT))
                .define('p', CreatePumpsBlocks.LARGE_COG_IRON_PUMP_BLOCK.get())
                .unlockedBy(getHasName(CreatePumpsBlocks.LARGE_COG_BRASS_PUMP_BLOCK.get()), has(CreatePumpsBlocks.LARGE_COG_BRASS_PUMP_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CreatePumpsBlocks.GOLD_PUMP.get())
                .pattern("g g")
                .pattern("gpg")
                .pattern(" g ")
                .define('g', Ingredient.of(Items.GOLD_INGOT))
                .define('p', AllBlocks.MECHANICAL_PUMP.get())
                .unlockedBy(getHasName(CreatePumpsBlocks.GOLD_PUMP.get()), has(CreatePumpsBlocks.GOLD_PUMP.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CreatePumpsBlocks.LARGE_COG_GOLD_PUMP_BLOCK.get())
                .pattern("g g")
                .pattern("gpg")
                .pattern(" gL")
                .define('g', Ingredient.of(Items.GOLD_INGOT))
                .define('L', AllBlocks.LARGE_COGWHEEL)
                .define('p', AllBlocks.MECHANICAL_PUMP.get())
                .unlockedBy(getHasName(CreatePumpsBlocks.LARGE_COG_GOLD_PUMP_BLOCK.get()), has(CreatePumpsBlocks.LARGE_COG_IRON_PUMP_BLOCK.get()))
                .save(pWriter);



        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CreatePumpsBlocks.DIAMOND_PUMP.get())
                .pattern("ddd")
                .pattern("dpd")
                .pattern("ddd")
                .define('d', Ingredient.of(Items.DIAMOND))
                .define('p', CreatePumpsBlocks.IRON_PUMP.get())
                .unlockedBy(getHasName(CreatePumpsBlocks.DIAMOND_PUMP.get()), has(CreatePumpsBlocks.DIAMOND_PUMP.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CreatePumpsBlocks.LARGE_COG_DIAMOND_PUMP_BLOCK.get())
                .pattern("ddd")
                .pattern("dpd")
                .pattern("ddd")
                .define('d', Ingredient.of(Items.DIAMOND))
                .define('p', CreatePumpsBlocks.LARGE_COG_IRON_PUMP_BLOCK.get())
                .unlockedBy(getHasName(CreatePumpsBlocks.LARGE_COG_DIAMOND_PUMP_BLOCK.get()), has(CreatePumpsBlocks.LARGE_COG_DIAMOND_PUMP_BLOCK.get()))
                .save(pWriter);

    }




}
