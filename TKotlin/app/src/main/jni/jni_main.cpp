#include "dingran_curltest_test_JniTest.h"
#include "curl/curl.h"
//#include <iostream.h>
#include <stdio.h>
#include <cstring>

#include <android/log.h> // 这个是输出LOG所用到的函数所在的路径
using namespace std;
#define LOG_TAG    "JNILOG" // 这个是自定义的LOG的标识
#undef LOG // 取消默认的LOG

#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__) // 定义LOG类型
#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__) // 定义LOG类型
#define LOGW(...)  __android_log_print(ANDROID_LOG_WARN,LOG_TAG,__VA_ARGS__) // 定义LOG类型
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__) // 定义LOG类型
#define LOGF(...)  __android_log_print(ANDROID_LOG_FATAL,LOG_TAG,__VA_ARGS__) // 定义LOG类型



//static long Post_Response(void *data, int size, int nmemb, char *content)
//{
//	static int position = 0;
//	long sizes = size * nmemb;
//	memcpy(content+position,data,sizes);
//	position += sizes;
//
//	return sizes;
//}

static long Post_Response(void *data, int size, int nmemb, std::string &content)
{
	long sizes = size * nmemb;
	std::string temp((char*)data,sizes);
	content += temp;

	return sizes;
}

/*
 * Class:     dingran_curltest_test_JniTest
 * Method:    curlInit
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_xwpeng_tkotlin_CurlTest_curlTest
  (JNIEnv *env, jobject obj)
{

	LOGE("Java_dingran_curltest_test_JniTest_curlInit in...");
	CURL *curl;
	CURLcode res;
	// 接受返回的内容，用于打印出来看
	std::tring content;
//	char content[1024*20];

	    curl_global_init(CURL_GLOBAL_ALL);

	    curl = curl_easy_init();
	    if (curl) {
	        curl_easy_setopt(curl, CURLOPT_URL, "http://www.baidu.com");
	        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, Post_Response);
	        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &content);
	        res = curl_easy_perform(curl);
	        if (0!=res) {
//	            printf("curl error: %d\n", res);
	            LOGD("curl error: %d\n", res);
	        }
	        LOGD("curl error: %d\n", res);


	        LOGD("curl content = %s \n", content.c_str());

	        curl_easy_cleanup(curl);
	    }

//	    res = curl_easy_setopt(curl, CURLOPT_WRITEDATA, &content);
//		if (res != CURLE_OK)
//		{
//			LOGD("curl_easy_setopt error: %d\n", res);
//		}
//
//		res = curl_easy_perform(curl);
//		if(res != CURLE_OK)
//		{
//			LOGD("curl_easy_perform error: %d\n", res);
//		}
//
//		LOGD("curl content =  %s \n", content);

	curl_global_cleanup();
	LOGE("Java_dingran_curltest_test_JniTest_curlInit exit...");
    return 2012;
}

