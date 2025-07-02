package com.magicbili.excellentshopbridge;

import dev.norska.dsw.prices.DSWEconInterface;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * VaultEconProvider
 * 
 * This class implements DeluxeSellwands' economy interface to handle monetary transactions
 * through Vault API. It allows DeluxeSellwands to deposit money to players through 
 * any Vault-compatible economy plugin.
 */
public class VaultEconProvider implements DSWEconInterface {

    private final Economy economy;
    
    /**
     * Constructor - obtains the Vault economy provider
     * Throws RuntimeException if no Vault economy provider is found
     */
    public VaultEconProvider() {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            throw new RuntimeException("Vault economy provider not found");
        }
        economy = rsp.getProvider();
    }

    /**
     * Withdraws money from a player's account
     * 
     * @param player The player to withdraw from
     * @param amount The amount to withdraw
     */
    @Override
    public void take(Player player, Double amount) {
        economy.withdrawPlayer(player, amount);
    }

    /**
     * Deposits money to a player's account
     * 
     * @param player The player to deposit to
     * @param amount The amount to deposit
     */
    @Override
    public void add(Player player, Double amount) {
        economy.depositPlayer(player, amount);
    }

    /**
     * Sets a player's balance to the specified amount
     * 
     * @param player The player whose balance to set
     * @param amount The amount to set the balance to
     */
    @Override
    public void set(Player player, Double amount) {
        double balance = economy.getBalance(player);
        if (amount > balance) {
            economy.depositPlayer(player, amount - balance);
        } else if (amount < balance) {
            economy.withdrawPlayer(player, balance - amount);
        }
    }

    /**
     * Integer version of take method (for compatibility)
     */
    @Override
    public void take(Player player, Integer amount) {
        economy.withdrawPlayer(player, amount.doubleValue());
    }

    /**
     * Integer version of add method (for compatibility)
     */
    @Override
    public void add(Player player, Integer amount) {
        economy.depositPlayer(player, amount.doubleValue());
    }

    /**
     * Integer version of set method (for compatibility)
     */
    @Override
    public void set(Player player, Integer amount) {
        set(player, amount.doubleValue());
    }

    /**
     * Returns the currency name
     * 
     * @return The singular name of the currency
     */
    @Override
    public String currencyString() {
        return economy.currencyNameSingular();
    }

    /**
     * Returns the currency format code
     * 
     * @return 1 = "$100.00", 2 = "100.00$", 3 = "100.00 dollars"
     */
    @Override
    public int currencyFormat() {
        // 1 = "$100.00", 2 = "100.00$", 3 = "100.00 dollars"
        return 1;
    }
} 