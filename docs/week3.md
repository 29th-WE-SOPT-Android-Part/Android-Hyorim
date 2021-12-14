# Week3

> Apply Design / Recycler view / ViewPager2 / BottomNavigation / Tabhost

- [x] Level 1
- [x] Level 2
- [x] Level 3

Level 1 | Level 2 | Level 3
------------ | ------------- | -------------
제플린 디자인 적용 | NestedScrollableHost를 사용하여 <br> 중첩 스크롤 문제 해결 <br> RecyclerView Item에 각각 다른 이미지 넣기 | 갤러리에서 이미지 받아오기
<img src="https://user-images.githubusercontent.com/59546818/145691308-ea926baa-a64b-4dd1-a5a7-33825bb48d76.gif" width="250"> | <img src="https://user-images.githubusercontent.com/59546818/145691314-00c16985-ff1a-49f5-984e-24c3583440f6.gif" width="250"> | <img src="https://user-images.githubusercontent.com/59546818/145691332-f9419bbf-47c2-453c-871e-f6b0c1ea434b.gif" width="250">

<br>


## Level1

과제에 디자인 적용하기 (Zeplin 활용)



### 1. Themes



1. **Font Padding**

   폰드와 디자인을 적용했는데 view가 뚱뚱해 보인다면 Font Padding 점검

   `themes`에 `<item name="android:includeFontPadding">false</item>` 속성을 추가

   

2. **Text Style**

   ​		Zeplin의 **Style Guide**에 **Text style catalog** 를 `themes`에 추가

   ```xml
   <style name="HeaderH1">
       <item name="android:fontFamily">@font/noto_sans_kr</item>
       <item name="android:textStyle">bold</item>
       <item name="android:textSize">14sp</item>
   </style>
   ```

   ​		xml에서 `android:textAppearance="@style/HeaderH1"`  로 호출



3. **Bottom Navigation Style**

   BottomNavigation의 Label에 폰트를 적용하기 위해서는 theme에 style을 추가해야한다.

   ```xml
   <style name="Widget.BottomNavigationView"
       parent="Widget.Design.BottomNavigationView">
       <item name="fontFamily">@font/noto_sans_kr_medium</item>
       <item name="android:textSize">10sp</item>
   </style>
   ```

   

4. **Status Bar Color**

   week1부터 적용했지만 정리할겸 여기다 쓴다!

   Status bar text color의 경우 status bar의 색상에 따라 false, true로 조정할 수 있다

   ```xml
   <!-- Primary brand color. -->
   <item name="statusBarBackground">@color/grey_status</item>
   <!-- Status bar color. -->
   <item name="android:statusBarColor" tools:targetApi="l">?attr/statusBarBackground</item>
   <!-- status bar text color -->
   <item name="android:windowLightStatusBar">false</item>
   <item name="android:includeFontPadding">false</item>
   ```

   

5. **No Action Bar**

   ```xml
   <style name="Theme.SOPT_Assigmnet_1" parent="Theme.MaterialComponents.DayNight.NoActionBar">
   ```


<br/>


### 2. Selector

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item ... />
    <item ... />
</selector>
```

 1. **Bottom Navigation** 

    Bottom Navigation은 SVG 아이콘과 Text 변경 (color, state_checked)

    activity_home.xml

    ```kotlin
    app:itemIconTint="@color/selector_bottom_navi"
    app:itemRippleColor="#FFFFFF"
    app:itemTextColor="@color/selector_bottom_navi"
    ```

    selecor_bottom_navi.xml

    ```xml
    <item android:color="@color/bubblegum" android:state_checked="true" />
    <item android:color="#8f9090" android:state_checked="false" />
    ```

 2. **Button / EditText** 

    둘다 drawable에 shape값을 가진 xml 지정

    Button   - state_selected

    EditText - state_focused 

    ```xml
    <item android:drawable="@drawable/rectangle_fill_gray_btn" android:state_selected="false"/>
    <item android:drawable="@drawable/rectangle_fill_orange" android:state_selected="true"/>
    ```

 3. **Text**

    Button의 클릭 여부에 따라 색을 다르게 지정하려면 코드와 함께 아래 코드를 사용해야 한다.

    ex) `binding.followerBtn.isSelected = true`

    ```xml
    <item android:color="@color/black_text" android:state_selected="false" />
    <item android:color="@color/white" android:state_selected="true" />


<br>


### 3. Glide 활용

dependencies 추가

```kotlin
implementation 'com.github.bumptech.glide:glide:4.12.0'
annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
```

Glide를 적용할 ProfileFragment에 데이터 바인딩을 적용해둠.

따라서 이미지를 처리하는 BindingAdapter 안의 Glide코드에 circleCrop() 메소드 추가

```kotlin
@BindingAdapter("imageSrc")
@JvmStatic
fun loadImage(imageView : ImageView, url : Int){
    Glide.with(imageView.context)
        .load(url)
        .circleCrop()
        .into(imageView)
}
```


<br>

### 4. Bottom Navigation

​	  **ViewPager2**

1. Fragment 생성

2. Layout에 ViewPager2 배치

3. ViewPagerAdapter 구현

4. ViewPager2와 Adapter 연동

   - fragmentList 초기화
   - Adapter 초기화
   - Adapter.fragments.addAll(fragmentList)
   - UI단에 Adapter 연결

   

   **Bottom Navigation**

5. 아이콘 만들기(vector asset)

6. menu.xml 만들기

7. Color Selector 만들기

8. Layout에 BottomNavigationView추가

9. BottomNavigation과 ViewPager2 연동


<br/>


### 5. Tab Layout

1. Fragment 생성

2. Layout에 TabLayout, ViewPager2 배치
3. Adapter 연동(생성자 : fragment)
4. ViewPager2와 TabLayout 연동



<br><br>

## Level2

### Level 2-1 중첩 스크롤 문제

`ViewPager2` does not natively support nested scroll views in cases where the scroll view has the same orientation as the `ViewPager2` object that contains it. 

To support a scroll view inside a `ViewPager2` object with the same orientation, you must call [`requestDisallowInterceptTouchEvent()`](https://developer.android.com/reference/android/view/ViewGroup#requestDisallowInterceptTouchEvent(boolean)) on the `ViewPager2` object when you expect to scroll the nested element instead. The [ViewPager2 nested scrolling sample](https://github.com/android/views-widgets-samples/blob/master/ViewPager2/app/src/main/res/layout/item_nested_recyclerviews.xml#L43) demonstrates one way of solving this problem with a versatile [custom wrapper layout](https://github.com/android/views-widgets-samples/blob/master/ViewPager2/app/src/main/java/androidx/viewpager2/integration/testapp/NestedScrollableHost.kt).	(출처 : android developer)



 [custom wrapper layout](https://github.com/android/views-widgets-samples/blob/master/ViewPager2/app/src/main/java/androidx/viewpager2/integration/testapp/NestedScrollableHost.kt) 의 코드를`NestedScrollableHost.kt` 으로 추가 후

Layout 에서 ViewPager2를 NestedScrollableHost로 감싸기

```xml
<com.hyorim.sopt_assigmnet_1.NestedScrollableHost>
    <androidx.viewpager2.widget.ViewPager2 />
</com.hyorim.sopt_assigmnet_1.NestedScrollableHost>
```

<br>


### Level 2-2 이미지 추가

> 리사이클러뷰에 들어가는 이미지를 url을 활용해 다르게 설정

1.  FollowerData.kt에서 photo 변수의 타입을 String으로 변경 (기존 Int)

2. RecyclerView의 item인 follower_list에 데이터 바인딩을 적용해둠

3. BindingAdapter 내의 함수의 매개변수 타입을 String으로 설정(Url)

   ```kotlin
   object BindingConversions {
   
       @BindingAdapter("imageUrl")
       @JvmStatic                                  
       fun loadImage(imageView : ImageView, url : String){
           Glide.with(imageView.context)
               .load(url)
               .circleCrop()
               .into(imageView)
       }	
   }
   ```

4. 이미지 URL 변수 선언

   ```kotlin
   companion object {
       const val MINION1 = "https://..."
       const val MINION2 = "https://..."
       const val MINION3 = "https://..."
   }
   ```

5. 데이터 추가

   ```kotlin
   private fun initAdapter() {
       followerAdapter = FollowerAdapter()
       binding.rvFollower.adapter = followerAdapter
       followerAdapter.followerList.addAll(
           listOf(
               FollowerData(MINION1, "김효림", "내이름"),
               FollowerData(MINION2, "김효람", "이렇게 불릴 때도 있음"),
               FollowerData(MINION3, "비버", "닮은 동물"),
               FollowerData(MINION1, "루피", "닮은 캐릭터(?)"),
               FollowerData(MINION2, "리사이클러뷰 테스트", "스크롤 확인용 데이터")
           )
       )
       followerAdapter.notifyDataSetChanged()
   }
   ```

   
<br><br>


## Level 3

### Level 3-1

> RecyclerView에 DataBinding 적용
>
> Prerequisite
>
> 	1. Build.grade에 dataBinding 추가
> 	1. Data Class 선언

1. **리스트 아이템을 <layout> 으로 감싸기**

   RecyclerView의 '아이템'에 데이터 바인딩을 적용해야 한다.

   📌꿀팁) 기존에 사용하던 layout코드에 커서를 둔 상태에서 alt + Enter를 누르면

   자동으로 binding layout으로 설정 가능하다

   <img src="https://user-images.githubusercontent.com/59546818/145688254-4b380188-dbd1-46d3-8113-01a295de9578.png" alt="image" style="zoom:33%;" />

2. **xml 파일에서 data 정의**

   <data> 태그는 <layout>에서 사용할 변수를 정의하는데 사용된다.

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <layout xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto"
       xmlns:tools="http://schemas.android.com/tools">
   
       <data>
           <variable
               name="repository"
               type="com.hyorim.sopt_assigmnet_1.RepositoryData" />
       </data>
   
       <androidx.constraintlayout.widget.ConstraintLayout
           android:layout_width="match_parent"
           android:layout_height="wrap_content">
   
           <TextView
            	...
               android:text="@{repository.repoTitle}"  />
           
        ...
   ```



3. **Adapter**

   fragment나 Layout에서 DataBinding을 적용할 경우 ExampleFragment.kt 혹은 ExampleActivity.kt 에서 Binding을 해주는 반면,

   RecyclerView에 적용시에는 Adpater에서 Binding을 해준다.

   데이터바인딩을 적용시킴으로 인해 변경된 코드는 아래 두 항목이다.

   - onBind( ) 의 `binding.repository = data`
   - onCreateViewHolder( )의 `binding : RepositoryListBinding = DataBindingUtil.inflate( ... )`

   ```kotlin
   class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
       val repositoryList = mutableListOf<RepositoryData>()
   
       class RepositoryViewHolder(private val binding: RepositoryListBinding) :
           RecyclerView.ViewHolder(binding.root) {
           fun onBind(data: RepositoryData) { binding.repository = data }
       }
   
       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
           val binding: RepositoryListBinding =
               DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.repository_list, parent, false)
           return RepositoryViewHolder(binding)
       }
       
       override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
           holder.onBind(repositoryList[position])
       }
   
       override fun getItemCount(): Int = repositoryList.size
   }
   ```

   

> Cf ) Activity와 Fragment에서 바인딩 하는 코드

```kotlin
/** Activity */
val binding = DataBindingUtil.setContentView(this, layoutResourceId)

/** Fragment */
val binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
```

<br>
          

### Level 3-2

> 갤러리에서 이미지 받아오기

#### 1. 갤러리 열기

```kotlin
private fun openGallery() {
    val intent = Intent(Intent.ACTION_PICK)
    intent.type = MediaStore.Images.Media.CONTENT_TYPE
    resultLauncher.launch(intent)
}
```

코드 구글링을 했을 때 `startActivityForResult(intent, OPEN_GALLERY)` 로 갤러리를 호출하는 코드가 대다수였는데, depreciated 라고 표시됐다.

대안을 찾아본 결과 `ActivityResultLauncher`를 사용한다고 한다.

 resultLauncher.launch(intent) 메소드로 활용하며, 기존 방식과 다르게 Request Code를 전달하지 않아도 된다. 

resultLauncher는 전역변수로 lateinit을 사용하여 최상단에 배치해 두었다. (메소드에서 자유롭게 사용하기 위해)

좋은 코드 컨벤션인지는 모르겠다. 다빈언니 도와줘!!



#### 2. 갤러리에서 다시 Fragment로 돌아올 때

week1에서 공부했던 StartActivityForResult() 함수가 사용되었다. 

마찬가지로 `result.resultCode == Activity.RESULT_OK` 가 사용되었다.

새로 변경된 ActivityResultLauncher에서는 

```kotlin
private lateinit var resultLauncher: ActivityResultLauncher<Intent>
//...
private fun setImage() {
    resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val imageView = binding.ivFromGallery
                val uri = result.data?.data

                loadImage(imageView, uri)
            }
        }
}
```



#### 3. Glide 사용

```kotlin
private fun loadImage(imageView : ImageView, uri : Uri?){
    Glide.with(imageView.context)
        .load(uri)
        .into(imageView)
}
```



<br/>
          

### 추가로 알게된 내용 : FragmentManager

 **Activity와 Fragment 간의 화면 전환**

지난주 과제에서는 `HomeActivity`에서 Viewpager2를 활용하여 Fragment1과 Fragment2를 전환하였다.

그러나 이번주 과제의 경우 `FragmentProfile` 안에서 Fragment1과 Fragment2를 전환하고자 했다.

Activity의 내용을 Fragment로 옮기는 단순한 작업이라 생각했는데, 예상치 못한 에러가 발생했다. 



**FragmentManager**에는 세 종류가 있다.

- supportFragmentManager (기존)
- parentFragmentManager (변경)
- childFragmentManager



supportFragmentManager 를 parentFragmentManager로 변경하여 에러를 해결하였다.



![img](https://developer.android.com/images/guide/fragments/manager-mappings.png?hl=ko)

(출처 : https://velog.io/@renovatio_hyuns/Activity%EC%99%80-Fragment%EA%B0%84%EC%9D%98-%ED%99%94%EB%A9%B4%EC%A0%84%ED%99%98)

