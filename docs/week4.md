# Week4




| Sign In                                                      | Sign Up                                                      |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![Login](https://user-images.githubusercontent.com/59546818/141488927-0c7b7755-ba62-4d48-a338-313f078478ba.gif) | ![SignUp](https://user-images.githubusercontent.com/59546818/141488992-737c4edd-8613-4694-a5dd-4c7bb12335cc.gif) |



<br/>



## Level1

### 1. Postman Test

https://github.com/w00ing/WE_SOPT_API_MOCKUP

1. **[Post] Sing Up**


![image](https://user-images.githubusercontent.com/59546818/141481805-8922fc03-3450-4df4-812c-718690cec193.png)

2. **[Post] Login**

![image](https://user-images.githubusercontent.com/59546818/141481817-85ed5b42-2b00-4bf3-9dec-ff1950a2e728.png)


3. **[Get] 유저 조회** 

![image](https://user-images.githubusercontent.com/59546818/141481836-99d4ce6b-f8e4-4053-b7da-681c1d33a0df.png)



<br/><br/>




### 2. Retrofit2 실습

1. **라이브러리 추가 및 AndroidManifest 설정**

   ##### Gradle(Module)

   ```kotlin
   // 서버 연결을 위한 Retrofit2
   implementation 'com.squareup.retrofit2:retrofit:2.9.0'
   // Retrofit2에서 gson 사용을 위한 컨버터
   implementation "com.squareup.retrofit2:converter-gson:2.9.0"
   //gson
   implementation "com.google.code.gson:gson:2.8.6"
   ```

   **Manifests**

   ```kotlin
   <uses-permission android:name="android.permission.INTERNET" />
   
   <application
   android:usesCleartextTraffic="true"
   	...
   >
   ```



2. **서버 Request / Response 객체 설계** 

   **RequestLoginData.kt**

   ```kotlin
   data class RequestLoginData(
       @SerializedName("email")
       val email : String,
       val password : String
   )
   ```

   **ResponseLoginData.kt** 

   ```
   data class ResponseLoginData(
       val status: Int,
       val success: Boolean,
       val message: String,
       val data: Data,
   ) {
       data class Data(
           val id: Int,
           val name: String,
           val email: String,
       )
   }
   ```



3. **Retrofit Interface 설계**

   ```kotlin
   interface SampleService {
       @Headers("Content-Type: application/json")
       @POST("user/login")
       fun postLogin(
           @Body requestLoginData: RequestLoginData
       ): Call<ResponseLoginData>
   
       @Headers("Content-Type: application/json")
       @POST("user/signup")
       fun postSignUp(
           @Body requestSignUpData: RequestSignUpData
       ): Call<ResponseSignUpData>
   }
   ```

   

4. **Retrofit Interface 실제 구현체(객체 만들기)**

   ```kotlin
   object ServiceCreator {
       private const val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"
   
       var gson = GsonBuilder().setLenient().create()
   
       private val retrofit : Retrofit = Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create(gson)) //gson converter 연동
           .build()
   
       val sampleService: SampleService = retrofit.create(SampleService :: class.java)
   
   }
   ```



5. **Callback 등록하여 통신 요청**

   ```kotlin
   object ServiceCreator {
       private const val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"
   
       var gson = GsonBuilder().setLenient().create()
   
       private val retrofit : Retrofit = Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create(gson)) //gson converter 연동
           .build()
   
       val sampleService: SampleService = retrofit.create(SampleService :: class.java)
   
   }
   ```

   


✔ 경로 설정 주의하기

SampleService interface `@POST("user/signup")`에서 /user 로 써서 에러남(2시간이나 헤맸...)


```
 E/NetworkTest: error:com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed JSON at line 2 column 1 path $
```

```
E/NetworkTest: error:com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed JSON at line 2 column 1 path $
```



<br/><br/>



## Level 2. 

### Level 2-2 OkHttp 활용

1. Interceptor 추가
2. Interceptor를 클라이언트에 추가
3. 2의 클라이언트를 레트로핏 빌드시 추가
4. 기존에 사용하던 헤더 제거(create 할때 헤더를 고정으로 넣어줌)

```kotlin
interface SampleService {
    @POST("user/login")
    fun postLogin(
        @Body requestLoginData: RequestLoginData
    ): Call<ResponseLoginData>

    @POST("user/signup")
    fun postSignUp(
        @Body requestSignUpData: RequestSignUpData
    ): Call<ResponseSignUpData>
}
```

```kotlin
object ServiceCreator {
    private const val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    var gson = GsonBuilder().setLenient().create()

    // 고정헤더 값에 사용한 Interceptor
    private val interceptor = Interceptor{
        val request = it.request()
            .newBuilder()
            .addHeader("Content-Type","application/json")
            .build()
        return@Interceptor it.proceed(request)
    }

    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson)) //gson converter 연동
        .build()

    val sampleService: SampleService = retrofit.create(SampleService :: class.java)

}
```