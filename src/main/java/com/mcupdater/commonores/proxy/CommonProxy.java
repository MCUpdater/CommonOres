package com.mcupdater.commonores.proxy;

import com.mcupdater.commonores.util.LanguageBuilder;
import net.minecraft.util.text.translation.LanguageMap;

import java.io.ByteArrayInputStream;

public class CommonProxy {

	protected String localizations;

	public void preInit() {};

	public void loadComplete() {}

	public void registerModels() {}

	public void registerRenders() {}

	public void generateLocalizations() {
		localizations = LanguageBuilder.buildTranslatedNames();
	}

	public void injectLocalizations() {
		LanguageMap.inject(new ByteArrayInputStream(localizations.getBytes()));
	}
}
