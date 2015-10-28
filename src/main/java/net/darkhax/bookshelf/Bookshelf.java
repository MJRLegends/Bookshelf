package net.darkhax.bookshelf;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.darkhax.bookshelf.command.CommandItemColor;
import net.darkhax.bookshelf.handler.EnchantmentListExpansionHandler;
import net.darkhax.bookshelf.handler.ForgeEventHandler;
import net.darkhax.bookshelf.handler.PotionArrayExpansionHandler;
import net.darkhax.bookshelf.lib.Constants;
import net.darkhax.bookshelf.lib.util.MathsUtils;
import net.darkhax.bookshelf.potion.PotionBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.MOD_VERSION)
public class Bookshelf {
    
    @SidedProxy(serverSide = Constants.PROXY_COMMON, clientSide = Constants.PROXY_CLIENT)
    public static net.darkhax.bookshelf.common.ProxyCommon proxy;
    
    @Mod.Instance(Constants.MOD_ID)
    public static Bookshelf instance;
    
    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        
        proxy.preInit();
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
        new EnchantmentListExpansionHandler();
        new PotionArrayExpansionHandler();
        
        ResourceLocation texture = new ResourceLocation("bookshelf:textures/inventory/test.png");
        for (int id = 0; id <= 23; id++) {
            new PotionBase(100 + id, false, MathsUtils.getRandomColor(), texture, id).setPotionName("It Watches");
        }
    }
    
    @EventHandler
    public void init (FMLInitializationEvent event) {
        
        proxy.init();
    }
    
    @EventHandler
    public void onPostInit (FMLPostInitializationEvent event) {
        
        proxy.postInit();
    }
    
    @EventHandler
    public void onServerStarting (FMLServerStartingEvent event) {
        
        event.registerServerCommand(new CommandItemColor());
    }
}