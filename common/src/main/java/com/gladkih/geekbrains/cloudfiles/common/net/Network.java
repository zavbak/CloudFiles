package com.gladkih.geekbrains.cloudfiles.common.net;

import io.reactivex.Completable;

import java.io.Serializable;

public interface Network {
     Completable start();
     Completable send(Serializable o);
     Completable close();
}
