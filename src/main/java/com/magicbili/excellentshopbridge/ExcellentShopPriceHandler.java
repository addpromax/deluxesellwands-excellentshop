package com.magicbili.excellentshopbridge;

import dev.norska.dsw.prices.DSWPriceHandlerInterface;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import su.nightexpress.nexshop.api.shop.type.TradeType;
import su.nightexpress.nexshop.ShopAPI;
import su.nightexpress.nexshop.shop.virtual.impl.VirtualShop;
import su.nightexpress.nexshop.shop.virtual.impl.VirtualProduct;
import su.nightexpress.nexshop.shop.virtual.VirtualShopModule;
import su.nightexpress.nexshop.api.shop.product.PreparedProduct;

/**
 * ExcellentShopPriceHandler
 * 
 * This class implements DeluxeSellwands' price handler interface to provide item prices
 * from ExcellentShop's virtual shop module. It finds the best selling price for items
 * across all available virtual shops in ExcellentShop.
 */
public class ExcellentShopPriceHandler implements DSWPriceHandlerInterface {

    /**
     * Gets the worth of an item based on ExcellentShop's pricing
     * 
     * @param player The player using the sellwand
     * @param item The item being sold
     * @param amount The amount of items being sold
     * @return The total value of the items based on ExcellentShop's prices
     */
    @Override
    public Double getItemWorth(Player player, ItemStack item, int amount) {
        // Get ExcellentShop's virtual shop module
        VirtualShopModule module = ShopAPI.getVirtualShop();
        if (module == null) return 0.0;
        
        // Find the most profitable product for selling the given ItemStack
        // This checks all virtual shops and returns the one with the best sell price
        VirtualProduct product = module.getBestProductFor(item, TradeType.SELL, player);
        if (product == null) return 0.0;

        // Calculate total value for the specified amount
        double sellPrice = 0.0;
        try {
            // Get the prepared product to calculate the actual sell price
            // This includes any player-specific discounts or modifiers
            PreparedProduct prepared = product.getPrepared(player, TradeType.SELL, false);
            sellPrice = prepared.getPrice();
        } 
        catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
        
        if (sellPrice <= 0) return 0.0;
        
        // Return the calculated price for the specified amount
        return sellPrice * amount;
    }
} 