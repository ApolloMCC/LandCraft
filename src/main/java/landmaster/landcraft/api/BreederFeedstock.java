package landmaster.landcraft.api;

import java.util.*;

import com.google.common.collect.*;

import it.unimi.dsi.fastutil.ints.*;
import net.minecraft.item.*;
import net.minecraftforge.oredict.*;

public class BreederFeedstock {
	private static final Int2ObjectMap<MassTempDuo> feedstockMassTempDict = new Int2ObjectOpenHashMap<>();
	
	public static class MassTempDuo {
		public final int mass, temp;
		
		public MassTempDuo(int mass, int temp) {
			this.mass = mass;
			this.temp = temp;
		}
	}
	
	public static class OreMassTempTri {
		public final int oid;
		public final int mass, temp;
		
		public OreMassTempTri(int oid, int mass, int temp) {
			this.oid = oid;
			this.mass = mass;
			this.temp = temp;
		}
	}
	
	public static Collection<OreMassTempTri> getOreMassTempTris() {
		return Collections2.transform(feedstockMassTempDict.int2ObjectEntrySet(),
				entry -> new OreMassTempTri(entry.getIntKey(),entry.getValue().mass,entry.getValue().temp));
	}
	
	public static void addOreDict(String ore, int mass, int temp) {
		addOreDict(ore, mass, temp, false);
	}
	
	public static void addOreDict(String ore, int mass, int temp, boolean force) {
		if (force || OreDictionary.doesOreNameExist(ore)) {
			addOreDict(OreDictionary.getOreID(ore), mass, temp);
		} else {
			LCLog.log.warn("OreDictionary entry "+ore+" does not exist; skipping for BreederFeedstock");
		}
	}
	
	public static void addOreDict(int oreId, int mass, int temp) {
		if (mass <= 0) {
			throw new IllegalArgumentException("Mass must be positive");
		} else if (temp <= 0) {
			throw new IllegalArgumentException("Temperature must be positive");
		}
		feedstockMassTempDict.put(oreId, new MassTempDuo(mass, temp));
	}
	
	public static void removeOreDict(String ore) {
		if (OreDictionary.doesOreNameExist(ore)) {
			removeOreDict(OreDictionary.getOreID(ore));
		}
	}
	
	public static void removeOreDict(int oreId) {
		feedstockMassTempDict.remove(oreId);
	}
	
	public static int getMass(ItemStack is) {
		if (!is.isEmpty()) {
			int[] arr = OreDictionary.getOreIDs(is);
			for (int i=0; i<arr.length; ++i) {
				if (feedstockMassTempDict.containsKey(arr[i])) {
					return feedstockMassTempDict.get(arr[i]).mass;
				}
			}
		}
		return 0;
	}
	
	public static int getTemp(ItemStack is) {
		if (!is.isEmpty()) {
			int[] arr = OreDictionary.getOreIDs(is);
			for (int i=0; i<arr.length; ++i) {
				if (feedstockMassTempDict.containsKey(arr[i])) {
					return feedstockMassTempDict.get(arr[i]).temp;
				}
			}
		}
		return 0;
	}
}
