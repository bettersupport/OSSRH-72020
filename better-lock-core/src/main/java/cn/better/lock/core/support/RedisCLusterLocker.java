package cn.better.lock.core.support;

import cn.better.lock.core.exception.GlobalLockException;

public class RedisCLusterLocker implements LockInterface{
    @Override
    public void lock(String lockKey, long timeOut) throws GlobalLockException {

    }

    @Override
    public boolean lockWithoutWait(String lockKey, long timeOut) throws GlobalLockException {
        return true;
    }

    @Override
    public void unlock(String lockKey) throws GlobalLockException {

    }

    @Override
    public void unlockMost(String lockKey) throws GlobalLockException {

    }
}
