/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.strictmodetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Debug;
import android.os.IBinder;
import android.util.Log;

import dalvik.system.BlockGuard;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ServiceBase extends Service {

    private static final String TAG = "StrictService";

    @Override public void onCreate() {
        Log.v(TAG, "onCreate");
    }

    @Override public IBinder onBind(Intent intent) {
        Log.v(TAG, "onBind");
        return mBinder;
    }

    protected final IService.Stub mBinder = new IService.Stub() {
        public int getThreadPolicy() {
            return BlockGuard.getThreadPolicy().getPolicyMask();
        }

        public boolean doDiskWrite(int dummyValue) {
            return true;
        }
    };
}
