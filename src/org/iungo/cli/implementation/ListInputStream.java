package org.iungo.cli.implementation;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

public class ListInputStream<T> extends InputStream {

	/*
	 * Class.
	 */
	
	public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
	
	/*
	 * Instance.
	 */
	
	protected final List<T> list;
	
	protected final Charset charset;
	
	protected Iterator<T> iterator;
	
	protected byte[] buffer = null;
	
	protected int index = -1;
	
	public ListInputStream(final List<T> list) {
		this(list, DEFAULT_CHARSET);
	}

	public ListInputStream(final List<T> list, final Charset charset) {
		super();
		this.list = list;
		this.charset = charset;
		iterator = list.iterator();
		if (iterator.hasNext()) {
			setBuffer(iterator.next().toString());
		}
	}

	protected void setBuffer(final String data) {
		buffer = (data + System.lineSeparator()).getBytes(charset);
		index = -1;
	}
	
	@Override
	public int read() throws IOException {
		int data;
		if (buffer == null) {
			data = -1;
		} else {
			if (++index == buffer.length) {
				if (iterator.hasNext()) {
					setBuffer(iterator.next().toString());
					index = 0;
					data = buffer[0];
				} else {
					buffer = null;
					data = -1;
				}
				index = 0;
			} else {
				data = buffer[index];
			}
		}
		return data;
	}

	@Override
	public void close() throws IOException {
		buffer = null;
		index = -1;
	}
}
