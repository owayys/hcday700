package owayys.hcday700;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(Hcday700.MODID)
public class Hcday700 {
    public static final String MODID = "hcday700";
    public Hcday700() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
}
