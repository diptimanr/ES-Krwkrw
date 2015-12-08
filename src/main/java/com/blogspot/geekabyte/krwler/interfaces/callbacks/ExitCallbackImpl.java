package com.blogspot.geekabyte.krwler.interfaces.callbacks;

import java.util.Set;

public class ExitCallbackImpl implements KrwlerExitCallback {

	@Override
	public void callBack(Set<String> crawledUrls) {
		System.out.println("Crawling Complete .....");

	}
}
