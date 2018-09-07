package com.zph.crawler.winio.handle;

import com.sun.jna.NativeLibrary;

public class VirtualKeyBoard {
	public static final WinIo winIo = WinIo.INSTANCE;
	private static int minSleepTime = 50;
	static {
		NativeLibrary.addSearchPath("WinIo32", VirtualKeyBoard.class.getResource("/").getPath());
		NativeLibrary.addSearchPath("WinIo64", VirtualKeyBoard.class.getResource("/").getPath());
		if (!winIo.InitializeWinIo()) {
			throw new IllegalStateException("Cannot Initialize the WinIO");
		}
	}

	public static void KeyDown(int key) throws Exception {
		User32Util.KBCWait4IBE();
		winIo.SetPortVal(WinIo.CONTROL_PORT, 0xd2, 1);
		User32Util.KBCWait4IBE();
		winIo.SetPortVal(WinIo.DATA_PORT, key, 1);
	}

	public static void KeyUp(int key) throws Exception {
		User32Util.KBCWait4IBE();
		winIo.SetPortVal(WinIo.CONTROL_PORT, 0xd2, 1);
		User32Util.KBCWait4IBE();
		winIo.SetPortVal(WinIo.DATA_PORT, (key | 0x80), 1);
	}

	public static void KeyPress(char key) throws Exception {
		KeyPress(VKMapping.toVK("" + key));
	}

	public static void KeyPress(int vk) throws Exception {
		int scan = User32.INSTANCE.MapVirtualKey(vk, 0);
		KeyDown(scan);
		Thread.sleep(50);
		KeyUp(scan);
	}

	public static void KeyPress(String[] key, long firstSleepTime, long sleepTime) throws Exception {

		if (null == key || key.length == 0) {
			throw new IllegalArgumentException("words Is NULL Or Empty");
		}

		if (sleepTime < minSleepTime) {
			throw new IllegalArgumentException("SleepTime Less Than 50 ms");
		}

		if (firstSleepTime > 0) {
			Thread.sleep(firstSleepTime);
		}

		for (String word : key) {
			Integer vk = VirtualKeyMapping.VK_MAP.get(word);
			if (null == vk) {
				vk = VirtualKeyMapping.NEED_SHIFT_VK.get(word);
			}
			if (null == vk) {
				throw new RuntimeException(word + " Not Support");
			}
			boolean needShift = word.length() == 1 && null != VirtualKeyMapping.NEED_SHIFT_VK.get(word);
			if (needShift) {
				KeyDown(VirtualKeyMapping.VK_MAP.get("LShift"));
			}
			KeyDown(vk);
			Thread.sleep(sleepTime);
			KeyUp(vk);
			if (needShift) {
				KeyUp(VirtualKeyMapping.VK_MAP.get("LShift"));
			}
		}
	}

}
