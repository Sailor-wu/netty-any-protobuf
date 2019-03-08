package com.wjybxx.example;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkerThreadFactory implements ThreadFactory{

	private final AtomicInteger threadIndex=new AtomicInteger(0);

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r,"workerThread-"+threadIndex.getAndIncrement());
	}
}
