# ExcellentShop DSW Bridge

A bridge plugin that integrates ExcellentShop with DeluxeSellwands (DSW), allowing players to use sellwands that get prices from ExcellentShop's virtual shops.

## Features

- Registers ExcellentShop as a price provider for DeluxeSellwands
- Registers a Vault-based economy provider with the same name "EXCELLENTSHOP"
- Finds the best selling price for each item across all available virtual shops in ExcellentShop
- Allows for seamless integration between the two plugins with a single configuration setting

## Requirements

- ExcellentShop 4.17.3 or later
- DeluxeSellwands 2025.04 or later
- Vault
- Any economy plugin that supports Vault (e.g., EssentialsX, CMI, etc.)
- NightCore library (dependency for ExcellentShop)

## Installation

1. Install ExcellentShop, DeluxeSellwands, and Vault on your server
2. Install any economy plugin that supports Vault
3. Download the latest release of ExcellentShopDSWBridge
4. Place the plugin JAR in your server's plugins folder
5. Start or restart your server

## Configuration

No configuration needed for the plugin itself. You only need to configure your DeluxeSellwands sellwand items to use EXCELLENTSHOP:

1. Open DeluxeSellwands `items.yml`
2. Under each sellwand configuration, set the provider to `EXCELLENTSHOP`:

```yaml
sellwands:
  your_sellwand:
    settings:
      provider: EXCELLENTSHOP  # This will use ExcellentShop for prices and Vault for economy
```

You do NOT need to set `economyProvider` separately, as the plugin registers both price and economy providers under the same name.

## How It Works

This plugin acts as a bridge between DeluxeSellwands and ExcellentShop:

1. When a player uses a sellwand configured with the `EXCELLENTSHOP` provider, the plugin checks ExcellentShop's virtual shops
2. The plugin finds the most profitable sell price for each item from all available virtual shops
3. The money is added to the player's account through Vault

This allows players to use sellwands that honor the same prices set in your ExcellentShop virtual shops, creating a consistent economy experience.

## Technical Details

The plugin consists of three main components:

1. **ExcellentShopDSWBridge** - Main plugin class that registers the providers
2. **ExcellentShopPriceHandler** - Implements DSW's price handler interface to get prices from ExcellentShop
3. **VaultEconProvider** - Implements DSW's economy interface to handle transactions via Vault

## Building from Source

This project uses Maven for building. To build the project:

```bash
mvn clean package
```

The built JAR will be in the `target` directory.

## License

This plugin is released under the Apache-2.0 license. 
