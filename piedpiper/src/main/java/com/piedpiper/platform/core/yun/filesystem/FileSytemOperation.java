package com.piedpiper.platform.core.yun.filesystem;

import java.io.InputStream;
import java.io.OutputStream;

public abstract interface FileSytemOperation {
	public abstract String uploadFile(InputStream paramInputStream, String paramString, long paramLong);

	public abstract void deleteFile(String paramString);

	public abstract void downloadFile(OutputStream paramOutputStream, String paramString);
}
