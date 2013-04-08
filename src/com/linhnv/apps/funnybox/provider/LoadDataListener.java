package com.linhnv.apps.funnybox.provider;

public interface LoadDataListener<T> {
	public void onLoadComplete(T t);
	public void onLoadError(String message);
}
