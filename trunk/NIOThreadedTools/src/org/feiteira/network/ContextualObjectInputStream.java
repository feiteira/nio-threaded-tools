package org.feiteira.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class ContextualObjectInputStream extends ObjectInputStream {
	@Override
	public Class resolveClass(ObjectStreamClass desc) throws IOException,
			ClassNotFoundException {
		ClassLoader currentTccl = null;
		try {
			currentTccl = Thread.currentThread().getContextClassLoader();
			return currentTccl.loadClass(desc.getName());
		} catch (Exception e) {
		}

		return super.resolveClass(desc);
	}

	public ContextualObjectInputStream(InputStream in) throws IOException {
		super(in);
	}

}
