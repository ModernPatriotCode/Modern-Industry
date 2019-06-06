package modepat.modernindustry;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modepat.modernindustry.api.ReferenceModernIndustry;
import modepat.modernindustry.blocks.BlockFlowBattery;
import modepat.modernindustry.blocks.BlockGearBox;
import modepat.modernindustry.blocks.BlockGearBox.EnumGearBox;
import modepat.modernindustry.blocks.BlockMaterialStorage;
import modepat.modernindustry.blocks.BlockMaterialStorage.EnumMaterialStorageBlock;
import modepat.modernindustry.blocks.BlockModernOre;
import modepat.modernindustry.blocks.BlockSpecialGlass;
import modepat.modernindustry.blocks.BlockSpecialGlass.EnumSpecialGlass;
import modepat.modernindustry.blocks.BlockModernOre.EnumModernOre;
import modepat.modernindustry.core.BlockCore;
import modepat.modernindustry.core.ItemBlockCore;
import modepat.modernindustry.core.ItemCore;
import modepat.modernindustry.gui.GUIFlowBattery;
import modepat.modernindustry.items.ItemCuttingTool;
import modepat.modernindustry.items.ItemMonkeyWrench;
import modepat.modernindustry.tileentities.TileEntityFlowBattery;
import modepat.modernindustry.tileentities.TileEntityGearBox;
import modepat.modernindustry.tileentities.ter.RendererGearBox;

@Mod("modernindustry")
public class ModernIndustryMain
{
	private static final Logger LOGGER = LogManager.getLogger();
    
    public ModernIndustryMain() {
       
    	ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> {
    		return (openContainer) -> {
    			ResourceLocation location = openContainer.getId();
    			if(location.toString().equals(ReferenceModernIndustry.MODID + ReferenceModernIndustry.GUI_FLOW_BATTERY)) {
    				EntityPlayerSP player = Minecraft.getInstance().player;
    				BlockPos position = openContainer.getAdditionalData().readBlockPos();
    				TileEntity tileEntity = player.world.getTileEntity(position);
    				if (tileEntity instanceof TileEntityFlowBattery) return new GUIFlowBattery(player.inventory, (TileEntityFlowBattery) tileEntity);
    			}
    			return null;
    		};
    	});
    	
    	// Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doOnClient);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(TileEntityType.class, this::registerTileEntities);
        
       
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("MODERN INDUSTRY SETUP METHOD CALLED");
    }

    private void doOnClient(final FMLClientSetupEvent event) {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGearBox.class, new RendererGearBox());
        // do something that can only be done on the client
        LOGGER.info("MODERN INDUSTRY CLIENT METHOD CALLED");
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        //InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        /*LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));*/
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("MODERN INDUSTRY SERVER METHOD CALLED");
    }
    
    @SubscribeEvent
	public void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
    	
    	ReferenceModernIndustry.TILE_ENTITY_TYPE_FLOW_BATTERY = TileEntityType.Builder.create(TileEntityFlowBattery::new).build(null);
    	ReferenceModernIndustry.TILE_ENTITY_TYPE_FLOW_BATTERY.setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, "flow_battery_tile_entity"));
    	event.getRegistry().register(ReferenceModernIndustry.TILE_ENTITY_TYPE_FLOW_BATTERY);
    	
    	ReferenceModernIndustry.TILE_ENTITY_TYPE_GEAR_BOX = TileEntityType.Builder.create(TileEntityGearBox::new).build(null);
    	ReferenceModernIndustry.TILE_ENTITY_TYPE_GEAR_BOX.setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, "gearbox_tile_entity"));
    	event.getRegistry().register(ReferenceModernIndustry.TILE_ENTITY_TYPE_GEAR_BOX);

    	
		LOGGER.info("MODERN INDUSTRY TILE ENTITIES REGISTERED");
	}

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    
    @Mod.EventBusSubscriber(modid = ReferenceModernIndustry.MODID ,bus=Mod.EventBusSubscriber.Bus.MOD)
    @ObjectHolder(ReferenceModernIndustry.MODID)
    public static class RegistryEvents {
    	
    	 @SubscribeEvent
         public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
    		itemRegistryEvent.getRegistry().registerAll(
         			
         			//Items
         			
         			//Ingots
         			ItemCore.registerItem(ItemCore.aluminum_ingot, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "aluminum_ingot"),
         			ItemCore.registerItem(ItemCore.gallium_ingot, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "gallium_ingot"),
         			ItemCore.registerItem(ItemCore.porous_metal_ingot, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "porous_metal_ingot"),
         			
         			//Crystals
         			ItemCore.registerItem(ItemCore.destabilized_glass_shard, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "destabilized_glass_shard"),
         			ItemCore.registerItem(ItemCore.stabilized_glass_shard, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "stabilized_glass_shard"),
         			ItemCore.registerItem(ItemCore.alumina_crystal, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "alumina_crystal"),
         			ItemCore.registerItem(ItemCore.gallium_crystal, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "gallium_crystal"),
         			ItemCore.registerItem(ItemCore.hematite_crystal, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "hematite_crystal"),
         			
         			//Powders
         			ItemCore.registerItem(ItemCore.thermite_powder, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "thermite_powder"),
         			ItemCore.registerItem(ItemCore.aluminum_oxide_powder, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "aluminum_oxide_powder"),
         			ItemCore.registerItem(ItemCore.iron_oxide_powder, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "iron_oxide_powder"),
         			
         			//Parts
         			ItemCore.registerItem(ItemCore.coil, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "coil"),
         			ItemCore.registerItem(ItemCore.magnet, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "magnet"),
         			ItemCore.registerItem(ItemCore.conducting_bits, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "conducting_bits"),
         			
         			//Tools
         			ItemCore.monkey_wrench = new ItemMonkeyWrench().setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, "monkey_wrench")),
         			ItemCore.cutting_tool = new ItemCuttingTool().setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, "cutting_tool")),
         			ItemCore.registerItem(ItemCore.conducting_focus, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "conducting_focus"),
         			
         			//Various
         			ItemCore.registerItem(ItemCore.shrapnel, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "shrapnel"),
         			ItemCore.registerItem(ItemCore.crystal_seed, ReferenceModernIndustry.MODERNINDUSTRYGROUP, "crystal_seed"),
         			
         			//Tile Entities
        			ItemBlockCore.registerItemBlock(ItemBlockCore.flow_battery_item, "flow_battery", BlockCore.flow_battery, ReferenceModernIndustry.MODERNINDUSTRYGROUP, 1),
        			ItemBlockCore.registerItemBlock(ItemBlockCore.basic_gearbox_item, EnumGearBox.BASIC.getName(), BlockCore.basic_gearbox, ReferenceModernIndustry.MODERNINDUSTRYGROUP),
        			ItemBlockCore.registerItemBlock(ItemBlockCore.advanced_gearbox_item, EnumGearBox.ADVANCED.getName(), BlockCore.advanced_gearbox, ReferenceModernIndustry.MODERNINDUSTRYGROUP),
        			ItemBlockCore.registerItemBlock(ItemBlockCore.refined_gearbox_item, EnumGearBox.REFINED.getName(), BlockCore.refined_gearbox, ReferenceModernIndustry.MODERNINDUSTRYGROUP),
			
        			//Ores
        			ItemBlockCore.registerItemBlock(ItemBlockCore.aluminum_ore_item, EnumModernOre.ALUMINUM.getName(), BlockCore.aluminum_ore, ReferenceModernIndustry.MODERNINDUSTRYGROUP),
        			ItemBlockCore.registerItemBlock(ItemBlockCore.gallium_ore_item, EnumModernOre.GALLIUM.getName(), BlockCore.gallium_ore, ReferenceModernIndustry.MODERNINDUSTRYGROUP),
			
        			//Material Storage
        			ItemBlockCore.registerItemBlock(ItemBlockCore.aluminum_ingot_block_item, EnumMaterialStorageBlock.ALUMINUM.getName(), BlockCore.aluminum_ingot_block, ReferenceModernIndustry.MODERNINDUSTRYGROUP),
        			ItemBlockCore.registerItemBlock(ItemBlockCore.gallium_ingot_block_item, EnumMaterialStorageBlock.GALLIUM.getName(), BlockCore.gallium_ingot_block, ReferenceModernIndustry.MODERNINDUSTRYGROUP),
        			ItemBlockCore.registerItemBlock(ItemBlockCore.porous_metal_ingot_block_item, EnumMaterialStorageBlock.POROUS_METAL.getName(), BlockCore.porous_metal_ingot_block, ReferenceModernIndustry.MODERNINDUSTRYGROUP),
			
        			//Various
        			ItemBlockCore.registerItemBlock(ItemBlockCore.destabilized_glass_block_item, EnumSpecialGlass.UNSTABLE.getName(), BlockCore.destabilized_glass_block, ReferenceModernIndustry.MODERNINDUSTRYGROUP),
        			ItemBlockCore.registerItemBlock(ItemBlockCore.stabilized_glass_block_item, EnumSpecialGlass.STABLE.getName(), BlockCore.stabilized_glass_block, ReferenceModernIndustry.MODERNINDUSTRYGROUP)
         			
         			);
         	
         	LOGGER.info("MODERN INDUSTRY ITEMS REGISTERED");
         }
        
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        	blockRegistryEvent.getRegistry().registerAll(
        			
        			//Tile Entities
        			BlockCore.flow_battery = new BlockFlowBattery().setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, "flow_battery")),
        			BlockCore.basic_gearbox = new BlockGearBox(EnumGearBox.BASIC).setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, EnumGearBox.BASIC.getName())),
        			BlockCore.advanced_gearbox = new BlockGearBox(EnumGearBox.ADVANCED).setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, EnumGearBox.ADVANCED.getName())),
        			BlockCore.refined_gearbox = new BlockGearBox(EnumGearBox.REFINED).setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, EnumGearBox.REFINED.getName())),
        			
        			//Ores
        			BlockCore.aluminum_ore = new BlockModernOre(EnumModernOre.ALUMINUM).setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, EnumModernOre.ALUMINUM.getName())),
        			BlockCore.gallium_ore = new BlockModernOre(EnumModernOre.GALLIUM).setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, EnumModernOre.GALLIUM.getName())),
        			
        			//Frames
        			
        			
        			//Lamps
        			
        			
        			//Material Storage
        			BlockCore.aluminum_ingot_block = new BlockMaterialStorage(EnumMaterialStorageBlock.ALUMINUM).setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, EnumMaterialStorageBlock.ALUMINUM.getName())),
        			BlockCore.gallium_ingot_block = new BlockMaterialStorage(EnumMaterialStorageBlock.GALLIUM).setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, EnumMaterialStorageBlock.GALLIUM.getName())),
        			BlockCore.porous_metal_ingot_block = new BlockMaterialStorage(EnumMaterialStorageBlock.POROUS_METAL).setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, EnumMaterialStorageBlock.POROUS_METAL.getName())),
        			
        			//Various
        			BlockCore.destabilized_glass_block = new BlockSpecialGlass(EnumSpecialGlass.UNSTABLE).setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, EnumSpecialGlass.UNSTABLE.getName())),
        			BlockCore.stabilized_glass_block = new BlockSpecialGlass(EnumSpecialGlass.STABLE).setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, EnumSpecialGlass.STABLE.getName()))
        			
        			
        			);

            LOGGER.info("MODERN INDUSTRY BLOCKS REGISTERED");
        }
        
        
    }
    
}
