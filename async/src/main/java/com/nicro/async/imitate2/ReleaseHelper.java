package com.nicro.async.imitate2;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by rongwenzhao on 2018/3/31.
 * 挂电话帮助类
 */

public enum ReleaseHelper implements Release {

    RELEASED;

    public static boolean isRelease(Release release) {
        return release == RELEASED;
    }

    public static boolean release(AtomicReference<Release> releaseAtomicReference) {
        Release current = releaseAtomicReference.get();
        Release d = RELEASED;
        if (current != d) {
            current = releaseAtomicReference.getAndSet(d);
            if (current != d) {
                if (current != null) {
                    current.release();
                }
            }
        }
        return false;
    }

    @Override
    public boolean isReleased() {
        return true;
    }

    @Override
    public void release() {

    }
}
