# Android-Hyorim
![github_김효림_ver1-17](https://user-images.githubusercontent.com/70698151/135754253-98a770e0-9c09-479c-bdfa-b955c3d4011a.png)



# Week1. Android Assignment
> Summery : Make 'Sign In', 'Sign Up','Self Introduction' pages

- [x] Level 1
- [x] Level 2
- [X] Level 3

로그인 화면 | 회원가입 화면 | 자기소개 페이지
------------ | ------------- | -------------
isNullOrBlank()를 활용한 입력확인 | 1. isBlank()를 활용한 입력확인 <br> 2. registerForActivityResult와<br/> 명시적 Intent로 데이터 교환 | 1.NestedScrollView 활용 <br> 2.암시적 Intent 사용(Github연결)
<img src="https://user-images.githubusercontent.com/59546818/136702618-81e1a173-d785-44d1-9bc9-a16af7a60581.gif" width="250"> | <img src="https://user-images.githubusercontent.com/59546818/136703000-287a3141-5597-4ffe-86d7-379445d16136.gif" width="250"> | <img src="https://user-images.githubusercontent.com/59546818/136702680-71609b28-6552-4d10-9601-745fa6fd6c11.gif" width="250">

<br>

## **Level 1 필수과제 & Level 2 도전과제**
### 1. 로그인 SignInActivity

1) 로그인 버튼 누르기
    1) 입력 완료 :  Intent를 활용 **HomeActivity**로 전환
    2) 입력 미완료 : '로그인 실패' Toast 출력
2) 비밀번호 가리기 : `android:inputType="textPassword"` 속성 활용
3) EditText 미리보기 : `android:hint="아이디를 입력해주세요"` 속성 활용
4) 회원가입 버튼 누르면 Intent를 활용하여 **SignUpActivity**로 이동

- **로그인 버튼 눌렀을 때**
    ```Kotlin
    binding.loginBtn.setOnClickListener {
        if (isInputComplete()) {
            Toast.makeText(this, idEditText.text.toString() + "님 환영합니다", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
        }
    }
    ```

- **입력 조건 충족 확인**
    ```Kotlin
    private fun isInputComplete(): Boolean {
        val id = idEditText.text.toString()
        val pw = pwEditText.text.toString()
        val isIdNull = id.isBlank()
        val isPwNull = pw.isBlank()
        Log.d(tag, "isIdNull =$isNullOrBlank")
        Log.d(tag, "isPwNull =$isNullOrBlank")

        return !isIdNull && !isPwNull
    }
    ```

<br/>

### 2. 회원가입 SignUpActivity

1. 입력 조건 충족 검사는 isBlank() 메소드를 활용하여 로그인과 동일하게 진행 <br/>
2. 조건이 충족 되면 `finish()`를 활용해 **SignInActivity**로 이동
3. 로그인 화면으로 돌아왔을 때 아이디와, 비밀번호가 입력되어 있어야함 **[도전과제]**
- **`Intent.putExtra()` 데이터를 보내는 SignUpActivity**
    ```Kotlin
    binding.signUpBtn.setOnClickListener {
        if (isInputComplete()){
            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("pw", pw)
            setResult(RESULT_OK, intent)
            if (!isFinishing) finish()
        } else {
            Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
        }
    }
    ```

- **`registerForActivityResult` 데이터를 받는 SignIpActivity**
    ```kotlin
    activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val idFromSignUp = it.data?.getStringExtra("id") ?: ""    // default값 ""로 지정
                val pwFromSignUp = it.data?.getStringExtra("pw") ?: ""
                idEditText.setText(idFromSignUp)
                pwEditText.setText(pwFromSignUp)
            }
        }
    ```
    > 송신부 : 앞선 Activity에 데이터를 전송하기 위해 setResult()로 전달 <br>
    > 수신부 : startActivityForResult로 데이터 수신
<br/>

### 3. 자기소개 페이지 HomeActivity
1. ImageView, TextView 활용
2. 암시적 인텐트 활용 -> Githup 페이지로 이동
    ```kotlin
    binding.gitIcon.setOnClickListener{
        Log.i(tag, "Git Icon Clicked")
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://github.com/KxxHyoRim")
        startActivity(intent)
    }
    ```
    - Intent란? 4대 컴포넌트끼리 유기적으로 정보전달을 가능하게 해주는 전달 수단
    
    |명시적 Intent| 실행되어야 할 Component와 Activity가 특정되어 있는 경우 |
    |-------------|-----------------------------------------------------|
    |**암시적 Intent**| **Intent의 액션과 데이터는 지정되어 있으나, 호출할 대상이 달라질 수 있는 경우** <br> ex. 웹 열기 : Naver, Chrome, Safari..|

3. 프로필 사진의 비율(1:1)
    ```xml
     <ImageView
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:scaleType="fitXY"
        app:layout_constraintDimensionRatio="1:1" />
    ```
4. nestedScrollView 사용법 ( NestedScrollView - ViewGroup - Views )
    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent" >

        <androidx.core.widget.NestedScrollView
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <androidx.constraintlayout.widget.ConstraintLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content">

            <!-- View 넣기 ex. ImageView, TextView -->

            </androidx.constraintlayout.widget.ConstraintLayout>

        </androidx.core.widget.NestedScrollView>

    </androidx.constraintlayout.widget.ConstraintLayout>

    ```

    > NestedScrollView는 세로 스크롤만 지원되는 반면, ScrollView는 세로/가로 모두 지원 <br>
    > ScrollView는 RecyclerView와 함께 작동함에 있어서 사용이 불편함.

<br/>



## **Level 3 심화과제**
### **1. DataBinding 개념**

The Data Binding Library is a support library that allows you to **bind UI components in your layouts to data sources** in your app using a **declarative format rather than programmatically.** -Android Developer 

여기서 Declarative는 직역하면 '선언적'이라는 말이 된다. <br/>
eclarative format :  `@{}` notation을 사용하여 Data Sources 부분과 연결  <br/>
Prgrammatically : `(UIcomponent).setText("...")` 와 같이 코드로 일일이 구현  <br/><br/>
장점 : 코드 간결 & nullpointerexception 방지
 
### **2. Build 환경 및 코드**
1. build.gradle (Module)
    ``` kotlin
    dataBinding {
        enabled = true
    }
    ```

<br/>

2. Data Class 선언(생략가능) <br/>

    데이터 클래스로 데이터 모아놓고 관리하기위해 data class 생성
    ```kotlin
    data class Introduce(
        val name: String,
        var age: Int,
        var mbti: String,
        var introducuction: String,
        var photo: Int
    )
    ```

3. xml
    layout으로 전체 감싸기 constraintlayout 안에 있던 xmlns 코드 layout쪽으로 이동)
    ``` xml
    <layout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto">
        <data>
            <variable
                name="viewmodel"
                type="com.myapp.data.ViewModel" />
        </data>
        <ConstraintLayout... /> <!-- UI layout's root element -->
    </layout>
    ```


4. DataAdapter (object로 선언)
    사진 binding을 위한 DataAdapter - Glide 사용
    ``` kotlin
    object BindingConversions {

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(imageView : ImageView, url : Int){
            Glide.with(imageView.context).load(url)
                .into(imageView)
        }
    }
    ```
    
5. Data Initialize & Binding 객체와 Data Source 연결
    ``` kotlin
    binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

    // Initialize
    val introduce = Introduce (
        "김효림",
        23,
        "ENTJ",
        "반갑습니다",
        R.drawable.my_photo
    )

    binding.introduce = introduce
    ```

### **3. Kotlin의 Lambda식**

    Kotlin에는 SAM(Single Abstract Method) Conversation이 제공된다.
    SAM 변환을 활용하면 함수형 인터페이스를 구현하는 클래스를 만들지 않아도 된다.
    SAM은 하나의 추상 메소드에 대해 lambda 식을 제공한다. ex) setOnClickListener()



### **4. 번외로 알게된 것**
1. Kotlin은 String Template을 지원
    - `$변수명`을 통해 변수가 포함된 문자열을 쉽게 출력
    - `Log.e(Tag, "isNameNull :$isNameNull")`
2. 둥근 박스 디자인
    - 별도의 xml파일을 만든 뒤, 해당 스타일을 지정하고자 하는 View의 background로 지정
  
      #### EditText 테두리 모서리 둥글게
      <p>
        <img src="https://user-images.githubusercontent.com/59546818/136698043-7fa9c24d-05bf-42fb-be36-0376e33c0418.png" width="200" height="" > 
      </p>

      ```xml
      <shape
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:shape="rectangle">
        <solid android:color="#FFFFFF" />
        <stroke android:color="#018786" android:width="2dp" />
        <corners android:radius="7dp" />
      </shape>
      ```

      <br>


      #### 버튼 모서리 둥글게
      <img src="https://user-images.githubusercontent.com/59546818/136698047-f67821a4-4edb-4cb7-aa88-9b74d60c13f2.png" width="200" height="">


      ```xml
      <shape xmlns:android="http://schemas.android.com/apk/res/android"
        android:padding="10dp"
        android:shape="rectangle">
        <solid android:color="@color/main" />
        <corners
            android:bottomLeftRadius="12dp"
            android:bottomRightRadius="12dp"
            android:topLeftRadius="12dp"
            android:topRightRadius="12dp" />
      </shape>
       ```



