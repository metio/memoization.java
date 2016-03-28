package com.github.sebhoss.utils.memoization.shared;

public final class MemoizationException extends RuntimeException {

	private static final long serialVersionUID = 652523829901288629L;

	public MemoizationException(final Throwable throwable) {
		super(throwable);
	}

}
