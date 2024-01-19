package com.cristian.JPASerpisFP.View.Utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import static com.cristian.JPASerpisFP.View.Utils.Input.*;

public class Util {
	public static <T> boolean showList(List<T> list, String name) {
		boolean res = true;
		if(list.isEmpty()) {
			System.out.println("No hay " + name);
			res = false;
		} else {
			Iterator<T> it = list.iterator();
			while(it.hasNext()) {
				System.out.println(it.next());
			}
		}
		return res;
	}
	
}
