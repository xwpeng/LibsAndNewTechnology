# Copyright (C) 2009 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
LOCAL_PATH := $(call my-dir)

#使用静态库
include $(CLEAR_VARS)
LOCAL_MODULE := curl
LOCAL_SRC_FILES := ./curllib/libcurl.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)

LOCAL_CFLAGS := -D_GNU_SOURCE
LOCAL_CPPFLAGS := -frtti

# 打算编译出的动态库的名字
LOCAL_MODULE:= jni_curl

# 使用的本地静态库
LOCAL_SHARED_LIBRARIES := libcurl

LOCAL_SRC_FILES := jni_main.cpp

# 如果不包含这一句的话，会提示：__android_log_print 未定义
LOCAL_LDLIBS := -llog



include $(BUILD_SHARED_LIBRARY)
