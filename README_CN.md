# ExcellentShop DSW Bridge

ExcellentShop与DeluxeSellwands (DSW)的桥接插件，允许玩家使用从ExcellentShop虚拟商店获取价格的售卖魔杖。

## 功能特点

- 将ExcellentShop注册为DeluxeSellwands的价格提供者
- 使用相同名称"EXCELLENTSHOP"注册基于Vault的经济提供者
- 在所有可用的ExcellentShop虚拟商店中查找每个物品的最佳售卖价格
- 通过单一配置设置实现两个插件之间的无缝集成

## 需求

- ExcellentShop 4.17.3或更高版本
- DeluxeSellwands 2025.04或更高版本
- Vault
- 任何支持Vault的经济插件（例如：EssentialsX, CMI等）
- NightCore库（ExcellentShop的依赖）

## 安装

1. 在服务器上安装ExcellentShop、DeluxeSellwands和Vault
2. 安装任何支持Vault的经济插件
3. 下载最新版本的ExcellentShopDSWBridge
4. 将插件JAR文件放入服务器的plugins文件夹中
5. 启动或重启服务器

## 配置

插件本身不需要配置。您只需要配置DeluxeSellwands的售卖魔杖物品使用EXCELLENTSHOP：

1. 打开DeluxeSellwands的`items.yml`
2. 在每个售卖魔杖配置下，将provider设置为`EXCELLENTSHOP`：

```yaml
sellwands:
  your_sellwand:
    settings:
      provider: EXCELLENTSHOP  # 这将使用ExcellentShop作为价格提供者，Vault作为经济提供者
```

您不需要单独设置`economyProvider`，因为该插件以相同的名称注册了价格提供者和经济提供者。

## 工作原理

此插件作为DeluxeSellwands和ExcellentShop之间的桥梁：

1. 当玩家使用配置了`EXCELLENTSHOP`提供者的售卖魔杖时，插件会检查ExcellentShop的虚拟商店
2. 插件会在所有可用的虚拟商店中查找每个物品的最佳售卖价格
3. 通过Vault将金钱添加到玩家的账户中

这允许玩家使用与ExcellentShop虚拟商店中设置的相同价格的售卖魔杖，创造一致的经济体验。

## 技术细节

该插件由三个主要组件组成：

1. **ExcellentShopDSWBridge** - 主插件类，负责注册提供者
2. **ExcellentShopPriceHandler** - 实现DSW的价格处理接口，从ExcellentShop获取价格
3. **VaultEconProvider** - 实现DSW的经济接口，通过Vault处理交易

## 从源代码构建

本项目使用Maven进行构建。要构建项目：

```bash
mvn clean package
```

构建好的JAR文件将位于`target`目录中。

## 许可证

本插件基于Apache-2.0许可证发布。 