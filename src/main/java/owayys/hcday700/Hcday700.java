package owayys.hcday700;

import net.fabricmc.api.ModInitializer;

public class Hcday700 implements ModInitializer {
	public static final String MOD_ID = "hcday700";

	@Override
	public void onInitialize() {
		ChestStat.registerStats();

		EventHandler.registerEvents();

		System.out.println("hcday700 mod initialized!");
	}
}