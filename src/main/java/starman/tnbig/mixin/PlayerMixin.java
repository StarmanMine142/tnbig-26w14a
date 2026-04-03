package starman.tnbig.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public class PlayerMixin {

	@Redirect(
			method = "getCommandedBlocks",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/network/chat/Component;literal(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;"
			)
	)
	private MutableComponent replaceWithTranslatable(String text) { // Изменено на MutableComponent
		if ("Selected group contains no blocks in the area".equals(text)) {
			return Component.translatable("message.minecraft.no_blocks_in_group");
		}
		return Component.literal(text);
	}
}