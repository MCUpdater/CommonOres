package com.mcupdater.commonores;

import java.util.ArrayList;
import java.util.List;

import com.mcupdater.commonores.items.ItemIngot;
import com.mcupdater.commonores.proxy.CommonProxy;
import com.mcupdater.commonores.world.BlockOre;

import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = "commonores", useMetadata = true)
public class CommonOres {

	@SidedProxy(clientSide = "com.mcupdater.commonores.proxy.ClientProxy", serverSide = "com.mcupdater.commonores.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static ModMetadata metadata;
	public static Config config;

	public static BlockOre oreBlock;
	public static List<String> types = new ArrayList<String>();
	public static ItemIngot ingotItem;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		metadata = event.getModMetadata();
		config = new Config(event.getSuggestedConfigurationFile());
		
		// TODO: fork this out into a separate handler
		final String[] oreTypes = { "Copper", "Tin" };

		for( String type : oreTypes ) {
			types.add(type);
		}
		oreBlock = new BlockOre();
		GameRegistry.register(oreBlock);
		GameRegistry.register(oreBlock.getItemBlock());

		ingotItem = new ItemIngot();
		GameRegistry.register(ingotItem);

		proxy.preInit();
		proxy.registerModels();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.registerRenders();
	}

	@EventHandler
	public void loadComplete(FMLLoadCompleteEvent event) {
		FMLLog.info("Generating Localizations");
		proxy.generateLocalizations();
		proxy.injectLocalizations();
		proxy.loadComplete();
	}
}
