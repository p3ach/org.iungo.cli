package org.iungo.cli.builder.api;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.iungo.node.api.NodeContext;

public class Builder {

	public static void main(String[] args) throws ClassNotFoundException {
		final Class<?> c = Class.forName(NodeContext.class.getName());
		final Method[] methods = c.getMethods();
		for (Method method : methods) {
//			if (Modifier.isStatic(method.getModifiers())) {
				System.out.println(method);
				System.out.print(String.format("%s %s (", c.getName(), method.getName()));
				final Class<?>[] pt = method.getParameterTypes();
				for (int i = 0; i < pt.length; i++) {
					System.out.println(pt[i].getName());
				}
//			}
		}
	}

}
