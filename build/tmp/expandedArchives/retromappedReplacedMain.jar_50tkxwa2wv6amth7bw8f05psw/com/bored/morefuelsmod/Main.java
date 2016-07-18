package com.bored.morefuelsmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.IFuelHandler;
import com.bored.morefuelsmod.Fuels;
import com.bored.morefuelsmod.proxy.ClientProxy;
import com.bored.morefuelsmod.proxy.CommonProxy;
import com.bored.morefuelsmod.item.ModItems;
import com.bored.morefuelsmod.block.ModBlocks;
import com.bored.morefuelsmod.client.MoreFuelsTab;

@Mod(modid = Main.modid, name = Main.name, version = Main.version, acceptedMinecraftVersions = "[1.9.4]")
public class Main {

	public static final String modid = "morefuelsmod-1.9.4";
	public static final String name = "More Fuels Mod";
	public static final String version = "1.4.0";

	@Mod.Instance(modid)
	public static Main instance;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println(name + " is loading!");
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		boolean enableRFtLrecipe = config.get(Configuration.CATEGORY_GENERAL, "enableRFtLrecipe", true).getBoolean(true);
		if(enableRFtLrecipe)
			RFtL.init();
		config.save();
		ModItems.init();
		ModBlocks.init();
		ModCrafting.init();
		ModSmelting.init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerFuelHandler(new Fuels());
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
	
	@SidedProxy(clientSide = "com.bored.morefuelsmod.proxy.ClientProxy")
	public static ClientProxy proxy;
	
	public static final MoreFuelsTab creativeTab = new MoreFuelsTab();

}