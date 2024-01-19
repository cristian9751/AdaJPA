package com.cristian.JPASerpisFP.View.Utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import static com.cristian.JPASerpisFP.View.Utils.Input.*;

public class Util {
	public static <T> void showList(List<T> list, String name) {
		Iterator<T> it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
}
