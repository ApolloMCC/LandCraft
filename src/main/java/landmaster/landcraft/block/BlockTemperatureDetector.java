package landmaster.landcraft.block;

import java.util.List;

import javax.annotation.Nullable;

import landmaster.landcore.api.Tools;
import landmaster.landcraft.content.LandCraftContent;
import landmaster.landcraft.tile.TEBreeder;
import landmaster.landcraft.tile.TETemperatureDetector;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTemperatureDetector extends BlockMachineBase {
	static {
		MinecraftForge.EVENT_BUS.register(BlockTemperatureDetector.class);
	}
	
	public BlockTemperatureDetector() {
		super(Material.ROCK);
		this.setHarvestLevel("pickaxe", 0);
		this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.STONE);
        this.setTranslationKey("temperature_detector").setRegistryName("temperature_detector");
        this.setCreativeTab(LandCraftContent.creativeTab);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Nullable
	@Override
	public TETemperatureDetector createTileEntity(World world, IBlockState state) {
		return new TETemperatureDetector();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side,
            float hitX, float hitY, float hitZ) {
		return false; // This does not have a GUI.
	}
	
	@Override
	public boolean canProvidePower(IBlockState state) {
		return true;
	}
	
	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		TileEntity thisTE = blockAccess.getTileEntity(pos);
		if (thisTE instanceof TETemperatureDetector) {
			BlockPos auxCoords = ((TETemperatureDetector)thisTE).getBreederCoords();
			TileEntity auxTE = blockAccess.getTileEntity(auxCoords);
			Vec3i diff = auxCoords.subtract(pos);
			if (Math.abs(diff.getX()) <= 4
					&& Math.abs(diff.getY()) <= 4 
					&& Math.abs(diff.getZ()) <= 4 && auxTE instanceof TEBreeder) {
				TEBreeder breeder = (TEBreeder)auxTE;
				int convertedTemp = (int)breeder.getTemp();
				if (side.getHorizontalIndex() >= 0) {
					return (convertedTemp >> (side.getHorizontalIndex()*4)) & 0xF;
				}
			}
		}
		return 0;
	}
	
	@SubscribeEvent
	public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		if (!world.isRemote
				&& world.getTileEntity(pos) instanceof TEBreeder
				&& Block.getBlockFromItem(event.getItemStack().getItem()) instanceof BlockTemperatureDetector) {
			NBTTagCompound nbt = Tools.getTagSafe(event.getItemStack(), true);
			if (!nbt.hasKey("BlockEntityTag")) {
				nbt.setTag("BlockEntityTag", new NBTTagCompound());
			}
			NBTTagCompound blockEntityTag = nbt.getCompoundTag("BlockEntityTag");
			blockEntityTag.setInteger("breederX", pos.getX());
			blockEntityTag.setInteger("breederY", pos.getY());
			blockEntityTag.setInteger("breederZ", pos.getZ());
			
			event.getEntityPlayer().sendMessage(new TextComponentTranslation("msg.temperature_detector.set", pos.getX(), pos.getY(), pos.getZ()));
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		for (int i=0; i<2; ++i) {
			tooltip.add(I18n.format("tooltip.temperature_detector.info."+i));
		}
		if (stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			if (nbt.hasKey("BlockEntityTag")) {
				NBTTagCompound blockEntityTag = nbt.getCompoundTag("BlockEntityTag");
				tooltip.add(I18n.format("tooltip.temperature_detector.coords",
						blockEntityTag.getInteger("breederX"),
						blockEntityTag.getInteger("breederY"),
						blockEntityTag.getInteger("breederZ")));
			}
		}
	}
}
