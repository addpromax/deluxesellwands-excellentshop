package com.magicbili.excellentshopbridge;

import dev.norska.dsw.DeluxeSellwands;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import su.nightexpress.nexshop.ShopAPI;

/**
 * ExcellentShopDSWBridge
 * 
 * Main plugin class that bridges DeluxeSellwands with ExcellentShop.
 * This plugin registers ExcellentShop as a price provider and Vault as an economy provider 
 * for DeluxeSellwands under the same name "EXCELLENTSHOP".
 */
public class ExcellentShopDSWBridge extends JavaPlugin {

    @Override
    public void onEnable() {
        // Check for DeluxeSellwands dependency
        Plugin dswPlugin = Bukkit.getPluginManager().getPlugin("DeluxeSellwands");
        if (dswPlugin == null || !dswPlugin.isEnabled()) {
            getLogger().severe("DeluxeSellwands is not loaded! Disabling plugin...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // Check for ExcellentShop dependency
        Plugin esPlugin = Bukkit.getPluginManager().getPlugin("ExcellentShop");
        if (esPlugin == null || !esPlugin.isEnabled()) {
            getLogger().severe("ExcellentShop is not loaded! Disabling plugin...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // Check for Vault dependency
        Plugin vaultPlugin = Bukkit.getPluginManager().getPlugin("Vault");
        if (vaultPlugin == null || !vaultPlugin.isEnabled()) {
            getLogger().severe("Vault is not loaded! Disabling plugin...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // Check if ExcellentShop's Virtual Shop module is available
        try {
            if (ShopAPI.getVirtualShop() == null) {
                getLogger().severe("ExcellentShop Virtual Shop module is not available! Disabling plugin...");
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }
        }
        catch (Exception e) {
            getLogger().severe("Error accessing ExcellentShop API: " + e.getMessage());
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        
        // Register ExcellentShop as a price provider for DeluxeSellwands
        try {
            // Register the price handler implementation for "EXCELLENTSHOP" identifier
            DeluxeSellwands.getInstance().getPriceHandler().registerNewPriceHandler("EXCELLENTSHOP", new ExcellentShopPriceHandler());
            getLogger().info("ExcellentShop has been registered as a price provider for DeluxeSellwands");
            getLogger().info("Use 'EXCELLENTSHOP' as the provider identifier in sellwand configuration");
            
            // Register the economy provider implementation for the same "EXCELLENTSHOP" identifier
            DeluxeSellwands.getInstance().getEconProvider().registerNewProvider("EXCELLENTSHOP", new VaultEconProvider());
            getLogger().info("Vault has been registered as an economy provider with the name 'EXCELLENTSHOP'");
            getLogger().info("You only need to set 'provider: EXCELLENTSHOP' in sellwand configuration, no need to set economyProvider");
        }
        catch (Exception e) {
            getLogger().severe("Error registering providers: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 